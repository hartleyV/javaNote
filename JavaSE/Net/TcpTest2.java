package Net;




import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 案例【2】：客户端向服务端传文件，服务端把文件保存到本地,存储成功后服务端向客户端发消息
 * 客户端：创建socket--网络传输的输出流、读取文件的输入流，关闭资源
 * 服务端：指定端口号创建socketServer--网络传输的输入流，写入文件的输出流，关闭资源
 * @author Hartley
 * @create 2020/5/18
 */
public class TcpTest2 {
    @Test
    public void find(){
        File file1 = new File(".");//当前路径在哪里，，原来是在总项目下
        System.out.println(file1.getAbsolutePath());
        String src = "Java语法练习/Collection/config.ini";
        File file = new File(src);
        System.out.println(file.exists());
    }
    @Test
    public void client(){
        int port = 8848;
        String src = "Java语法练习/Collection/config.ini";
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),port);
             OutputStream os = socket.getOutputStream();
             FileInputStream fis =
                     new FileInputStream(new File(src));
             //接受服务器传来的信息
             InputStream is = socket.getInputStream();
             ByteArrayOutputStream bos = new ByteArrayOutputStream()){

            byte[] buffer = new byte[1024];
            int len=-1;
            while ( (len = fis.read(buffer))!=-1){
                os.write(buffer,0,len);//写给传到服务端的输出流
            }
            //写完文件后，通知服务端停止接收
            socket.shutdownOutput();

            //接收服务端消息ing
            while ((len = is.read(buffer))!=-1){
                bos.write(buffer,0,len);//可以先把字节数据统统存到字节数组流当中
            }
            System.out.println(bos.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    @Test
    public void server(){
        String dst = "太难了.ini";
        try (ServerSocket ss = new ServerSocket(8848);
            Socket socket = ss.accept();
            InputStream is = socket.getInputStream();
            FileOutputStream fos = new FileOutputStream(new File(dst));
            //向客户端发消息
            OutputStream os = socket.getOutputStream()){

            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len=is.read(buffer))!=-1){
                fos.write(buffer,0,len);
                System.out.println(new String(buffer,0,len));
            }
            System.out.println("服务端存储完毕");

            os.write("成功把你扔过来的文件存好了，告诉你一声".getBytes());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
