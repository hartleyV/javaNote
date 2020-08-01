package com.demo.mybatis;

import com.demo.mybatis.dao.IUserDAO;
import com.demo.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/7/29
 */
public class AnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDAO userDAO;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDAO = session.getMapper(IUserDAO.class);
    }

    @After
    public void destory() throws Exception{
        session.commit();//????
        session.close();
        in.close();
    }


    /**
     * 存---增
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("Hartley");
        user.setUserAddress("铁岭");

        userDAO.saveUser(user);
    }


    /**
     * 更新--改
     */
    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserName("Hartley");
        user.setUserAddress("北京三里屯");
        user.setUserId(49);


        userDAO.updateUser(user);
    }

    /**
     * 删除--删
     */
    @Test
    public void testDelete(){
        User user = new User();
        user.setUserId(41);
        userDAO.deleteUser(45);
    }

    /**
     * 查指定ID的内容
     */
    @Test
    public void testFind(){
        User user = userDAO.findById(49);
        System.out.println(user);

    }

    /**
     * 根据名字模糊查询
     */
    @Test
    public void testFindByName(){
        List<User> users = userDAO.findUserByName("王");

        for (User user : users){
            System.out.println(user);
        }
    }

    /**
     *查询所有的用户，以及其名下所有账户
     */
    @Test
    public void testFindAll(){
        List<User> users = userDAO.findAll();

        for (User user : users){
            System.out.println("---【每个账号信息】---");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }


    @Test
    public void testTotalUser(){
        System.out.println("总用户数为：["+userDAO.getTotalUser()+"]");
    }

}
