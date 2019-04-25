package com.hc.distributed.controllers;

import com.hc.distributed.dtos.Data;
import com.hc.distributed.dtos.RequestBean;
import com.hc.distributed.dtos.ResultBean;
import com.hc.distributed.exceptions.CheckException;
import com.hc.distributed.service.UserService;
import org.csource.common.MyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public ResultBean<Data> login(@RequestBody RequestBean requestBean) {
        return new ResultBean<>(userService.login(requestBean));
    }

    @PostMapping("/register")
    public ResultBean<Integer> register(@RequestBody RequestBean requestBean) {
        return new ResultBean<>(userService.register(requestBean));
    }



}
