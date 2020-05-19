package Net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Url类的使用，创建url对象，打开url连接，获取输入流，，，disconnect
 * @author Hartley
 * @Create 2020/5/19
 */
public class UrlTest {
    public static void main(String[] args) throws IOException {
        String url_path = "https://b-ssl.duitang.com/uploads/item/201804/29/20180429220230_ueyya.thumb.700_0.jpeg";
                //          协议名         主机名        文件路径
        URL url = new URL(url_path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();//打开连接

        try (InputStream is = connection.getInputStream();
             FileOutputStream fos = new FileOutputStream(new File("dili.jpg"))){

            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = is.read(buf))!=-1){
                fos.write(buf,0,len);
            }

            System.out.println("下载完成！");
            connection.disconnect();//关闭连接


        }
    }
}
