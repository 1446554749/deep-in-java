package com.dij.pattern;

import java.util.Optional;

public class HandlerChain<T> {
    private AbstractHandler<T> first = null;
    private AbstractHandler<T> end = null;

    public void addHandler(AbstractHandler<T> handler) {
        Optional.ofNullable(first)
                .ifPresentOrElse((end) -> {
                    end.setNext(handler);
                    end = handler;
                }, () -> {
                    first = handler;
                    end = handler;
                });
    }

    public void handle(T data) {
        first.handle(data);
    }
}
