package com.rupindr.javaredux;

public class Action<T> {

    public String type;
    public T payload;

    public Action(String type, T payload) {
        this.type = type;
        this.payload = payload;
    }

}
