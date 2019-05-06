package com.hc.hcsso.service;


import com.hc.hcsso.dtos.Data;
import com.hc.hcsso.dtos.RequestBean;

import java.io.IOException;

public interface UserService {

    Data login(RequestBean requestBean);

    int register(RequestBean requestBean);

    Data valid(RequestBean requestBean);

    Data logout(RequestBean requestBean);

    Data token(RequestBean requestBean);
}
