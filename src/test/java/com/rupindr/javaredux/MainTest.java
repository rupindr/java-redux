package com.rupindr.javaredux;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

class AppState implements State {

    public String value = "";

    @Override
    public int compareTo(Object o) {
        AppState appState = (AppState) o;
        return this.value.equals(appState.value) ? 0 : 1;
    }
}

public class MainTest {

    @Test
    public void subscribeTest() {
        Store<AppState> store = new Store<>();
        store.registerReducer("main", (action, state) -> {
            AppState newState = new AppState();
            switch (action.getType()) {
                case "SET_VALUE":
                    newState.value = (String) action.getPayload();
                    return newState;
            }
            return state;
        }, new AppState());

        store.subscribe("main", state -> assertEquals("New Value", state.value));
        store.subscribe("main", (prevState, state) -> {
            assertEquals("", prevState.value);
            assertEquals("New Value", state.value);
        });

        store.dispatch(ActionCreator.create("SET_VALUE", "New Value"));

    }

    @Test
    public void testNullInitialState() {
        Store<AppState> store = new Store<>();
        store.registerReducer("main", (action, state) -> state);
        assertEquals(null, store.getState().get("main"));
        store.subscribe("main", (prevState, state) -> assertEquals(null, prevState));
        store.dispatch(ActionCreator.create("INIT", ""));
    }

    @Test
    public void testNonNullInitialState() {
        Store<AppState> store = new Store<>();
        store.registerReducer("main", (action, state) -> state, new AppState());
        assertNotEquals(null, store.getState().get("main"));
        store.subscribe("main", (prevState, state) -> assertNotEquals(null, prevState));
        store.dispatch(ActionCreator.create("INIT", ""));
    }

    @Test
    public void testGetStateWithNoReducer() {
        Store<AppState> store = new Store<>();
        assertEquals(0, store.getState().entrySet().size());
    }

}
