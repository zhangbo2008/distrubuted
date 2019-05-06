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

    // sso服务端

    @PostMapping("/login")
    public ResultBean<Data> login(@RequestBody RequestBean requestBean) {
        return new ResultBean<>(userService.login(requestBean));
    }

    @PostMapping("/valid")
    public ResultBean<Data> validToken(@RequestBody RequestBean requestBean) {
        return new ResultBean<>(userService.valid(requestBean));
    }

    @PostMapping("/register")
    public ResultBean<Integer> register(@RequestBody RequestBean requestBean) {
        return new ResultBean<>(userService.register(requestBean));
    }

    @PostMapping("/logout")
    public ResultBean<Data> logout(@RequestBody RequestBean requestBean) {
        return new ResultBean<>(userService.logout(requestBean));
    }

    @PostMapping("sublogout")
    public ResultBean<Data> subLogout(@RequestBody RequestBean requestBean, HttpServletRequest request) {
        /*try {
            RequestBean requestBean1 = new Gson().fromJson(request.getReader(), RequestBean.class);
            System.out.println("ssssssss" + requestBean1.getAuthToken());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        if (!request.getRemoteAddr().startsWith("127.0.0.1")) {
            try {
                System.out.println("come in sssssssssssssssss");
                return new HttpClientUtil().postAction("http://localhost:8889/user/logout", new RequestBean());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return new ResultBean<>(userService.subLogout(requestBean));
        }
        return new ResultBean<>();
    }

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
