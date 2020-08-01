package com.demo.mybatis.utils;

import com.demo.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * description：
 用来创造数据源的工具类（用来连接数据库，获取connection对象的
 * @author Hartley
 * date： 2020/7/29
 */
public class DataSourceUtil {
    public static Connection getConnection(Configuration cfg){
        try{
            Class.forName(cfg.getDriver());
            return DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
