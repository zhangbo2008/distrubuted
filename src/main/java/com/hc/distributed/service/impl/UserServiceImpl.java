package com.hc.distributed.service.impl;

import com.hc.distributed.constant.UserStatusEnum;
import com.hc.distributed.dao.UserDao;
import com.hc.distributed.dtos.Data;
import com.hc.distributed.dtos.RequestBean;
import com.hc.distributed.exceptions.CheckException;
import com.hc.distributed.model.User;
import com.hc.distributed.service.UserService;
import com.hc.distributed.utils.LocaleMessageSourceUtil;
import com.hc.distributed.utils.UserVerfiyUtil;
import com.hc.distributed.utils.VerifyUtil;
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
        if (VerifyUtil.checkNull(user) && VerifyUtil.isEmpty(user.getAccount())
                && VerifyUtil.isEmpty(user.getPassword())) {
            return loginImpl(user);
        }

        throw new CheckException(localeMessageSourceUtil.getMessage(UserStatusEnum.PARAMETER_ERROR.UserStatusEnum()));
    }

    private Data loginImpl(User user) {
        String account = user.getAccount();
        String password = user.getPassword();
        user = userDao.listUserByUAccountAndPassword(account, password);

        if (!UserVerfiyUtil.checkNull(user)) {
            throw new CheckException(localeMessageSourceUtil.getMessage(UserStatusEnum.USER_ACCOUNT_ERROR.UserStatusEnum()));
        }
        return new Data(user);
    }

    @Override
    public int register(RequestBean requestBean) {
        User user = requestBean.getUser();
        if (VerifyUtil.checkNull(user) && VerifyUtil.isEmpty(user.getAccount())
                && VerifyUtil.isEmpty(user.getPassword()) && VerifyUtil.isEmpty(user.getEmail())) {
            return registerImpl(user);
        }

        throw new CheckException(UserStatusEnum.PARAMETER_ERROR.UserStatusEnum());
    }

    private int registerImpl(User user) {
        try {
            return userDao.insertUser(user);
        } catch (Exception e) {
            throw new CheckException(UserStatusEnum.USER_HAS_REGISTER.UserStatusEnum());
        }
    }

}
