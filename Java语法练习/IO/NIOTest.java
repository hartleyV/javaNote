import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.io.*;
import java.util.*;
/**
*NIO-java4：面向块处理--采用内存映射文件的方式,将部分文件的区域映射到内存，提高读写效率
         （旧IO采用依次读取字节的方式）
    |--核心对象：
	    |--【Channel】通道：类比输入输出流
		      |--特点：
			         程序不能直接访问channel数据，需要Buffer作为中间商
					 可以指定部分或者全部文件映射到Buffer
		      |--Channel接口的实现类
			  	     FileChannel文件（FileInputStream/FileOutputStream只能读/写，RandomAccessFile可读写-根据其构造器的mode参数）
			         Pipe.SinkChannel/Pipe.SourceChannel 用于进程间通信的管道
					 ServerSocketChannel/SocketChannel 用于TCP网络通信的通道
					 DatagramChannel 用于UDP网络通信的通道
		      |--创建：通过传统节点的getChannel方法返回对应通道
		      |--重要方法：【map】将Channel部分或所有数据映射为ByteBuffer
			  							MappedByteBuffer map(FileChannel.MapMode-只读-读写, position,size)
								   【read/write】及其重载形式用于向Buffer读写
	    |--【Buffer】缓存：装入数据、输出数据，数据的输入和输出都要经过缓存（本质是数组，容量allocate后不可改变）
	          |--Buffer接口的实现类有：【ByteBuffer、MappedByteBuffer、CharXX】
														（Short、Int、Long、Float、Double不常用）
			      没有构造器，生成对应对象需要调用static XxxBuffer allocate(int capacity)--
				  --|ByteBuffer还可allocateDirect出一个读写效率高的直接Buffer，但是其创建成本大
	          |--重要索引：0<=mark<=position位置(读写指针)<=limit界限<=capacity容量
	          |--重要方法：【flip】（准备读取数据前）：将limit置为当前position位置，将position置0
			  					   【clear】（准备装入数据前）：将position置0，将limit置capacity
	          |--【put()】时position前移；【get(index)】获取索引位置数据

*字符集CharSet（二进制与字符间对应关系）
	    |--用法： 获取字符集 Charset cn = Charset.forName(字符集别名)
						获取编码器CharsetEncoder encoder = cn.newEncoder();----encoder的encode方法
										便捷操作（简单编码时）---直接调用Charset的encode方法--返回字节缓存
						获取解码器CharsetDecoder decoder = cn.newDecoder();---decoder的decode方法
										简单解码时------直接调用Charset的decode方法--返回字符缓存

*文件锁
	    |--用于阻止多个并发进程同时修改一个文件
	    |--用法：Channel的lock(position,size,boolean shared)，从文件position开始的内容加锁-阻塞式（shared-true表示允许其他进程读，false表示排他锁，不许其他进程读写）
		               trylock(同上)，但非阻塞，--成功则返回文件锁--不成则返回null
	    |--注意：有些平台--文件锁只是建议性的 + 不能同步锁定一个文件到内存中+关闭某个FileChannel后会释放该文件所有锁
		               文件锁时Java虚拟机持有的，所以同一虚拟机上不能有两个程序对同一文件上锁

***待填坑******************
*NIO2-Java7
	    |--

*@author Hartley
*@version 1.0.0
*/

class  NIOTest
{
	//Buffer操作
	public static void bufferTest()
	{
		//创建CharBuffer
		CharBuffer buffer = CharBuffer.allocate(8);//分配缓存大小为8个字符
		//打印当前索引状态
		println("Position : "+buffer.position() );
		println("limit : "+buffer.limit() );
		println("Capacity : "+buffer.capacity() );

		//向buffer添加数据
		buffer.put('a');
		buffer.put('b');
		buffer.put('c');

		//再次打印：
		println("=======添加原始后=====");
		println("Position : "+buffer.position() );//为3（0、1、2位置有数据）
		println("limit : "+buffer.limit() );

		//flip 到待取出状态
		buffer.flip();
		//再次打印：
		println("=======flip()后=====");
		println("Position : "+buffer.position() );
		println("limit : "+buffer.limit() );
		
		//绝对读取get 取出第一个元素
		println("第一个元素为："+buffer.get(0) );
		println("Position : "+buffer.position() );

		//clear 到待装入状态（只是索引改变，其内部元素未动）
		buffer.clear();
		println("=======clear()后=====");
		println("Position : "+buffer.position() );
		println("limit : "+buffer.limit() );
		println("第一个元素仍为："+buffer.get(0) );
		println("执行get后的Position : "+buffer.position() );//不变
	}

