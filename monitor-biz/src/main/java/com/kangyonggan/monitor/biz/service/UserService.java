package com.kangyonggan.monitor.biz.service;

import com.kangyonggan.monitor.model.vo.User;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
public interface UserService {

    User findUserByUsername(String username);

}
