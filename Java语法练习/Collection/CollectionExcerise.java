import java.util.*;
/**
*���Ͽκ���ϰ
*@author Hartley
*@version 1.0.0
*/

class  CollectionExcerise
{
	//��Set���ϱ����û�������ַ���
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

	//��list��ȡ��ɾ��ָ����������Ԫ��
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
	//���ַ����������Map��key�����ִ�������value
	//����˼·���������飬���´���
	public static void test3()
	{
		String[] level= {"a","b","b","a","b","c","d","e"};
		Map freq = new HashMap();
		//������foreach
		for (String key:level)
		{
			//����map��Ĭ�Ϸ�������ѯ�����valueΪnullʱ�����丳ֵΪ1����nullʱ��һ
			freq.merge(key,1,(old,cur)->(Integer)old+1);
			/*
			if (freq.containsKey( key))
			{
				Integer f = (Integer)freq.get(key);
				freq.put(key,f+1); //����Hash'Map�����أ�����ֱ�Ӹ���~
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
