package com.demo.mybatis;

import com.demo.mybatis.dao.IUserDAO;
//import com.demo.mybatis.dao.UserDAO;
import com.demo.mybatis.pojo.User;

import com.demo.mybatis.io.Resources;
import com.demo.mybatis.sqlsession.SqlSession;
import com.demo.mybatis.sqlsession.SqlSessionFactory;
import com.demo.mybatis.sqlsession.SqlSessionFactoryBuilder;

/*
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
*/
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/7/26
 */
public class MybatisTest {

    public static void main(String[] args) throws IOException {
        //读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂，并用其生成SqlSession对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        SqlSession session = factory.openSession();

        //使用SqlSession创建Dao接口的代理对象
        IUserDAO userDao = session.getMapper(IUserDAO.class);


        //UserDAO userDao = new UserDAO(factory);
        List<User> users = userDao.findAll();
        for (User user:users){
            System.out.println(user);
        }
        //释放资源
       // session.close();



        in.close();

    }
}
