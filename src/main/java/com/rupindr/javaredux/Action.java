package com.rupindr.javaredux;

/**
 * @param <T> type of payload
 */
public class Action<T> {

    private final String type;
    private final T payload;

    /**
     * @param type action type
     * @param payload payload
     */
    public Action(String type, T payload) {
        this.type = type;
        this.payload = payload;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @return payload
     */
    public T getPayload() {
        return payload;
    }

}
