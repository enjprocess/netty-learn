package com.shengsiyuan.netty.p7.client;

import com.shengsiyuan.netty.p7.Center;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class ClientChannelHandler extends SimpleChannelInboundHandler<Center.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Center.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        int rand = new Random().nextInt(3);
        Center.Person person = null;
        switch (rand) {
            case 0:
                person = Center.Person.newBuilder().setDataType(Center.Person.DataType.DOG)
                        .setDog(Center.Dog.newBuilder().setName("wangcai").setAge(11).setAddress("homeless").build()).build();
                break;
            case 1:
                person = Center.Person.newBuilder().setDataType(Center.Person.DataType.CAT)
                        .setCat(Center.Cat.newBuilder().setName("gongyuanxun").setAge(12).setAddress("tiantang").build()).build();
                break;
            case 2:
                person = Center.Person.newBuilder().setDataType(Center.Person.DataType.MOUSE)
                        .setMouse(Center.Mouse.newBuilder().setName("gongsheng").setAge(13).setAddress("earth").build()).build();
                break;
        }
        channel.writeAndFlush(person);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
