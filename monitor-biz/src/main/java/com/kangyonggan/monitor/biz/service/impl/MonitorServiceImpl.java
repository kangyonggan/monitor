package com.kangyonggan.monitor.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.extra.core.annotation.Log;
import com.kangyonggan.monitor.biz.service.MonitorService;
import com.kangyonggan.monitor.mapper.MonitorMapper;
import com.kangyonggan.monitor.model.constants.AppConstants;
import com.kangyonggan.monitor.model.dto.MonitorDto;
import com.kangyonggan.monitor.model.vo.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
@Service
public class MonitorServiceImpl extends BaseService<Monitor> implements MonitorService {

    @Autowired
    private MonitorMapper monitorMapper;

    @Override
    @Log
    public void save(Monitor monitor) {
        myMapper.insertSelective(monitor);
    }

    @Override
    @Log
    public List<Monitor> searchMonitors(int pageNum, String app, String type, String packageName, String className, String methodName, Long minUsedTime, Long maxUsedTime) {
        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        return monitorMapper.selectMonitors(app, type, packageName, className, methodName, minUsedTime, maxUsedTime);
    }

    @Override
    @Log
    public Monitor findMonitorById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    @Log
    public MonitorDto findMonitorStat(String app, String type, String packageName, String className, String methodName, Long beginTime, Long endTime) {
        return monitorMapper.selectMonitorStat(app, type, packageName, className, methodName, beginTime, endTime);
    }
}
