package com.demo.mybatis.sqlsession;

/**
 * 自定义MyBatis中与数据库交互的核心接口类
 */
public interface SqlSession {
    /**
     * 根据参数dao接口的字节码创建一个代理对象
     * @param daoInterfaceClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);
    //释放资源
    void close();
}
