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


    /**
     * Informs the listener that a new field was created
     * @param createdField
     */
    void newFieldCreated(Field createdField);


    /**
     * Informs the listener that it's possible to place the ship at the current location
     * @param ship
     * @param possible true if possible
     */
    void outputPlacingPossible(Ship ship, boolean possible);


    /**
     * Informs the listener that a ship was successfully placed
     * @param ship
     */
    void outputShipPlaced(Ship ship);


    /**
     * Informs the listener that a ship is not placed anymore and can be moved
     * @param ship
     */
    void outputShipMovable(Ship ship);


    /**
     * Informs the listener that a bombing was (not)successful
     * @param bombedPosition
     * @param successful false if no ship on the bombedPosition and if the ship part was already bombed
     */
    void outputBombingSuccessful(Position bombedPosition, boolean successful);


    /**
     * Informs the listener that the player or the computer has won
     * @param playerWon true if player won, false if computer won
     */
    void outputWinner(boolean playerWon);
}
