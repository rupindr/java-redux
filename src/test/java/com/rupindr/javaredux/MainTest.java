package com.rupindr.javaredux;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

class MyState implements State {

    public String value = "";

    @Override
    public int compareTo(Object o) {
        MyState myState = (MyState) o;
        return this.value.equals(myState.value) ? 0 : 1;
    }
}

public class MainTest {

    @Test
    public void subscribeTest() {
        Store<MyState> store = new Store<>();
        store.registerReducer("main", (action, state) -> {
            MyState newState = new MyState();
            switch (action.getType()) {
                case "SET_VALUE":
                    newState.value = (String) action.getPayload();
                    return newState;
            }
            return state;
        }, new MyState());

        store.subscribe("main", state -> assertEquals("New Value", state.value));
        store.subscribe("main", (prevState, state) -> {
            assertEquals("", prevState.value);
            assertEquals("New Value", state.value);
        });

        store.dispatch(ActionCreator.create("SET_VALUE", "New Value"));

    }

    @Test
    public void testNullInitialState() {
        Store<MyState> store = new Store<>();
        store.registerReducer("main", (action, state) -> state);
        assertEquals(null, store.getState().get("main"));
        store.subscribe("main", (prevState, state) -> assertEquals(null, prevState));
        store.dispatch(ActionCreator.create("INIT", ""));
    }

    @Test
    public void testNonNullInitialState() {
        Store<MyState> store = new Store<>();
        store.registerReducer("main", (action, state) -> state, new MyState());
        assertNotEquals(null, store.getState().get("main"));
        store.subscribe("main", (prevState, state) -> assertNotEquals(null, prevState));
        store.dispatch(ActionCreator.create("INIT", ""));
    }

}
