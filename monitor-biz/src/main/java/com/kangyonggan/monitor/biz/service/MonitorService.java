package com.kangyonggan.monitor.biz.service;

import com.kangyonggan.monitor.model.vo.Monitor;

import java.util.List;

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

    /**
     * 搜索监控记录
     *
     * @param pageNum
     * @param app
     * @param type
     * @param packageName
     * @param className
     * @param methodName
     * @param minUsedTime
     * @param maxUsedTime
     * @return
     */
    List<Monitor> searchMonitors(int pageNum, String app, String type, String packageName, String className, String methodName, Long minUsedTime, Long maxUsedTime);

    /**
     * 查找监控详情
     *
     * @param id
     * @return
     */
    Monitor findMonitorById(Long id);
}
