package com.kangyonggan.monitor.biz.service;

import com.kangyonggan.monitor.model.vo.Role;

import java.util.List;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
public interface RoleService {

    /**
     * 查找用户角色
     *
     * @param username
     * @return
     */
    List<Role> findRolesByUsername(String username);


}
