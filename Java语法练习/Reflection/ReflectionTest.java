package Reflection;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
 --【3】通过 类加载器获取读入流-与 文件读入流，对应默认目录的区别
 --【4】创建对应运行时类的对象。newInstance方法调用运行时类的空参构造器（权限应为public）

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

	//************程序入口***************
	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
	}
}

//Person类
class Person{

}