import java.io.*;
import java.util.*;
/**
*File 类
*IO流
       |--优点：缓存提高效率，操作便捷，一次性处理大批量内容
       |--划分输入输出流是根据程序所在内存考虑的
       |--字节流8为（基类InputStream OutputStream);字符流16位（基类Reader Writer）
       |--分类：节点流（程序直接连接数据源）处理流/包装流（通过封装后的流来实现数据的读写）
       |--实现类：
	            |--访问文件FileXX，数组ByteArrayXX CharArrayXX，管道（进程间通信）PipedXX
	            |--打印流PrintWriter（继承Writer）PrintStream（继承OutputStream）**区别？？*
	            |--访问字符串StringW/R，缓冲流(提高效率，需要flush）BufferedXX
	            |--对象流（对象序列化）ObjectI/O
	            |--转换流（字节转字符）InputStreamReader OutputStreamWriter
	            |--推回输入流 PushbackInputStream PushbackReader，用unread存到推回缓冲区后，
				    read会先从推回缓冲区去取，取完再从原输入流中取
	   |--System方法：
			    |--重定向：setIn(InputStream in) setOut(OutputStream out)重定向标准输入输出（以Java程序为中心），
				                  setErr(PrintStream err)重定向标准错误输出流
*读取其他进程数据（以Java程序为中心，分析是输入还是输出）：getInputStream/getOutputStream/getErrorStream
*【RandomAccessFile】任意访问文件（只能是文件哝，IO流不行)
	   |--可读可写（只能是文件哝，IO流不行)
	   |--只读取文件中指定的某一部分时 选他选他~
	   |--可以自由移动文件记录指针 long getFilePointer返回指针当前位置，seek(long pos)移到指定位置
	   |--两个构造器：
			    |--参数一：String / File指定文件本身；
			    |--参数二：mode参数== r-只读，rw读写（没有文件则自动创建），
				                  rws（文件每个内容、元数据同步更新到底层存储），
								  rwd（文件每个内容同步跟新到底层数据）
*对象【序列化】
	   |--用途：把对象序列化为字节文件，通过反序列化恢复为对象，使对象脱离程序运行而独立出来
	   |--需要注意：
	             哪些会被序列化：对象类名、实例变量（方法、类变量、transient变量不会被序列化）
				 反序列化对象：需要原来对象的class文件
				                         ---通过private static final long serialVersionUID确保不同版本class的兼容性
				 读取序列化对象：需要按写入顺序读取
	   |--用法：
	   	    |--【1】需要先使要序列化的类实现Serializable或Externalizable接口（只是一个标记）-- 每个JavaBean都要实现Serializable
	   	    |--【2】创建ObjectOutputStream处理流 对象（需要建立在节点流之上：构造器要传入节点流）
	   	    |--【3】调用ObjectOutputStream对象的方法 writeObject(对象)  将对象写入到输出流
	   	    |--不想被序列化的实例变量：
			   	    |--	用【transient】修饰	 （对应成员变量在序列化时会被隔离）  	    
			   	    |--	自定义序列化 	    
			   	         |-- 【1】Serializable-在待序列化类中添加以下方法	    
			   	                 |-- private void writeObject(java.io.ObjectOutputStream out) throws IOException
								      控制写入哪些实例变量，如何加密，如何写入（无此方法默认会调用out.defaultWriterObject
			   	                 |-- private void readObject(java.io.ObjectInputStream in) throws IOException,ClassNotFoundException
								      执行与上述相反的操作
								 |-- private void readObjectNoData() throws ObjectStreamException
								      当序列流不完整时（版本差异，被篡改），会调用它来初始化反序列的对象
								 |-- private Object readResolve() throws ObjectStreamException
								      在readObject方法后执行，返回值会替换原来的反序列化对象，
									  对序列化单例类、枚举类超级有用（以免创建新的其他对象）
			   	         |-- 【2】Externalizable-  程序员决定存储哪些信息 ，性能略好，但编程复杂	
						 
			|--待序列化对象的直接或间接父类要么有无参构造器，要么实现序列化接口，否则InvalidClassException
			    且当父类不可序列、有无参构造器时，父类中的成员变量值不会序列化到二进制文件中
				且当成员变量有引用类型时，其引用类需要也是可序列化的，否则该类无法序列化
	   	    |--多次序列化同一个对象，只有第一次writeObject才会将对象序列化字节序列并输出，
				后面再序列化该对象，只是输出一个序列化编号（所以第一次之后无论对象实例变量如何变化，实例变量也不会被输出）
*对象【反序列化】
	   |--特点：无需构造器初始化对象，多个序列化对象在读取时需按顺序
	                 
	   |--用法：
	   	    |--【1】创建ObjectInputStream对象
	   	    |--【2】调用readObject方法，返回Object类型对象，需要把他强行转换为真实类型
			    由数据恢复到对象还需要对应类，否则引发ClassNotFoundException异常
*NIO新的IO类
*@author Hartley
*@version 1.0.0
*/

