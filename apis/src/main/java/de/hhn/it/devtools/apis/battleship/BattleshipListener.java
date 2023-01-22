package de.hhn.it.devtools.apis.battleship;


/**
 * Callback to notify observers about a state change of the game.
 */

public interface BattleshipListener {

    /**
     * to check if player who shot won, if yes new windows with the player that won
     * @param potentialWinner is the player that shot the last shot
     */
    void outputWinner(Player potentialWinner);


    /**
     * @param player the player whose number of ships is updated
     * updates the button text to indicate how many ships are left
     */
    void updateUnplacedShips(Player player);


    /**
     * if not all ships are placed the button to enter the FIRINGSHOTS state is made invisible
     */
    void updateFiringShotsButton();


    /**
     * when all ships are placed so that the button for state change to FIRINGSHOTS is displayed
     */
    void allShipsPlaced();


    /**
     * to update the field after a change
     */
    void updateField();


    /**
     * reset the appearance of the button so that it does not always look as if it is selected
     */
    void resetShipSelected();

}
