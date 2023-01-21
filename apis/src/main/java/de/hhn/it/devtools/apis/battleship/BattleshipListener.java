package de.hhn.it.devtools.apis.battleship;


/**
 * Callback to notify observers about a state change of the game.
 */

public interface BattleshipListener {

    //
    void outputWinner(Player potentialWinner);
    //
    void updateUnplacedShips();
    //
    void updateFiringShotsButton();

    //
    void allShipsPlaced();

    //
    void updateField();

    //
    void resetShipSelected();

}
