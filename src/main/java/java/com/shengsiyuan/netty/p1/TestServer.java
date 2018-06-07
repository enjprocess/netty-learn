package java.com.shengsiyuan.netty.p1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

    public static void main(String[] args) throws InterruptedException {

            //定义线程组
            //只负责接受连接，接受后转由worker线程组完成真正的处理
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            //负责具体处理
            EventLoopGroup workerGroup = new NioEventLoopGroup();

        try { //使用netty提供的优雅关闭

            //netty提供的简化启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //绑定线程组、通道、定义子处理器
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new TestServerInitializer());

            //绑定服务端口
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            //监听服务的关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }



    }
}
