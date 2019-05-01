package com.tiger.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

public class Server {
    private static final int port = 8088;

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false); // 设置为非阻塞模式
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); //注册选择器
            Handler handler = new Handler();

            new Thread(() -> {
                while (true) {
                    try {
                        selector.select();
                        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                        while (iterator.hasNext()) {
                            try {
                                SelectionKey selectionKey = iterator.next();
                                if (selectionKey.isAcceptable()) {   // 连接请求
                                    handler.handlerAccept(selectionKey);
                                } else if (selectionKey.isReadable()) { //有数据发送过来
                                    handler.handlerRead(selectionKey);

                                } else if (!selectionKey.isValid()) { // 断开连接
                                    handler.close(selectionKey);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, "Thread-accept").start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class Handler {

        public void handlerAccept(SelectionKey selectionKey) throws IOException {
            SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));
            System.out.println("receive client connection");
        }

        public void handlerRead(SelectionKey selectionKey) throws IOException {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
            if (channel.read(buffer) <= 0) {
                return;
            }
            buffer.flip();
            String message = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
            System.out.println("server receive:" + message);
            buffer.flip();
            channel.write(buffer);
            channel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));
        }

        public void close(SelectionKey selectionKey) throws IOException {
            selectionKey.channel().close();
            System.out.println("close connection");
        }
    }
}
