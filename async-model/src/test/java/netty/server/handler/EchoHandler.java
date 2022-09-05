package netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class EchoHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String s = buf.toString(Charset.defaultCharset());
        log.info("receive:{}", s);

        ByteBuf buffer = ctx.alloc()
                .buffer();
        buffer.writeBytes("hello".getBytes());
        buffer.writeBytes(s.getBytes());

        ctx.writeAndFlush(buffer);
        super.channelRead(ctx, msg);
    }
}
