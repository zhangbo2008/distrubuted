package com.hc.hcsso.controllers;

import com.hc.hcsso.dtos.Data;
import com.hc.hcsso.dtos.RequestBean;
import com.hc.hcsso.dtos.ResultBean;
import com.hc.hcsso.model.User;
import com.hc.hcsso.service.UserService;
import com.hc.hcsso.utils.HttpClientUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户控制层
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

    // sso服务端

    /**
     * 用户登陆
     * @param requestBean user、clientUrl、token
     * @return token、clientUrl、authToken
     */
    @PostMapping("/login")
    public ResultBean<Data> login(@RequestBody RequestBean requestBean) {
        return new ResultBean<>(userService.login(requestBean));
    }

    /**
     * 验证客户端的token与clientUrl是否合法,若合法则将客户端的clientUrl注册到token中
     * @param requestBean token、clientUrl
     * @return 操作结果，成功data为null
     */
    @PostMapping("/valid")
    public ResultBean<Data> validToken(@RequestBody RequestBean requestBean) {
        return new ResultBean<>(userService.valid(requestBean));
    }

    @PostMapping("/register")
    public ResultBean<Integer> register(@RequestBody RequestBean requestBean) {
        return new ResultBean<>(userService.register(requestBean));
    }

    /**
     * 注销用户在所有子系统的登陆状态
     * @param requestBean token
     * @return 操作结果
     */
    @PostMapping("/logout")
    public ResultBean<Data> logout(@RequestBody RequestBean requestBean) {
        return new ResultBean<>(userService.logout(requestBean));
    }

    /**
     * 注销局部会话，若请求方不为SSO认证中心，则请求认证中心注销所有子系统的登陆状态
     * @param requestBean token
     * @param request 请求
     * @return 操作结果
     */
    @PostMapping("sublogout")
    public ResultBean<Data> subLogout(@RequestBody RequestBean requestBean, HttpServletRequest request) {
        if (!request.getRemoteAddr().startsWith("127.0.0.1")) {
            try {
                return new HttpClientUtil().postAction("http://localhost:8889/user/logout", new RequestBean());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return new ResultBean<>(userService.subLogout(requestBean));
        }
        return new ResultBean<>();
    }

    /**
     * 接收来自服务器的token与clientUrl,
     * @param httpSession 操作session
     * @param requestBean token、clientUrl
     * @return 操作结果，成功data为带token与clientUrl
     */
    // sso客户端
    @PostMapping("/token")
    public ResultBean<Data> token(HttpSession httpSession, @RequestBody RequestBean requestBean) {
        ResultBean<Data> resultBean = new ResultBean<>(userService.token(requestBean));
        // 验证成功
        if (resultBean.getCode() == 0) {
//             此处仅仅设置用户会话，用户信息的获取在其他请求处理
            System.out.println("设置session");
            httpSession.setAttribute("user", new User());
        }
        return resultBean;
    }

    /**
     * 用以测试
     */
    @PostMapping("/test")
    public ResultBean<Data> test() {
        try {
            return new HttpClientUtil().postAction("http://localhost:8889/user/test2", new RequestBean().setAuthToken("sss"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultBean<>();
    }

    @PostMapping("test2")
    public ResultBean<Data> test2(@RequestBody RequestBean requestBean) {
        System.out.println("token = " + requestBean.getToken());
        System.out.println("xAuthToken = " + requestBean.getAuthToken());
        System.out.println("clientUrl = " + requestBean.getClientUrl());
        return new ResultBean<>();
    }

    @GetMapping("/testsession")
    public ResultBean<Data> testSession(HttpSession session) {
        session.setAttribute("user", new User("ss", "ss", "ddd"));
        return new ResultBean<>();
    }
}
