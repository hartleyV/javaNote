import java.util.*;
/**
*Set--类比于罐子，元素无序且不可重复！！--线程不安全~
*1 HashSet***速度快（根据哈希值索引）--【添加、查询性能good】~
*-LinkedHashSet采用链表，【遍历快】~但因为链表开销=插入删除慢
*2 TreeSet***基于红黑树算法来保持次序--集合元素需要【保持排序】时
*3 EnumSet***Set实现类中性能最好，--但只能保存同一枚举类【枚举值】
*@author Hartley、
*/
class R implements Comparable
{
	int age;
	public R(){}
	public R(int age)
	{
		this.age = age;
	}

	//重写toString
	@Override
	public String toString()
	{
		return "age = " + this.age;
	}

	//重写equals方法
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if(obj != null && obj.getClass() == R.class )
		{
			return this.age == ((R)obj).age;
		}
			return false;
	}

	//重写hashCode方法
	@Override
	public int hashCode()
	{
		return this.age;//注意这样在修改对象成员变量时，可导致不同对象同一哈希值，存在同一桶中，降低性能
	}

	//重写Comparable方法：***自然排序***【规则】：结果与equals一致，
	@Override
	public int compareTo(Object obj)
	{
		R r = (R)obj;
		return this.age > r.age ? 1 : this.age<r.age ? -1 : 0;
	}
}
class SetTest 
{
	SetTest(){}
	
	
	//****************************************【HashSet】************************************
	//元素排列顺序根据对象hashCode计算的哈希值；判断重复根据equals和hashCode方法；集合值可null
	public static void hashSetTest()
	{
		HashSet hs = new HashSet();
		hs.add( new R(5) );
		hs.add( new R(-3) );//在hashSet中排列后为 -3 5 6
		hs.add( new R(6) );
		Iterator it = hs.iterator();
		System.out.println(hs);
		R s1 = (R)it.next();
		s1.age = 5;//将第一个对象哈希值改为5 但其实际存储位置在-3
		System.out.println(hs);
		hs.remove(new R(5) );//
		System.out.println(hs);
		System.out.println("是否存在5元素："+hs.contains(new R(5)) );
		System.out.println("是否存在-3元素："+hs.contains(new R(-3)) );
		//所以这样一来第一个元素无法正确得以操作
		
	}
	//************************************【LinkedHashSet】************************************
	//元素排列顺序即为插入顺序（通过链表）；
	public static void linkedHashSetTest()
	{
		LinkedHashSet lhs = new LinkedHashSet();
		lhs.add(5);
		lhs.add(-3);
		System.out.println(lhs);
	}




	//*****************************************【TreeSet】**************************************
	//元素按大小排序~，（方法多了直接访问第一个、最后一个、前一个、后一个的方法，以及截取子treeSet的方法
	//采用 红黑数 数据结构存储集合元素
	//所添加对象必须实现comparable接口
	public static void treeSetTest()
	{
		TreeSet ts = new TreeSet();
		/*
		ts.add(5);
		ts.add(6);//开始调用comparaTo方法(自然排序）
		ts.add(0);
		ts.add(-3);
		System.out.println(ts);//自动按元素大小排好顺序
		System.out.println("TreeSet第一个元素："+ts.first());
		System.out.println("TreeSet最后一个元素："+ts.last());
		System.out.println("TreeSet大于5的元素："+ts.higher(5) );
		System.out.println("TreeSet小于0的元素："+ts.lower(0) );
		System.out.println("截取小于5元素的子treeSet："+ts.headSet(5));
		System.out.println("截取大于等于0元素的子treeSet："+ts.tailSet(0));
		System.out.println("截取大于等于0、小于6元素的子treeSet"+ts.subSet(0,6));
		ts.clear();
		System.out.println(ts);
		*/

		//演示改变关键的成员变量值，删除对应元素失败~//////////////////////////////////////////////
		ts.add(new R(5) );
		ts.add(new R(6) );
		ts.add(new R(7) );		
		System.out.println(ts);//调用toString
		
		R first = (R)ts.first();//这个简化了取出第一个元素~~
		/*
		Iterator it = ts.iterator();//传统迭代器~~
		R first = (R) (it.next() );
		*/		
		first.age = 100;//修改可变元素的关键成员变量age

		System.out.println(ts);//修改关键变量后（直接影响hashCode）	
		System.out.println("删掉修改后变量的元素（5->100）5："+ts.remove(new R(5) ));
		System.out.println("删掉修改后变量的元素（5->100）100："+ts.remove(new R(100) ));
		System.out.print("删掉元素6："+ts.remove(new R(6) ));
		System.out.println(ts);//试图删除6

		//定制比较器排序：lamabda表达式//////////////////////////////////////////////////////////
		TreeSet ts1 = new TreeSet( (o1,o2)->
			{
				R r1 = (R)o1;
				R r2 = (R)o2;
				//return r1.age>r2.age ? -1: r1.age<r2.age ? 1:0;//当前比待比较的大返回-1—降序；
				return r2.age - r1.age;
			});
		ts1.add(new R(-5) ); 
		ts1.add(new R(0) ); 
		ts1.add(new R(6) ); 
		System.out.println(ts1);


	}

	enum Season
	{
		SPRING,SUMMER,AUTUMN,WINTER;
	}
	//存放枚举类的集合==以枚举值在内部按照位向量形成存储（紧凑高效）
	//不许存null==只能用类方法创建EnumSet对象
	public static void enumSetTest()
	{
		//类方法allOf获得的枚举集合元素--即为Season枚举类的全部枚举值
		EnumSet es = EnumSet.allOf(Season.class);
		System.out.println(es);

		//类方法noneOf创建的是Season枚举类的*空集合*
		EnumSet es2 = EnumSet.noneOf(Season.class);

		//向该空集合添加元素
		es.add(Season.SPRING);
		System.out.println(es2);

		//用枚举值创建枚举集合
		EnumSet es3 = EnumSet.of(Season.SUMMER,Season.WINTER);
		System.out.println(es3);

		//集合运算-差运算（总的枚举值 与 es3的枚举值 做差~）
		EnumSet es4 = EnumSet.complementOf(es3);
		System.out.println(es4);
		
		//从同一枚举类元素的Collection中复制
		Collection c = new HashSet();
		c.add(Season.SPRING);
		c.add(Season.WINTER);
		EnumSet es5 = EnumSet.copyOf(c);
		System.out.println(es5);
	}


	public static void main(String[] args) 
	{	
		//treeSetTest();
		enumSetTest();


		
	}
}
