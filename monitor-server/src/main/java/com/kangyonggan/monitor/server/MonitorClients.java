package com.kangyonggan.monitor.server;

import lombok.extern.log4j.Log4j2;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Log4j2
public class MonitorClients {

    private static volatile List<MonitorThread> monitors = new ArrayList();
    private static final long heartbeatInterval = 65000;

    static {
        init();
    }

    private static void init() {
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(heartbeatInterval);
                        Iterator<MonitorThread> iterator = monitors.iterator();
                        while (iterator.hasNext()) {
                            MonitorThread monitorThread = iterator.next();
                            if (monitorThread.isInvalid()) {
                                monitorThread.close();
                                iterator.remove();
                                log.info("Thread Is Dead, Removed {}", monitorThread);
                            }
                        }

                        log.info("Connected Count: {}", monitors.size());
                    } catch (Exception e) {
                        log.error("Check Connect Socket Exception", e);
                    }
                }
            }
        }.start();
    }

    public static void add(Socket socket) throws Exception {
        MonitorThread monitorThread = new MonitorThread(socket);
        monitors.add(monitorThread);
    }

    public static void remove(MonitorThread monitorThread) {
        monitorThread.close();
        monitors.remove(monitorThread);
    }

    public static List<MonitorThread> getMonitors() {
        return monitors;
    }
}
