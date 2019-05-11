package com.hc.hcbasic.controllers.filter;

import com.google.gson.Gson;
import com.hc.hcbasic.constant.UserStatusEnum;
import com.hc.hccommon.exceptions.UnloginException;
import com.hc.hcsso.dtos.ResultBean;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.hc.hccommon.utils.VerifyUtil.checkNull;

/**
 * 过滤未登陆的用户
 *
 * @author HC
 * @create 2019-05-05 11:17
 */
@Slf4j
@WebFilter(filterName = "userLoginFilter", urlPatterns = {"/user/test"})
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("用户登陆状态过滤器");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 存在局部会话
        if (checkNull(request.getSession().getAttribute("user"))) {
            filterChain.doFilter(request, servletResponse);
        } else {
            servletResponse.getWriter().write(new Gson().toJson(new ResultBean<>(new UnloginException(UserStatusEnum.USER_HAS_NOT_LOGIN.getMsg()))));
        }
    }
}
