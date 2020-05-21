package Net;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLOutput;

/**
*网络编程
Socket: IP + port（16位整数）
IP类：InetAddress

 案例【1】：客户端发送数据给服务端

*@author Hartley
*@version 1.0.0
*/

class  NetTest
{
	//客户端
	@Test
	public void client() throws UnknownHostException {
		//指定对方ip===本地的
		InetAddress inet = InetAddress.getByName("127.0.0.1");
		try(Socket socket = new Socket(inet,8848);//获取套字节
			//通过socket获取输出流
			OutputStream os = socket.getOutputStream()) {

			os.write("小哥哥你好，我是赛丽亚，打钱~".getBytes());//向服务端传输数据

		}catch (IOException e)
		{
			e.printStackTrace();
		}

		/*
		os.close();
		socket.close();//关闭资源
		*/
	}
	//服务端
	@Test
	public void server() throws IOException {
		ServerSocket ss = new ServerSocket(8848);//1】创建服务端的socket并指定端口

		try(Socket socket = ss.accept();//2】accept方法从客户端接收到socket对象
			InputStream is = socket.getInputStream();//3】获取输入流
			Reader r = new InputStreamReader(is)) {

			//byte[] buffer = new byte[1024];//字节流，当汉字过多有可能乱码
			char[] buffer = new char[32];
			int len = -1;
			while ( (len = r.read(buffer)) != -1)//4】读取数据、关闭资源
			{
				System.out.println(new String(buffer,0,len));
			}

			System.out.println("this message is from : "+socket.getInetAddress().getHostAddress());
		}catch (Exception e){
			e.printStackTrace();
		}


	}
	//IP类测试
	public static void inetAddressTest() throws Exception
	{
		String baiduHost = "www.baidu.com";
		InetAddress ip = InetAddress.getByName(baiduHost);
		System.out.println(baiduHost+"'s ip is "+ip);

		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println(localIp);//返回本地主机  ： 主机名/主机地址
	
		String localName = localIp.getHostName();
		System.out.println("主机名："+localName);

		String localAddress = localIp.getHostAddress();
		System.out.println("主机地址："+localAddress);
	}
	//************程序入口***************
	public static void main(String[] args) throws Exception
	{
		inetAddressTest();
	}
}
