package com.shengsiyuan.netty.p3.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ServerChannelHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    //当客户端发送消息后进行处理
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel currentChannel = ctx.channel();
        channelGroup.forEach((channel) -> {
            if (channel != currentChannel) {
                channel.writeAndFlush(currentChannel.remoteAddress() + ": " + msg + "\n");
            } else {
                channel.writeAndFlush("自己: " + msg + "\n");
            }
        });
    }

    //当连接建立好后，就进行广播
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】 " + channel.remoteAddress() + " 加入\n");
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】" + channel.remoteAddress() + " 退出\n");
        //netty会自动将断开连接的channel从channelGroup中移除
        System.out.println(channelGroup.size()); //验证这一想法
    }

    //服务器日志
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线");
    }
}
