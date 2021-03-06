package com.hc.hcbasic.service.impl;

import com.hc.hcbasic.constant.UserStatusEnum;
import com.hc.hcbasic.dao.UserDao;
import com.hc.hcbasic.dtos.Data;
import com.hc.hcbasic.dtos.RequestBean;
import com.hc.hccommon.exceptions.CheckException;
import com.hc.hcbasic.model.User;
import com.hc.hcbasic.service.UserService;
import com.hc.hcbasic.utils.LocaleMessageSourceUtil;
import com.hc.hccommon.utils.UserVerfiyUtil;
import com.hc.hccommon.utils.VerifyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Resource
    private LocaleMessageSourceUtil localeMessageSourceUtil;

    @Override
    public Data login(RequestBean requestBean) {
        User user = requestBean.getUser();
        if (VerifyUtil.checkNull(user) && VerifyUtil.isNotEmpty(user.getAccount())
                && VerifyUtil.isNotEmpty(user.getPassword())) {
            return loginImpl(user);
        }

        throw new CheckException(localeMessageSourceUtil.getMessage(UserStatusEnum.PARAMETER_ERROR.getMsg()));
    }

    private Data loginImpl(User user) {
        String account = user.getAccount();
        String password = user.getPassword();
        user = userDao.listUserByUAccountAndPassword(account, password);

        if (!UserVerfiyUtil.checkNull(user)) {
            throw new CheckException(localeMessageSourceUtil.getMessage(UserStatusEnum.USER_ACCOUNT_ERROR.getMsg()));
        }
        return new Data(user);
    }

    @Override
    public int register(RequestBean requestBean) {
        User user = requestBean.getUser();
        if (VerifyUtil.checkNull(user) && VerifyUtil.isNotEmpty(user.getAccount())
                && VerifyUtil.isNotEmpty(user.getPassword()) && VerifyUtil.isNotEmpty(user.getEmail())) {
            return registerImpl(user);
        }

        throw new CheckException(UserStatusEnum.PARAMETER_ERROR.getMsg());
    }

    private int registerImpl(User user) {
        try {
            return userDao.insertUser(user);
        } catch (Exception e) {
            throw new CheckException(UserStatusEnum.USER_HAS_REGISTER.getMsg());
        }
    }

}
