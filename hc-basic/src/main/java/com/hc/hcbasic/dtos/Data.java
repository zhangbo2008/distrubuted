package com.hc.hcbasic.dtos;


import com.hc.hcbasic.model.User;

@lombok.Data
public class Data {
    private User user;

    public Data(Object object) {
        if (object instanceof User)
            this.user = VerifyUtil.cast(object);
    }

}
