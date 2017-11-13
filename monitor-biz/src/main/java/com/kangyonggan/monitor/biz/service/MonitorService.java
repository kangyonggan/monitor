package com.kangyonggan.monitor.biz.service;

import com.kangyonggan.monitor.model.vo.Monitor;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
public interface MonitorService {

    /**
     * 保存监控记录
     *
     * @param monitor
     */
    void save(Monitor monitor);

}
