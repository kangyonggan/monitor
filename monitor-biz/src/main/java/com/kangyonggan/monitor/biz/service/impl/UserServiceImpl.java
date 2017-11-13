package com.kangyonggan.monitor.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.extra.core.annotation.Cache;
import com.kangyonggan.extra.core.annotation.CacheDel;
import com.kangyonggan.extra.core.annotation.Log;
import com.kangyonggan.monitor.biz.service.UserService;
import com.kangyonggan.monitor.biz.util.Digests;
import com.kangyonggan.monitor.biz.util.Encodes;
import com.kangyonggan.monitor.biz.util.StringUtil;
import com.kangyonggan.monitor.mapper.RoleMapper;
import com.kangyonggan.monitor.mapper.UserMapper;
import com.kangyonggan.monitor.model.constants.AppConstants;
import com.kangyonggan.monitor.model.vo.ShiroUser;
import com.kangyonggan.monitor.model.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

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

    @Override
    @Log
    public List<User> searchUsers(int pageNum, String username, String realname, String email) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(username)) {
            criteria.andLike("username", StringUtil.toLikeString(username));
        }

        if (StringUtils.isNotEmpty(realname)) {
            criteria.andLike("realname", StringUtil.toLikeString(realname));
        }

        if (StringUtils.isNotEmpty(email)) {
            criteria.andLike("email", StringUtil.toLikeString(email));
        }

        example.setOrderByClause("id desc");

        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        return myMapper.selectByExample(example);
    }

    @Override
    @Log
    public void saveUserWithDefaultRole(User user) {
        entryptPassword(user);

        if (StringUtils.isEmpty(user.getRealname())) {
            user.setRealname("未来");
        }

        myMapper.insertSelective(user);

        saveUserRoles(user.getUsername(), AppConstants.DEFAULT_ROLE_CODE);
    }

    @Override
    @Log
    @CacheDel(key = {"user:username:${user.username}", "role:username:${user.username}", "menu:username:${user.username}"})
    public void updateUserByUsername(User user) {
        if (StringUtils.isEmpty(user.getUsername())) {
            return;
        }

        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", user.getUsername());
        myMapper.updateByExampleSelective(user, example);
    }

    @Override
    public User findUserById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    @Log
    @CacheDel(key = "user:username:${user.username}")
    public void updateUserPassword(User user) {
        User tUser = new User();
        tUser.setUsername(user.getUsername());
        tUser.setPassword(user.getPassword());
        tUser.setSalt(user.getSalt());

        entryptPassword(tUser);

        updateUserByUsername(tUser);
    }

    @Override
    @Log
    @CacheDel(key = {"role:username:${username}", "menu:username:${username}"})
    public void updateUserRoles(String username, String roleCodes) {
        roleMapper.deleteAllRolesByUsername(username);

        if (StringUtils.isNotEmpty(roleCodes)) {
            saveUserRoles(username, roleCodes);
        }
    }

    @Override
    @Log
    public boolean existsUsername(String username) {
        User user = new User();
        user.setUsername(username);

        return super.exists(user);
    }

    /**
     * 批量保存用户角色
     *
     * @param username
     * @param roleCodes
     */
    private void saveUserRoles(String username, String roleCodes) {
        userMapper.insertUserRoles(username, Arrays.asList(roleCodes.split(",")));
    }

    /**
     * 设定安全的密码，生成随机的salt并经过N次 sha-1 hash
     *
     * @param user
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(AppConstants.SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, AppConstants.HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }
}
