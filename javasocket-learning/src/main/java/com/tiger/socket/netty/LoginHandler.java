package com.tiger.socket.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            ByteBuf buffer = (ByteBuf) msg;
            int len = buffer.readableBytes();
            if (len > 0) {
                byte[] bytes = new byte[len];
                buffer.readBytes(bytes);
                log.info("login:" + new String(bytes));
                ctx.writeAndFlush(Unpooled.EMPTY_BUFFER); // 触发ChannelOutbound事件
//                ctx.fireChannelRead(msg); // 触发ChannelInbound事件
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warn("client exception, close connection", cause);
        ctx.close();
    }
}



