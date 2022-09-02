import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;


@Slf4j
public class NettyPromiseTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();

        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                int x = 1 / 0;
                promise.setSuccess(222);
            } catch (Exception e) {
                promise.setFailure(e);
            }
        }).start();


        try {
            Integer res = promise.get();
            log.info("result is {}", res);
        } finally {
            group.shutdownGracefully();
        }

    }

}
