package com.hc.distributed.dtos;


import com.hc.distributed.model.User;
import com.hc.distributed.utils.VerifyUtil;

@lombok.Data
public class Data {
    private User user;

    public Data(Object object) {
        if (object instanceof User)
            this.user = VerifyUtil.cast(object);
    }

}
