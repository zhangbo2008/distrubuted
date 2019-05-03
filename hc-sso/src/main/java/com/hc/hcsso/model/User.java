package com.hc.hcsso.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String account;

    private String password;

    private String email;
}
