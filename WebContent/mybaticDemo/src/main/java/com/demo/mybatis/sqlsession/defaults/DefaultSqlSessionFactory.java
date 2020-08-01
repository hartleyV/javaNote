package com.demo.mybatis.sqlsession.defaults;

import com.demo.mybatis.cfg.Configuration;
import com.demo.mybatis.sqlsession.SqlSession;
import com.demo.mybatis.sqlsession.SqlSessionFactory;

/**
 * description：
 *factory接口的实现类
 * @author Hartley
 * date： 2020/7/28
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }
    //用来创建新的数据库对象
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
