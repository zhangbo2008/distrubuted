package com.hc.distributed.service;

import com.hc.distributed.dtos.Data;
import com.hc.distributed.dtos.RequestBean;

public interface UserService {

    Data login(RequestBean requestBean);

    int register(RequestBean requestBean);



}
