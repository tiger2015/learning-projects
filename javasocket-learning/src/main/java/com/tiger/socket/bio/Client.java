package com.tiger.socket.bio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Client {

    public static void main(String[] args) {

        IntStream.range(0, 1024).forEach(i -> new Thread(() -> {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress("127.0.0.1", 8088), 3000);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                    char[] buffer = new char[1024];
                    while (true) {
                        if (socket.isClosed()) {
                            break;
                        }
                        writer.write("client" + i);
                        writer.flush();
                        int len = reader.read(buffer);
                        System.out.println("client-" + i + " receive:" + new String(buffer, 0, len));
                        TimeUnit.SECONDS.sleep(1);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }, "thread-client-" + i).start());

    }
}
