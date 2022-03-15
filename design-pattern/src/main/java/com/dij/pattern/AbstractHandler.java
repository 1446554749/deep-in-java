package com.dij.pattern;


import java.util.Optional;

public abstract class AbstractHandler<T> {
    private AbstractHandler<T> next = null;

    protected abstract void doHandle(T data);

    public void setNext(AbstractHandler<T> handler) {
        this.next = handler;
    }

    public void handle(T data) {
        this.doHandle(data);
        Optional.ofNullable(next).ifPresent(
                it -> it.handle(data)
        );
    }
}
