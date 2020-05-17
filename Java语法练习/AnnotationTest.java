import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.*;
/**
*ע�� Annotation
		�ڱ��롢����ء�����ʱ��ȡע�⣬������Ӧ����
	|--������ע��==��java.lang����
			|--@Override//����д���෽��ʱ�������Է�����ǩ������
			|--@Deprecated//���η������ࡢ�ӿ�--ʹ�ñ����ε��ࡢ����ʱ����ʾ��ʱ��warnning
			|--@SuppressWarnings//���Թر�ָ��value�ľ���
			|--@SafeWarargs//���ڹرա�����Ⱦ���ľ��棨���Ͳ������ٰѸö���ֵ�������͵Ķ��� ��
			|--@FunctionInterface//ֻ��һ���������Ľӿ�--Lambda
	|--��java.lang.annotation�£���������Annotation��
			|--@Retention(value = xxx)ָ�����ε�ע������Ա�����ʲôʱ��ֻ��valueֵ����ʡvalue=��
					vlaue = RetentionPolicy.CLASS��Ĭ��ֵ��--annotation������class�ļ��У�������ʱJVM���ɻ�ȡannotation��Ϣ
					vlaue = RetentionPolicy.RUNTIME---ע��Ҳ������class�ļ���������ʱ��ͨ�������ȡannotation��Ϣ
					vlaue = RetentionPolicy.SOURCE---ע��ֻ������Դ�ļ����������ᶪ������ע����Ϣ
			|--@Target(value = xxx)ָ�����ε�ע�����������ʲô
					vlaue = ElementType.ANNOTATION_TYPE: �����ε�annotationֻ������annotation
					                                 .CONSTRUCTOR: ���ι�����
													 .FIELD�����γ�Ա����
													 .LOCAL_VARIABLE�����ξֲ�����
													 .METHOD�����η���
													 .PACKAGE�����ΰ�����
													 .PARAMETER�����β���
													 .TYPE�������ࡢ�ӿڣ�����ע�����ͣ���ö�ٶ���

			|--@Document���ε�ע���������javadocʱ����ȡ���ĵ�������������ע�⣩					
			|--@Inheritedʹ���ε�ע������м̳��ԣ�����@Inherited���Σ���������Զ���@Inherited���Σ�
			|--@Interface�Զ���Annotation			
	|--��ȡAnnotation��Ϣ
	|--�ظ�ע�⣨java8)
	|--TypeAnnotation��java8)
	|--APT����-����ʱ����annotation����
*@author Hartley
*@version 1.0.0
*/

@SuppressWarnings(value = "unchecked")
class  AnnotationTest
{
	//************�������***************
	public static void main(String[] args) 
	{
		//new Carrot().color();//@Deprecated ��ʾ��ʱ��warnning
		List<String> list = new ArrayList();
	}
}

//�Զ���ע��
 @interface girl
{
	String name() default "null";//���Ժ���Ա������������Ĭ��ֵ����ʹ��ע��ʱ����ָ����
}

@girl
class Vegetable
{
	public void eat()
	{
	}
}

@Target(ElementType.FIELD)
@interface testable
{
}
class Carrot extends Vegetable
{
	@Override
	//public String eat()//������д����ǩ��������
	public void eat()
	{
	}

	@Deprecated
	public void color()
	{
		System.out.println("Carrot is read!");
	}
}