class  IOTest
{
	//File类的基本操作
	public static void fileTest()
	{
		File file = new File(".");//以当前路径创建文件对象,（ .表示当前目录）注：绝对路径时要 \\ 或者 /
		System.out.println(file.getName() + " 对象是否存在："+file.exists());//返回true
		System.out.println(file.getName());
		System.out.println(file.getParent());//获取相对父路径（会出错返回null）
		System.out.println(file.getAbsoluteFile().getParent());//获取上一级路径（相对父路径）
		System.out.println(file.getAbsoluteFile());//获取绝对路径
		
		File newFile = new File(System.currentTimeMillis()+".lhs");
		
		try
		{
			newFile.createNewFile();//以file对象创建文件
			System.out.println(newFile.mkdir());//创建目录，前面创建文件了，所以返回false

			System.out.println("==当前路径下所有文件和路径==");
			String[] fileLists = file.list();//当前路径下所有文件和路径
			for (String list : fileLists )//遍历
			{
				System.out.println(list);
			}
			
			System.out.println("==所有磁盘根路径==");
			File[] roots = File.listRoots();
			for (File root : roots )
			{
				System.out.println(root);
			}


			File tmpFile = File.createTempFile("temp",null);//创建临时文件,名字为temp，null对应默认后缀tmp，在file路径下
			tmpFile.deleteOnExit();//做个删除钩子，退出JVM后临时文件自动删除

			
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		
	}

	//File过滤器-函数接口FileNameFilter里***accept(File dir,String name)***	
	public static void filterTest()
	{
		File file = new File(".");//当前目录
		//列出当前目录下 .java文件，以及子目录
		String[] lists = file.list( (dir,name)->name.endsWith(".java") || new File(name).isDirectory() );
		for (String list : lists )
		{
			System.out.println(list);
		}
	}

	//用读取文件的输入流FileInputStream读取文件（读取字节/字符 + 节点流）
	public static void fileInputStreamTest()
	{
		FileInputStream fis = null;
		byte[] buffer = new byte[1024];//1024个字节==1KB
		/*FileReader fis = null;
		char[] buffer = new char[1024];//1024个字符 == 2KB
		*/
		int len=0;//实际读取的长度

		try
		{
			 fis = new FileInputStream("tt.txt");//字节
				//fis = new FileReader("tt.txt");//字符
			//循环用一排1024长度的竹筒取水，每个竹筒放1个字节，取到没有水了返回-1
			while( (len = fis.read(buffer) ) != -1)
		{
			System.out.println( new String(buffer,0,len));//len是转换成字符串的长度
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
				fis.close();//关闭资源
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
			
		}
		
	}

	//用输出流FileOutputStream输出文件（读取字节/字符 + 节点流）
	public static void fileOutputStreamTest()
	{
		FileReader fis = null;//创建输入流读取文件
		FileWriter fos = null;//输出流
		
		File source = new File("tt.txt");
		File sink = new File("new_tt.txt");

		char[] buffer = new char[32];//字符缓存
		int len;//实际读取长度

		try
		{
			fis = new FileReader(source);
			
			if ( ! sink.exists())
			{
				
				System.out.println( "created ? :"+sink.createNewFile());
			}

			fos = new FileWriter(sink);
			
			fos.write("直接用字符串写的 \r\n");//win下换行符是\r\n , Linux下是\n
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
				fos.close();//会自动执行flush方法，把缓存全部输出到文件
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}

	//简化流程 处理流printStream（处理输入输出超级简单 + 执行效率高）
	public static void printStreamTest()
	{
		FileOutputStream fos = null;
		PrintStream ps = null;//用来包装输入/输出流的处理流PrintStream

		try
		{
			fos = new FileOutputStream("new_tt.txt");
			ps = new PrintStream( fos );
			ps.println("处理流直接搞");//写入字符串后 自动加入换行符
			ps.println(new File("hh") );//也可传对象，输出的是toString字符串--类名@哈希？
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		finally
		{
			ps.close(); //直接关闭顶层的封装类即可，即可会自动关闭其他流
		}

	}

	//专门处理字符串的 StringReader StringWriter
	public static void stringRWTest()
	{
		String src = "从明天起做个幸福的人\n"
				+ "喂马，劈柴，周游世界\r\n"
				+ "从明天起，关心粮食和蔬菜\r\n"
				+ "我有一所房子，面朝大海，春暖花开\r\n"
				+ "从明天起，跟每个亲人通信\r\n"
				+ "告诉他们我的幸福\n";
		int len = 0;
		char[] buffer = new char[32];
		
		System.out.println("===字符串输入流===");
		try( StringReader sr = new StringReader(src) )//增强版try语句
		{
			while ( (len = sr.read(buffer) ) != -1)
			{
				System.out.print(new String(buffer,0,len) );//用buffer数组读出
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		System.out.println("===字符串输出流===");
		try(StringWriter sw = new StringWriter() )//创建的StringWriter实际上是用长度20的StringBuffer作为输出节点
		{
			sw.write(src);//write方法将字符串写入字符串输出流
			System.out.println(sw.toString() );//调用sw的toString一次性输出其中内容
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	//转换流测试，比如键盘输入System.in 是InputStream实例，可以通过InputStreamReader转换为字符输入流
	public static void transTest()
	{
		try(InputStreamReader isr = new InputStreamReader(System.in);
			//再封装为缓存类型输入流
			BufferedReader br = new BufferedReader(isr))
		{
			String line = null;
			while ( (line = br.readLine() ) != null)//逐行读取，以换行符为标志
			{
				if(line.equals("exit") )//字符串比较字符是否一致，要用equals！
				{
					System.exit(1);//声明退出程序条件
				}
				System.out.println(line);
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		

	}

	//推回输入流，读取对应匹配字符串之前的字符串
	public static void pushbackReaderTest()
	{
		String targetStr = "iam_target";
		File file = new File("tt.txt");
		String lastContent = null;
		char[] buffer = new char[32];
		int len = 0;
		try(PushbackReader pbr = new PushbackReader(new FileReader(file),64) )//定义推回缓冲区大小?根据后面最大推回的字符数，见【1】
		{
			while ( (len = pbr.read(buffer)) != -1)//每次读取buffer的长度：32个字符
			{
				String content = new String(buffer,0,len);//读取到的转换为字符串
				int index = 0;
				if ( (index = (lastContent + content).indexOf(targetStr)) !=-1)//如果当前content含targetStr
				{
					//将本次和之前的字符串同时推回缓冲区（字符串要转成字符数组）,超出推回缓冲区大小会抛出异常
					pbr.unread( (lastContent + content).toCharArray() );//【1】此处lastContent 加上 content最大长度为32+32 = 64
					//如果buffer长度不够，则重新分配一个index长的buffer
					if (index > buffer.length)
					{
						buffer = new char[index];
					}
					//读取匹配字符串之前的内容到缓存区buffer
					pbr.read(buffer,0,index);

					//打印，，退出
					System.out.print( new String(buffer,0,index) );
					System.exit(0);
				}else{
					//打印上次读取内容
					System.out.print(lastContent);
					//更新lastContent为当前
					lastContent = content;
				}
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	//重定向标准输入输出
	public static void redirectTest()
	{
		/*
		//重定向标准输出（把从打印到屏幕转移到输出流中）
		File file_out = new File("new_tt.txt");
		try(PrintStream ps_out = new PrintStream(new FileOutputStream(file_out)))//创建PrintStream输出流
		{
			//将标准输出System.out从屏幕，到指定的输出流
			System.setOut(ps_out);

			System.out.println("Can you see me>?");
			System.out.println("1 add 1 is ? "+(1+1) );
			
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		*/
		//重定向标准输入（把从键盘输入转移为输入流）
		File file_in = new File("tt.txt");

		try(FileInputStream fis = (new FileInputStream(file_in)))
		{
			//重定向标准输入
			System.setIn(fis);
			Scanner sc = new Scanner(System.in);
			sc.useDelimiter("\n");//用换行符作为分隔符

			while (sc.hasNext() )
			{
				System.out.println("标准输入内容为："+sc.next() );
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	//读取其他进程的输出信息--对应Java程序是输入流
	public static void processReadTest()
	{
		Process p = null;
		try
		{
			p = Runtime.getRuntime().exec("javac");//执行javac程序，并获取其进程
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}

		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()))
			//获取javac进程的错误流通过转换流变为字符流，并装饰为缓存流
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

	//向其他程序写入信息--对Java程序来讲是输出流
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
		try(PrintStream ps = new PrintStream( p.getOutputStream() )//不会抛出IOException，会有NullPointerException
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()) ) 
			)
		{
			ps.println("i am IOTest class, i will give you some message\n");
			ps.println("我是IOTest类，给你一点小道消息~");
			/*
			bw.write("i am IOTest class, i will give you some message\n");
			bw.write("我是IOTest类，给你一点小道消息~");
			*/
		}
		
	}

	//任意读取文件指定位置
	public static void randomReadTest()
	{
		String src = "从明天起做个幸福的人\n"
				+ "喂马，劈柴，周游世界\r\n"
				+ "从明天起，关心粮食和蔬菜\r\n"
				+ "我有一所房子，面朝大海，春暖花开\r\n"
				+ "从明天起，跟每个亲人通信\r\n"
				+ "告诉他们我的幸福\n";

		byte[] buffer = new byte[1024];
		int len = 0;
		File file = new File("random_access.txt");

		try( RandomAccessFile raf = new RandomAccessFile(file, "rws" ) )//参数rw--可读可写，文件没有就创建
		{
			raf.seek(23);//记录指针移动到指定位置；
			while ( (len = raf.read(buffer) ) != -1)
			{
				System.out.print(new String(buffer,0,len));
			}
			//raf.write(src.getBytes() );//写
			System.out.println("当前记录指针的位置"+ raf.getFilePointer() );
			raf.seek( raf.length() );//将记录指针移动到文件末尾！！
			raf.write( "这是追加的content".getBytes() );
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	//**************************【从任意位置插入数据】******************************
	//思路：记录指针到指定位置，读取指针后的内容到临时文件，填充插入数据，再把临时存储的接在后面
	public static void insertTest()
	{
		byte[] buffer = new byte[1024];
		int len = 0;
		File file = new File("tt.txt");
		String insert_contet = "**************我是插入的内容**************";
		int pos = 24;//待插入的位置
		
		File tempfile = null;

		try
		{
			//创建临时文件
			tempfile = File.createTempFile("temp",null);
			tempfile.deleteOnExit();//删除钩子
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		//创建任意访问读取流、临时输入输出流
		try(RandomAccessFile raf = new RandomAccessFile(file,"rw");
			 FileOutputStream fos = new FileOutputStream(tempfile);
			 FileInputStream fis = new FileInputStream(tempfile) )
		{
			raf.seek(pos);//记录指针拨动到指定位置
			//存储之后的内容到临时文件
			while ( (len = raf.read(buffer) ) != -1)//raf 从源文件中读
			{
				fos.write(buffer,0,len);//输出流将读到的内容输出到临时文件
			}
			
			//记录指针后内容备份完毕，就可以为所欲为的往后加东西了
			raf.seek(pos);
			raf.write( insert_contet.getBytes() );

			//然后在当前记录指针后面加上备份的内容
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

	//序列化
	public static void writeObjTest()
	{
		Person p = new Person("jerry",32);
		File file = new File("tmp");
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)))
		{
			oos.writeObject(p);//对象序列化~将对象写入输出流
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	//反序列化
	public static void readObjTest()
	{
		File src = new File("tmp");//序列化对象的数据
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(src)) )
		{
			//由数据恢复到对象还需要对应类，否则引发ClassNotFoundException异常
			Object p = ois.readObject();
			((Person)p).getInfo();
			/**/
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//************程序入口***************
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

//被运行，且被输入数据的java程序
//该程序可以接收标准输入 + 输出文本文件
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
				ps.println("键入内容为："+ sc.next() );
			}
			
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}

//用于序列化的东东 + 自定义序列化（还可 转序列化其他对象，反序列化时返回这个其他对象）
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

	//自定义序列化
	private void writeObject( java.io.ObjectOutputStream out) throws IOException
	{
		//将字符串存到StringBuilder，并反转加密
		out.writeObject( new StringBuffer(this.name).reverse() );
		out.writeInt( this.age );
	}
	private void readObject(java.io.ObjectInputStream in) throws IOException,ClassNotFoundException
	{
		
		//读取的次序要按照写入的次序
		this.name = ( (StringBuffer)in.readObject()).reverse().toString();
		this.age = in.readInt();
	}

	//每次序列化前调用该方法
	public Object writeReplace() throws ObjectStreamException
	{
		List<Object> list = new ArrayList<>();
		list.add(name);
		list.add(age);
		return list;
	}
}