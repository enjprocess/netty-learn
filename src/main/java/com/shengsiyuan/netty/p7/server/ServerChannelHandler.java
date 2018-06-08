package com.shengsiyuan.netty.p7.server;

import com.shengsiyuan.netty.p7.Center;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerChannelHandler extends SimpleChannelInboundHandler<Center.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Center.Person msg) throws Exception {
        switch(msg.getDataType()) {
            case DOG:
                Center.Dog dog = msg.getDog();
                System.out.println(dog.getName());
                System.out.println(dog.getAge());
                System.out.println(dog.getAddress());
                break;
            case CAT:
                Center.Cat cat = msg.getCat();
                System.out.println(cat.getName());
                System.out.println(cat.getAge());
                System.out.println(cat.getAddress());
                break;
            default:
                Center.Mouse mouse = msg.getMouse();
                System.out.println(mouse.getName());
                System.out.println(mouse.getAge());
                System.out.println(mouse.getAddress());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 已连接");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 已活跃");
    }
}
