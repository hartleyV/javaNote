import java.io.*;
import java.util.*;
/**
*File ��
*IO��
       |--�ŵ㣺�������Ч�ʣ�������ݣ�һ���Դ������������
       |--��������������Ǹ��ݳ��������ڴ濼�ǵ�
       |--�ֽ���8Ϊ������InputStream OutputStream);�ַ���16λ������Reader Writer��
       |--���ࣺ�ڵ���������ֱ����������Դ��������/��װ����ͨ����װ�������ʵ�����ݵĶ�д��
       |--ʵ���ࣺ
	            |--�����ļ�FileXX������ByteArrayXX CharArrayXX���ܵ������̼�ͨ�ţ�PipedXX
	            |--��ӡ��PrintWriter���̳�Writer��PrintStream���̳�OutputStream��**���𣿣�*
	            |--�����ַ���StringW/R��������(���Ч�ʣ���Ҫflush��BufferedXX
	            |--���������������л���ObjectI/O
	            |--ת�������ֽ�ת�ַ���InputStreamReader OutputStreamWriter
	            |--�ƻ������� PushbackInputStream PushbackReader����unread�浽�ƻػ�������
				    read���ȴ��ƻػ�����ȥȡ��ȡ���ٴ�ԭ��������ȡ
	   |--System������
			    |--�ض���setIn(InputStream in) setOut(OutputStream out)�ض����׼�����������Java����Ϊ���ģ���
				                  setErr(PrintStream err)�ض����׼���������
*��ȡ�����������ݣ���Java����Ϊ���ģ����������뻹���������getInputStream/getOutputStream/getErrorStream
*��RandomAccessFile����������ļ���ֻ�����ļ��棬IO������)
	   |--�ɶ���д��ֻ�����ļ��棬IO������)
	   |--ֻ��ȡ�ļ���ָ����ĳһ����ʱ ѡ��ѡ��~
	   |--���������ƶ��ļ���¼ָ�� long getFilePointer����ָ�뵱ǰλ�ã�seek(long pos)�Ƶ�ָ��λ��
	   |--������������
			    |--����һ��String / Fileָ���ļ�����
			    |--��������mode����== r-ֻ����rw��д��û���ļ����Զ���������
				                  rws���ļ�ÿ�����ݡ�Ԫ����ͬ�����µ��ײ�洢����
								  rwd���ļ�ÿ������ͬ�����µ��ײ����ݣ�
*�������л���
	   |--��;���Ѷ������л�Ϊ�ֽ��ļ���ͨ�������л��ָ�Ϊ����ʹ��������������ж���������
	   |--��Ҫע�⣺
	             ��Щ�ᱻ���л�������������ʵ���������������������transient�������ᱻ���л���
				 �����л�������Ҫԭ�������class�ļ�
				                         ---ͨ��private static final long serialVersionUIDȷ����ͬ�汾class�ļ�����
				 ��ȡ���л�������Ҫ��д��˳���ȡ
	   |--�÷���
	   	    |--��1����Ҫ��ʹҪ���л�����ʵ��Serializable��Externalizable�ӿڣ�ֻ��һ����ǣ�-- ÿ��JavaBean��Ҫʵ��Serializable
	   	    |--��2������ObjectOutputStream������ ������Ҫ�����ڽڵ���֮�ϣ�������Ҫ����ڵ�����
	   	    |--��3������ObjectOutputStream����ķ��� writeObject(����)  ������д�뵽�����
	   	    |--���뱻���л���ʵ��������
			   	    |--	�á�transient������	 ����Ӧ��Ա���������л�ʱ�ᱻ���룩  	    
			   	    |--	�Զ������л� 	    
			   	         |-- ��1��Serializable-�ڴ����л�����������·���	    
			   	                 |-- private void writeObject(java.io.ObjectOutputStream out) throws IOException
								      ����д����Щʵ����������μ��ܣ����д�루�޴˷���Ĭ�ϻ����out.defaultWriterObject
			   	                 |-- private void readObject(java.io.ObjectInputStream in) throws IOException,ClassNotFoundException
								      ִ���������෴�Ĳ���
								 |-- private void readObjectNoData() throws ObjectStreamException
								      ��������������ʱ���汾���죬���۸ģ��������������ʼ�������еĶ���
								 |-- private Object readResolve() throws ObjectStreamException
								      ��readObject������ִ�У�����ֵ���滻ԭ���ķ����л�����
									  �����л������ࡢö���೬�����ã����ⴴ���µ���������
			   	         |-- ��2��Externalizable-  ����Ա�����洢��Щ��Ϣ �������Ժã�����̸���	
						 
			|--�����л������ֱ�ӻ��Ӹ���Ҫô���޲ι�������Ҫôʵ�����л��ӿڣ�����InvalidClassException
			    �ҵ����಻�����С����޲ι�����ʱ�������еĳ�Ա����ֵ�������л����������ļ���
				�ҵ���Ա��������������ʱ������������ҪҲ�ǿ����л��ģ���������޷����л�
	   	    |--������л�ͬһ������ֻ�е�һ��writeObject�ŻὫ�������л��ֽ����в������
				���������л��ö���ֻ�����һ�����л���ţ����Ե�һ��֮�����۶���ʵ��������α仯��ʵ������Ҳ���ᱻ�����
*���󡾷����л���
	   |--�ص㣺���蹹������ʼ�����󣬶�����л������ڶ�ȡʱ�谴˳��
	                 
	   |--�÷���
	   	    |--��1������ObjectInputStream����
	   	    |--��2������readObject����������Object���Ͷ�����Ҫ����ǿ��ת��Ϊ��ʵ����
			    �����ݻָ���������Ҫ��Ӧ�࣬��������ClassNotFoundException�쳣
*NIO�µ�IO��
*@author Hartley
*@version 1.0.0
*/

