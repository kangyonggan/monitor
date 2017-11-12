package com.kangyonggan.monitor.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MonitorClients {

    private static List<MonitorThread> monitors = new ArrayList();

    static {
        init();
    }

    private static void init() {
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                        Iterator<MonitorThread> iterator = monitors.iterator();
//                        while (iterator.hasNext()) {
//                            MonitorThread monitorThread = iterator.next();
//                            if (monitorThread.isInvalid()) {
//                                monitorThread.close();
//                                System.out.println("thread is dead, removed:" + monitorThread);
//                                iterator.remove();
//                            }
//                        }

                        System.out.println("has connect " + monitors.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void add(Socket socket) throws Exception {
        MonitorThread monitorThread = new MonitorThread(socket);
        monitors.add(monitorThread);
    }


}
