package com.demo.mybatis.dao;

import com.demo.mybatis.pojo.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDAO {

    /**
     * 查询所有账号的信息（一个账号对应一个User）
     * @return
     */
    @Select("select * from account")
    @Results(id="accountMap",value={
            @Result(id=true,column="id",property="id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            //根据账户表中的数据查用户：一对一关系，
            // 一对一用EAGER，一对多用LAZEY
            @Result(column = "uid",property = "user",
                    one=@One(select="com.demo.mybatis.dao.IUserDAO.findById",
                            fetchType= FetchType.EAGER))
    })
    List<Account> findAll();


    //多对一查表：实现通过实体类User的id查询名下所有账户
    @Select("select * from Account where uid=#{userId}")
    List<Account> findAccountById(Integer userId);
}
