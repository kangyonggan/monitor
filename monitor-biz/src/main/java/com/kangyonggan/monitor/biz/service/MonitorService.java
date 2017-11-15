package com.kangyonggan.monitor.biz.service;

import com.kangyonggan.monitor.model.dto.MonitorDto;
import com.kangyonggan.monitor.model.vo.Monitor;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询实时监控
     *
     * @param app
     * @param type
     * @param packageName
     * @param className
     * @param methodName
     * @param beginTime
     * @param endTime
     * @return
     */
    MonitorDto findMonitorStat(String app, String type, String packageName, String className, String methodName, Long beginTime, Long endTime);
}
