import java.net.InetAddress;
/**
*网络编程

Socket: IP + port（16位整数）

IP类：InetAddress

*@author Hartley
*@version 1.0.0
*/

class  NetTest
{
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
