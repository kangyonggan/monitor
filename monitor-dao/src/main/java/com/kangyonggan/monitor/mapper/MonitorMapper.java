package com.kangyonggan.monitor.mapper;

import com.kangyonggan.monitor.model.dto.MonitorDto;
import com.kangyonggan.monitor.model.vo.Monitor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorMapper extends MyMapper<Monitor> {

    /**
     * 搜索监控记录
     *
     * @param app
     * @param type
     * @param packageName
     * @param className
     * @param methodName
     * @param minUsedTime
     * @param maxUsedTime
     * @return
     */
    List<Monitor> selectMonitors(@Param("app") String app, @Param("type") String type, @Param("packageName") String packageName, @Param("className") String className, @Param("methodName") String methodName, @Param("minUsedTime") Long minUsedTime, @Param("maxUsedTime") Long maxUsedTime);

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
    MonitorDto selectMonitorStat(@Param("app") String app, @Param("type") String type, @Param("packageName") String packageName, @Param("className") String className, @Param("methodName") String methodName, @Param("beginTime") Long beginTime, @Param("endTime") Long endTime);
}