package com.rupindr.javaredux;

/**
 * @param <T> type of custom state
 */
public interface Reducer<T extends State> {

    /**
     * @param action action object
     * @param currentState current state
     * @return state object
     */
    T reduce(Action<?> action, T currentState);

}
