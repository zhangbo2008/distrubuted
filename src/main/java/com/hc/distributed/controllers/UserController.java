package com.hc.distributed.controllers;

import com.hc.distributed.dtos.Data;
import com.hc.distributed.dtos.RequestBean;
import com.hc.distributed.dtos.ResultBean;
import com.hc.distributed.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
