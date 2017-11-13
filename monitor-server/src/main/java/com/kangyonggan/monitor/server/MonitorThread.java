package com.kangyonggan.monitor.server;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

@Log4j2
public class MonitorThread {

    private Socket socket;
    private InputStream in;
    private boolean isRunning;
    private long lastRequestTime;

    public MonitorThread(Socket socket) throws Exception {
        this.socket = socket;
        lastRequestTime = System.currentTimeMillis();

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
                        int bodyLen = Integer.parseInt(header);
                        if (bodyLen > 0) {
                            byte bodyBuff[] = new byte[Integer.parseInt(header)];
                            in.read(bodyBuff);
                            String body = new String(bodyBuff);
                            log.info("Request Body: " + body);
                        }

                        lastRequestTime = System.currentTimeMillis();

                        // TODO 落库

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

}
