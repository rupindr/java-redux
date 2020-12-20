package com.rupindr.javaredux;

/**
 * @param <T>
 */
public interface Reducer<T extends State> {

    /**
     * @param action
     * @param currentState
     * @return
     */
    T reduce(Action<?> action, T currentState);

}
