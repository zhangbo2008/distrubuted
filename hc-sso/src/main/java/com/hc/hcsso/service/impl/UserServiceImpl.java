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

    /**
     * 用户的Mysql数据库操作
     */
    @Resource
    UserDao userDao;

    /**
     * 服务器之间的通讯方式
     */
    @Resource
    HttpClientUtil httpClientUtil;

    /**
     * redis数据库操作
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private LocaleMessageSourceUtil localeMessageSourceUtil;

    /**
     * 建立token与clientUrl的映射
     */
    private static Map<String, List<String>> tokenAndUrlMap = new HashMap<>();

    /**
     * 建立token与user信息的映射
     */
    private static Map<String, User> tokenAndUserMap = new HashMap<>();

    /**
     * 建立token与sessionID，即x-auth-token之间的映射
     */
    private static Map<String, String> tokenAndSessionId = new HashMap<>();

    /**
     * 验证用户是否已登录、验证请求参数是否合法
     * @param requestBean user、clientUrl、token
     * @return token、clientUrl、authToken
     */
    @Override
    public Data login(RequestBean requestBean) {
        String token = requestBean.getToken();
        String clientUrl = requestBean.getClientUrl();
        log.info("login() : token = {}, clientUrl = {}", token, clientUrl);

        // 用户使用token、clientUrl代表需要查询是否已登录
        // tokenAndUrlMap中包含key代表已登录
        if (isNotEmpty(token, clientUrl) && tokenAndUrlMap.containsKey(token)) {
            log.info("token = {} 用户已登陆", token);
            // 将信息反馈给Client
            Data data = transmitToken(token, clientUrl);
            tokenAndSessionId.put(token, data.getAuthToken());
            return data;
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

        // 说明参数检验不通过，抛出参数错误异常
        throw new CheckException(UserStatusEnum.PARAMETER_ERROR.getMsg());
    }

    /**
     * 用户登录逻辑实现
     * @param user 用户信息:account、password
     * @param clientUrl Client的url
     * @return token、clientUrl、authToken
     */
    private Data loginImpl(User user, String clientUrl) {
        String account = user.getAccount();
        String password = user.getPassword();

        // 查询数据库，若无此用户数据则抛出账号错误异常
        user = userDao.listUserByUAccountAndPassword(account, password);
        if (!checkNull(user)) {
            throw new CheckException(UserStatusEnum.USER_ACCOUNT_ERROR.getMsg());
        }

        // 使用uuid生成token并存入tokenMap中，注意此时并没有注册clientUrl
        String token = UUID.randomUUID().toString();
        tokenAndUrlMap.put(token, new ArrayList<>());
        tokenAndUserMap.put(token, user);

        // 将信息反馈给Client、
        Data data = transmitToken(token, clientUrl);
        tokenAndSessionId.put(token, data.getAuthToken());

        log.info("loginImpl() 登录成功：token = {}, clientUrl = {}, sesssionId = {}, User = {}", token, clientUrl, tokenAndSessionId.get(token), user);
        return data;
    }

    private void saveUrl(String token, String clientUrl) {
        boolean hasSave = false;
        List<String> clientUrlList = tokenAndUrlMap.get(token);
        for (String url : clientUrlList) {
            if (url.contains(clientUrl)) {
                hasSave = true;
            }
        }
        if (!hasSave) {
            clientUrlList.add(clientUrl);
        }
    }

    /**
     * 用以将生成的token或者以验证登陆的token传递回去给Client
     *
     * @param token     令牌
     * @param clientUrl Client的url
     */
    private Data transmitToken(String token, String clientUrl) {
        try {
            return (httpClientUtil.postAction(clientUrl + "/user/token", new RequestBean().setToken(token).setClientUrl(clientUrl)).getData());
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

    /**
     * 检验基本的token、clientUrl参数的合法性
     * @param requestBean token、clientUrl
     * @return 操作结果，成功data为null
     */
    @Override
    public Data valid(RequestBean requestBean) {
        String token = requestBean.getToken();
        String clientUrl = requestBean.getClientUrl();
        if (isNotEmpty(token, clientUrl)) {
            return validImpl(token, clientUrl);
        }

        throw new CheckException(localeMessageSourceUtil.getMessage(UserStatusEnum.PARAMETER_ERROR.getMsg()));
    }

    /**
     * 验证token是否存在，若存在则请求注销所有子系统的局部变量并且销毁token
     * @param requestBean token 令牌凭证
     * @return 操作结果
     */
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

    /**
     * 验证来自服务器的token与clientUrl参数合法性,
     * @param requestBean token、clientUrl
     * @return 操作结果，成功data为带token与clientUrl
     */
    @Override
    public Data token(RequestBean requestBean) {
        String token = requestBean.getToken();
        String clientUrl = requestBean.getClientUrl();
        if (isNotEmpty(token, clientUrl)) {
            return tokenImpl(token, clientUrl);
        }
        throw new CheckException(UserStatusEnum.PARAMETER_ERROR.getMsg());
    }

    /**
     * 验证来自服务器的token与clientUrl,
     * @param requestBean token、clientUrl
     * @return 操作结果，成功data为带token与clientUrl
     */
    @Override
    public Data subLogout(RequestBean requestBean) {
        String xAuthToken = requestBean.getAuthToken();
        log.info("subLogout() : xAuthToken = {}", xAuthToken);
        if (isNotEmpty(xAuthToken)) {
            return subLogoutImpl(xAuthToken);
        }
        throw new CheckException(UserStatusEnum.PARAMETER_ERROR.getMsg());
    }

    /**
     * 通过redis直接删除局部变量在redis数据库中的user属性
     * @param xAuthToken x-auth-token 相当于SESSIONID
     * @return 操作成功
     */
    private Data subLogoutImpl(String xAuthToken) {
        redisTemplate.opsForList().getOperations().delete("spring:session:sessions:" + xAuthToken);
        return new Data();
    }

    private Data tokenImpl(String token, String clientUrl) {
        if (remoteValid(token, clientUrl)) {
            return new Data().setToken(token).setClientUrl(clientUrl);
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
            if ((httpClientUtil.postAction("http://localhost:8889/user/valid", new RequestBean().setToken(token).setClientUrl(clientUrl))).getCode() == 0) {
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
            httpClientUtil.postAction(clientUrl + "/user/sublogout", new RequestBean().setAuthToken(tokenAndSessionId.get(token)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证客户端的token与clientUrl是否合法,若合法则将客户端的clientUrl注册到token中
     * @param token  令牌，用户SSO认证中心登录的凭证
     * @param clientUrl 子系统的url
     * @return 操作结果，成功data为null
     */
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
