import java.util.*;
import java.util.function.*;
import java.util.stream.*;
/**
***************************【集合】*******************************************
*Collection(Set--TreeSet\HashSet ;  List--ArrayList\LinkedList ; Queue-ArrayDeque\LinedList) --集合类似一个容器：
*1 增、删、改?、查（集合元素均为Object类型--后面可由泛型约束）
*2 集合运算（差、并）
*3 遍历集合
*4 Predicate filter
*5 Stream
*/
class  CollectionTest
{
	//1 增、删、查--///////////////////////////////////////////////////////////////
	public static void basicTest(Collection c)
	{
		//添加元素
		c.add("AE86");
		c.add("8");
		c.add("藤原豆腐店专用");
		c.add("8");
		
		println("初始集合c的大小为："+c.size()+" ; 包含元素有："+c);
/*
		//查---返回布尔
		boolean flag = c.contains("AE86");
		println("是否存在AE86元素？" + flag);

		//remove删---只删除第一个符合条件的~
		c.remove("8");
		println("删除后集合c的大小为："+c.size()+" ; 包含元素有："+c);
		//clear全删
		c.clear();
		println("全删后集合c的大小为："+c.size()+" ; 包含元素有："+c);
*/
	}

	//2 集合运算///////////////////////////////////////////////////////////////
	public static void calTest( )
	{
		//Collection 是集合接口，ArrayList是实现类
		Collection c = new ArrayList();
		basicTest(c);//添加元素--------------------------------
		Collection num = new ArrayList();
		num.add("8");

		//********************【交集运算】*****************************

		c.retainAll(num);
		println("交集运算集合c的大小为："+c.size()+" ; 包含元素有："+c);

		basicTest(c);//添加元素-------------------------------

		//********************【差运算】*****************************

		c.removeAll(num);//(会差掉所有与num相交部分）
		println("差运算集合c的大小为："+c.size()+" ; 包含元素有："+c);

	}
	//3 遍历///////////////////////////////////////////////////////////////
	public static void iterTest( )
	{
		Collection c = new ArrayList();
		basicTest(c); //添加元素--------------------------------
		
		//********************【Lambda+forEach】*****************************
		/*使用Lambda变量
		--利用接口Iterable中forEach(Consumer action)方法：会把每个元素传给Consumer的accept方法
		*/

		print("Lambda表达式 + forEach遍历为：");
		c.forEach(obj->print(obj + " : ") );
		println("");

		//************************【Iterator】***********************************
		//java8--使用Iterator(Iterator only用于遍历Collection中元素）

		Iterator it = c.iterator();
		//hasNext 、next、 remove--it (只能遍历时只能通过迭代器删除上一个Next）、forEachRemaining(Consumer act)
		print("iterator              遍历为：");
		
		int flag = 0;//删除第二个8标记
		while (it.hasNext())
		{		
			//next返回值为String----利用泛型 则不需要强制转换
			String str = (String)it.next();
			System.out.print(str + " * ");
			//注意！！String比较字符序列是否一致应用equals!! 等号只比较引用地址
			if (str.equals("8") )
			{
				flag++;
			}
			if (flag == 2)
			{
				it.remove();
			}	
		}
		println("");
		println("迭代时迭代器删除后--大小为："+c.size()+" ; 包含元素有："+c);
		
		print("for-each 迭代：");
		//************************【forEach】***********************************
		for(Object obj : c)
		{
			print(obj + " :: ");
		}
		println("");
		
	}
	//4 利于Predicate 的test方法，统计符合条件的元素数量/////////////////////////////////
	public static int predicateTest(Collection c,Predicate p)
	{
		//遍历集合 + 调用test方法 
		int num = 0;
		for(Object obj:c)
		{
			if (p.test(obj))
			{
				num++;
			}
		}
		return num;
	}

	//5 Stream（支持并行和串行）操作/////////////////////////////////////
	public static void streamTest(Collection c)
	{
		Stream s = c.stream();
		//s.forEach(System.out::println);

		//采用Stream中：中间方法（返回另一个流）、末端方法，极大简化书写效率
		//***比如返回符合条件的元素数量***
		println("集合中字符长度大于4的元素个数为："
			+c.stream()
				.filter(obj->( ((String)obj).length()>4 ))
				.count() );

		println("集合中包含子串“8”的元素个数为："
			+c.stream()
				.filter(obj->( ((String)obj).contains("8") ))
				.count() );

		//过程：集合对象 转 流对象 转 IntStream ，最后调用IntStream的forEach方法
		c.stream().mapToInt(obj->((String)obj).length()).forEach(CollectionTest::println);
	}

	public static void main(String[] args) 
	{
		//iterTest( );遍历
		Collection c = new ArrayList();
		basicTest(c); 

		//4 用lambda语句表示Predicate filter 中test方法--过滤条件
		//c.removeIf(obj->( ( (String)obj).length()>4) );
		//println(c);
		/*
		println("集合中字符长度小于5的元素个数："+predicateTest(c,obj->( ((String)obj).length()<5 )) );
		*/
		//5 stream
		streamTest(c);
		


	}

	public static void print(Object obj)
	{
		System.out.print(obj);
	}
	public static void println(Object obj)
	{
		System.out.println(obj);
	}
}
