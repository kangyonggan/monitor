package com.kangyonggan.monitor.biz.service;

import com.kangyonggan.monitor.model.vo.Menu;

import java.util.List;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
public interface MenuService {

    List<Menu> findMenusByUsername(String username);

}
