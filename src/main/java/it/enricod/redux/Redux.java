package it.enricod.redux;


import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import org.apache.commons.beanutils.PropertyUtilsBean;


/**
 * Main entry point for Redux4j.
 *
 */
public class Redux {
	private static final PropertyUtilsBean props = new PropertyUtilsBean();

	/**
	 * Creates a new Store.
	 *
	 * @param initialState the store initial state.
	 * @param reducer      the reducer function.
	 * @return a store.
	 */
	public static <State, Action> Store<State, Action> createStore(State initialState, Reducer<Action, State> reducer,
																   Middleware... middlewares) {
		return Store.create(initialState, reducer, middlewares);
	}

	/**
	 * Combine multiple reducers in one.
	 *
	 * @param reducers the reducers to be combined.
	 * @return a reducer combining the given reducers.
	 */
	@SafeVarargs
	public static <State> Reducer<Object, State> combineReducers(Tuple2<String, Reducer>... reducers) {
		Map<String, Reducer> allReducers = HashMap.ofEntries(reducers);

		return (action, state) -> {
			if (state instanceof Map) {
				Map<String, Object> s = (Map<String, Object>) state;
				allReducers.forEach((a, r) -> s.put(a, r.apply(action, s.get(a))));
			} else {
				allReducers.forEach((a, r) -> {
					try {
						props.setProperty(state, a, r.apply(action, props.getNestedProperty(state, a)));
					} catch (Throwable e) {
						e.printStackTrace();
					}
				});
			}
			return state;
		};
	}
}