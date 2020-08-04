package com.spring.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/8/4
 */


//@Configuration
public class JdbcConfig {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /**
     * 创建QueryRunner对象（不加注解只是一个普通方法，因为没有存入IOC的map中
     * @param
     * @return
     */

    @Bean(name = "runner")
    @Scope(value = "prototype")
    public QueryRunner createQureyRunner(@Qualifier("ds2") DataSource ds){

        return new QueryRunner(ds);
    }

    /**
     * 创建数据源对象
     */
    @Bean(name = "ds1")
    public DataSource createDataSource(){
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        return ds;
    }


    @Bean(name = "ds2")
    public DataSource createDataSource2(){
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/springpro2");
            ds.setUser("root");
            ds.setPassword("admin");

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        return ds;
    }
}
