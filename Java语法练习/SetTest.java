import java.util.*;
/**
*Set--����ڹ��ӣ�Ԫ�������Ҳ����ظ�����--�̲߳���ȫ~
*1 HashSet***�ٶȿ죨���ݹ�ϣֵ������--����ӡ���ѯ����good��~
*-LinkedHashSet���������������졿~����Ϊ������=����ɾ����
*2 TreeSet***���ں�����㷨�����ִ���--����Ԫ����Ҫ����������ʱ
*3 EnumSet***Setʵ������������ã�--��ֻ�ܱ���ͬһö���ࡾö��ֵ��
*@author Hartley��
*/
class R implements Comparable
{
	int age;
	public R(){}
	public R(int age)
	{
		this.age = age;
	}

	//��дtoString
	@Override
	public String toString()
	{
		return "age = " + this.age;
	}

	//��дequals����
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

	//��дhashCode����
	@Override
	public int hashCode()
	{
		return this.age;//ע���������޸Ķ����Ա����ʱ���ɵ��²�ͬ����ͬһ��ϣֵ������ͬһͰ�У���������
	}

	//��дComparable������***��Ȼ����***�����򡿣������equalsһ�£�
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
	
	
	//****************************************��HashSet��************************************
	//Ԫ������˳����ݶ���hashCode����Ĺ�ϣֵ���ж��ظ�����equals��hashCode����������ֵ��null
	public static void hashSetTest()
	{
		HashSet hs = new HashSet();
		hs.add( new R(5) );
		hs.add( new R(-3) );//��hashSet�����к�Ϊ -3 5 6
		hs.add( new R(6) );
		Iterator it = hs.iterator();
		System.out.println(hs);
		R s1 = (R)it.next();
		s1.age = 5;//����һ�������ϣֵ��Ϊ5 ����ʵ�ʴ洢λ����-3
		System.out.println(hs);
		hs.remove(new R(5) );//
		System.out.println(hs);
		System.out.println("�Ƿ����5Ԫ�أ�"+hs.contains(new R(5)) );
		System.out.println("�Ƿ����-3Ԫ�أ�"+hs.contains(new R(-3)) );
		//��������һ����һ��Ԫ���޷���ȷ���Բ���
		
	}
	//************************************��LinkedHashSet��************************************
	//Ԫ������˳��Ϊ����˳��ͨ��������
	public static void linkedHashSetTest()
	{
		LinkedHashSet lhs = new LinkedHashSet();
		lhs.add(5);
		lhs.add(-3);
		System.out.println(lhs);
	}




	//*****************************************��TreeSet��**************************************
	//Ԫ�ذ���С����~������������ֱ�ӷ��ʵ�һ�������һ����ǰһ������һ���ķ������Լ���ȡ��treeSet�ķ���
	//���� ����� ���ݽṹ�洢����Ԫ��
	//����Ӷ������ʵ��comparable�ӿ�
	public static void treeSetTest()
	{
		TreeSet ts = new TreeSet();
		/*
		ts.add(5);
		ts.add(6);//��ʼ����comparaTo����(��Ȼ����
		ts.add(0);
		ts.add(-3);
		System.out.println(ts);//�Զ���Ԫ�ش�С�ź�˳��
		System.out.println("TreeSet��һ��Ԫ�أ�"+ts.first());
		System.out.println("TreeSet���һ��Ԫ�أ�"+ts.last());
		System.out.println("TreeSet����5��Ԫ�أ�"+ts.higher(5) );
		System.out.println("TreeSetС��0��Ԫ�أ�"+ts.lower(0) );
		System.out.println("��ȡС��5Ԫ�ص���treeSet��"+ts.headSet(5));
		System.out.println("��ȡ���ڵ���0Ԫ�ص���treeSet��"+ts.tailSet(0));
		System.out.println("��ȡ���ڵ���0��С��6Ԫ�ص���treeSet"+ts.subSet(0,6));
		ts.clear();
		System.out.println(ts);
		*/

		//��ʾ�ı�ؼ��ĳ�Ա����ֵ��ɾ����ӦԪ��ʧ��~//////////////////////////////////////////////
		ts.add(new R(5) );
		ts.add(new R(6) );
		ts.add(new R(7) );		
		System.out.println(ts);//����toString
		
		R first = (R)ts.first();//�������ȡ����һ��Ԫ��~~
		/*
		Iterator it = ts.iterator();//��ͳ������~~
		R first = (R) (it.next() );
		*/		
		first.age = 100;//�޸Ŀɱ�Ԫ�صĹؼ���Ա����age

		System.out.println(ts);//�޸Ĺؼ�������ֱ��Ӱ��hashCode��	
		System.out.println("ɾ���޸ĺ������Ԫ�أ�5->100��5��"+ts.remove(new R(5) ));
		System.out.println("ɾ���޸ĺ������Ԫ�أ�5->100��100��"+ts.remove(new R(100) ));
		System.out.print("ɾ��Ԫ��6��"+ts.remove(new R(6) ));
		System.out.println(ts);//��ͼɾ��6

		//���ƱȽ�������lamabda���ʽ//////////////////////////////////////////////////////////
		TreeSet ts1 = new TreeSet( (o1,o2)->
			{
				R r1 = (R)o1;
				R r2 = (R)o2;
				//return r1.age>r2.age ? -1: r1.age<r2.age ? 1:0;//��ǰ�ȴ��ȽϵĴ󷵻�-1������
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
	//���ö����ļ���==��ö��ֵ���ڲ�����λ�����γɴ洢�����ո�Ч��
	//�����null==ֻ�����෽������EnumSet����
	public static void enumSetTest()
	{
		//�෽��allOf��õ�ö�ټ���Ԫ��--��ΪSeasonö�����ȫ��ö��ֵ
		EnumSet es = EnumSet.allOf(Season.class);
		System.out.println(es);

		//�෽��noneOf��������Seasonö�����*�ռ���*
		EnumSet es2 = EnumSet.noneOf(Season.class);

		//��ÿռ������Ԫ��
		es.add(Season.SPRING);
		System.out.println(es2);

		//��ö��ֵ����ö�ټ���
		EnumSet es3 = EnumSet.of(Season.SUMMER,Season.WINTER);
		System.out.println(es3);

		//��������-�����㣨�ܵ�ö��ֵ �� es3��ö��ֵ ����~��
		EnumSet es4 = EnumSet.complementOf(es3);
		System.out.println(es4);
		
		//��ͬһö����Ԫ�ص�Collection�и���
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
