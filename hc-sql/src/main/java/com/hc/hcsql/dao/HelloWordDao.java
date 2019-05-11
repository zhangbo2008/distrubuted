package com.hc.hcsql.dao;


import com.hc.hcsql.annotation.DaoMapper;
import com.hc.hcsql.annotation.SqlAnnotation;
import com.hc.hcsql.annotation.SqlParam;
import com.hc.hcsql.model.User;

import java.util.List;

@DaoMapper
public interface HelloWordDao {

    @SqlAnnotation("SELECT * FROM user WHERE user_name = #{userName}")
    List<User> sayHello(@SqlParam("userName") String userName);

    @SqlAnnotation("INSERT INTO user (user_id) VALUES (${userId})")
    int insert(@SqlParam("userId") int userId);

    @SqlAnnotation("UPDATE user SET user_name = #{userName} WHERE user_id = ${userId}")
    int update(@SqlParam("userName") String userName, @SqlParam("userId") int userId);

    @SqlAnnotation("DELETE FROM user WHERE user_id = #{userId}")
    int delete(@SqlParam("userId") int userId);
}
