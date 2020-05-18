package Collection;

import java.util.*;
/**
*List接口-集合--有序可重复；添加元素会建立索引
*ArrayList--线程不安全，可由Collection工具类包装
*Vector--线程安全，性能低
*Queue--队列接口--
*---PriorityQueue实现类--【准队列】结构(会将队列元素自动排序
*---Deque子接口【双向队列】-ArrayDeque实现类-既可以作为队列结构 & 栈结构
*以上内部均封装了动态可再分配的obj数组(随机访问效率高,遍历时用get() ），默认长度为10，可由ensureCapacity一次分配够↑
*	--LinkedList实现类，链表结构（插入删除性能高，遍历时用Iterator~），实现了Deque接口	
*@author Hartley
*@version 1.0.0
*/

class  ListTest
{
	//************************【List集合增/删/改/查】************************
	public static void listTest()
	{
		String[] books_name ={
				"神雕侠侣",
				"笑傲江湖",
				"射雕英雄传"
		};
		List books = new ArrayList();

		//增
		for (int i=0;i<books_name.length ;i++ )
		{
			books.add(books_name[i] );
		}
		println(books);

		//删
		books.remove(2);
		println(books);

		//改
		books.set(0,"鹿鼎记");
		println(books);

		//查(查不到返回-1)
		int index = books.indexOf(new String("笑傲江湖") );//会调用对象的equals方法
		println("射雕英雄传的索引位置为："+ index );

		//截取子元素[0,2)
		List partList = books.subList(0,2);
		println(partList);
		
		//List接口默认方法
		//sort：按规则排列集合元素
		books.sort( (o1,o2)->( (String)o2).length()-( (String)o1).length() );
		println(books);
		//replaceAll：按规则替换集合元素
		books.replaceAll(o1->( (String)o1).length() );
		println(books);
		
		//直接遍历
		for (int i=0; i<books.size(); i++)
		{
			println("||"+ books.get(i) );//直接通过索引获取元素
		}

		//船新迭代器ListIterator--可以双向遍历;而且可以增加元素
		ListIterator lit = books.listIterator();
		
		while(lit.hasNext())
		{
			println("**"+lit.next());
			lit.add("new!");//紧接当前next元素后面添加，但下一个next指不到新添加的元素
		}
		while(lit.hasPrevious())
		{
			println("^^"+lit.previous());
		}
		/*
		println("**"+lit.next());
		lit.add("new!");//紧接当前next元素后面添加，但下一个next指不到新添加的元素
		println("**"+lit.previous());//用previous阔以~
*/
		
	}
	//Arrays的asList方法--固定list集合，不可增删改
	public static void fixList()
	{
		List fixList = Arrays.asList(
				"神雕侠侣",
				"笑傲江湖",
				"射雕英雄传");
		println(fixList.getClass() );//该固定元素的集合为Array中内部ArrayList的实例
		fixList.forEach(ListTest::println);
		println(fixList);

	
	}
	//************************【PriorityQueue】************************
	public static void priorityQueueTest()
	{
		//创建pq对象时，可以传入Caparator对象，按照其规则进行排序
		PriorityQueue pq = new PriorityQueue();
		pq.add(3);
		pq.add(-4);
		pq.add(34);
		pq.add(6);
		println(pq);
		println("PriorityQueue中第一个元素："+pq.peek());
	}
	//************************【ArrayDeque】************************
	public static void  arrayDequeTest()
	{
		ArrayDeque ad = new ArrayDeque();
		println("使用ArrayDeque用作栈结构");
		//压栈
		ad.push(4);
		ad.addFirst(1);
		ad.offerFirst(9);
		println(ad);
		//取栈顶元素
		println(ad.peek());
		//println(ad.getFirst());
		//println(ad.peekFirst());
		//弹出
		println(ad.pop());
		println(ad.removeFirst());
		println(ad.pollFirst());
		println(ad);

		println("使用ArrayDeque用作队列结构");
		//进元素
		ad.offer(3);
		ad.offer(9);
		ad.offer(6);
		println(ad);
		//取头部
		println(ad.peek());
		//取出
		println(ad.poll());
		println(ad.poll());
		println(ad.poll());

	}
	//************************【LinkedList】************************
	public static void linkedListTest()
	{
		LinkedList ll = new LinkedList();
		ll.offer("队列1");
		ll.offer("队列2");
		ll.offer("队列3");
		println(ll);
		println("访问队列尾："+ll.getLast());
	}
	public static void println(Object obj)
	{
		System.out.println(obj);
	}



	//程序入口
	public static void main(String[] args) 
	{
		//listTest();
		//fixList();
		//priorityQueueTest();
		//arrayDequeTest();
		linkedListTest();

	}
}
