package java.com.shengsiyuan.netty.p2.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.SocketAddress;
import java.time.LocalDateTime;

public class ServerChannelHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        SocketAddress sa = ctx.channel().remoteAddress();
        System.out.println("receive msg from client " + sa + ": " + msg);
        ctx.writeAndFlush(LocalDateTime.now() + "");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
