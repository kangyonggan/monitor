package com.kangyonggan.monitor.biz.service;

import com.kangyonggan.monitor.model.vo.ShiroUser;
import com.kangyonggan.monitor.model.vo.User;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
public interface UserService {

    /**
     * 获取登录的用户信息
     *
     * @return
     */
    ShiroUser getShiroUser();

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);

}
