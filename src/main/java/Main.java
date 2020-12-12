import com.rupindr.javaredux.Action;
import com.rupindr.javaredux.MyState;
import com.rupindr.javaredux.Store;

public class Main {
    private static Action<String> setNameAction(String name) {
        return new Action<>("SET_NAME", name);
    }
    public static void main(String[] args) {
        Store<MyState> store = new Store<>();
        store.registerReducer("main", (action, state) -> {
            MyState newState = new MyState();
            switch (action.type) {
                case "SET_NAME":
                    newState.name = (String) action.payload;
                    return newState;
            }
            return state;
        });
        store.subscribe("main", System.out::println);
        store.dispatch(setNameAction("Hello"));
        store.dispatch(setNameAction("There"));
    }
}
