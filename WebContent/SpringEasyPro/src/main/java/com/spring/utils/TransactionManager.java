package com.spring.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * description：
    事务管理相关的工具类（开启、提交、回滚、释放事务
 * @author Hartley
 * date： 2020/8/6
 */
@Component("transactionManager")
public class TransactionManager {

    @Resource(name = "connectionUtils")
    private ConnectionUtils connectionUtils;

    /**
     * 开启事务(改成手动
     */
    public void beginTransaction(){
        try {
            //空指针？？？
            Connection conn = connectionUtils.getThreadConnection();
            conn.setAutoCommit(false);//手动
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * 提交事务
     */
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();//手动
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * 回滚事务
     */
    public void rollBack(){
        try {
            connectionUtils.getThreadConnection().rollback();//手动
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    /**
     * 释放事务
     */
    public void release(){
        try {
            connectionUtils.getThreadConnection().close();//还回连接池
            connectionUtils.remove();//并使其解绑
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
