import java.util.*;
/**
*���ͣ�Ϊ���ü��ϼ�ס����������~����������ʵ��--�൱�ڶ���߼����ࣩ 
*��;�����ƶ�����Ԫ�����ͣ�����ʱ���ɷ��֣�&ȡ������ǿ��ת��--�������ཡ׳
*�÷���
*-|| �����ڡ��ࡢ�ӿڡ�
*-----|| ������<T>�� ��������Ȼ���䣬ֻ�ǵ��ù����� -new ����<>() ���˸������Ρ���
*-----|| ����̳з�����ʱ��Ҫ�����÷����������ʵ�� ��class x entends y<String>��;����дʹ�÷��͵ķ���ʱҲ��д��
*-----|| û�з����࣬���Բ������ھ�̬��Ա�����������ʹ�÷���
*-----|| List<Object> ��List<Integer>ͬ��һ��class��������û�м̳й�ϵ�������໥��ֵ�������������븸����촦������������Number num = new Integer(); num=0.1;//error
*-----|| ����ͨ�����������໯��List<?>-���ⷺ��List���࣡����ע�⣿Ϊ����ʵ�Ρ���ͬString��Integerһ����
*--------|| ���Դ����κ�����ʵ�Σ��൱��������ת��ΪObject��ע���޷�ȷ���������ͣ��������Ԫ�أ�Java�����������һ��λ�����͵ļ����У�
*-----------|| ��add����ʹ���˷���E��Ϊ����Ԫ�����ͣ���Ҫ��ȷ��ſ���ӣ�����null��
*-----------|| ��get()���ص���Object���ͣ������
*--------|| ����ͨ�����List<? entends String & Comparable>����֮��һ���������ߣ�����ӿ����ޣ�˳��:���ƴ��������ʵ��
*--------|| ����ͨ�����List<? super T> list����ʾ���Ͳ���ΪT��T�ĸ���
*-|| �����ڡ�������
*-----|| ���ͷ�������ͬ���õ㣬���벻ͬ����ʵ�Σ��������η�<T,E> ����ֵ���� method(List<T> list)��--/--<T,E extends Shape>
*--------|| ʲôʱ��ʹ�÷��ͷ���ORͨ���->�����β�ֻ�ڴ���ʱʹ�ã���������������ֵ����������:ͨ���
*-----|| ���͹����� �����η�<T> ����(T xx)��,�������벻ͬ���͵�����ʵ�����������
*--------|| ��������������ʽ- new Shape(5); ��ʾ- new <Integer> Shape(5);
*-|| �����ƶϣ������������¿����Զ��ж�����
*-|| ������ת����
*-----||����List<String> list ��ֵ��List2 l�������������ʵ�Σ�
*-----||ת����List list2��ֵ��List<String> list��ת����ע��list2�ڸ�ֵǰ����ӵ�Ԫ��ӦΪString��������list����Ԫ�ص�ʱ��������Ͳ�ƥ�䱨��
*-|| �������飺����֧�֣�
*-----|| �������ԭ�򣺱���ʱû��unchecked���棬����ʱ�Ͳ������ClassCastException�쳣
*-----|| ����������Ԫ�ذ������ͱ���/�βΣ�ͨ������ԣ�-���Բ���new List<String>[10]��������List<String>[] lists = new List[10];//����unchecked����
*@author Hartley
*@version 1.0.0
*/

class  GenericTest
{
	//�����÷�
	public static void test1()
	{
		List<Integer> list = new ArrayList<>();//�Ҷ�ʡ��д��~ֻдһ�������Ρ�<>
		list.add(3);
		list.add(7);
		list.add(4);
		System.out.println(list);
		list.forEach(tmp->System.out.println(tmp+1));//ʹ�÷��ͺ���Ҳ����ǿ��ת����
		
		//����**��������**~
		Map<String,List<String>> courseInfo = new HashMap<>();
		List<String> jackCourse = new ArrayList<>();
		jackCourse.add("Chinese");
		jackCourse.add("Math");
		jackCourse.add("SemiConductor");
		courseInfo.put("jack",jackCourse);

		System.out.println(courseInfo );
	}
	//���ͷ���
	public static <T>  T genericMethod(T[] arr,List<T> list)
	{
		for (T ele:arr )
		{
			list.add(ele);
		}
		return arr[arr.length-1];//�������һ��Ԫ��
	}
	//���Ƽ���Ԫ��,ȡ�����һ��Ԫ��
	public static <T> T copyOf(Collection<? super T> dst,Collection<? extends T> src)
	{
		T last = null;//�ڷ�����ʹ�ñ���ǰ��Ҫ��ʼ��
		//ͨ�������~
		for (T tmp:src )
		{
			last = tmp;
			dst.add(tmp);
		}
		return last;
	}

	//treeSet�õ�comparator�ӿڼ�Ϊͨ�������Լ�������Ե��û������������������~
	public void treeSetTest()
	{
		//�������������treeSet
		Set<String> id = new TreeSet<>(
			new Comparator<String>()//��Ӧ����Ϊcomparator<? extends T>,�ȿɱȽ�String��Ҳ��Object��
		{
			public int compare(String first,String second)
			{
				return first.length() - second.length();
			}
		});
	}
	//�Զ��ƶ�
	public static void assertTest()
	{
		Test<String> t1 = new Test<>(5);//�Ҷ˾�����
		Test<String> t2 = new <Integer>Test<String>(6);//�Ҷ����;���ʾ
		Test<String> t3 = new Test<String>(7);//��������ʽ
		//Test<String> t4 = new <Integer>Test<>(8);//��������ʾ+����-->�����ƶϳ�Test�����Ͳ���
	}

	//��������
	public static void genericArrayTest()
	{
		//List<String>[] lists = new List<String>[10];//�����д��,����ͨ�����룺Υ�������������ԭ��
		List<String>[] lists = new List[10];//unchecked warnning
		Object[] obj = lists;//��������1��
		List<Integer> ele = new ArrayList<Integer>();//Ԫ������ΪInteger
		ele.add(4);
		obj[1] = ele;//������
		String str = lists[1].get(0);//����ʱ�����ClassCastException�쳣
	}

	//�������
	public static void main(String[] args) 
	{
		List<String> list = new ArrayList<>();
		String[] arr = {"jack","heyjudy","doggy","github"};

		System.out.println(genericMethod(arr,list) );
		System.out.println(list);

		List<Integer> src = new ArrayList<>();
		src.add(23);
		src.add(99);
		src.add(48);
		List<Number> copy = new ArrayList<>();//Integer�ĸ���
		Integer last = copyOf(copy,src);
		System.out.println("the last element: "+last+" the copy list is��"+copy);

		
		List list2 = new ArrayList();
		list2.add(6);//��ӵ�Ԫ������ΪInteger
		//System.out.println( (String) list2.get(0));//�˴��漰��Integer����ǿתΪString���ᱨ��
		genericArrayTest();
	}
}

//������
class Test <E>
{
	//���͹�����
	public <T> Test(T t)
	{
		System.out.println(t);
	}
}
