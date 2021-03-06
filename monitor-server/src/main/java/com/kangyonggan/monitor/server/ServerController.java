package com.kangyonggan.monitor.server;

import com.kangyonggan.extra.core.annotation.Log;
import com.kangyonggan.extra.core.annotation.Monitor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
@RestController
@RequestMapping("server")
@Log4j2
public class ServerController {

    @Autowired
    private MonitorServer monitorServer;

    /**
     * 启动服务
     *
     * @return
     */
    @RequestMapping(value = "start", method = RequestMethod.GET)
    @Log
    public String startServer() {
        monitorServer.start();
        return "success";
    }

    /**
     * 停止服务
     *
     * @return
     */
    @RequestMapping(value = "stop", method = RequestMethod.GET)
    @Log
    public String stopServer() {
        monitorServer.stop();
        return "success";
    }

    /**
     * 测试监控
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "test1", method = RequestMethod.GET)
    @Monitor(type = "hello", description = "测试监控能不能落库")
    public String test1(@RequestParam(value = "name", required = false, defaultValue = "Monitor") String name) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            log.error(e);
        }

        return "Hello " + name;
    }

    /**
     * 测试监控
     *
     * @return
     */
    @RequestMapping(value = "test2", method = RequestMethod.GET)
    @Monitor(type = "hello", description = "测试监控没有入参")
    public String test2() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            log.error(e);
        }
        return "Hello Monitor";
    }

}
