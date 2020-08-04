package com.spring.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * description：
    作为配置类，作用同bean.xml
 新注解：Configuration
 * @author Hartley
 * date： 2020/8/4
 */
@Configuration
@ComponentScan(basePackages="com.spring")
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {


}
