package it.enricod.redux;

/**
 * This interface defines a Middleware, which is a consumer of three arguments:
 * 1. the store itself
 * 2. the action which is to be dispatched
 * 3. the next middleware in the chain of middlewares.
 *
 */
@FunctionalInterface
public interface Middleware<State, Action>
		extends TriConsumer<State, Action, Middleware<State,Action>> {
}
