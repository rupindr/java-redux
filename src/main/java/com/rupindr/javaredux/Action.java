package com.rupindr.javaredux;

/**
 * @param <T>
 */
public class Action<T> {

    private final String type;
    private final T payload;

    /**
     * @param type
     * @param payload
     */
    public Action(String type, T payload) {
        this.type = type;
        this.payload = payload;
    }

    /**
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * @return
     */
    public T getPayload() {
        return payload;
    }

}
