package com.kangyonggan.monitor.biz.service.impl;

import com.kangyonggan.monitor.biz.service.MenuService;
import com.kangyonggan.monitor.model.vo.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
@Service
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {

    @Override
    public List<Menu> findMenusByUsername(String username) {
        return null;
    }
}
