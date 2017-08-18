package it.enricod.redux;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

public class CounterTest1 {
    // Actions
    static final String INC = "INC";
    static final String DEC = "DEC";

    // this is our reducer which increments if INC, decrement if DEC
    // and does nothing otherwise
    final Reducer<String, Integer> reducer = (action, state) -> {
        return state + Match(action).of(
                Case($(INC), 1),
                Case($(DEC), -1),
                Case($(), 0)
        );
    };

    final Middleware<Integer, String> middleware = ( state,
                                                                    action,
                                                                    next) -> {
        System.out.println("Before1 " + state);
        next.accept(state, action, null);
        System.out.println("After1 " +state);
    };

    final Middleware<Integer, String> middleware2 = ( state,
                                                     action,
                                                     next) -> {
        System.out.println("Before2 " + state);
        next.accept(state, action, null);
        System.out.println("After2 " +state);
    };

    public void foo() {
        // This is our store with its initial state of zero and the reducer seen above
        Store<Integer, String> store = Redux.createStore(0, reducer,middleware, middleware2);

        // dispatch an INC action
        store.dispatch(INC);
        store.getState(); // 1

        // dispatch an DEC action
        store.dispatch(DEC);
        store.getState(); // 0
    }

    public static void main (String[] args) {
        CounterTest1 counter = new CounterTest1();
        counter.foo();
    }
}