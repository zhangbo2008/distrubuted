package com.hc.hcbasic.dao;

import com.hc.hcbasic.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    int insertUser(User user);

    User listUserByUserAccount(String account);

    User listUserByUAccountAndPassword(@Param("account") String account, @Param("password") String password);
}
