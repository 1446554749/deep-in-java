package netty.protocal;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * *3
 * $3
 * set
 * $4
 * name
 * $6
 * liming
 */
@Slf4j
public class RedisClient {

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        final byte[] LINE = new byte[]{13, 10};
        ChannelFuture future = new Bootstrap().channel(NioSocketChannel.class)
                .group(group)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        ByteBuf buf = ctx.alloc()
                                                .buffer();
                                        buf.writeBytes("*3".getBytes());
                                        buf.writeBytes(LINE);
                                        buf.writeBytes("$3".getBytes());
                                        buf.writeBytes(LINE);
                                        buf.writeBytes("set".getBytes());
                                        buf.writeBytes(LINE);
                                        buf.writeBytes("$4".getBytes());
                                        buf.writeBytes(LINE);
                                        buf.writeBytes("name".getBytes());
                                        buf.writeBytes(LINE);
                                        buf.writeBytes("$6".getBytes());
                                        buf.writeBytes(LINE);
                                        buf.writeBytes("liming".getBytes());
                                        buf.writeBytes(LINE);
                                        log.debug("write message to redis");
                                        ctx.writeAndFlush(buf);
                                        super.channelActive(ctx);
                                    }

                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf buf = (ByteBuf) msg;
                                        log.info("read message from redis");
                                        log.info(buf.toString(Charset.defaultCharset()));
                                        super.channelRead(ctx, msg);
                                    }
                                });
                    }
                })
                .connect("localhost", 6379);


        try {
            Channel channel = future.sync()
                    .channel();
            log.info("connect ok");
            channel.closeFuture()
                    .sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
