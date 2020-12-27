package com.rupindr.javaredux;

/**
 * @param <T> type of first parameter
 * @param <U> type of second parameter
 */
@FunctionalInterface
public interface Subscriber<T, U> {
    void apply(T t, U u);
}
