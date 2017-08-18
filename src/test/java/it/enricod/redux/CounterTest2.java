package it.enricod.redux;

import io.vavr.Tuple;


import java.util.*;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CounterTest2 {


    private static Map<String, Object> initialState = new HashMap<String, Object>();

        public static void main(String[] args) {


            initialState.put("str", "foo");
            initialState.put("int", 1);
            initialState.put("list", new ArrayList<String>());
            Reducer<String, String> concatBar =
                    (action, state) -> "CONCAT".equals(action) ? state + "bar" : state;

            Reducer<String, Integer> plus2 =
                    (action, state) -> "PLUS".equals(action) ? state + 2 : state;

            Reducer<String, List<String>> addFoo = (action, state) -> {
                if ("ADD".equals(action))
                    state.add("foo");
                return state;
            };

            Reducer reducers = Redux.combineReducers(Tuple.of("str", concatBar), Tuple.of("int", plus2), Tuple.of("list", addFoo));


            Store<Map<String,Object>, String> store = Redux.createStore(initialState, reducers);

            assertThat(store.getState().get("str"), equalTo("foo"));
            store.dispatch("CONCAT");
            assertThat(store.getState().get("str"), equalTo("foobar"));

            assertThat(store.getState().get("int"), equalTo(1));
            store.dispatch("PLUS");
            assertThat(store.getState().get("int"), equalTo(3));
            store.dispatch("PLUS");
            assertThat(store.getState().get("int"), equalTo(5));

            store.dispatch("CONCAT");
            assertThat(store.getState().get("str"), equalTo("foobarbar"));

            assertThat(store.getState().get("list"), equalTo(Arrays.asList()));
            store.dispatch("ADD");
            assertThat(store.getState().get("list"), equalTo(Arrays.asList("foo")));
            store.dispatch("ADD");
            assertThat(store.getState().get("list"), equalTo(Arrays.asList("foo", "foo")));
        }




}
