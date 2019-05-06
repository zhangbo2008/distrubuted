package com.hc.hcsso.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatusEnum {

    PARAMETER_ERROR("parameter_error"),

    USER_ACCOUNT_ERROR("user_account_error"),

    USER_HAS_REGISTER("user_has_register"),

    URL_OR_TOKEN_ERROR("url or token error"),

    USER_HAS_NOT_LOGIN("you has not login")
    ;

    private String msg;

    public String getMsg() {
        return msg;
    }
}
