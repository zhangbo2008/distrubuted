package com.hc.hcbasic.sqlReflect;

import lombok.Data;

@Data
public class User {

    private int userId;
    private String userName;
    private String userPhone;
    private String userImage;
    private String userPassword;

    User() {

    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
