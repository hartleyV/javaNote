import java.util.*;
import java.io.*;
/**
*Map���Ͻӿ�
*Դ������ʵ��Map��Set��������Ͻ�valueȫ����Ϊnull
*key-value����װ�ڼ��ϵ�Entry�ڲ����У����Խ�value����key�ĸ�ӹ
*--|| HashMap��죨�ж�key�Ƿ���ͬ��ͨ��equals+hashCode���ж�value��equals�жϣ�--�����null��k/v�����ؼ���3/4��
*-----|| Hashtable(�̰߳�ȫ�������ڹ��Ͼ�������ʹ��--�������null��key/value
*--------|| Properties(Hashtable���࣬���ڴ��������ļ�)
*--|| LinkedHashMap����˫������ṹά��Ԫ�ش��򣨰��������
*--|| TreeMap(ʵ����StoreMap�ӿڣ������÷�keySet->toArray->�����key���飩���ں�����ṹ��key���򣨷�Ϊ��Ȼ����-��Ҫ��keyʵ��comparable�ӿڣ���������-��Ҫ��
*--|| WeakHashMap(HashMap��key�����˶����ǿ����==��map���ϲ����٣�����Ͳ��ᱻ���գ�������-������������
*--|| IdentityHashMap(�Ƚ�key�Ƿ���ͬ���Ǹ���key1==key2��
*--|| EnumMap������ã��ڲ�������洢��key����null��value�ɣ�����ʱ��Ҫָ��ö����
*@author Hartley
*@version 1.0.0
*/

class  MapTest
{
	public static void mapTest()
	{
		Map mt = new HashMap();
		//���key-value
		mt.put(1,"����A��");
		mt.put(2,"����С��");
		mt.put(3,"������");
		println(mt);
		//key�����أ����ǻ᷵�ر����ǵ�value
		println(mt.put(3,null));
		//value��������ظ�,������ʱ����Ϊnull
		println( mt.put(4,"����С��")  );
		println(mt);

		//�ж��Ƿ������Ӧkey/value
		println("�Ƿ����keyΪ2��"+mt.containsKey(2));
		println("�Ƿ����valueΪ����С�£�"+mt.containsValue("����С��"));

		//for-each����--��ȡ��key�ļ���
		for(Object key:mt.keySet() )
		{
			println("||"+mt.get(key));
		}

		//����keyɾ��Ԫ��
		mt.remove(1);
		println(mt);

		//����ӵ�Ĭ�Ϸ���
		//replace--��key������ʱ������ı�
		mt.replace(4,"����СŮ��");
		println(mt);
		//merge--��Ӧkey��valueΪnullʱֱ�ӻ�Ϊָ��ֵ��������keyͨ��mappingFunction����
		mt.merge(2,"Ұԭ��֮��",(old,cur)->((String)old).length()+"��"+(String)cur );
		println(mt);
		
		//����valueֵ
		//���ԭ��valueΪnull������BiFunction�ӿڼ����µ�value(�������Ϊ��ǰkey��
		mt.computeIfAbsent(3,key-> " this key is: " + key );
		println(mt);
		//���ԭ��value��Ϊnull����key-valueͨ����remappingFunction��������µ�value
		mt.computeIfPresent(2,(key,value)-> " this key is: " + (Integer)key+" && the value is��"+(String)value );
		println(mt);
	}
	//************************��HashMap��*****************************
	public static void hashMapTest()
	{
		HashMap hm = new HashMap();
		hm.put(new A(3),new A(3));
		hm.put(new A(2),new A(3));
		println(hm);

		//����value��ͨ��equals�ж�--ֻҪ���õ�equals����ture����Ϊ����value
		println("�Ƿ����A(5)���ֵ��"+ hm.containsValue(new A(5)));
		//����key��equals & hashCodeͬʱ�ж�
		println("�Ƿ����A(2)�������" + hm.containsKey(new A(2)));

	}

