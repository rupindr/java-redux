package com.rupindr.javaredux;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @param <T> custom state class
 */
public class Store<T extends State> {

    private Map<String, ReducerBase<T>> reducers;

    public Store() {
        reducers = new HashMap<>();
    }

    /**
     * @return current state
     */
    public Map<String, T> getState() {
        return reducers.entrySet().stream().collect(HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue().state), HashMap::putAll);
    }

    /**
     * @param name reducer name
     * @param reducer reducer object
     * @param initState initial state
     */
    public void registerReducer(String name, Reducer<T> reducer, T initState) {
        reducers.put(name, new ReducerBase<>(name, reducer, initState));
    }

    /**
     * @param name reducer name
     * @param reducer reducer object
     */
    public void registerReducer(String name, Reducer<T> reducer) {
        registerReducer(name, reducer, null);
    }

    /**
     * @param action action object
     */
    public void dispatch(Action<?> action) {
        for (Map.Entry<String, ReducerBase<T>> reducerEntry : reducers.entrySet()) {
            ReducerBase<T> reducerBase = reducerEntry.getValue();
            T currState = reducerBase.state;
            T newState = reducerBase.reducer.reduce(action, reducerBase.state);
            boolean isChanged = (currState != null && currState.compareTo(newState) != 0) || (currState == null && newState != null);
            reducerBase.state = newState;
            if (isChanged) reducerBase.execSubscribers(currState);
        }
    }

    /**
     * @param name reducer name
     * @param fn subscriber
     */
    public void subscribe(String name, Subscriber<T, T> fn) {
        if (!reducers.containsKey(name)) {
            throw new NullPointerException("No reducer registered with name: " + name);
        }
        reducers.get(name).subscribe(fn);
    }

    /**
     * @param name reducer name
     * @param fn subscriber
     */
    public void subscribe(String name, Consumer<T> fn) {
        if (!reducers.containsKey(name)) {
            throw new NullPointerException("No reducer registered with name: " + name);
        }
        reducers.get(name).subscribe(fn);
    }

}
