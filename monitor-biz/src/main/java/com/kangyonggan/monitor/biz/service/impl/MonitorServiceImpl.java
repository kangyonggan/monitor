package com.kangyonggan.monitor.biz.service.impl;

import com.kangyonggan.monitor.biz.service.MonitorService;
import com.kangyonggan.monitor.model.vo.Monitor;
import org.springframework.stereotype.Service;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
@Service
public class MonitorServiceImpl extends BaseService<Monitor> implements MonitorService {

    @Override
    public void save(Monitor monitor) {
        myMapper.insertSelective(monitor);
    }
}
