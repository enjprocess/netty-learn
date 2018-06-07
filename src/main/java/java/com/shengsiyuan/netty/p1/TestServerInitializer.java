package java.com.shengsiyuan.netty.p1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 连接注册后就会被创建，并且调用initChannel方法
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //注册ChannelHandler用来处理
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("middleWare", new MiddleWare());
        pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
    }
}
