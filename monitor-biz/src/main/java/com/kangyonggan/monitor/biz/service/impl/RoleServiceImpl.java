package com.kangyonggan.monitor.biz.service.impl;

import com.kangyonggan.monitor.biz.service.RoleService;
import com.kangyonggan.monitor.model.vo.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Override
    public List<Role> findRolesByUsername(String username) {
        return null;
    }
}
