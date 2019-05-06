package com.hc.hcbasic.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatusEnum {

    PARAMETER_ERROR("parameter_error"),

    USER_ACCOUNT_ERROR("user_account_error"),

    USER_HAS_REGISTER("user_has_register"),

    USER_HAS_NOT_LOGIN("user has not login")
    ;

    private String msg;

    public String getMsg() {
        return msg;
    }
}
