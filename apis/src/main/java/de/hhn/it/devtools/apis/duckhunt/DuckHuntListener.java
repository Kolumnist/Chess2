package de.hhn.it.devtools.apis.duckhunt;

/**
 * Callback to notify observers about the change of GameState
 */
public interface DuckHuntListener {
    /**
     * Informs the listener that DuckHunt has changed its state
     *
     * @param gameState hands over game-information
     */
    void newState(GameState gameState);
}
