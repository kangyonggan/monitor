package com.kangyonggan.monitor.server;

import java.io.IOException;
import java.net.ServerSocket;

public class MonitorServer {

    private ServerSocket serverSocket;

    public void init() {
        try {
            serverSocket = new ServerSocket(9917);
            System.out.println("Monitor Server was started at port 9917...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (serverSocket != null) {
            while (true) {
                try {
                    MonitorClients.add(serverSocket.accept());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new MonitorServer().init();
    }

}
