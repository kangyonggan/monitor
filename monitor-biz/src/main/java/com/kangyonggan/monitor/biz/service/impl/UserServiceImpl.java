package com.kangyonggan.monitor.biz.service.impl;

import com.kangyonggan.extra.core.annotation.Cache;
import com.kangyonggan.extra.core.annotation.Log;
import com.kangyonggan.monitor.biz.service.UserService;
import com.kangyonggan.monitor.model.vo.ShiroUser;
import com.kangyonggan.monitor.model.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Override
    @Log
    public ShiroUser getShiroUser() {
        return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }

    @Override
    @Log
    @Cache(key = "user:username:${username}")
    public User findUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }

        User user = new User();
        user.setUsername(username);
        return myMapper.selectOne(user);
    }
}
