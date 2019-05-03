package com.hc.hcsso.service;


import com.hc.hcsso.dtos.Data;
import com.hc.hcsso.dtos.RequestBean;

public interface UserService {

    Data login(RequestBean requestBean);

    int register(RequestBean requestBean);



}
