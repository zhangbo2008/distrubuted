package com.hc.hcbasic.sqlReflect;


@DaoMapper
public interface HelloWordDao {

    @SqlAnnotation("SELECT * FROM user WHERE user_name = #{userName}")
    User sayHello(@SqlParam("userName") String userName);

    @SqlAnnotation("INSERT INTO user (user_id) VALUES (${userId})")
    int insert(@SqlParam("userId") int userId);

    @SqlAnnotation("UPDATE user SET user_name = #{userName} WHERE user_id = ${userId}")
    int update(@SqlParam("userName") String userName, @SqlParam("userId") int userId);

    @SqlAnnotation("DELETE FROM user WHERE user_id = #{userId}")
    int delete(@SqlParam("userId") int userId);
}
