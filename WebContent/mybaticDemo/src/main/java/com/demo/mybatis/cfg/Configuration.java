package com.demo.mybatis.cfg;

import java.util.HashMap;
import java.util.Map;

/**
 * description：
 *自定义MyBatis配置类【数据库的连接信息】、mappers【sql语句、返回类型的全限定类名】
 * @author Hartley
 * date： 2020/7/28
 */
public class Configuration {
    private String driver;
    private String url;
    private String username;
    private String password;

    private Map<String,Mapper> mappers = new HashMap<String, Mapper>();

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
        this.mappers.putAll(mappers);
        //this.mappers = mappers;会覆盖掉前面的mappers记录1！！！
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
