package com.kangyonggan.monitor.web.controller.dashboard;

import com.kangyonggan.monitor.biz.service.MonitorService;
import com.kangyonggan.monitor.model.dto.MonitorDto;
import com.kangyonggan.monitor.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 11/14/17
 */
@Controller
@RequestMapping("dashboard/monitor/stat")
public class DashboardMonitorStatController extends BaseController {

    @Autowired
    private MonitorService monitorService;

    /**
     * 实时监控
     *
     * @param app
     * @param type
     * @param packageName
     * @param className
     * @param methodName
     * @param interval    间隔时间， 单位是秒
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("MONITOR_STAT")
    public String list(@RequestParam(value = "app", required = false, defaultValue = "") String app,
                       @RequestParam(value = "type", required = false, defaultValue = "") String type,
                       @RequestParam(value = "packageName", required = false, defaultValue = "") String packageName,
                       @RequestParam(value = "className", required = false, defaultValue = "") String className,
                       @RequestParam(value = "methodName", required = false, defaultValue = "") String methodName,
                       @RequestParam(value = "interval", required = false, defaultValue = "2") Long interval,
                       @RequestParam(value = "intervalCount", required = false, defaultValue = "5") Integer intervalCount,
                       Model model) {
        Long now = System.currentTimeMillis();
        model.addAttribute("lastTime", now);

        List<MonitorDto> monitorDtos = new ArrayList();
        for (int i = intervalCount; i > 0; i--) {
            MonitorDto monitorDto = monitorService.findMonitorStat(app, type, packageName, className, methodName, now - interval * 1000, now);
            monitorDto.setBeginTime(now - interval * 1000);
            monitorDto.setEndTime(now);
            monitorDtos.add(monitorDto);

            now -= interval * 1000;
        }

        model.addAttribute("monitors", monitorDtos);
        return getPathIndex();
    }

    /**
     * 下一次的实时监控
     *
     * @param app
     * @param type
     * @param packageName
     * @param className
     * @param methodName
     * @param interval
     * @param nextTime
     * @return
     */
    @RequestMapping(value = "next", method = RequestMethod.GET)
    @RequiresPermissions("MONITOR_STAT")
    @ResponseBody
    public Map<String, Object> nextMonitor(@RequestParam(value = "app", required = false, defaultValue = "") String app,
                                           @RequestParam(value = "type", required = false, defaultValue = "") String type,
                                           @RequestParam(value = "packageName", required = false, defaultValue = "") String packageName,
                                           @RequestParam(value = "className", required = false, defaultValue = "") String className,
                                           @RequestParam(value = "methodName", required = false, defaultValue = "") String methodName,
                                           @RequestParam(value = "interval", required = false, defaultValue = "2") Long interval,
                                           @RequestParam("nextTime") Long nextTime) {
        Map<String, Object> resultMap = getResultMap();
        MonitorDto monitorDto = monitorService.findMonitorStat(app, type, packageName, className, methodName, nextTime - interval * 1000, nextTime);
        monitorDto.setBeginTime(nextTime - interval * 1000);
        monitorDto.setEndTime(nextTime);

        resultMap.put("nextMonitor", monitorDto);
        resultMap.put("lastTime", nextTime + interval * 1000);
        return resultMap;
    }

}
