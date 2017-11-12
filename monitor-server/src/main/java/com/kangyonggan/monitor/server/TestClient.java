package com.kangyonggan.monitor.server;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TestClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("127.0.0.1", 9917);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        while (true) {
            writer.write("000");
            writer.flush();
            socket.shutdownOutput();
            Thread.sleep(5000);
        }

    }

}
