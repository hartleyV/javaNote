import java.util.*;
/**
*泛型：为了让集合记住其数据类型~（传入类型实参--相当于多个逻辑子类） 
*用途：限制丢进的元素类型（编译时即可发现）&取出不需强制转换--代码更简洁健壮
*用法：
*-|| 定义在【类、接口】
*-----|| 【类名<T>】 （类名仍然不变，只是调用构造器 -new 类名<>() 多了个“菱形”）
*-----|| 子类继承泛型类时，要给出该泛型类的类型实参 【class x entends y<String>】;且重写使用泛型的方法时也有写明
*-----|| 没有泛型类，所以不可以在静态成员（类变量）处使用泛型
*-----|| List<Object> 与List<Integer>同属一个class，且两者没有继承关系！不可相互赋值（以免在子类与父类差异处操作产生错误）Number num = new Integer(); num=0.1;//error
*-----|| 类型通配符（灵活子类化）List<?>-任意泛型List父类！！【注意？为类型实参】（同String、Integer一样）
*--------|| 可以传入任何类型实参，相当对其向上转型为Object，注意无法确定集合类型，不能添加元素（Java不许将对象放入一个位置类型的集合中）
*-----------|| （add方法使用了泛型E作为集合元素类型，需要明确后才可添加，除了null）
*-----------|| （get()返回的是Object类型，遂可以
*--------|| 上限通配符【List<? entends String & Comparable>】（之多一个父类上线，多个接口上限，顺序）:限制传入的类型实参
*--------|| 下限通配符【List<? super T> list】表示类型参数为T或T的父类
*-|| 定义在【方法】
*-----|| 泛型方法（不同调用点，传入不同类型实参）：【修饰符<T,E> 返回值类型 method(List<T> list)】--/--<T,E extends Shape>
*--------|| 什么时候使用泛型方法OR通配符->类型形参只在传入时使用（其他参数、返回值不依赖它）:通配符
*-----|| 泛型构造器 【修饰符<T> 类名(T xx)】,可以灵活传入不同类型的类型实参来构造对象
*--------|| 创建构造器：隐式- new Shape(5); 显示- new <Integer> Shape(5);
*-|| 类型推断，不引起歧义下可以自动判断类型
*-|| 擦除和转换：
*-----||擦除List<String> list 赋值给List2 l，会擦除掉类型实参；
*-----||转换：List list2赋值给List<String> list叫转换（注意list2在赋值前，添加的元素应为String，否则用list操作元素的时候会因类型不匹配报错）
*-|| 泛型数组：（不支持）
*-----|| 泛型设计原则：编译时没有unchecked警告，运行时就不会出现ClassCastException异常
*-----|| 不允许数组元素包含类型变量/形参（通配符可以）-所以不能new List<String>[10]，能声明List<String>[] lists = new List[10];//会有unchecked警告
*@author Hartley
*@version 1.0.0
*/

class  GenericTest
{
	//基本用法
	public static void test1()
	{
		List<Integer> list = new ArrayList<>();//右端省略写法~只写一个“菱形”<>
		list.add(3);
		list.add(7);
		list.add(4);
		System.out.println(list);
		list.forEach(tmp->System.out.println(tmp+1));//使用泛型后，再也不用强制转换啦
		
		//泛型**可以套娃**~
		Map<String,List<String>> courseInfo = new HashMap<>();
		List<String> jackCourse = new ArrayList<>();
		jackCourse.add("Chinese");
		jackCourse.add("Math");
		jackCourse.add("SemiConductor");
		courseInfo.put("jack",jackCourse);

		System.out.println(courseInfo );
	}
	//泛型方法
	public static <T>  T genericMethod(T[] arr,List<T> list)
	{
		for (T ele:arr )
		{
			list.add(ele);
		}
		return arr[arr.length-1];//返回最后一个元素
	}
	//复制集合元素,取出最后一个元素
	public static <T> T copyOf(Collection<? super T> dst,Collection<? extends T> src)
	{
		T last = null;//在方法中使用变量前需要初始化
		//通配符上限~
		for (T tmp:src )
		{
			last = tmp;
			dst.add(tmp);
		}
		return last;
	}

	//treeSet用的comparator接口即为通配符下限约束，可以调用基本方法，增加灵活性~
	public void treeSetTest()
	{
		//创建定制排序的treeSet
		Set<String> id = new TreeSet<>(
			new Comparator<String>()//对应定义为comparator<? extends T>,既可比较String，也可Object？
		{
			public int compare(String first,String second)
			{
				return first.length() - second.length();
			}
		});
	}
	//自动推断
	public static void assertTest()
	{
		Test<String> t1 = new Test<>(5);//右端均隐藏
		Test<String> t2 = new <Integer>Test<String>(6);//右端类型均显示
		Test<String> t3 = new Test<String>(7);//构造器隐式
		//Test<String> t4 = new <Integer>Test<>(8);//构造器显示+菱形-->不可推断出Test的类型参数
	}

	//泛型数组
	public static void genericArrayTest()
	{
		//List<String>[] lists = new List<String>[10];//错误的写法,不会通过编译：违背泛型数组设计原则
		List<String>[] lists = new List[10];//unchecked warnning
		Object[] obj = lists;//？？？【1】
		List<Integer> ele = new ArrayList<Integer>();//元素类型为Integer
		ele.add(4);
		obj[1] = ele;//？？？
		String str = lists[1].get(0);//运行时会产生ClassCastException异常
	}

	//程序入口
	public static void main(String[] args) 
	{
		List<String> list = new ArrayList<>();
		String[] arr = {"jack","heyjudy","doggy","github"};

		System.out.println(genericMethod(arr,list) );
		System.out.println(list);

		List<Integer> src = new ArrayList<>();
		src.add(23);
		src.add(99);
		src.add(48);
		List<Number> copy = new ArrayList<>();//Integer的父类
		Integer last = copyOf(copy,src);
		System.out.println("the last element: "+last+" the copy list is："+copy);

		
		List list2 = new ArrayList();
		list2.add(6);//添加的元素类型为Integer
		//System.out.println( (String) list2.get(0));//此处涉及把Integer类型强转为String，会报错！
		genericArrayTest();
	}
}

//泛型类
class Test <E>
{
	//泛型构造器
	public <T> Test(T t)
	{
		System.out.println(t);
	}
}
