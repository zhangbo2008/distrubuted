package com.hc.hcbasic.service;

import com.hc.hcbasic.dtos.Data;
import com.hc.hcbasic.dtos.RequestBean;

public interface UserService {

    Data login(RequestBean requestBean);

    int register(RequestBean requestBean);



}
