package Net;


import org.junit.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Udp案例：发送，接收
 * @author Hartley
 * @create 2020/5/19
 */
public class UdpTest {
    @Test
    public void sender() throws UnknownHostException {
        String content = "我是通过Udp方式过来的-……-";
        byte[] data = content.getBytes();
        InetAddress ip = InetAddress.getLocalHost();
        int port = 8848;
        try (//ip、port在package上声明
             DatagramSocket ds = new DatagramSocket();){

            DatagramPacket packet = new DatagramPacket(data,0,data.length,ip, port);
            ds.send(packet);
        }
        catch(Exception e ){
            e.printStackTrace();
        }
    }

    @Test
    public void receiver(){
        int port = 8848;
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data,0,data.length);

        try (DatagramSocket socket = new DatagramSocket(port);){

            socket.receive(packet);
            System.out.println(new String(packet.getData(),0,packet.getLength()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
