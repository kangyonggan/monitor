package com.kangyonggan.monitor.biz.service.impl;

import com.kangyonggan.extra.core.annotation.Cache;
import com.kangyonggan.extra.core.annotation.Log;
import com.kangyonggan.monitor.biz.service.RoleService;
import com.kangyonggan.monitor.mapper.RoleMapper;
import com.kangyonggan.monitor.model.vo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Log
    @Cache(key = "role:username:${username}")
    public List<Role> findRolesByUsername(String username) {
        return roleMapper.selectRolesByUsername(username);
    }
}
