import java.io.*;
/**
*�쳣���ƣ�������Ԥ�ڵ��쳣�������������ҵ���߼�����������
*-|| �ؼ���
*----|| try�������У�:��ſ��ܲ����쳣�Ĵ��룬ĳ������쳣���������벻��õ�ִ��
*----|| catch����һ��:������쳣���� �� ��Ӧ����Ĵ���顣ԭ����С�쳣���ٴ��쳣����������쳣������ | ������������쳣ʱ�����쳣�����ᱻ��ʽ����final�����ɸģ�
*----|| finally����һ��:��catch��һ�����ڻ���try���õ�����Դ���쳣���ƻᱣ֤finally��һ���ᱻִ�У����ǵ������˳�������ķ��� System.exit(1)
*----|| throws:�ڷ���ǩ�����������������ܻ�������쳣
*----|| throw:�׳�һ��ʵ���쳣�������Զ����
*-|| �쳣���ͣ�Checked�����봦��->�������ã�& Runtime�����账��->�Ƽ���
*----|| ��Checked���쳣����
*-----------|| 1.��ǰ����֪����ν�����쳣ʱ���á�try catch����䲶����쳣����catch���ڴ�����쳣
*-----------|| 2.��֪����ô������Ҫ�ڷ����������׳����쳣 ��throws�� XXException���������׸�JVM����ӡStackTrace��Ϣ����������
*----|| ��Checked����ȱ�ݡ���
*-----------|| ������ʾ������->���ӣ�
*-----------|| ��д���������쳣�ķ���->���쳣�������ƣ�ֻ���׳������и÷����׳��쳣�ı�����������쳣��
*----|| ��Runtime���쳣
*-----------|| �Ƚ���������ʾ����Ҳ���Բ��ܲ��ˣ�Soһ���Զ����쳣���̳�RuntimeException
*-|| �쳣���Ƶ�˼·�����ĳ�δ����������׳�һ���쳣����Ѱ�Ҷ�Ӧ���͵�catch�飨����ִ�е�һ����Ӧ��catch�飬û����ʹ��˳���
*-|| �Զ��ر���Դ��try��䣨�������finally������Դʱ������ӷ�ף���
*----|| ��д��try(������ʹ�õ���Դ){ʹ����Դ}
*----|| ��Ӧ��ԴҪʵ��AutoClosable��Closable�ӿ��е�close������AutoClosable���׳�Exception�쳣��������������ף�ǰ�ߵ��ӽӿ���close�����׳�����IOException��������ֻ����IO�쳣��
*-|| catch��throw������Ӧ�ú�̨��¼�쳣��־�������쳣������߷�����Ϣ
*-||�쳣��������ԭʼ�쳣��¼����������Ա��ת��һ��ҵ���쳣�׸��û���Throwable������Ĺ��������ɽ���cause-ԭʼ�쳣������Ϊ����//��װԭʼ�쳣��
*-||�쳣����Ŀ�꣺
*----|| ���뾡��������������
*----|| ���񲢱��浽�����Ϣ
*----|| ֪ͨ���ʵ���Ա
*----|| ��ȡ���ʵķ��������쳣

*@author Hartley
*@version 1.0.0
*/

class  ExceptionTest
{
	public static void test()
	{
		int[] arr = new int[10];
		String str =null;
		Integer num=0;	
		
		FileInputStream fis = null;

		try
		{
			/*		
			System.out.println("��1��");
			num = arr[10];
			System.out.println("��2��");
			num = 10/0;
			System.out.println("��3��");
			str.toString();*/
			
			fis = new FileInputStream("mag.txt");//��ͼ��һ�������ڵ��ļ���������IO�쳣

			//return ;//˳��--��ִ��finally�飨���finally������return��ֱ�ӷ��أ��˴���ִ�У�--��ִ�����return
			System.exit(1);//�����ֱ���˳�������ִ��finally����

		}
		
		catch(IOException ioe)//IO�쳣
		{
			System.out.println(ioe.getMessage() );//��ӡ����ԭ��
		}
		catch (IndexOutOfBoundsException | ArithmeticException e)//����Խ���쳣
		{	
			System.out.println("����Խ��������쳣");
			//e = new NullPointerException("hh");//������쳣ʱ���쳣�����Ĭ����final���Σ����ɸ�
		}
		/*catch (IndexOutOfBoundsException e)//����Խ���쳣
		{	
			System.out.println("����Խ���쳣");
			System.out.println("�쳣����ϸ������"+e.getMessage());
		}
		catch(ArithmeticException e)
		{
			System.out.println("�����쳣");
			System.out.println("�쳣����ϸ������"+e.getMessage());
		}*/
		catch(NumberFormatException e)
		{
			System.out.println("���ݸ�ʽת���쳣");//�������룿
			
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();//��ӡ�쳣�ĸ���ջ��Ϣ
			System.out.println("��ָ���쳣");
			System.out.println("�쳣����ϸ������"+e.getMessage());
		}
		catch(Exception e)//Exception�������쳣�ĸ��࣬������Ҫ�ŵ�����ڱȽ��쳣����ʱ�Ǹ��� e instanceOf Exception��
		{
			System.out.println("δ֪�쳣");
			e = new RuntimeException();//�����쳣ʱ���쳣�������Ը���
		}

		finally
		{
			//������Դ
			if(fis != null)
			{
				try
				{
					fis.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			System.out.println("I am finally");
		}
	}

	//�����Զ�������Դ��try���
	public static void autoCloseTest() 
	{
		try(
			//��try�����������������Զ����յ���Դ����ʽfinally�飩
			BufferedReader br = new BufferedReader(
													new FileReader("config.ini"))//BRʵ����Closeable�ӿڣ�try�����Զ��ر���
			)
			{
				//����Դ���в���
				System.out.println(br.readLine());
			}
		catch(Exception e)//���û��catch��������Ҫ�ڷ���ǰ����throws XXException
		{
			e.printStackTrace();
		}
		
	}

	//��ǿ��throw���--��������ϸ�¼���쳣��ʵ���࣬��������Exceptionһ�Թ�֮
	public static void throwTest() throws FileNotFoundException
	{
		try
		{
			new FileOutputStream("xx.txt");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;//��ǿ��throw��䣬�׳���Ҫ�ڷ��������������׳����쳣--����ǿ����ʵ���쳣����������ͳException
		}
	}
	//************�������***************
	public static void main(String[] args) throws FileNotFoundException
	{
		// test();
		//autoCloseTest();
		throwTest();
		//��һ���Զ����Runtime���͵��쳣�����Բ�����~
		//throw new DiyException("I am Diy_Exception");
	}
}

//�Զ����쳣��
class DiyException extends RuntimeException
{
	//�޲ι�����
	DiyException(){}
	//������->msg
	DiyException(String msg)
	{
		super(msg);
	}
}