package java.com.shengsiyuan.netty.p4;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class ServerChannelHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            Channel channel = ctx.channel();
            IdleStateEvent ise = (IdleStateEvent) evt;
            String event = "";
            switch (ise.state()) {
                case READER_IDLE:
                    event = "读空闲";
                    break;
                case WRITER_IDLE:
                    event = "写空闲";
                    break;
                case ALL_IDLE:
                    event = "读写空闲";
                    break;
            }
            System.out.println(channel.remoteAddress() + " " + event);
            channel.close();
        }
    }
}
