package com.kangyonggan.monitor.web.controller.dashboard;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.monitor.biz.service.MonitorService;
import com.kangyonggan.monitor.model.vo.Monitor;
import com.kangyonggan.monitor.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author kangyonggan
 * @since 11/14/17
 */
@Controller
@RequestMapping("dashboard/monitor/query")
public class DashboardMonitorQueryController extends BaseController {

    @Autowired
    private MonitorService monitorService;

    /**
     * 监控查询
     *
     * @param pageNum
     * @param app
     * @param type
     * @param packageName
     * @param className
     * @param methodName
     * @param minUsedTime
     * @param maxUsedTime
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("MONITOR_QUERY")
    public String list(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                       @RequestParam(value = "app", required = false, defaultValue = "") String app,
                       @RequestParam(value = "type", required = false, defaultValue = "") String type,
                       @RequestParam(value = "packageName", required = false, defaultValue = "") String packageName,
                       @RequestParam(value = "className", required = false, defaultValue = "") String className,
                       @RequestParam(value = "methodName", required = false, defaultValue = "") String methodName,
                       @RequestParam(value = "minUsedTime", required = false, defaultValue = "0") Long minUsedTime,
                       @RequestParam(value = "maxUsedTime", required = false, defaultValue = "0") Long maxUsedTime,
                       Model model) {
        List<Monitor> monitors = monitorService.searchMonitors(pageNum, app, type, packageName, className, methodName, minUsedTime, maxUsedTime);
        PageInfo<Monitor> page = new PageInfo(monitors);

        model.addAttribute("page", page);
        return getPathList();
    }


    /**
     * 监控详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}", method = RequestMethod.GET)
    @RequiresPermissions("MONITOR_QUERY")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("monitor", monitorService.findMonitorById(id));
        return getPathDetailModal();
    }

}
