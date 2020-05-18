import java.util.*;
/**
*Collections集合工具类-可操作Set、List、Map
*功能：排序--查询--修改--同步--不可变
*@author Hartley
*@version 1.0.0
*/

class  CollectionsTest
{
	//排序
	public static void sortTest()
	{
		ArrayList id = new ArrayList();
		id.add(5);
		id.add(1);
		id.add(7);
		id.add(3);
		System.out.println(id);
		//用Collections的reverse方法次序反转
		Collections.reverse(id);
		System.out.println(id);
		//sort排序（默认升序）
		Collections.sort(id);
		System.out.println(id);
		//定制排序(Comparator-lambda)
		Collections.sort(id,(o1,o2)->(Integer)o2-(Integer)o1 );
		System.out.println(id);
		//shuffle类似随机洗牌
		Collections.shuffle(id);
		System.out.println(id);

	}
	//二分查询、替换、最值、词频
	public static void searchTest()
	{
		ArrayList id = new ArrayList();
		id.add(5);
		id.add(1);
		id.add(5);
		id.add(-9);
		id.add(3);
		System.out.println(id);
		//二分查询需要先排序
		Collections.sort(id);
		System.out.println(id);
		int index = Collections.binarySearch(id,5);
		System.out.println("5在list集合中的索引为："+index);
		//替换(全部替换）
		Collections.replaceAll(id,5,6);
		System.out.println(id);
		//最值
		System.out.println(Collections.max(id));
		//词频
		System.out.println("6在集合id中出现过："+Collections.frequency(id,6));

	}
	//包装成线程同步
	public static void synchronizedTest()
	{
		Collection c = Collections.synchronizedCollection(new ArrayList() );//注意此处包装方法：同步XXX
		List list = Collections.synchronizedList(new ArrayList() );
		Set set = Collections.synchronizedSet(new HashSet() );
		Map map = Collections.synchronizedMap(new HashMap() );

	}
	//生成只读集合
	public static void unmodifiableTest()
	{
		ArrayList id = new ArrayList();
		id.add(5);
		id.add(1);
		id.add(5);
		id.add(-9);
		id.add(3);
		System.out.println(id);
		//包装成不可修改集合
		List unmodifiableId = Collections.unmodifiableList( id );
		System.out.println(unmodifiableId);
		//unmodifiableId.add(4);//不可修改啦！

		//创建空的不可改变的对象
		Map emptyMap = Collections.emptyMap();
		System.out.println(emptyMap);

		//创建只有一个对象且不可被修改的集合
		List list = Collections.singletonList("独生子我是");
		System.out.println(list);

	}
	//程序入口
	public static void main(String[] args) 
	{
		//sortTest();
		//searchTest();
		unmodifiableTest();
	}
}
