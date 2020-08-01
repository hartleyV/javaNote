package com.demo.mybatis.sqlsession;

import com.demo.mybatis.cfg.Configuration;
import com.demo.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.demo.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * description：
 *用来创建一个SqlSessionFactory对象
 * @author Hartley
 * date： 2020/7/26
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
