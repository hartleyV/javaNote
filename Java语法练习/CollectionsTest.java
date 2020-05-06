import java.util.*;
/**
*Collections���Ϲ�����-�ɲ���Set��List��Map
*���ܣ�����--��ѯ--�޸�--ͬ��--���ɱ�
*@author Hartley
*@version 1.0.0
*/

class  CollectionsTest
{
	//����
	public static void sortTest()
	{
		ArrayList id = new ArrayList();
		id.add(5);
		id.add(1);
		id.add(7);
		id.add(3);
		System.out.println(id);
		//��Collections��reverse��������ת
		Collections.reverse(id);
		System.out.println(id);
		//sort����Ĭ������
		Collections.sort(id);
		System.out.println(id);
		//��������(Comparator-lambda)
		Collections.sort(id,(o1,o2)->(Integer)o2-(Integer)o1 );
		System.out.println(id);
		//shuffle�������ϴ��
		Collections.shuffle(id);
		System.out.println(id);

	}
	//���ֲ�ѯ���滻����ֵ����Ƶ
	public static void searchTest()
	{
		ArrayList id = new ArrayList();
		id.add(5);
		id.add(1);
		id.add(5);
		id.add(-9);
		id.add(3);
		System.out.println(id);
		//���ֲ�ѯ��Ҫ������
		Collections.sort(id);
		System.out.println(id);
		int index = Collections.binarySearch(id,5);
		System.out.println("5��list�����е�����Ϊ��"+index);
		//�滻(ȫ���滻��
		Collections.replaceAll(id,5,6);
		System.out.println(id);
		//��ֵ
		System.out.println(Collections.max(id));
		//��Ƶ
		System.out.println("6�ڼ���id�г��ֹ���"+Collections.frequency(id,6));

	}
	//��װ���߳�ͬ��
	public static void synchronizedTest()
	{
		Collection c = Collections.synchronizedCollection(new ArrayList() );//ע��˴���װ������ͬ��XXX
		List list = Collections.synchronizedList(new ArrayList() );
		Set set = Collections.synchronizedSet(new HashSet() );
		Map map = Collections.synchronizedMap(new HashMap() );

	}
	//����ֻ������
	public static void unmodifiableTest()
	{
		ArrayList id = new ArrayList();
		id.add(5);
		id.add(1);
		id.add(5);
		id.add(-9);
		id.add(3);
		System.out.println(id);
		//��װ�ɲ����޸ļ���
		List unmodifiableId = Collections.unmodifiableList( id );
		System.out.println(unmodifiableId);
		//unmodifiableId.add(4);//�����޸�����

		//�����յĲ��ɸı�Ķ���
		Map emptyMap = Collections.emptyMap();
		System.out.println(emptyMap);

		//����ֻ��һ�������Ҳ��ɱ��޸ĵļ���
		List list = Collections.singletonList("����������");
		System.out.println(list);

	}
	//�������
	public static void main(String[] args) 
	{
		//sortTest();
		//searchTest();
		unmodifiableTest();
	}
}
