package com.hc.hcsso.service;


import com.hc.hcsso.dtos.Data;
import com.hc.hcsso.dtos.RequestBean;

import java.io.IOException;

public interface UserService {

    /**
     * 用户登陆
     * @param requestBean user、clientUrl、token
     * @return token、clientUrl、authToken
     */
    Data login(RequestBean requestBean);

    int register(RequestBean requestBean);

    /**
     * 验证客户端的token与clientUrl是否合法,若合法则将客户端的clientUrl注册到token中
     * @param requestBean token、clientUrl
     * @return 操作结果，成功data为null
     */
    Data valid(RequestBean requestBean);

    /**
     * 注销用户在所有子系统的登陆状态
     * @param requestBean token
     * @return 操作结果
     */
    Data logout(RequestBean requestBean);

    /**
     * 接收来自服务器的token与clientUrl,
     * @param requestBean token、clientUrl
     * @return 操作结果，成功data为带token与clientUrl
     */
    Data token(RequestBean requestBean);

    /**
     * 注销局部会话，若请求方不为SSO认证中心，则请求认证中心注销所有子系统的登陆状态
     * @param requestBean token
     * @return 操作结果
     */
    Data subLogout(RequestBean requestBean);
}
