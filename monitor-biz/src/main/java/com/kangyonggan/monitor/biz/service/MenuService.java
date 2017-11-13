package com.kangyonggan.monitor.biz.service;

import com.kangyonggan.monitor.model.vo.Menu;

import java.util.List;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
public interface MenuService {

    /**
     * 查找用户菜单
     *
     * @param username
     * @return
     */
    List<Menu> findMenusByUsername(String username);

}
