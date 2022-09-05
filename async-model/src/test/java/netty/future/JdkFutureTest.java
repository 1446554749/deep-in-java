package netty.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class JdkFutureTest {

    public static void main(String[] args) {
        final ExecutorService pool = Executors.newFixedThreadPool(1);

        final Future<Integer> future = pool.submit(() -> {
            log.debug("compute");
            Thread.sleep(1000);
            return 1;
        });

        try {
            log.debug("wait for result");
            Integer res = future.get();
            log.debug("result is {}", res);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }

    }

}
