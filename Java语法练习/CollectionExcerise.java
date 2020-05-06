import java.util.*;
/**
*集合课后练习
*@author Hartley
*@version 1.0.0
*/

class  CollectionExcerise
{
	//用Set集合保存用户输入的字符串
	public static void test1(int num)
	{
		Set set =new LinkedHashSet();
		Scanner input = new Scanner(System.in);
		while(num-- >0)
		{
			if(input.hasNext())
			{
				set.add(input.next());
			}
		}

		System.out.println(set);
		
	}

	//用list获取、删除指定索引处的元素
	public static void test2()
	{
		List list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		System.out.println(list);

		System.out.println(list.get(5));
		System.out.println(list.indexOf("b"));
		System.out.println(list.remove(1));
	}
	//将字符串数组加入Map中key，出现次数放入value
	//暴力思路：遍历数组，更新次数
	public static void test3()
	{
		String[] level= {"a","b","b","a","b","c","d","e"};
		Map freq = new HashMap();
		//可以用foreach
		for (String key:level)
		{
			//利用map的默认方法：查询结果的value为null时，将其赋值为1，非null时加一
			freq.merge(key,1,(old,cur)->(Integer)old+1);
			/*
			if (freq.containsKey( key))
			{
				Integer f = (Integer)freq.get(key);
				freq.put(key,f+1); //根据Hash'Map不可重，可以直接覆盖~
			}else{
				freq.put(key,1);
			}
			*/
		}
		System.out.println(freq);

	}
	public static void main(String[] args) 
	{
		test3();
	}
}