class  IOTest
{
	//File��Ļ�������
	public static void fileTest()
	{
		File file = new File(".");//�Ե�ǰ·�������ļ�����,�� .��ʾ��ǰĿ¼��ע������·��ʱҪ \\ ���� /
		System.out.println(file.getName() + " �����Ƿ���ڣ�"+file.exists());//����true
		System.out.println(file.getName());
		System.out.println(file.getParent());//��ȡ��Ը�·�����������null��
		System.out.println(file.getAbsoluteFile().getParent());//��ȡ��һ��·������Ը�·����
		System.out.println(file.getAbsoluteFile());//��ȡ����·��
		
		File newFile = new File(System.currentTimeMillis()+".lhs");
		
		try
		{
			newFile.createNewFile();//��file���󴴽��ļ�
			System.out.println(newFile.mkdir());//����Ŀ¼��ǰ�洴���ļ��ˣ����Է���false

			System.out.println("==��ǰ·���������ļ���·��==");
			String[] fileLists = file.list();//��ǰ·���������ļ���·��
			for (String list : fileLists )//����
			{
				System.out.println(list);
			}
			
			System.out.println("==���д��̸�·��==");
			File[] roots = File.listRoots();
			for (File root : roots )
			{
				System.out.println(root);
			}


			File tmpFile = File.createTempFile("temp",null);//������ʱ�ļ�,����Ϊtemp��null��ӦĬ�Ϻ�׺tmp����file·����
			tmpFile.deleteOnExit();//����ɾ�����ӣ��˳�JVM����ʱ�ļ��Զ�ɾ��

			
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		
	}

	//File������-�����ӿ�FileNameFilter��***accept(File dir,String name)***	
	public static void filterTest()
	{
		File file = new File(".");//��ǰĿ¼
		//�г���ǰĿ¼�� .java�ļ����Լ���Ŀ¼
		String[] lists = file.list( (dir,name)->name.endsWith(".java") || new File(name).isDirectory() );
		for (String list : lists )
		{
			System.out.println(list);
		}
	}

	//�ö�ȡ�ļ���������FileInputStream��ȡ�ļ�����ȡ�ֽ�/�ַ� + �ڵ�����
	public static void fileInputStreamTest()
	{
		FileInputStream fis = null;
		byte[] buffer = new byte[1024];//1024���ֽ�==1KB
		/*FileReader fis = null;
		char[] buffer = new char[1024];//1024���ַ� == 2KB
		*/
		int len=0;//ʵ�ʶ�ȡ�ĳ���

		try
		{
			 fis = new FileInputStream("tt.txt");//�ֽ�
				//fis = new FileReader("tt.txt");//�ַ�
			//ѭ����һ��1024���ȵ���Ͳȡˮ��ÿ����Ͳ��1���ֽڣ�ȡ��û��ˮ�˷���-1
			while( (len = fis.read(buffer) ) != -1)
		{
			System.out.println( new String(buffer,0,len));//len��ת�����ַ����ĳ���
		}

		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		finally
		{
			try
			{
				fis.close();//�ر���Դ
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
			
		}
		
	}

	//�������FileOutputStream����ļ�����ȡ�ֽ�/�ַ� + �ڵ�����
	public static void fileOutputStreamTest()
	{
		FileReader fis = null;//������������ȡ�ļ�
		FileWriter fos = null;//�����
		
		File source = new File("tt.txt");
		File sink = new File("new_tt.txt");

		char[] buffer = new char[32];//�ַ�����
		int len;//ʵ�ʶ�ȡ����

		try
		{
			fis = new FileReader(source);
			
			if ( ! sink.exists())
			{
				
				System.out.println( "created ? :"+sink.createNewFile());
			}

			fos = new FileWriter(sink);
			
			fos.write("ֱ�����ַ���д�� \r\n");//win�»��з���\r\n , Linux����\n
			while( (len = fis.read(buffer) ) != -1 )
			{
				fos.write(buffer,0,len);
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		finally
		{
			try
			{
				fis.close();
				fos.close();//���Զ�ִ��flush�������ѻ���ȫ��������ļ�
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}

	//������ ������printStream������������������� + ִ��Ч�ʸߣ�
	public static void printStreamTest()
	{
		FileOutputStream fos = null;
		PrintStream ps = null;//������װ����/������Ĵ�����PrintStream

		try
		{
			fos = new FileOutputStream("new_tt.txt");
			ps = new PrintStream( fos );
			ps.println("������ֱ�Ӹ�");//д���ַ����� �Զ����뻻�з�
			ps.println(new File("hh") );//Ҳ�ɴ������������toString�ַ���--����@��ϣ��
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		finally
		{
			ps.close(); //ֱ�ӹرն���ķ�װ�༴�ɣ����ɻ��Զ��ر�������
		}

	}

	//ר�Ŵ����ַ����� StringReader StringWriter
	public static void stringRWTest()
	{
		String src = "�������������Ҹ�����\n"
				+ "ι��������������\r\n"
				+ "�������𣬹�����ʳ���߲�\r\n"
				+ "����һ�����ӣ��泯�󺣣���ů����\r\n"
				+ "�������𣬸�ÿ������ͨ��\r\n"
				+ "���������ҵ��Ҹ�\n";
		int len = 0;
		char[] buffer = new char[32];
		
		System.out.println("===�ַ���������===");
		try( StringReader sr = new StringReader(src) )//��ǿ��try���
		{
			while ( (len = sr.read(buffer) ) != -1)
			{
				System.out.print(new String(buffer,0,len) );//��buffer�������
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		System.out.println("===�ַ��������===");
		try(StringWriter sw = new StringWriter() )//������StringWriterʵ�������ó���20��StringBuffer��Ϊ����ڵ�
		{
			sw.write(src);//write�������ַ���д���ַ��������
			System.out.println(sw.toString() );//����sw��toStringһ���������������
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	//ת�������ԣ������������System.in ��InputStreamʵ��������ͨ��InputStreamReaderת��Ϊ�ַ�������
	public static void transTest()
	{
		try(InputStreamReader isr = new InputStreamReader(System.in);
			//�ٷ�װΪ��������������
			BufferedReader br = new BufferedReader(isr))
		{
			String line = null;
			while ( (line = br.readLine() ) != null)//���ж�ȡ���Ի��з�Ϊ��־
			{
				if(line.equals("exit") )//�ַ����Ƚ��ַ��Ƿ�һ�£�Ҫ��equals��
				{
					System.exit(1);//�����˳���������
				}
				System.out.println(line);
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		

	}

	//�ƻ�����������ȡ��Ӧƥ���ַ���֮ǰ���ַ���
	public static void pushbackReaderTest()
	{
		String targetStr = "iam_target";
		File file = new File("tt.txt");
		String lastContent = null;
		char[] buffer = new char[32];
		int len = 0;
		try(PushbackReader pbr = new PushbackReader(new FileReader(file),64) )//�����ƻػ�������С?���ݺ�������ƻص��ַ���������1��
		{
			while ( (len = pbr.read(buffer)) != -1)//ÿ�ζ�ȡbuffer�ĳ��ȣ�32���ַ�
			{
				String content = new String(buffer,0,len);//��ȡ����ת��Ϊ�ַ���
				int index = 0;
				if ( (index = (lastContent + content).indexOf(targetStr)) !=-1)//�����ǰcontent��targetStr
				{
					//�����κ�֮ǰ���ַ���ͬʱ�ƻػ��������ַ���Ҫת���ַ����飩,�����ƻػ�������С���׳��쳣
					pbr.unread( (lastContent + content).toCharArray() );//��1���˴�lastContent ���� content��󳤶�Ϊ32+32 = 64
					//���buffer���Ȳ����������·���һ��index����buffer
					if (index > buffer.length)
					{
						buffer = new char[index];
					}
					//��ȡƥ���ַ���֮ǰ�����ݵ�������buffer
					pbr.read(buffer,0,index);

					//��ӡ�����˳�
					System.out.print( new String(buffer,0,index) );
					System.exit(0);
				}else{
					//��ӡ�ϴζ�ȡ����
					System.out.print(lastContent);
					//����lastContentΪ��ǰ
					lastContent = content;
				}
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	//�ض����׼�������
	public static void redirectTest()
	{
		/*
		//�ض����׼������ѴӴ�ӡ����Ļת�Ƶ�������У�
		File file_out = new File("new_tt.txt");
		try(PrintStream ps_out = new PrintStream(new FileOutputStream(file_out)))//����PrintStream�����
		{
			//����׼���System.out����Ļ����ָ���������
			System.setOut(ps_out);

			System.out.println("Can you see me>?");
			System.out.println("1 add 1 is ? "+(1+1) );
			
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		*/
		//�ض����׼���루�ѴӼ�������ת��Ϊ��������
		File file_in = new File("tt.txt");

		try(FileInputStream fis = (new FileInputStream(file_in)))
		{
			//�ض����׼����
			System.setIn(fis);
			Scanner sc = new Scanner(System.in);
			sc.useDelimiter("\n");//�û��з���Ϊ�ָ���

			while (sc.hasNext() )
			{
				System.out.println("��׼��������Ϊ��"+sc.next() );
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	//��ȡ�������̵������Ϣ--��ӦJava������������
	public static void processReadTest()
	{
		Process p = null;
		try
		{
			p = Runtime.getRuntime().exec("javac");//ִ��javac���򣬲���ȡ�����
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}

		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()))
			//��ȡjavac���̵Ĵ�����ͨ��ת������Ϊ�ַ�������װ��Ϊ������
			)
		{
			String line = null;
			while ( (line = br.readLine() ) != null)
			{
				System.out.println(line);
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	//����������д����Ϣ--��Java���������������
	public static void processWriteTest()
	{
		Process p = null;
/*	try
		{
			p = Runtime.getRuntime().exec("java ReadStandard");
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
*/
		try(PrintStream ps = new PrintStream( p.getOutputStream() )//�����׳�IOException������NullPointerException
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()) ) 
			)
		{
			ps.println("i am IOTest class, i will give you some message\n");
			ps.println("����IOTest�࣬����һ��С����Ϣ~");
			/*
			bw.write("i am IOTest class, i will give you some message\n");
			bw.write("����IOTest�࣬����һ��С����Ϣ~");
			*/
		}
		
	}

	//�����ȡ�ļ�ָ��λ��
	public static void randomReadTest()
	{
		String src = "�������������Ҹ�����\n"
				+ "ι��������������\r\n"
				+ "�������𣬹�����ʳ���߲�\r\n"
				+ "����һ�����ӣ��泯�󺣣���ů����\r\n"
				+ "�������𣬸�ÿ������ͨ��\r\n"
				+ "���������ҵ��Ҹ�\n";

		byte[] buffer = new byte[1024];
		int len = 0;
		File file = new File("random_access.txt");

		try( RandomAccessFile raf = new RandomAccessFile(file, "rws" ) )//����rw--�ɶ���д���ļ�û�оʹ���
		{
			raf.seek(23);//��¼ָ���ƶ���ָ��λ�ã�
			while ( (len = raf.read(buffer) ) != -1)
			{
				System.out.print(new String(buffer,0,len));
			}
			//raf.write(src.getBytes() );//д
			System.out.println("��ǰ��¼ָ���λ��"+ raf.getFilePointer() );
			raf.seek( raf.length() );//����¼ָ���ƶ����ļ�ĩβ����
			raf.write( "����׷�ӵ�content".getBytes() );
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	//**************************��������λ�ò������ݡ�******************************
	//˼·����¼ָ�뵽ָ��λ�ã���ȡָ�������ݵ���ʱ�ļ������������ݣ��ٰ���ʱ�洢�Ľ��ں���
	public static void insertTest()
	{
		byte[] buffer = new byte[1024];
		int len = 0;
		File file = new File("tt.txt");
		String insert_contet = "**************���ǲ��������**************";
		int pos = 24;//�������λ��
		
		File tempfile = null;

		try
		{
			//������ʱ�ļ�
			tempfile = File.createTempFile("temp",null);
			tempfile.deleteOnExit();//ɾ������
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		//����������ʶ�ȡ������ʱ���������
		try(RandomAccessFile raf = new RandomAccessFile(file,"rw");
			 FileOutputStream fos = new FileOutputStream(tempfile);
			 FileInputStream fis = new FileInputStream(tempfile) )
		{
			raf.seek(pos);//��¼ָ�벦����ָ��λ��
			//�洢֮������ݵ���ʱ�ļ�
			while ( (len = raf.read(buffer) ) != -1)//raf ��Դ�ļ��ж�
			{
				fos.write(buffer,0,len);//������������������������ʱ�ļ�
			}
			
			//��¼ָ������ݱ�����ϣ��Ϳ���Ϊ����Ϊ������Ӷ�����
			raf.seek(pos);
			raf.write( insert_contet.getBytes() );

			//Ȼ���ڵ�ǰ��¼ָ�������ϱ��ݵ�����
			while( (len = fis.read(buffer)) != -1)
			{
				raf.write(buffer,0,len);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	//���л�
	public static void writeObjTest()
	{
		Person p = new Person("jerry",32);
		File file = new File("tmp");
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)))
		{
			oos.writeObject(p);//�������л�~������д�������
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	//�����л�
	public static void readObjTest()
	{
		File src = new File("tmp");//���л����������
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(src)) )
		{
			//�����ݻָ���������Ҫ��Ӧ�࣬��������ClassNotFoundException�쳣
			Object p = ois.readObject();
			((Person)p).getInfo();
			/**/
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//************�������***************
	public static void main(String[] args) throws Exception
	{
		//fileTest();
		//filterTest();
		//fileInputStreamTest();
		//fileOutputStreamTest();
		//printStreamTest();
		//stringRWTest();
		//transTest();
		//pushbackReaderTest();
		//redirectTest();
		//processReadTest();
		//processWriteTest();
		//randomReadTest();
		//insertTest();
		//writeObjTest();

		//readObjTest();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tmp"));
		List list = (ArrayList)ois.readObject();
		System.out.println(list);
		
	}
}

//�����У��ұ��������ݵ�java����
//�ó�����Խ��ձ�׼���� + ����ı��ļ�
class ReadStandard
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		File file = new File("new_tt.txt");

		try(PrintStream ps = new PrintStream(new FileOutputStream(file)))
		{
			while (sc.hasNext() )
			{
				ps.println("��������Ϊ��"+ sc.next() );
			}
			
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}

//�������л��Ķ��� + �Զ������л������� ת���л��������󣬷����л�ʱ���������������
class Person implements Serializable
{
	private String name;
	private Integer age;

	public Person (String name,Integer age)
	{
		this.name = name;
		this.age = age;
	}

	public void getInfo()
	{
		System.out.println(name+" : "+age);
	}

	//�Զ������л�
	private void writeObject( java.io.ObjectOutputStream out) throws IOException
	{
		//���ַ����浽StringBuilder������ת����
		out.writeObject( new StringBuffer(this.name).reverse() );
		out.writeInt( this.age );
	}
	private void readObject(java.io.ObjectInputStream in) throws IOException,ClassNotFoundException
	{
		
		//��ȡ�Ĵ���Ҫ����д��Ĵ���
		this.name = ( (StringBuffer)in.readObject()).reverse().toString();
		this.age = in.readInt();
	}

	//ÿ�����л�ǰ���ø÷���
	public Object writeReplace() throws ObjectStreamException
	{
		List<Object> list = new ArrayList<>();
		list.add(name);
		list.add(age);
		return list;
	}
}