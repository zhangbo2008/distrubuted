package com.hc.distributed.service;

import com.hc.distributed.dtos.Data;
import com.hc.distributed.dtos.RequestBean;
import org.csource.common.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    Data login(RequestBean requestBean);

    int register(RequestBean requestBean);



}
