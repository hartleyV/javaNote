package com.demo.mybatis.cfg;

/**
 * description：
 *用来封装sql语句以及结果类型的权限定类名
 * @author Hartley
 * date： 2020/7/28
 */
public class Mapper {
    private  String queryString;//执行的sql语句
    private String resultType;//返回的实体类的权限定类名

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
