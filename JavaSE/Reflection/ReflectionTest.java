package Reflection;

import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

/**
 *执行步骤：java文件-javac-class字节码文件-类装载器-字节码校验器-解释器-操作系统上执行
*【反射】
 --特征：动态性（在编译前不明确创建什么类对象，在运行时通过反射创建类对象）
 --【1】创建Class对象实例/运行时类(加载到内存的class文件)的方式（四种）
	【forName】(常用)、类名.class、对象.getClass()、ClassLoader(通过当前类的getClassLoader方法获取)
 --运行时类有：类、接口、枚举类、基本数据类型、void、注解、数组（类型、维度一致即为同一运行时类）
 --【2】类加载器：
 		功能：把字节码文件的静态数据转换为方法区的运行时数据结构，并在堆中生成java.lang.Class对象
 			作为方法区数据结构的访问入口
 		类型有：引导（负责java核心类库）<-扩展（负责lib下的jar包）<-系统类加载器（classPath下的）
 --用途：判断对象的所属类、类中的成员变量和方法、获取泛型信息、创建类的对象
 	  动态代理，运行时处理注解
 --【3】通过（类加载器获取读入流）-与（文件读入流），对应默认目录的区别
 --【4】创建对应运行时类的对象。newInstance方法调用运行时类的空参构造器（权限应为public）
 --【5】获取运行时类中的属性和方法结构getDeclaredFields(常用)、getFields（需public，不常用)
 --获取运行时类的构造器
      方法：getConstructure()//获取声明为public的构造器
  			getDeclaredConstructure()//声明的所有构造器
 --【6】获取父类：getSuperclass()--返回Class类型
 				getGenericSuperclass()--泛型类，返回Type类型
 				getInterfaces()--接口，返回Class数组
 --【7】操作指定的数据结构（修改设置属性、调用方法）
 *@author Hartley
*@version 1.0.0
*/

class  ReflectionTest
{
	//【1】创建Class实例的四种方式
	@Test
	public void newClassTest() throws ClassNotFoundException {
		//通过Class的静态方法forName(包名.类名)
		Class c1 = Class.forName("Reflection.Person");
		//通过运行时类的class属性
		Class c2 = Person.class;
		//通过运行时类的对象调用getClass方法
		Person p = new Person();
		Class c3 = p.getClass();
		//通过所在类获取的ClassLoader加载
		ClassLoader loader = ReflectionTest.class.getClassLoader();
		Class c4 = loader.loadClass("Reflection.Person");

		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		//返回true 对应同一个class实例，说明加载到内存的运行时类会缓存一定时间，并可用不同方式获取
		System.out.println(c1==c2 && c1==c3 && c1==c4);

	}

	//【2】类加载器
	@Test
	public void classLoaderTest(){
		//系统类加载器 AppClassLoader
		ClassLoader l = Person.class.getClassLoader();
		System.out.println(l);
		//l的父加载器---扩展类加载器
		ClassLoader l1 = l.getParent();
		System.out.println(l1);
		//l1的父加载器--引导类加载器（负责核心类库的加载，无法获取--返回null）
		ClassLoader l2 = l1.getParent();
		System.out.println(l2);
	}