	//channel操作
	//创建输入输出通道，用输入通道调用map方法返回部分或全部字节缓存--文件映射到缓存
	//用输出通道的write方法可以将Byte缓存一次性写出
	public static void channelTest()
	{
		File file = new File("tt.txt");
		File file2 = new File("new.txt");
		try(FileChannel inChannel = new FileInputStream(file).getChannel();
			 FileChannel outChannel =new FileOutputStream(file2).getChannel() )
		{
			//用读入通道将文件内容一股脑的放入缓存中，，只读
			MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY,0,file.length());
			//将buffer数据全部输出
			outChannel.write(buffer);
			//重置buffer索引
			buffer.clear();

			//字符集采用GBK
			Charset charset = Charset.forName("GBK");
			//创建解码器，并将字节变为字符
			CharsetDecoder decoder = charset.newDecoder();
			CharBuffer charBuffer = decoder.decode(buffer);
			println(charBuffer);

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	//RandomAccessFile的FileChannel
	public static void randomTest()
	{
		File file = new File("tt.txt");

		try(RandomAccessFile raf = new RandomAccessFile( file, "rw" );//注意访问参数须为字符串
			 FileChannel channel = raf.getChannel() )//RandomAccessFile的文件通道可读可写
		{
			//此处channel映射相当于输入流通道的数据读取到ByteBuffer中
			ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY,0,file.length() );
			//把Channel中记录指针移到末尾（此处Channel相当于输出流--），以便在文件后面写入
			channel.position(file.length());
			channel.write(buffer);//一次性写到文件末尾
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//一次性读取--map--文件过大映射到内存可能造成性能下降，可采用【Channel + Buffer】
	public static void readFileTest()
	{
		File file = new File("tt.txt");

		try(FileInputStream fis = new FileInputStream(file);//文件输入流
			 FileChannel inChannel = fis.getChannel() )//只可以输入的通道)
		{
			//定义重复取水的容器，ByteBuffer，通过静态方法
			ByteBuffer buffer = ByteBuffer.allocate(256);
			//每次从输入通道向buffer中输出数据
			while ( (inChannel.read(buffer)) != -1)
			{
				buffer.flip();//limit住buffer中没有数据的空白部分

				Charset charset = Charset.forName("GBK");
				CharsetDecoder decoder = charset.newDecoder();
				CharBuffer charBuffer = decoder.decode(buffer);//用指定字符集将字节缓存转为字符缓存

				println(charBuffer);
				buffer.clear();//恢复buffer到待装入状态
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	//字符集
	public static void charsetTest()
	{
		 /*
		 //输出系统支持的字符集
		 SortedMap<String,Charset> map = Charset.availableCharsets();
		 for (String alias : map.keySet() )
		 {
			 println(alias+" -> "+map.get(alias));
		 }
		*/

		Charset cn = Charset.forName("GBK");
		CharsetEncoder encoder = cn.newEncoder();
		CharsetDecoder decoder = cn.newDecoder();
		//CharBuffer对象
		CharBuffer cb = CharBuffer.allocate(10);
		cb.put("多");
		cb.put("拉");
		cb.put("啊");
		cb.put("梦");
		cb.flip();//读取前需要设置limit指针！！
		try
		{
			//编码
			ByteBuffer bb = encoder.encode(cb);
			println( bb.capacity() );
			for (int i=0 ;i<bb.capacity() ;i++ )//若为哆啦A梦数组越界？？
			{
				System.out.print(bb.get(i) + " ");
			}
			println("");
			println("解码后的信息为："+decoder.decode(bb));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	//文件锁尝试
	public static void fileLockTest()
	{
		File file = new File("new.txt");
		try(//用节点流获取Channel对象？？？要用OutputStream//true是追加 false或不写是覆盖
			 FileChannel channel = new FileOutputStream(file,true).getChannel() )
		{
				
			println(channel.size());
			FileLock lock = channel.tryLock();//非阻塞式
			Thread.sleep(10000);
			lock.release();
			 /**/
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	//************程序入口***************
	public static void main(String[] args) 
	{
		//bufferTest();
		//channelTest();
		 //randomTest();
		 //readFileTest();
		//charsetTest();
		fileLockTest();
		
	}

	//打印
	public static void println(Object obj)
	{
		System.out.println(obj);
	}
}
