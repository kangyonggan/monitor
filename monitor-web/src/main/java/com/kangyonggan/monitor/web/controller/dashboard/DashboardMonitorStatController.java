package com.kangyonggan.monitor.web.controller.dashboard;

import com.kangyonggan.monitor.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author kangyonggan
 * @since 11/14/17
 */
@Controller
@RequestMapping("dashboard/monitor/stat")
public class DashboardMonitorStatController extends BaseController {

    /**
     * 监控统计
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("MONITOR_STAT")
    public String list() {

        return getPathIndex();
    }

    /**
     * 实时监控初始化
     *
     * @param app
     * @param type
     * @param packageName
     * @param className
     * @param methodName
     * @param interval
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("MONITOR_STAT")
    @ResponseBody
    public Map<String, Object> init(@RequestParam(value = "app", required = false, defaultValue = "") String app,
                                    @RequestParam(value = "type", required = false, defaultValue = "") String type,
                                    @RequestParam(value = "packageName", required = false, defaultValue = "") String packageName,
                                    @RequestParam(value = "className", required = false, defaultValue = "") String className,
                                    @RequestParam(value = "methodName", required = false, defaultValue = "") String methodName,
                                    @RequestParam(value = "interval", required = false, defaultValue = "2") String interval
                                    ) {

        return getResultMap();
    }

}
