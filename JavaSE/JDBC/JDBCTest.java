package JDBC;
import org.junit.Test;

import java.sql.*;
import java.util.Scanner;

/**
 * description：JDBC练习
    1连接数据库，获得statement对象，执行sql语句（每执行一次都要对sql语句进行编译，传到数据端）
    2增删改查（注意查询时，ResultSet索引是从1开始的！）
    3案例：输入的账号密码，从数据表中查询是否有相关记录
    4【PreparedStatement】相比Statement，可以预编译sql语句，只需第一次将sql语句传到数据端，
        以后每次只需向数据端传参数（网络传输量小），不需要反复编译sql语句，效率高，而且易读，易维护，
        防止sql注入（通过传入恒正确的表达式，在select查找时会匹配数据库中所有数据）
    5 execute与【executeUpdate】的异同
        同：均可以增删改
        差异：execute可以执行查询语句（返回true），然后通过getResultSet获取查询结果
             executeUpdate不可查询，其返回值代表影响的数据个数int
    6数据库【元数据】的获取
        元数据：和数据库服务器相关的数据（比如数据库版本，有哪些表，表有哪些字段，字段类型是什么等）
        获取：通过connection对象的getMetaDate获取DatabaseMetaData对象
    7事务：
        特点：
             原子性（Atomicity）-在事务中的多个操作，要么都成功，要么都失败（关闭自动提交，通过手动提交的方式）
             一致性（Consistency）：事务执行后，数据库状态与其他业务规则保持一致
             隔离性（Isolation）：事务独立运行。一个事务处理后的结果，影响了其他事务，那么其他事务会撤回。
             持久性（Durability）：软、硬件崩溃后，InnoDB数据表驱动会利用日志文件重构修改。
        操作：关闭自动提交conn.setAutoCommit(false);只有执行使用 conn.commit(); 才会提交进行
        要求：需要数据库支持并打开innodb才可使用事务

 * @author Hartley
 * date： 2020/5/24
 */
public class JDBCTest {

