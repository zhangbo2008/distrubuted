package com.hc.hcsso.controllers.config;

import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-06 11:00
 */
public class HeaderHttpSessionResolver implements HttpSessionIdResolver {
    @Override
    public List<String> resolveSessionIds(HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public void setSessionId(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s) {

    }

    @Override
    public void expireSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }
}
