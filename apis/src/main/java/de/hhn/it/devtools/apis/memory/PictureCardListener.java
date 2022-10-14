package de.hhn.it.devtools.apis.memory;

/**
 * Callback to notify users about the changing state of a Card in the memory game.
 */
public interface PictureCardListener {

    /**
     * Informs the listener that the Card has changed its current state.
     * @param state
     */
    void currentState(State state);
}
