package com.hc.distributed.dao;

import com.hc.distributed.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Mapper
public interface UserDao {

    int insertUser(User user);

    User listUserByUserAccount(String account);

    User listUserByUAccountAndPassword(@Param("account") String account, @Param("password") String password);
}
