package com.tiger.socket.bio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {
    private static final int port = 8088;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(port));

            new Thread(() -> {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        threadPool.execute(new Handler(socket));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, "Thread-accept").start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Handler implements Runnable {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                char[] buffer = new char[1024];
                while (true) {
                    if (socket.isClosed()) {
                        break;
                    }
                    int len = reader.read(buffer);
                    System.out.println("server receive:" + new String(buffer, 0, len));
                    writer.write("hello!");
                    writer.flush();
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
        }
    }

}
