package com.hc.hcsso.dtos;


import com.hc.hcsso.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 交互数据存储
 */
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Data {
    User user;

    /**
     * 单点登录令牌
     */
    String token;

    String clientUrl;

    String xAuthToken;
}
