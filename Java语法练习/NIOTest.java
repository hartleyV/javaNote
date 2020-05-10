import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.io.*;
import java.util.*;
/**
*NIO������鴦��--�����ڴ�ӳ���ļ��ķ�ʽ,�������ļ�������ӳ�䵽�ڴ棬��߶�дЧ��
         ����IO�������ζ�ȡ�ֽڵķ�ʽ��
    |--���Ķ���
	    |--��Channel��ͨ����������������
		      |--�ص㣺
			         ������ֱ�ӷ���channel���ݣ���ҪBuffer��Ϊ�м���
					 ����ָ�����ֻ���ȫ���ļ�ӳ�䵽Buffer
		      |--Channel�ӿڵ�ʵ����
			  	     FileChannel�ļ���FileInputStream/FileOutputStreamֻ�ܶ�/д��RandomAccessFile�ɶ�д-�����乹������mode������
			         Pipe.SinkChannel/Pipe.SourceChannel ���ڽ��̼�ͨ�ŵĹܵ�
					 ServerSocketChannel/SocketChannel ����TCP����ͨ�ŵ�ͨ��
					 DatagramChannel ����UDP����ͨ�ŵ�ͨ��
		      |--������ͨ����ͳ�ڵ��getChannel�������ض�Ӧͨ��
		      |--��Ҫ��������map����Channel���ֻ���������ӳ��ΪByteBuffer
			  							MappedByteBuffer map(FileChannel.MapMode-ֻ��-��д, position,size)
								   ��read/write������������ʽ������Buffer��д
	    |--��Buffer�����棺װ�����ݡ�������ݣ����ݵ�����������Ҫ�������棨���������飬����allocate�󲻿ɸı䣩
	          |--Buffer�ӿڵ�ʵ�����У���ByteBuffer��MappedByteBuffer��CharXX��
														��Short��Int��Long��Float��Double�����ã�
			      û�й����������ɶ�Ӧ������Ҫ����static XxxBuffer allocate(int capacity)--
				  --|ByteBuffer����allocateDirect��һ����дЧ�ʸߵ�ֱ��Buffer�������䴴���ɱ���
	          |--��Ҫ������0<=mark<=positionλ��(��дָ��)<=limit����<=capacity����
	          |--��Ҫ��������flip����׼����ȡ����ǰ������limit��Ϊ��ǰpositionλ�ã���position��0
			  					   ��clear����׼��װ������ǰ������position��0����limit��capacity
	          |--��put()��ʱpositionǰ�ƣ���get(index)����ȡ����λ������

*�ַ���CharSet�����������ַ����Ӧ��ϵ��
	    |--�÷��� ��ȡ�ַ��� Charset cn = Charset.forName(�ַ�������)
						��ȡ������CharsetEncoder encoder = cn.newEncoder();----encoder��encode����
										��ݲ������򵥱���ʱ��---ֱ�ӵ���Charset��encode����--�����ֽڻ���
						��ȡ������CharsetDecoder decoder = cn.newDecoder();---decoder��decode����
										�򵥽���ʱ------ֱ�ӵ���Charset��decode����--�����ַ�����
***�����
*�ļ���
*NIO2-Java7

*@author Hartley
*@version 1.0.0
*/

class  NIOTest
{
	//Buffer����
	public static void bufferTest()
	{
		//����CharBuffer
		CharBuffer buffer = CharBuffer.allocate(8);//���仺���СΪ8���ַ�
		//��ӡ��ǰ����״̬
		println("Position : "+buffer.position() );
		println("limit : "+buffer.limit() );
		println("Capacity : "+buffer.capacity() );

		//��buffer�������
		buffer.put('a');
		buffer.put('b');
		buffer.put('c');

		//�ٴδ�ӡ��
		println("=======���ԭʼ��=====");
		println("Position : "+buffer.position() );//Ϊ3��0��1��2λ�������ݣ�
		println("limit : "+buffer.limit() );

		//flip ����ȡ��״̬
		buffer.flip();
		//�ٴδ�ӡ��
		println("=======flip()��=====");
		println("Position : "+buffer.position() );
		println("limit : "+buffer.limit() );
		
		//���Զ�ȡget ȡ����һ��Ԫ��
		println("��һ��Ԫ��Ϊ��"+buffer.get(0) );
		println("Position : "+buffer.position() );

		//clear ����װ��״̬��ֻ�������ı䣬���ڲ�Ԫ��δ����
		buffer.clear();
		println("=======clear()��=====");
		println("Position : "+buffer.position() );
		println("limit : "+buffer.limit() );
		println("��һ��Ԫ����Ϊ��"+buffer.get(0) );
		println("ִ��get���Position : "+buffer.position() );//����
	}

