package com.kangyonggan.monitor.server;

import com.alibaba.fastjson.JSONObject;
import com.kangyonggan.monitor.biz.service.MonitorService;
import com.kangyonggan.monitor.biz.util.SpringUtils;
import com.kangyonggan.monitor.model.dto.MonitorInfo;
import com.kangyonggan.monitor.model.vo.Monitor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

@Log4j2
public class MonitorThread {

    private Socket socket;
    private InputStream in;
    private boolean isRunning;
    private long lastRequestTime;
    private MonitorService monitorService;

    public MonitorThread(Socket socket) throws Exception {
        this.socket = socket;
        lastRequestTime = System.currentTimeMillis();
        monitorService = SpringUtils.getBean(MonitorService.class);

        try {
            in = socket.getInputStream();
            init();
        } catch (Exception e) {
            MonitorClients.remove(this);
            throw e;
        }
    }

    private void init() throws Exception {
        isRunning = true;
        new Thread() {
            public void run() {
                while (isRunning) {
                    try {
                        byte headerBuff[] = new byte[8];
                        in.read(headerBuff);
                        String header = new String(headerBuff);
                        log.info("Request Header: " + header);
                        lastRequestTime = System.currentTimeMillis();

                        int bodyLen = Integer.parseInt(header);
                        if (bodyLen > 0) {
                            byte bodyBuff[] = new byte[Integer.parseInt(header)];
                            in.read(bodyBuff);
                            String body = new String(bodyBuff);
                            log.info("Request Body: " + body);

                            MonitorInfo monitorInfo = JSONObject.parseObject(body, MonitorInfo.class);

                            // 落库
                            Monitor monitor = convertToMonitor(monitorInfo);
                            monitorService.save(monitor);
                        }

                    } catch (IOException e) {
                        log.error("Receiver Socket Message Exception", e);
                        MonitorClients.remove(MonitorThread.this);
                    }
                }
            }
        }.start();
    }

    public void close() {
        isRunning = false;
        try {
            if (in != null) {
                in.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            log.error("Close Monitor Thread Exception", e);
        }
    }

    public boolean isInvalid() {
        return System.currentTimeMillis() - lastRequestTime > 40000;
    }

    private static Monitor convertToMonitor(MonitorInfo monitorInfo) {
        Monitor monitor = new Monitor();
        monitor.setApp(monitorInfo.getApp());
        monitor.setType(monitorInfo.getType());
        monitor.setDescription(monitorInfo.getDescription());
        monitor.setPackageName(monitorInfo.getPackageName());
        monitor.setClassName(monitorInfo.getClassName());
        monitor.setMethodName(monitorInfo.getMethodName());
        monitor.setBeginTime(monitorInfo.getBeginTime());
        monitor.setEndTime(monitorInfo.getEndTime());
        monitor.setArgs(JSONObject.toJSONString(monitorInfo.getArgs()));
        monitor.setReturnValue(JSONObject.toJSONString(monitorInfo.getReturnValue()));

        if (StringUtils.isEmpty(monitor.getArgs())) {
            monitor.setArgs(StringUtils.EMPTY);
        }
        if (StringUtils.isEmpty(monitor.getReturnValue())) {
            monitor.setReturnValue(StringUtils.EMPTY);
        }

        return monitor;
    }

}
