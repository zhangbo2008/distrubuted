package com.hc.hcbasic.model;

import lombok.Builder;
import lombok.Data;

@Data
public class User {
    private String account;

    private String password;

    private String email;
}
