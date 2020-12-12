package com.rupindr.javaredux;

public interface Reducer<T extends State> {

    T reduce(Action<?> action, T currentState);

}
