package com.kangyonggan.monitor.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MonitorThread {

    private Socket socket;
    private BufferedReader reader;
    private boolean isRunning;
    private long lastRequestTime;

    public MonitorThread(Socket socket) throws Exception {
        this.socket = socket;
        lastRequestTime = System.currentTimeMillis();

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            init();
        } catch (Exception e) {
            close();
            throw e;
        }
    }

    private void init() throws Exception {
        isRunning = true;
        new Thread() {
            public void run() {
                while (isRunning) {
                    System.out.println("================================");
                    byte buff[] = new byte[1024];
                    StringBuilder request = new StringBuilder();
                    try {
//                        int len = in.read(buff);
//
//                        while (len != -1) {
//                            request.append(new String(buff, 0, len));
//                            len = in.read(buff);
//                        }
                        String line;
                        System.out.println(reader.ready());;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }

                        lastRequestTime = System.currentTimeMillis();
//                        System.out.println("receiver:" + line);


                    } catch (IOException e) {
                        e.printStackTrace();
                        close();
                    }
                }
            }
        }.start();
    }

    public void close() {
        System.out.println("close");
        isRunning = false;
        try {
            if (reader != null) {
                reader.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isInvalid() {
        return System.currentTimeMillis() - lastRequestTime > 40000;
    }

}
