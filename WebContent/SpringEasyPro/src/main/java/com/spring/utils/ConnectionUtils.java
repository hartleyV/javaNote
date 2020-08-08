package com.spring.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * description：
    从数据源获取一个连接并与实现与线程的绑定ThreadLocal
 * @author Hartley
 * date： 2020/8/6
 */
@Component("connectionUtils")
public class ConnectionUtils {

    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    @Resource(name="ds2")
    private DataSource dataSource;//用来获取连接


    /**
     * 获取当前线程连接
     */
    public Connection getThreadConnection(){
        Connection conn = tl.get();//从ThreadLocal上获取连接
        if (conn==null){//判断当前线程是否有连接，莫得则从数据源中获取并存在ThreadLocal中
            try {
                ////连接为空？？我傻了,忘记赋值
                conn = dataSource.getConnection();//从数据h源获取连接
                tl.set(conn);//把连接存入ThreadLocal

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return conn;
    }

    /**
     * 解绑连接与线程
     */
    public void remove(){
        tl.remove();
    }
}
