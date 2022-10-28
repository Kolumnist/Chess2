package de.hhn.it.devtools.apis.battleship;


/**
 * Callback to notify observers about a state change of the game.
 */

public interface BattleshipListener {

    /**
     *  Informs the listener that the game has changed its state.
     * @param state current state of the game
     */
    void newState(GameState state);
}
