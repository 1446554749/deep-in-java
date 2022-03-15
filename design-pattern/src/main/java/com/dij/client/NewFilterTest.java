package com.dij.client;

import com.dij.pattern.HandlerChain;

public class NewFilterTest {
    public static void main(String[] args) {
        HandlerChain<Player> chain = initChain();
        chain.handle(new Player());
    }

    public static <T> HandlerChain<T> initChain() {
        //利用反射和注解,扫描handler,然后让客户端直接使用chain,无需更改客户端代码

        return new HandlerChain<>();
    }

}