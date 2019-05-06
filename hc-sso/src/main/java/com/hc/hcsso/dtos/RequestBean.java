package com.hc.hcsso.dtos;

import com.hc.hcsso.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RequestBean {
    User user;

    String token;

    String clientUrl;
}
