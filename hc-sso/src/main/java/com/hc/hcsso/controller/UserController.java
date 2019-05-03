package com.hc.hcsso.controller;

import com.hc.hcsso.dtos.Data;
import com.hc.hcsso.dtos.RequestBean;
import com.hc.hcsso.dtos.ResultBean;
import com.hc.hcsso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-03 14:18
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/login")
    public ResultBean<Data> login(HttpServletRequest request, @RequestBody RequestBean requestBean) {
        ResultBean<Data> resultBean =  new ResultBean<>(userService.login(requestBean));

        if (resultBean.getCode() == 0) {
            request.getSession().setAttribute("isLogin", true);
        }

        String token = UUID.randomUUID().toString();
//        redisTemplate.opsForList().leftPush(token, request.)
        return resultBean;
    }

    @PostMapping("/register")
    public ResultBean<Integer> register(@RequestBody RequestBean requestBean) {
        return new ResultBean<>(userService.register(requestBean));
    }



}
