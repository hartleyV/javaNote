import java.util.*;
/**
*���ͣ�Ϊ���ü��ϼ�ס����������~����������ʵ�Σ� 
*���ƶ�����Ԫ�����ͣ�����ʱ���ɷ��֣�&ȡ������ǿ��ת��--�������ཡ׳
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
	public static void main(String[] args) 
	{
		test1();
	}
}
