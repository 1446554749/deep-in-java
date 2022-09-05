package netty.component;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;

import java.nio.charset.Charset;

public class HandlerTest {
    public static void main(String[] args) {
//        testInBoundHandler();
//        testOutBoundHandler();
        testEmbeddedChannel();
    }

    private static void testEmbeddedChannel() {
        ChannelInboundHandlerAdapter h1 = new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf byteBuf = (ByteBuf) msg;
                String s = byteBuf.toString(Charset.defaultCharset());
                System.out.println(s);
//                ctx.channel().writeAndFlush()
            }
        };

        ChannelOutboundHandlerAdapter h2 = new ChannelOutboundHandlerAdapter() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                ByteBuf byteBuf = (ByteBuf) msg;
                String s = byteBuf.toString(Charset.defaultCharset());
                System.out.println(s);
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(h1, h2);
        channel.writeInbound(ByteBufAllocator.DEFAULT.buffer().writeBytes("in bound".getBytes()));
        channel.writeOutbound(ByteBufAllocator.DEFAULT.buffer().writeBytes("out bound".getBytes()));

    }
}
