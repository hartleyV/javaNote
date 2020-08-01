package com.demo.mybatis.sqlsession.proxy;

import com.demo.mybatis.cfg.Mapper;
import com.demo.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/7/28
 */
public class MapperProxy implements InvocationHandler {
    //map的【key】是dao接口的权限定类名+方法名
    private Map<String, Mapper> mappers;
    private Connection conn;

    public MapperProxy(Map<String,Mapper> mappers,Connection conn){
        this.mappers = mappers;
        this.conn = conn;
    }

    //用于对方法进行增强（调用selectList方法）
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        //组合成【key】
        String key = className+"."+methodName;
        //获取对应的Mapper对象
        Mapper mapper = mappers.get(key);

        if (mapper==null){
            throw new IllegalArgumentException("参数传入有误");
        }
        return new Executor().selectList(mapper,conn);
    }
}
