package com.rupindr.javaredux;

public class MyState implements State {

    public String name = "";

    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        MyState myState = (MyState) o;
        return this.name.equals(myState.name) ? 0 : 1;
    }
}
