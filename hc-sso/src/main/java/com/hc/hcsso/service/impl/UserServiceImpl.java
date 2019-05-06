package com.hc.hcsso.service.impl;

import com.hc.hcsso.constant.UserStatusEnum;
import com.hc.hcsso.dao.UserDao;
import com.hc.hcsso.dtos.Data;
import com.hc.hcsso.dtos.RequestBean;
import com.hc.hcsso.dtos.ResultBean;
import com.hc.hcsso.exceptions.CheckException;
import com.hc.hcsso.exceptions.ErrorException;
import com.hc.hcsso.exceptions.UnloginException;
import com.hc.hcsso.model.User;
import com.hc.hcsso.service.UserService;
import com.hc.hcsso.utils.HttpClientUtil;
import com.hc.hcsso.utils.LocaleMessageSourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

import static com.hc.hccommon.utils.VerifyUtil.checkNull;
import static com.hc.hccommon.utils.VerifyUtil.isNotEmpty;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private LocaleMessageSourceUtil localeMessageSourceUtil;

    private static Map<String, List<String>> tokenAndUrlMap = new HashMap<>();

    private static Map<String, User> tokenAndUserMap = new HashMap<>();

    private static Map<String, String> tokenAndSessionId = new HashMap<>();

    @Override
    public Data login(RequestBean requestBean) {
        String token = requestBean.getToken();
        String clientUrl = requestBean.getClientUrl();
        log.info("login() : token = {}, clientUrl = {}", token, clientUrl);

        System.out.println("....." + isNotEmpty(token, clientUrl));
        System.out.println("....." + tokenAndUrlMap.containsKey(token));

        // 用户已登陆
        if (isNotEmpty(token, clientUrl) && tokenAndUrlMap.containsKey(token)) {
            log.info("token = {} 用户已登陆", token);
            // 验证
            return transmitToken(token, clientUrl);
        }

        // 使用账号密码进行登陆
        User user = requestBean.getUser();

        // 登陆信息为空，说明用户意图不为登陆，抛出未登录状态的异常
        if (!checkNull(user)) {
            throw new UnloginException(UserStatusEnum.USER_HAS_NOT_LOGIN.getMsg());
        }

        // 检验合法参数，并进入登陆逻辑
        if (isNotEmpty(user.getAccount(), user.getPassword(), clientUrl)) {
            return loginImpl(user, clientUrl);
        }

        throw new CheckException(UserStatusEnum.PARAMETER_ERROR.getMsg());
    }

    private Data loginImpl(User user, String clientUrl) {
        String account = user.getAccount();
        String password = user.getPassword();
        user = userDao.listUserByUAccountAndPassword(account, password);

        if (!checkNull(user)) {
            throw new CheckException(UserStatusEnum.USER_ACCOUNT_ERROR.getMsg());
        }

        // 生成token并存入tokenMap中，但此时并没有注册系统
        String token = UUID.randomUUID().toString();
        tokenAndUrlMap.put(token, new ArrayList<>());
        tokenAndUserMap.put(token, user);

        Data data = transmitToken(token, clientUrl);
        tokenAndSessionId.put(token, data.getAuthToken());

        log.info("loginImpl() 登录成功：token = {}, clientUrl = {}, sesssionId = {}, User = {}", token, clientUrl, tokenAndSessionId.get(token), user);
        return data;
    }

    /**
     * 用以将生成的token或者以验证登陆的token传递回去给Client
     *
     * @param token     令牌
     * @param clientUrl Client的url
     */
    private Data transmitToken(String token, String clientUrl) {
        try {
            return (new HttpClientUtil().postAction(clientUrl + "/user/token", new RequestBean().setToken(token).setClientUrl(clientUrl)).getData());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ErrorException("clientUrl error");
        }
    }

    @Override
    public int register(RequestBean requestBean) {
        User user = requestBean.getUser();
        if (checkNull(user) && isNotEmpty(user.getAccount())
                && isNotEmpty(user.getPassword()) && isNotEmpty(user.getEmail())) {
            return registerImpl(user);
        }

        throw new CheckException(UserStatusEnum.PARAMETER_ERROR.getMsg());
    }

    @Override
    public Data valid(RequestBean requestBean) {
        String token = requestBean.getToken();
        String clientUrl = requestBean.getClientUrl();
        if (isNotEmpty(token, clientUrl)) {
            return validImpl(token, clientUrl);
        }

        throw new CheckException(localeMessageSourceUtil.getMessage(UserStatusEnum.PARAMETER_ERROR.getMsg()));
    }

    @Override
    public Data logout(RequestBean requestBean) {
        String token = requestBean.getToken();
        log.info("logout() : token = {}", token);
        if (tokenAndUrlMap.containsKey(token)) {
            List<String> urls = tokenAndUrlMap.get(token);

            // 注销所有子系统的登陆状态
            for (String clientUrl : urls) {
                logoutSubSystem(token, clientUrl);
            }
            // 移除用户登陆状态
            tokenAndUrlMap.remove(token);
            tokenAndUserMap.remove(token);
            tokenAndSessionId.remove(token);
            // 默认成功
            return null;
        }
        throw new CheckException("令牌错误");
    }

    @Override
    public Data token(RequestBean requestBean) {
        String token = requestBean.getToken();
        String clientUrl = requestBean.getClientUrl();
        if (isNotEmpty(token, clientUrl)) {
            return tokenImpl(token, clientUrl);
        }
        throw new CheckException(UserStatusEnum.PARAMETER_ERROR.getMsg());
    }

    @Override
    public Data subLogout(RequestBean requestBean) {
        String xAuthToken = requestBean.getAuthToken();
        log.info("subLogout() : xAuthToken = {}", xAuthToken);
        if (isNotEmpty(xAuthToken)) {
            return subLogoutImpl(xAuthToken);
        }
        throw new CheckException(UserStatusEnum.PARAMETER_ERROR.getMsg());
    }

    private Data subLogoutImpl(String xAuthToken) {
        redisTemplate.opsForList().getOperations().delete("spring:session:sessions:" + xAuthToken);
        return new Data();
    }

    private Data tokenImpl(String token, String clientUrl) {
        if (remoteValid(token, clientUrl)) {
            return new Data();
        }
        throw new ErrorException("valid error");
    }

    /**
     * 向SSO发送令牌与本地url，验证注册
     *
     * @param token     令牌
     * @param clientUrl 子系统url
     * @return true 验证成功
     */
    private boolean remoteValid(String token, String clientUrl) {
        try {
            // 0为验证成功
            if ((new HttpClientUtil().postAction("http://localhost:8889/user/valid", new RequestBean().setToken(token).setClientUrl(clientUrl))).getCode() == 0) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void logoutSubSystem(String token, String clientUrl) {
        try {
            log.info("logoutSubSystem(): sessionId = {}", tokenAndSessionId.get(token));
            new HttpClientUtil().postAction(clientUrl + "/user/sublogout", new RequestBean().setAuthToken(tokenAndSessionId.get(token)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Data validImpl(String token, String clientUrl) {
        boolean hasSave = false;

        // 验证数据是否合法且token是否存在
        if (tokenAndUrlMap.containsKey(token)) {
            List<String> urls = tokenAndUrlMap.get(token);
            // 验证url是否已保存
            if (null != urls) {
                for (String url : urls) {
                    if (url.contains(clientUrl)) {
                        hasSave = true;
                    }
                }
            }

            if (!hasSave) {
                urls.add(clientUrl);
            }
            // 返回null即可以，默认成功
            return null;
        } else {
            throw new ErrorException("has not exist token");
        }
    }

    private int registerImpl(User user) {
        try {
            return userDao.insertUser(user);
        } catch (Exception e) {
            throw new CheckException(UserStatusEnum.USER_HAS_REGISTER.getMsg());
        }
    }

}
