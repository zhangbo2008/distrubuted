package com.hc.hcsso.controllers.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域处理
 *
 * @author HC
 * @create 2019-05-05 14:39
 */
@WebFilter
public class LogInterceptor implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");

            filterChain.doFilter(request, response);
    }
}