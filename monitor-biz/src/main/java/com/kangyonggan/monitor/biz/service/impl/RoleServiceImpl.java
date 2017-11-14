package com.kangyonggan.monitor.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.extra.core.annotation.Log;
import com.kangyonggan.monitor.biz.service.RoleService;
import com.kangyonggan.monitor.mapper.RoleMapper;
import com.kangyonggan.monitor.model.constants.AppConstants;
import com.kangyonggan.monitor.model.vo.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
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
    public List<Role> findRolesByUsername(String username) {
        return roleMapper.selectRolesByUsername(username);
    }

    @Override
    @Log
    public boolean existsRoleCode(String code) {
        Role role = new Role();
        role.setCode(code);

        return super.exists(role);
    }

    @Override
    @Log
    public List<Role> findAllRoles() {
        Role role = new Role();
        role.setIsDeleted(AppConstants.IS_DELETED_NO);

        return myMapper.select(role);
    }

    @Override
    public List<Role> searchRoles(int pageNum, String code, String name) {
        Example example = new Example(Role.class);

        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(code)) {
            criteria.andEqualTo("code", code);
        }
        if (StringUtils.isNotEmpty(name)) {
            criteria.andEqualTo("name", name);
        }

        example.setOrderByClause("id desc");

        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        return myMapper.selectByExample(example);
    }

    @Override
    @Log
    public void saveRole(Role role) {
        myMapper.insertSelective(role);
    }

    @Override
    @Log
    public Role findRoleById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    @Log
    public void updateRole(Role role) {
        myMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    @Log
    public void updateRoleMenus(String code, String menuCodes) {
        deleteRoleMenus(code);

        if (StringUtils.isNotEmpty(menuCodes)) {
            roleMapper.insertRoleMenus(code, Arrays.asList(menuCodes.split(",")));
        }
    }

    @Override
    @Log
    public void deleteRoleById(Long id) {
        myMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除角色菜单
     *
     * @param code
     */
    private void deleteRoleMenus(String code) {
        roleMapper.deleteRoleMenus(code);
    }
}
