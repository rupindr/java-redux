package com.rupindr.javaredux;

/**
 * @param <T>
 * @param <U>
 */
@FunctionalInterface
public interface Subscriber<T, U> {
    void apply(T t, U u);
}
