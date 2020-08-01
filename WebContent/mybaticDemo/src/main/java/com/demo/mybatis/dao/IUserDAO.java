package com.demo.mybatis.dao;

//import com.demo.mybatis.annotations.Select;
import com.demo.mybatis.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
//import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * description：
 *用户的持久层接口
 * @author Hartley
 * date： 2020/7/26
 */
public interface IUserDAO {
    //使用注解（不用配置文件~）
    @Select("select * from user")
    @Results(id = "userMap",value={
            @Result(id=true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "birthday",property = "userBirthday"),
            //用来查询一个user对象的所有账户，一对多用many
            //column：数据库字段名--user表，property：实体类属性--List<Account>,
            // select：查询另一个表的方法
            @Result(column = "id",property = "accounts",
                    many = @Many(select = "com.demo.mybatis.dao.IAccountDAO.findAccountById",
                            fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    //供IAccountDAO接口类查询用户
    @Select("select * from user where id = #{id}")
    @ResultMap({"userMap"})
    User findById(Integer id);



    //保存用户
    @Insert("insert into user(username,address,sex,birthday)values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    @Update("update user set username=#{username},sex=#{sex},birthday=#{birthday},address=#{address} where id=#{id}")
    void updateUser(User user);

    @Delete("delete  from user where id=#{id}")
    void deleteUser(Integer id);



    //模糊查询
    //@Select("select * from user where username like #{username}")
    @ResultMap({"userMap"})
    @Select("select * from user where username like '%${value}%'")
    List<User> findUserByName(String username);

    //查询总用户数
    @Select("select count(*) from user")
    int getTotalUser();
}
