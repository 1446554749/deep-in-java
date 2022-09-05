package netty.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class NettyFutureTest {

    public static void main(String[] args) {

        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();

        Future<Integer> future = eventLoop.submit(() -> {
            log.info("compute");
            Thread.sleep(1000);
            return 1;
        });

        try {
            log.info("wait for result");
            future.addListener((resFuture) -> {
                Integer res = (Integer) resFuture.get();
                log.info("result is {}", res);
            });
//            Integer res = future.get();
        } finally {
            group.shutdownGracefully();
        }

    }

}
