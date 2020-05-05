import java.util.*;
import java.io.*;
/**
*Map集合接口
*源码上先实现Map，Set在其基础上将value全部置为null
*key-value被封装在集合的Entry内部类中，可以将value看作key的附庸
*--|| HashMap最快（判断key是否相同→通过equals+hashCode，判断value→equals判断）--允许放null到k/v（负载极限3/4）
*-----|| Hashtable(线程安全，但过于古老尽量避免使用--不允许放null到key/value
*--------|| Properties(Hashtable子类，用于处理属性文件)
*--|| LinkedHashMap：以双向链表结构维护元素次序（按插入次序）
*--|| TreeMap(实现了StoreMap接口，有序，用法keySet->toArray->有序的key数组）基于红黑树结构对key排序（分为自然排序-需要对key实现comparable接口；定制排序-无要求）
*--|| WeakHashMap(HashMap的key保留了对象的强引用==该map集合不销毁，对象就不会被回收）弱引用-比如匿名对象
*--|| IdentityHashMap(比较key是否相同，是根据key1==key2）
*--|| EnumMap性能最好（内部用数组存储）key不许null（value可）创建时需要指定枚举类
*@author Hartley
*@version 1.0.0
*/

class  MapTest
{
	public static void mapTest()
	{
		Map mt = new HashMap();
		//添加key-value
		mt.put(1,"哆啦A梦");
		mt.put(2,"蜡笔小新");
		mt.put(3,"五六七");
		println(mt);
		//key不可重，覆盖会返回被覆盖的value
		println(mt.put(3,null));
		//value可以随便重复,不覆盖时返回为null
		println( mt.put(4,"蜡笔小新")  );
		println(mt);

		//判断是否包含对应key/value
		println("是否包含key为2："+mt.containsKey(2));
		println("是否包含value为蜡笔小新："+mt.containsValue("蜡笔小新"));

		//for-each遍历--先取出key的集合
		for(Object key:mt.keySet() )
		{
			println("||"+mt.get(key));
		}

		//根据key删除元素
		mt.remove(1);
		println(mt);

		//新添加的默认方法
		//replace--当key不存在时，不会改变
		mt.replace(4,"飞天小女警");
		println(mt);
		//merge--对应key的value为null时直接换为指定值，否则用key通过mappingFunction计算
		mt.merge(2,"野原新之助",(old,cur)->((String)old).length()+"个"+(String)cur );
		println(mt);
		
		//计算value值
		//如果原来value为null，则用BiFunction接口计算新的value(传入参数为当前key）
		mt.computeIfAbsent(3,key-> " this key is: " + key );
		println(mt);
		//如果原来value不为null，用key-value通过用remappingFunction计算计算新的value
		mt.computeIfPresent(2,(key,value)-> " this key is: " + (Integer)key+" && the value is："+(String)value );
		println(mt);
	}
	//************************【HashMap】*****************************
	public static void hashMapTest()
	{
		HashMap hm = new HashMap();
		hm.put(new A(3),new A(3));
		hm.put(new A(2),new A(3));
		println(hm);

		//包含value？通过equals判断--只要调用的equals返回ture即认为包含value
		println("是否存在A(5)这个值？"+ hm.containsValue(new A(5)));
		//包含key？equals & hashCode同时判断
		println("是否存在A(2)这个键？" + hm.containsKey(new A(2)));

	}

	//************************【LinkedHashMap】*****************************
	public static void linkedHashMapTest()
	{
		LinkedHashMap lhm = new LinkedHashMap();
		lhm.put(1,"xx");
		lhm.put(4,"YY");
		lhm.put(3,"VV");
		//foreach遍历
		lhm.forEach( (key,value)->System.out.println("Key-> "+key+" Value-> "+value));
		
	}

	//************************【Properties】*****************************
	public static void propertiesTest() throws Exception
	{
		Properties prop = new Properties();
		//添加属性
		prop.setProperty("name","Hartley");
		prop.setProperty("age","24");
		//将属性写入到文件（持久化）
		prop.store(new FileOutputStream("config.ini"),"note: author Info");

		//从文件读取属性
		Properties prop2 = new Properties();
		prop2.load(new FileInputStream("config.ini"));
		println(prop2);

	}

	//************************【treeMap】*****************************
	//因为树的key有序，所以会有一些位置相关的方法（第一个、最后一个、前一个。。。）
	public static void treeMapTest()
	{
		TreeMap tm = new TreeMap();
		tm.put(new A(3),1);
		tm.put(new A(-3),2);
		tm.put(new A(8),3);
		println(tm);

		//获取第一个（key最小的）Entry对象（键值对）
		println(  ((A)(tm.firstEntry().getKey())).count);
		//获取第一个key
		println(tm.firstKey());
		//获取Map中比指定key大的一个key
		println(tm.higherKey(new A(0) ) );
		//获取Map中比指定key大的一个Entry
		println(tm.higherEntry(new A(3)));
		//截取key值在两指定key之间的map子集合，默认包含from 不包含to
		//println(tm.subMap(new A(0),true,new A(8),true ));
		println(tm.subMap(new A(3),new A(8) ));
	}

	//************************【WeakHashMap】*****************************
	public static void weakHashMapTest()
	{
		WeakHashMap whm = new WeakHashMap();
		whm.put(new A(1),3);//匿名对象--弱引用
		whm.put(new A(2),3);
		whm.put("String",3);//系统缓存的字符串对象--强引用
		println(whm);

		System.gc();//通知系统回收
		println(whm);//只剩下强引用的key-value

	}
	//************************【IdentityHashMap】*****************************
	public static void identityHashMapTest()
	{
		IdentityHashMap ihm = new IdentityHashMap();
		ihm.put(new String("English"),66);
		ihm.put(new String("English"),76);//因为用key1==key2的方式，所以可以添加
		ihm.put("English",86);
		ihm.put("English",96);//与上一个key==，所以添加时会将后个覆盖前个
		println(ihm);

	}

	//************************【EnumMap】*****************************
	public static void enumMapTest()
	{
		EnumMap em = new EnumMap(Season.class);//此次只是指定枚举集合key的类型
		//添加枚举Map元素
		em.put(Season.SPRING,"温柔的春");
		em.put(Season.SUMMER,"狂野的夏");
		println(em);
	}
	//程序入口
	public static void main(String[] args)  throws Exception
	{
		//mapTest();
		//hashMapTest();
		//linkedHashMapTest();
		//propertiesTest();
		 //treeMapTest();
		 //weakHashMapTest();
		 //identityHashMapTest();
		 enumMapTest();
	}

	public static void println(Object obj)
	{
		System.out.println(obj);
	}
}

//用作key需要实现Comparable接口
class A implements Comparable
{
	public int count;
	public A(int count) 
	{
		this.count = count;
	}
	//实现compareTo方法
	public int compareTo(Object obj)
	{
		return this.count - ( (A)obj).count;
	}
	//重写toString
	public String toString()
	{
		return "[count："+this.count+"]";
	}
	//重写equals
	public boolean equals(Object obj)
	{
		/*
		if(this == obj) 
		{
			return true;
		}
		if(obj !=null && obj.getClass() == A.class)
		{
			return this.count == ( (A)obj).count;
		}
		return false;
		*/
		return true;//equals方法一直返回 同
	}
	//重写hashCode
	public int hashCode()
	{
		//return this.count;//哈希值只与count相关
		return this.count * (int)(Math.random() *100);//哈希值-不同实例对象哈希不同
	}
}

//四季 枚举类
enum Season
{
	SPRING,SUMMER,AUTUMN,WINTER;
}