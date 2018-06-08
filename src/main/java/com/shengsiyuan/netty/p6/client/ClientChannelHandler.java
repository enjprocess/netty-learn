package com.shengsiyuan.netty.p6.client;

import com.shengsiyuan.netty.p6.Student;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientChannelHandler extends SimpleChannelInboundHandler<Student.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Student.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        Student.Person person = Student.Person.newBuilder().setName("lisi")
                .setAge(2)
                .setAddress("hangzhou").build();
        channel.writeAndFlush(person);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
