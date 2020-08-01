package com.demo.mybatis;

import com.demo.mybatis.dao.IUserDAO;
import com.demo.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/7/29
 */
public class MybatisAnnotationTest {

    //基于注解的Mybatis使用
    public static void main(String[] args) throws IOException {
        //1.获取字节输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2根据字节输入流构建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3 工厂对象连接数据库，创建connection对象,返回sqlSession对象
        SqlSession session = factory.openSession();
        //4 SqlSession，返回接口类的代理对象
        // （根据不同方法的key找到对应mapper，执行sql语句对应sql语句，并把结果封装指定类型）
        IUserDAO userDAO = session.getMapper(IUserDAO.class);
        List<User> users = userDAO.findAll();

        for (User user:users){
            System.out.println(user);
        }

        session.close();
        in.close();



    }
}
