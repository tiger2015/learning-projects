package com.tiger.socket;


import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

public class ByteBufferTest {

    public static void main(String[] args) throws CharacterCodingException {
        ByteBuffer buffer = ByteBuffer.allocate(100);
        pringBufferInfo(buffer);
        buffer.put((byte) 0x41);
        buffer.put((byte) 0x32);
        pringBufferInfo(buffer);
        buffer.flip();
        pringBufferInfo(buffer);
        buffer.mark();
        System.out.println(Charset.forName("UTF-8").newDecoder().decode(buffer));
        pringBufferInfo(buffer);
        buffer.reset();
        pringBufferInfo(buffer);


    }


    public static void pringBufferInfo(ByteBuffer buffer) {
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("remain:" + buffer.remaining()); // 缓存中剩余的字节数
        System.out.println("============");
    }


}
