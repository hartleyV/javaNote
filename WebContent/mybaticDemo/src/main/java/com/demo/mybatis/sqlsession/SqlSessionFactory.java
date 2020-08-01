package com.demo.mybatis.sqlsession;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/7/26
 */
public interface SqlSessionFactory {
    //用来打开一个SqlSession对象
    SqlSession openSession();
}
