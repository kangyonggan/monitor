package com.kangyonggan.monitor.biz.service.impl;

import com.kangyonggan.monitor.biz.service.UserService;
import com.kangyonggan.monitor.model.vo.User;
import org.springframework.stereotype.Service;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Override
    public User findUserByUsername(String username) {
        return null;
    }
}
