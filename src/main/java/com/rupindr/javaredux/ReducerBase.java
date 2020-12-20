package com.rupindr.javaredux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @param <T>
 */
class ReducerBase<T extends State> {

    public String name;
    public Reducer<T> reducer;
    public T state;
    public List<Subscriber<T, T>> subscribers = new ArrayList<>();
    public List<Consumer<T>> functionalSubscribers = new ArrayList<>();

    /**
     * @param name
     * @param reducer
     * @param initState
     */
    public ReducerBase(String name, Reducer<T> reducer, T initState) {
        this.name = name;
        this.reducer = reducer;
        this.state = initState;
    }

    /**
     * @param fn
     */
    public void subscribe(Subscriber<T, T> fn) {
        subscribers.add(fn);
    }

    /**
     * @param fn
     */
    public void subscribe(Consumer<T> fn) {
        functionalSubscribers.add(fn);
    }

    /**
     * @param prevState
     */
    public void execSubscribers(T prevState) {
        subscribers.forEach(s -> s.apply(prevState, this.state));
        functionalSubscribers.forEach(s -> s.accept(this.state));
    }

}
