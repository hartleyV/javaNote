import java.net.InetAddress;
/**
*������

Socket: IP + port��16λ������

IP�ࣺInetAddress

*@author Hartley
*@version 1.0.0
*/

class  NetTest
{
	//IP�����
	public static void inetAddressTest() throws Exception
	{
		String baiduHost = "www.baidu.com";
		InetAddress ip = InetAddress.getByName(baiduHost);
		System.out.println(baiduHost+"'s ip is "+ip);

		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println(localIp);//���ر�������  �� ������/������ַ
	
		String localName = localIp.getHostName();
		System.out.println("��������"+localName);

		String localAddress = localIp.getHostAddress();
		System.out.println("������ַ��"+localAddress);
	}
	//************�������***************
	public static void main(String[] args) throws Exception
	{
		inetAddressTest();
	}
}