    @Test//=======================【1连接数据库】=======================
    //获得statement对象，执行sql语句
    public void test() {


        try {//初始化数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (//利用DriverManager的getConnection方法建立数据库的连接
             Connection connection = DriverManager.getConnection
                     ("jdbc:mysql://127.0.0.1/firstdb?characterEncoding=UTF-8",
                             "root", "admin");
             //用于执行sql语句的statement语句(是在sql包下的)
             Statement statement = connection.createStatement();) {
            //try-with-resource自动关闭资源~~以上资源都实现了AutoCloseable

            System.out.println("连接到的对象为：" + connection);
            System.out.println("statement对象为：" + statement);

            //编写sql语句：插入100条数据
            for (int i = 1; i <= 100; i++) {
                String name = "唐姆";
                int hp = 999;
                int damage = 1;
                String insertTom = "insert into hero values(null,'" + name + "'," + (i * 99) + "," + damage + ");";
                statement.execute(insertTom);//返回值是sql语句为select，查询到数据返回true，否则false
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @Test//==============================【2增删改】============================
    public void test2()  {

        String tableName = "hero";
        String name = "zz";
        //插入数据时主键不能重复
        String insertSql = "insert into " + tableName + " values (null,'" + name + "'," + 99 + "," + 25 + ");";
        String removeSql = "delete from " + tableName + " where id = 3;";
        String updateSql = "update " + tableName + " set name = '成龙' where id = 4;";//字符串用单引号！

        executeSql(insertSql);
        executeSql(removeSql);
        executeSql(updateSql);


    }

    @Test//查
    public void selectTest(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection conn = DriverManager.getConnection
                ("jdbc:mysql://127.0.0.1/firstdb?characterEncoding","root","admin");
            Statement state = conn.createStatement()){

            String selectSql = "select * from hero limit 1,4";

            //查询结果打印
            ResultSet rs = state.executeQuery(selectSql);//ResultSet会随着Stamtement关闭而关闭
            while (rs.next()) {
                //注意sql中索引是从1开始的~~~！！！
                int id = rs.getInt("id");// 可以使用字段名
                String n = rs.getString(2);// 也可以使用字段的顺序
                float hp = rs.getFloat("hp");
                int damage = rs.getInt(4);
                System.out.printf("%d\t%s\t%.1f\t%d%n", id, n, hp, damage);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test//============================【3校验账号密码】============================
    public void checkTest(){
        //输入的账号信息
        String inputAccount = "admin";
        String inputPWD = "123";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection conn = DriverManager.getConnection
                ("jdbc:mysql://127.0.0.1/firstdb?characterEncoding","root","admin");
            Statement state = conn.createStatement()){

            //*******************正式开始*************************
            //String selectSql = "select * from user ";
            String selectSql =
                    "select * from user where name = '"+inputAccount+"' and pwd ='"+inputPWD+"'";

            ResultSet rs = state.executeQuery(selectSql);
            //如果有数据则表示输入正确
            if(rs.next()){
                System.out.println("找到了，在第"+rs.getString("id")+"行");
            }else{
                System.out.println("没找到噻");
            }
            /*
            //查询结果打印..依次遍历好麻烦的说，利用sql的查询语句！！
            ResultSet rs = state.executeQuery(selectSql);//ResultSet会随着Stamtement关闭而关闭
            while (rs.next()) {
                //注意sql中索引是从1开始的~~~！！！
                int id = rs.getInt("id");// 可以使用字段名
                String name = rs.getString("name");
                String pwd = rs.getString("pwd");

                if(inputAccount.equals(name) && inputPWD.equals(pwd)){
                    System.out.printf("找到了，在第%d条，\t%s\t%s%n", id, name,pwd);
                    return;
                }

            }
            System.out.println("没找到相应信息");
             */

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test//=================【4预编译PreparedStatement】============================
    public void preparedTest(){
        //输入的账号信息
        String inputAccount = "admin";
        String inputPWD = "123";

        String url = "jdbc:mysql://127.0.0.1/firstdb?characterEncoding";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //用于预编译statement的sql语句
        String sql = "insert into hero values(null,?,?,?)";
        try(Connection conn = DriverManager.getConnection
                                (url,"root","admin");
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            //创建预编译的statement,参数Statement.RETURN_GENERATED_KEYS表示会返回自增长的id

            //添加参数后执行
            ps.setString(1,"张三");//此处1表示第一个？处
            ps.setFloat(2,122.02f);
            ps.setInt(3,66);
            ps.execute();

            //获取自增长的id值(增长到最后的值)
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                System.out.println(id);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test//==========【5 executeUpdate】与execute============================
    public void executeUpdateTest(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection
                ("jdbc:mysql://127.0.0.1/firstdb?characterEncoding", "root", "admin");
             Statement state = conn.createStatement()) {
            //************从这开始************
            String sql = "select * from user limit 0,10  ";
            boolean flag = state.execute(sql);//execute可以执行查询语句，且返回值为true
            System.out.println("当前execute的sql语句是不是查询呐？"+flag);
            ResultSet rs = state.getResultSet();//然后通过get方法获取查询结果

            while(rs.next()){//打印结果
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String pwd = rs.getString(3);
                System.out.printf("%d\t%s\t%s%n",id,name,pwd);
            }

            String sql2 = "update user set pwd='234' where pwd='233'";
            int num = state.executeUpdate(sql2);//executeUpdate可以返回影响的数据个数
            System.out.println("更新操作改变了"+num+"条数据");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Test//测试Statement与PreparedStatement性能差别？？？好像有问题,statement比较快，，
    public void testSpeed(){
        //输入的账号信息
        String inputAccount = "admin";
        String inputPWD = "123";

        String url = "jdbc:mysql://127.0.0.1/firstdb?characterEncoding";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        String sql = "insert into user values(null,?,?)"; //用于预编译statement的sql语句
        try(Connection conn = DriverManager.getConnection
                (url,"root","admin");
            Statement state = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql)){//创建预编译的statement

            long start = System.currentTimeMillis();//开始计时
            for (int i=0;i<100;i++){
                //添加参数后执行
                ps.setString(1,"张三");//此处1表示第一个？处
                ps.setString(2,"333");
                ps.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("预编译PreparedStatement插入100条数据用时："+(end-start));

            String sql2 = "insert into user values(null,'?','789')";
            start = System.currentTimeMillis();
            for (int j=0;j<100;j++){
                state.execute(sql2);
            }
            end = System.currentTimeMillis();
            System.out.println("普通Statement插入100条数据用时："+(end-start));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //==========【7事务】============================
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        Statement s4query = null;
        Statement s4delete = null;
        try {
            conn = DriverManager.getConnection
                    ("jdbc:mysql://127.0.0.1/firstdb?characterEncoding", "root", "admin");

            s4query = conn.createStatement();
            s4delete = conn.createStatement();
            Scanner s = new Scanner(System.in);

           //【1】关闭自动提交ee
            conn.setAutoCommit(false);
            //获取前1-10条id
            ResultSet rs = s4query.executeQuery("select id from hero order by id asc limit 0,10");
            while(rs.next()){
                int id = rs.getInt(1);
                System.out.println("将要删掉id为："+id+"了哟");
                s4delete.execute("delete from hero where id = "+id);
            }

            //是否删除这10条
            while(true){
                System.out.println("是否要删除数据(Y/N)");

                String str = s.next();
                if ("Y".equals(str)) {
                    //如果输入的是Y，则提交删除操作
                    //【2】手动提交
                    conn.commit();
                    System.out.println("提交删除");
                    break;
                } else if ("N".equals(str)) {
                    System.out.println("放弃删除");
                    break;
                }
            }

        } catch (SQLException throwables) {
            try {
                //【3】发生异常则回滚操作
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }finally {
            try {
                s4query.close();//依次关闭数据库资源
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                s4delete.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Test//==========【7 事务】============================
    public void metaTest(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection
                ("jdbc:mysql://127.0.0.1/firstdb?characterEncoding", "root", "admin");) {

            // 查看数据库层面的元数据
            // 即数据库服务器版本，驱动版本，都有哪些数据库等等
            DatabaseMetaData dbmd = conn.getMetaData();

            // 获取数据库服务器产品名称
            System.out.println("数据库产品名称:\t"+dbmd.getDatabaseProductName());
            // 获取数据库服务器产品版本号
            System.out.println("数据库产品版本:\t"+dbmd.getDatabaseProductVersion());
            // 获取数据库服务器用作类别和表名之间的分隔符 如firstdb.user中的点
            System.out.println("数据库和表分隔符:\t"+dbmd.getCatalogSeparator());
            // 获取驱动版本
            System.out.println("驱动版本:\t"+dbmd.getDriverVersion());

            System.out.println("可用的数据库列表：");
            // 获取数据库名称
            ResultSet rs = dbmd.getCatalogs();

            while (rs.next()) {
                System.out.println("数据库名称:\t"+rs.getString(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //执行sql语句
    public static void executeSql(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection
                ("jdbc:mysql://127.0.0.1/firstdb?characterEncoding", "root", "admin");
             Statement state = conn.createStatement()) {

            state.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


