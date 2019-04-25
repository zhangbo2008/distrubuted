package com.hc.distributed.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatusEnum {

    PARAMETER_ERROR("parameter_error"),

    USER_ACCOUNT_ERROR("user_account_error"),

    USER_HAS_REGISTER("user_has_register")
    ;

    private String msg;

    public String UserStatusEnum() {
        return msg;
    }
}
