package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * description：
 *数据库连接池（使用前就已经创建好了若干个连接，每次请求都会从中借出去，也会换回来）
     构造方法约定了这个连接池一共有多少连接
     创建连接不能使用try-with-resource这种自动关闭连接的方式（因为要循环利用）
     连接为空，则wait等待，否则借出一条连接
     在归还完毕后，调用notifyAll，通知那些等待的线程，有新的连接可以借用了
 * @author Hartley
 * date： 2020/5/28
 */
public class ConnectionPool {
    private int size;
    List<Connection> list;
    public  ConnectionPool(int size){
        this.size = size;
        list = new ArrayList<>();
    }

    public void init(){

        try {
            Class.forName("com.mysql.jdbc.Driver");//初始化驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //加入指定大小的连接
        for (int i=0;i<size;i++){
            try {
                Connection con  = DriverManager.getConnection
                        ("jdbc:mysql://127.0.0.1/firstdb?characterEncoding", "root", "admin");
                list.add(con);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    //从池子中借连接,同步一下~
    public synchronized Connection getConnection(){
        while (list.isEmpty()){
            try { //如果池子没有连接了 则等着呗
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //有连接了，就从池中捞出来，送走
        Connection conn = list.remove(0);
        return conn;
    }

    //收到还回来的连接，并且叫醒睡着的线程，,同步一下~
    public synchronized void returnConnection(Connection conn){
        list.add(conn);
        this.notifyAll();
    }
}

//测试连接池
class ConnectionTest extends Thread {
    private ConnectionPool pool;
    public  ConnectionTest(String name, ConnectionPool pool){
        super(name); //设置线程名称
        this.pool = pool;
    }

    //实现run方法
    public void run(){
        //每个线程先申请获得连接，然后执行查询命令
        Connection conn = pool.getConnection();
        System.out.println(this.getName()+"\t从池中获得了数据库连接");

        try(Statement state = conn.createStatement()){
            this.sleep(1000);//让他睡1s
            state.execute("select * from hero");

        }
         catch (SQLException | InterruptedException throwables) {
            throwables.printStackTrace();
        }

        pool.returnConnection(conn);

    }

    //程序入口
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool(3);//声明池子大小
        pool.init();//创建三个连接的池子

        for (int i=0;i<20;i++){
            new ConnectionTest("线程"+i,pool).start();//创建线程并启动
        }
    }
}
