package com.rupindr.javaredux;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Store<T extends State> {

    private Map<String, ReducerBase<T>> reducers;

    public Store() {
        reducers = new HashMap<>();
    }

    public Map<String, T> getState() {
        return reducers.entrySet().stream().collect(HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue().state), HashMap::putAll);
    }

    public void registerReducer(String name, Reducer<T> reducer, T initState) {
        reducers.put(name, new ReducerBase<>(name, reducer, initState));
    }

    public void registerReducer(String name, Reducer<T> reducer) {
        registerReducer(name, reducer, null);
    }

    public void dispatch(Action<?> action) {
        for (Map.Entry<String, ReducerBase<T>> reducerEntry : reducers.entrySet()) {
            T currState = reducerEntry.getValue().state;
            T newState = reducerEntry.getValue().reducer.reduce(action, reducerEntry.getValue().state);
            boolean isChanged = (currState != null && currState.compareTo(newState) != 0) || (currState == null && newState != null);
            reducerEntry.getValue().state = newState;
            if (isChanged) reducerEntry.getValue().execSubscribers(currState);
        }
    }

    public void subscribe(String name, Subscriber<T, T> fn) {
        reducers.get(name).subscribe(fn);
    }

    public void subscribe(String name, Consumer<T> fn) {
        reducers.get(name).subscribe(fn);
    }

}
