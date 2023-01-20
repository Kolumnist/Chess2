package de.hhn.it.devtools.javafx.battleship;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;

public class FXBattleshipListener implements BattleshipListener {

    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();

    /**
     * Informs the listener that the game has changed its state.
     *
     * @param state current state of the game
     */
    @Override
    public void newState(GameState state) {
        singletonAttributeStore.setAttribute("test", new CmpBattleshipService() {
        });
    }

    /**
     * Informs the listener that a new field was created
     *
     * @param createdField
     */
    @Override
    public void newFieldCreated(Field createdField) {

    }

    /**
     * Informs the listener that it's possible to place the ship at the current location
     *
     * @param ship
     * @param possible true if possible
     */
    @Override
    public void outputPlacingPossible(Ship ship, boolean possible) {

    }

    /**
     * Informs the listener that a ship was successfully placed
     *
     * @param ship
     */
    @Override
    public void outputShipPlaced(Ship ship) {
        CmpBattleshipService a =(CmpBattleshipService) singletonAttributeStore.getAttribute("test");
    }

    /**
     * Informs the listener that a ship is not placed anymore and can be moved
     *
     * @param ship
     */
    @Override
    public void outputShipMovable(Ship ship) {

    }

    /**
     * Informs the listener that a bombing was (not)successful
     *
     * @param bombedPosition
     * @param successful     false if no ship on the bombedPosition and if the ship part was already bombed
     */
    @Override
    public void outputBombingSuccessful(Position bombedPosition, boolean successful) {

    }

    /**
     * Informs the listener that the player or the computer has won
     *
     * @param playerWon true if player won, false if computer won
     */
    @Override
    public void outputWinner(boolean playerWon) {

    }
}
