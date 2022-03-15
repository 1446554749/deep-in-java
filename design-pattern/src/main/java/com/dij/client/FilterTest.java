package com.dij.client;

import com.dij.pattern.AbstractHandler;
import com.dij.pattern.HandlerChain;

public class FilterTest {
    public static void main(String[] args) {
        //框架代码没有违反开闭原则，但是客户端代码需要维护chain的定义
        HandlerChain<Player> chain = new HandlerChain<>();
        chain.addHandler(new CheckNameHandler());
        chain.addHandler(new CheckSexHandler());
        chain.handle(new Player());
    }

    public static <T> HandlerChain<T> initChain(T data) {
        //利用反射和注解,扫描handler,然后让客户端直接使用chain,无需更改客户端代码

        return new HandlerChain<>();
    }

}

class Player {
    public String name = "sdf";
    public Integer sex = 1;
}

class CheckNameHandler extends AbstractHandler<Player> {

    @Override
    protected void doHandle(Player data) {
        if (!data.name.equals("asd")) {
            throw new RuntimeException("name not equal asd");
        }
    }
}

class CheckSexHandler extends AbstractHandler<Player> {

    @Override
    protected void doHandle(Player data) {
        if (data.sex != 1) {
            throw new RuntimeException("sex is not 1");
        }
    }
}
