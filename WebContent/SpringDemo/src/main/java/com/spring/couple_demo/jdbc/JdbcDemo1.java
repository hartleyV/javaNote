package com.spring.couple_demo.jdbc;

import java.sql.*;

/**
 * description：
    程序的耦合：示例
 * @author Hartley
 * date： 2020/7/30
 */
public class JdbcDemo1 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Jdbc连接方式：注册驱动
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver()");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis_demo","root","admin");
        PreparedStatement pstm = conn.prepareStatement("select * from user");
        ResultSet rs = pstm.executeQuery();

        while (rs.next()){
            System.out.println(rs.getString("username"));

        }

        rs.close();
        pstm.close();
        conn.close();
    }
}
