package com.kangyonggan.monitor.server;

import com.kangyonggan.monitor.biz.util.PropertiesUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;

@Log4j2
@Component
public class MonitorServer {

    private ServerSocket serverSocket;
    private boolean isRunning;

    public void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        try {
            int port = Integer.parseInt(PropertiesUtil.getPropertiesWithDefault("server.port", "9917"));
            serverSocket = new ServerSocket(port);
            log.info("Monitor Server Was Started At Port {}...", port);
        } catch (IOException e) {
            log.error("Monitor Server started Failure!", e);
        }

        if (serverSocket != null) {
            new Thread() {
                public void run() {
                    while (isRunning) {
                        try {
                            MonitorClients.add(serverSocket.accept());
                        } catch (Exception e) {
                            log.error("Monitor Client Connect Server Failure Or Server Closed!", e);
                        }
                    }
                }
            }.start();
        }
    }

    public void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;

        Iterator<MonitorThread> iterator = MonitorClients.getMonitors().iterator();
        while (iterator.hasNext()) {
            MonitorThread monitorThread = iterator.next();
            monitorThread.close();
            iterator.remove();
            log.info("Stop Thread {}", monitorThread);
        }

        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            log.error("Server Stop Exception", e);
        }
    }

}
