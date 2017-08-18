package it.enricod.redux;


import io.vavr.Function2;

/**
 * This represents a Reducer which is a function taking an action and a state and then return
 * the updated state.
 *
 */
@FunctionalInterface
public interface Reducer<Action, State> extends Function2<Action, State, State> {
}
