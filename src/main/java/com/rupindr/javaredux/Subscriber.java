package com.rupindr.javaredux;

@FunctionalInterface
public interface Subscriber<T, U> {
    void apply(T t, U u);
}
