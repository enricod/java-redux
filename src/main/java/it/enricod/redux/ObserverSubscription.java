package it.enricod.redux;

import java.util.Observer;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * This represents part of a store, regarding the subscribing/un-subscribing using
 * {@link java.util.Observer}.
 *
 */
public interface ObserverSubscription {
	/**
	 * Subscribe a consumer of the state.
	 *
	 * @param observer the observer.
	 */
	void subscribe(Observer observer);

	/**
	 * Un-subscribe a consumer from this store.
	 *
	 * @param observer the observer to be removed from the list of observers.
	 */
	void unsubscribe(Observer observer);
}
