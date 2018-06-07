package com.zhuokai.netty.action.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 处理客户的请求
 * 重写channelRead、exceptionCaught、channelReadComplete方法
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    //处理请求，将接受到的信息写给发送者，而不冲刷出站消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String data = byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println("receive: " + data);
        ctx.write(byteBuf);
    }


    //当客户端的数据都读取完的时候，才将要回写的数据发出
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
    }

    //捕获异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