	//channel����
	//�����������ͨ����������ͨ������map�������ز��ֻ�ȫ���ֽڻ���--�ļ�ӳ�䵽����
	//�����ͨ����write�������Խ�Byte����һ����д��
	public static void channelTest()
	{
		File file = new File("tt.txt");
		File file2 = new File("new.txt");
		try(FileChannel inChannel = new FileInputStream(file).getChannel();
			 FileChannel outChannel =new FileOutputStream(file2).getChannel() )
		{
			//�ö���ͨ�����ļ�����һ���Եķ��뻺���У���ֻ��
			MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY,0,file.length());
			//��buffer����ȫ�����
			outChannel.write(buffer);
			//����buffer����
			buffer.clear();

			//�ַ�������GBK
			Charset charset = Charset.forName("GBK");
			//�����������������ֽڱ�Ϊ�ַ�
			CharsetDecoder decoder = charset.newDecoder();
			CharBuffer charBuffer = decoder.decode(buffer);
			println(charBuffer);

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	//RandomAccessFile��FileChannel
	public static void randomTest()
	{
		File file = new File("tt.txt");
		try(RandomAccessFile raf = new RandomAccessFile( file, "rw" );//ע����ʲ�����Ϊ�ַ���
			 FileChannel channel = raf.getChannel() )//RandomAccessFile���ļ�ͨ���ɶ���д
		{
			//�˴�channelӳ���൱��������ͨ�������ݶ�ȡ��ByteBuffer��
			ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY,0,file.length() );
			//��Channel�м�¼ָ���Ƶ�ĩβ���˴�Channel�൱�������--�����Ա����ļ�����д��
			channel.position(file.length());
			channel.write(buffer);//һ����д���ļ�ĩβ
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//һ���Զ�ȡ--map--�ļ�����ӳ�䵽�ڴ������������½����ɲ��á�Channel + Buffer��
	public static void readFileTest()
	{
		File file = new File("tt.txt");

		try(FileInputStream fis = new FileInputStream(file);//�ļ�������
			 FileChannel inChannel = fis.getChannel() )//ֻ���������ͨ��)
		{
			//�����ظ�ȡˮ��������ByteBuffer��ͨ����̬����
			ByteBuffer buffer = ByteBuffer.allocate(256);
			//ÿ�δ�����ͨ����buffer���������
			while ( (inChannel.read(buffer)) != -1)
			{
				buffer.flip();//limitסbuffer��û�����ݵĿհײ���

				Charset charset = Charset.forName("GBK");
				CharsetDecoder decoder = charset.newDecoder();
				CharBuffer charBuffer = decoder.decode(buffer);//��ָ���ַ������ֽڻ���תΪ�ַ�����

				println(charBuffer);
				buffer.clear();//�ָ�buffer����װ��״̬
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	//�ַ���
	public static void charsetTest()
	{
		 /*
		 //���ϵͳ֧�ֵ��ַ���
		 SortedMap<String,Charset> map = Charset.availableCharsets();
		 for (String alias : map.keySet() )
		 {
			 println(alias+" -> "+map.get(alias));
		 }
		*/

		Charset cn = Charset.forName("GBK");
		CharsetEncoder encoder = cn.newEncoder();
		CharsetDecoder decoder = cn.newDecoder();
		//CharBuffer����
		CharBuffer cb = CharBuffer.allocate(10);
		cb.put("��");
		cb.put("��");
		cb.put("��");
		cb.put("��");
		cb.flip();//��ȡǰ��Ҫ����limitָ�룡��
		try
		{
			//����
			ByteBuffer bb = encoder.encode(cb);
			println( bb.capacity() );
			for (int i=0 ;i<bb.capacity() ;i++ )//��Ϊ����A������Խ�磿��
			{
				System.out.print(bb.get(i) + " ");
			}
			println("");
			println("��������ϢΪ��"+decoder.decode(bb));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	//************�������***************
	public static void main(String[] args) 
	{
		//bufferTest();
		//channelTest();
		 //randomTest();
		 //readFileTest();
		charsetTest();
		
	}

	//��ӡ
	public static void println(Object obj)
	{
		System.out.println(obj);
	}
}