	//************************��LinkedHashMap��*****************************
	public static void linkedHashMapTest()
	{
		LinkedHashMap lhm = new LinkedHashMap();
		lhm.put(1,"xx");
		lhm.put(4,"YY");
		lhm.put(3,"VV");
		//foreach����
		lhm.forEach( (key,value)->System.out.println("Key-> "+key+" Value-> "+value));
		
	}

	//************************��Properties��*****************************
	public static void propertiesTest() throws Exception
	{
		Properties prop = new Properties();
		//�������
		prop.setProperty("name","Hartley");
		prop.setProperty("age","24");
		//������д�뵽�ļ����־û���
		prop.store(new FileOutputStream("config.ini"),"note: author Info");

		//���ļ���ȡ����
		Properties prop2 = new Properties();
		prop2.load(new FileInputStream("config.ini"));
		println(prop2);

	}

	//************************��treeMap��*****************************
	//��Ϊ����key�������Ի���һЩλ����صķ�������һ�������һ����ǰһ����������
	public static void treeMapTest()
	{
		TreeMap tm = new TreeMap();
		tm.put(new A(3),1);
		tm.put(new A(-3),2);
		tm.put(new A(8),3);
		println(tm);

		//��ȡ��һ����key��С�ģ�Entry���󣨼�ֵ�ԣ�
		println(  ((A)(tm.firstEntry().getKey())).count);
		//��ȡ��һ��key
		println(tm.firstKey());
		//��ȡMap�б�ָ��key���һ��key
		println(tm.higherKey(new A(0) ) );
		//��ȡMap�б�ָ��key���һ��Entry
		println(tm.higherEntry(new A(3)));
		//��ȡkeyֵ����ָ��key֮���map�Ӽ��ϣ�Ĭ�ϰ���from ������to
		//println(tm.subMap(new A(0),true,new A(8),true ));
		println(tm.subMap(new A(3),new A(8) ));
	}

	//************************��WeakHashMap��*****************************
	public static void weakHashMapTest()
	{
		WeakHashMap whm = new WeakHashMap();
		whm.put(new A(1),3);//��������--������
		whm.put(new A(2),3);
		whm.put("String",3);//ϵͳ������ַ�������--ǿ����
		println(whm);

		System.gc();//֪ͨϵͳ����
		println(whm);//ֻʣ��ǿ���õ�key-value

	}
	//************************��IdentityHashMap��*****************************
	public static void identityHashMapTest()
	{
		IdentityHashMap ihm = new IdentityHashMap();
		ihm.put(new String("English"),66);
		ihm.put(new String("English"),76);//��Ϊ��key1==key2�ķ�ʽ�����Կ������
		ihm.put("English",86);
		ihm.put("English",96);//����һ��key==���������ʱ�Ὣ�������ǰ��
		println(ihm);

	}

	//************************��EnumMap��*****************************
	public static void enumMapTest()
	{
		EnumMap em = new EnumMap(Season.class);//�˴�ֻ��ָ��ö�ټ���key������
		//���ö��MapԪ��
		em.put(Season.SPRING,"����Ĵ�");
		em.put(Season.SUMMER,"��Ұ����");
		println(em);
	}
	//�������
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

//����key��Ҫʵ��Comparable�ӿ�
class A implements Comparable
{
	public int count;
	public A(int count) 
	{
		this.count = count;
	}
	//ʵ��compareTo����
	public int compareTo(Object obj)
	{
		return this.count - ( (A)obj).count;
	}
	//��дtoString
	public String toString()
	{
		return "[count��"+this.count+"]";
	}
	//��дequals
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
		return true;//equals����һֱ���� ͬ
	}
	//��дhashCode
	public int hashCode()
	{
		//return this.count;//��ϣֵֻ��count���
		return this.count * (int)(Math.random() *100);//��ϣֵ-��ͬʵ�������ϣ��ͬ
	}
}

//�ļ� ö����
enum Season
{
	SPRING,SUMMER,AUTUMN,WINTER;
}