	//【3】通过类加载器获取读入流--与直接建文件读入流，默认目录的区别
	@Test
	public void propTest(){
		Properties pros = new Properties();
		ClassLoader loader = Reflection.ReflectionTest.class.getClassLoader();

		String file1 ="Java语法练习/Reflection/config.properties";
		String file2 = "Reflection/config.properties";
		try (//fis默认路径为总项目下
			 FileInputStream fis = new FileInputStream(file1);
			 //ClassLoader的读入流在创建loader的类所在包同级目录下
			 InputStream is = loader.getResourceAsStream(file2)){

			//pros.load(fis);
			pros.load(is);

			String name = pros.getProperty("name");
			String age = pros.getProperty("age");
			System.out.println(name+"："+age);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	//【4】创建运行时类的对象,体会反射的动态性
	@Test
	public void instanceTest() throws IllegalAccessException, InstantiationException {
		/*
		Class<Person> clazz = Person.class;
		Person p = clazz.newInstance();
		System.out.println(p);
		 */
		String classPath = "";

		for(int i=0;i<20;i++){
			int num = new Random().nextInt(3);//返回0-2
			switch (num){
				case 0:
					classPath = "java.util.Date";
					break;
				case 1:
					classPath = "java.lang.Object";
					break;
				default:
					classPath = "Reflection.Person";
					break;
			}
			try {
				Object obj = getInstance(classPath);
				System.out.println(obj);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	}
	//根据类的完整名称创建对应对象
	public Object getInstance(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		Class clazz = Class.forName(classPath);
		return clazz.newInstance();
	}

	//【5】获取运行时类中的属性和方法结构
	@Test
	public void getFieldMethod(){
		Class clazz = Person.class;
		//================成员变量===================
		//Field[] fields = clazz.getFields();//获取运行时类及其父类的public属性
		Field[] fields = clazz.getDeclaredFields();//获取运行时类的所有属性（不含父类的）

		for (Field field:fields){
			//获取权限修饰符
			int modifier = field.getModifiers();//不同数值对应不同权限
			System.out.print(modifier+"\t");
			//获取数据类型
			Class type = field.getType();//返回值时Class类型
			System.out.print(type.getName()+"::\t");//名称通过Class的getName方法获得
			//变量名
			String name = field.getName();
			System.out.println(name);
			System.out.println(field);
		}
		System.out.println("");
		//================成员方法===================
		//Method[] methods = clazz.getMethods();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method m:methods){
			//获取注解
			Annotation[] anno = m.getAnnotations();//有问题，待处理
			for (Annotation a:anno){
				System.out.print(a+"\t");
			}
			//权限修饰符
			System.out.print(m.getModifiers()+"\t");
			//返回值类型
			System.out.print(m.getReturnType().getName()+"\t");
			//方法名
			System.out.print(m.getName()+"::\t");
			//形参列表
			Class[] parameterTypes = m.getParameterTypes();
			System.out.print(Arrays.toString(parameterTypes)+"\t");
			//抛出的异常
			Class[] exceptions = m.getExceptionTypes();
			System.out.println(Arrays.toString(exceptions));


			System.out.println(m);
		}
	}
	//【6】类+接口+包
	@Test
	public void getClassTest(){
		Class clazz = Person.class;
		//获取运行时类的父类
		//Class cFather = clazz.getSuperclass();
		//获取带泛型的父类
		Type cFather = clazz.getGenericSuperclass();
		//获取其泛型参数
		ParameterizedType paramType = (ParameterizedType)cFather;
		Type[] actualTypeAuguments = paramType.getActualTypeArguments();
		System.out.println(((Class)actualTypeAuguments[0]).getName());//Class实现了Type接口

		System.out.println("");
		//===============================================
		//获取其接口--可以多实现，返回为class数组
		Class[] classes = clazz.getInterfaces();
		for (Class c:classes){
			System.out.println(c);
		}
		System.out.println("");
		//获取其父类接口
		Class[] father_classes = clazz.getSuperclass().getInterfaces();
		for (Class c:father_classes){
			System.out.println(c);
		}
		//===============================================
		//获取包名
		System.out.println(clazz.getPackage());
	}
	//【7】操作数据结构
	@Test
	public void manipulateTest() throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		Class<Person> clazz = Person.class;//获取运行时类实例
		//=======================操控属性=======================
		//Field sex = clazz.getField("sex");//不是public就获取不到。。不常用
		Field sex = clazz.getDeclaredField("sex");//获取所有声明的成员变量
		sex.setAccessible(true);//修改访问权限为可访问
		//设置属性前，需要指定一个对象实例
		Person p = (Person)clazz.newInstance();
		//开始修改/赋值属性
		sex.set(p,"male");
		System.out.println(sex.get(p));//获取p对象的sex属性
		// System.out.println(p.getSex());

		//=======================操控方法=======================
		//获取指定的方法（方法名，形参类型-没有参数则不写）
		Method t1 = clazz.getDeclaredMethod("t1", String.class);
		t1.setAccessible(true);//
		//执行方法（调用对象，实参）--返回值即为调用方法的返回值
		String name = (String)t1.invoke(p,"haha");
		System.out.println(name);

		//=====================操控静态方法=====================
		Method info = clazz.getDeclaredMethod("info");
		info.setAccessible(true);
		//用类调用静态方法，void返回值为null
		Object returnVal = info.invoke(Person.class);//也可以写成null
		System.out.println(returnVal);

		//=====================操控指定构造器=====================
		//不常用带参数的构造器，，为了通用性
		Constructor<Person> con = clazz.getDeclaredConstructor(String.class);
		con.setAccessible(true);
		Person p2 = con.newInstance("Jerry2");
		System.out.println(p2);

	}

}


//Person类,父类是带泛型的ArrayLit
class Person extends ArrayList<String> implements Comparable {
	private String name;
	public int age;
	private String sex;

	public Person(){}
	private Person(String name){
		this.name = name;
	}

	@SuppressWarnings(value="unchecked")
	public String t1(String name){
		return name;
	}
	public String getSex(){
		return this.sex;
	}
	private void t2(int num)throws IOException{}

	public static void info(){
		System.out.println("@copyright 2020");
	}
	@Override
	public String toString(){
		return name+" is "+age;
	}
	@Override
	public int compareTo(Object o) {
		return 0;
	}
}