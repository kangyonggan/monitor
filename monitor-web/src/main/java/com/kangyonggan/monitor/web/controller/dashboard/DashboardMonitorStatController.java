package com.kangyonggan.monitor.web.controller.dashboard;

import com.kangyonggan.monitor.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
