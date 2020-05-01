import java.util.*;
import java.util.function.*;
import java.util.stream.*;
/**
***************************�����ϡ�*******************************************
*Collection(Set--TreeSet\HashSet ;  List--ArrayList\LinkedList ; Queue-ArrayDeque\LinedList) --��������һ��������
*1 ����ɾ����?���飨����Ԫ�ؾ�ΪObject����--������ɷ���Լ����
*2 �������㣨�����
*3 ��������
*4 Predicate filter
*5 Stream
*/
class  CollectionTest
{
	//1 ����ɾ����--///////////////////////////////////////////////////////////////
	public static void basicTest(Collection c)
	{
		//���Ԫ��
		c.add("AE86");
		c.add("8");
		c.add("��ԭ������ר��");
		c.add("8");
		
		println("��ʼ����c�Ĵ�СΪ��"+c.size()+" ; ����Ԫ���У�"+c);
/*
		//��---���ز���
		boolean flag = c.contains("AE86");
		println("�Ƿ����AE86Ԫ�أ�" + flag);

		//removeɾ---ֻɾ����һ������������~
		c.remove("8");
		println("ɾ���󼯺�c�Ĵ�СΪ��"+c.size()+" ; ����Ԫ���У�"+c);
		//clearȫɾ
		c.clear();
		println("ȫɾ�󼯺�c�Ĵ�СΪ��"+c.size()+" ; ����Ԫ���У�"+c);
*/
	}

	//2 ��������///////////////////////////////////////////////////////////////
	public static void calTest( )
	{
		//Collection �Ǽ��Ͻӿڣ�ArrayList��ʵ����
		Collection c = new ArrayList();
		basicTest(c);//���Ԫ��--------------------------------
		Collection num = new ArrayList();
		num.add("8");

		//********************���������㡿*****************************

		c.retainAll(num);
		println("�������㼯��c�Ĵ�СΪ��"+c.size()+" ; ����Ԫ���У�"+c);

		basicTest(c);//���Ԫ��-------------------------------

		//********************�������㡿*****************************

		c.removeAll(num);//(����������num�ཻ���֣�
		println("�����㼯��c�Ĵ�СΪ��"+c.size()+" ; ����Ԫ���У�"+c);

	}
	//3 ����///////////////////////////////////////////////////////////////
	public static void iterTest( )
	{
		Collection c = new ArrayList();
		basicTest(c); //���Ԫ��--------------------------------
		
		//********************��Lambda+forEach��*****************************
		/*ʹ��Lambda����
		--���ýӿ�Iterable��forEach(Consumer action)���������ÿ��Ԫ�ش���Consumer��accept����
		*/

		print("Lambda���ʽ + forEach����Ϊ��");
		c.forEach(obj->print(obj + " : ") );
		println("");

		//************************��Iterator��***********************************
		//java8--ʹ��Iterator(Iterator only���ڱ���Collection��Ԫ�أ�

		Iterator it = c.iterator();
		//hasNext ��next�� remove--it (ֻ�ܱ���ʱֻ��ͨ��������ɾ����һ��Next����forEachRemaining(Consumer act)
		print("iterator              ����Ϊ��");
		
		int flag = 0;//ɾ���ڶ���8���
		while (it.hasNext())
		{		
			//next����ֵΪString----���÷��� ����Ҫǿ��ת��
			String str = (String)it.next();
			System.out.print(str + " * ");
			//ע�⣡��String�Ƚ��ַ������Ƿ�һ��Ӧ��equals!! �Ⱥ�ֻ�Ƚ����õ�ַ
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
		println("����ʱ������ɾ����--��СΪ��"+c.size()+" ; ����Ԫ���У�"+c);
		
		print("for-each ������");
		//************************��forEach��***********************************
		for(Object obj : c)
		{
			print(obj + " :: ");
		}
		println("");
		
	}
	//4 ����Predicate ��test������ͳ�Ʒ���������Ԫ������/////////////////////////////////
	public static int predicateTest(Collection c,Predicate p)
	{
		//�������� + ����test���� 
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

	//5 Stream��֧�ֲ��кʹ��У�����/////////////////////////////////////
	public static void streamTest(Collection c)
	{
		Stream s = c.stream();
		//s.forEach(System.out::println);

		//����Stream�У��м䷽����������һ��������ĩ�˷������������дЧ��
		//***���緵�ط���������Ԫ������***
		println("�������ַ����ȴ���4��Ԫ�ظ���Ϊ��"
			+c.stream()
				.filter(obj->( ((String)obj).length()>4 ))
				.count() );

		println("�����а����Ӵ���8����Ԫ�ظ���Ϊ��"
			+c.stream()
				.filter(obj->( ((String)obj).contains("8") ))
				.count() );

		//���̣����϶��� ת ������ ת IntStream ��������IntStream��forEach����
		c.stream().mapToInt(obj->((String)obj).length()).forEach(CollectionTest::println);
	}

	public static void main(String[] args) 
	{
		//iterTest( );����
		Collection c = new ArrayList();
		basicTest(c); 

		//4 ��lambda����ʾPredicate filter ��test����--��������
		//c.removeIf(obj->( ( (String)obj).length()>4) );
		//println(c);
		/*
		println("�������ַ�����С��5��Ԫ�ظ�����"+predicateTest(c,obj->( ((String)obj).length()<5 )) );
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
