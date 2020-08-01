package com.demo.mybatis.sqlsession.defaults;

import com.demo.mybatis.cfg.Configuration;
import com.demo.mybatis.sqlsession.SqlSession;
import com.demo.mybatis.sqlsession.proxy.MapperProxy;
import com.demo.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/7/28
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration cfg;
    private Connection conn;

    public DefaultSqlSession(Configuration cfg){
        this.cfg = cfg;
        this.conn = DataSourceUtil.getConnection(cfg);
    }

    //用于创建代理对象
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T)Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),conn));

    }
    //关闭资源
    public void close() {
        if (conn != null){
            try {

                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
