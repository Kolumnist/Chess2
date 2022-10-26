package de.hhn.it.devtools.apis.battleship;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.State;

/**
 * Callback to notify observers about a state change of the game.
 */

public interface BattleshipListener {

    /**
     *  Informs the listener that the game has changed its state.
     * @param state actual state of the CoffeeMaker
     */
    void newState(GameState state);
}
