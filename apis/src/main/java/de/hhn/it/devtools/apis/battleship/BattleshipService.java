package de.hhn.it.devtools.apis.battleship;

import java.util.IllegalFormatException;

/**
 *  This BattleshipService is an interface for the interaction of a player with components.
 */

public interface BattleshipService {


    /* Places ship at cursor-location */
    void placeShip() throws IllegalPositionException;

    /**
     * rotates the ship to left or right (front of the ship is the anchor point)
     */
    void rotateShip();

    /* Returns the selected Ship */
    Ship getSelectedShip();


    /**
     * Bombs selected panel
     * @return bombing (not)successful
     */
    boolean bombPanel();


    /**
     * sets the size of the field chosen in the game creation menu.
     * @param size width and height of the field
     * @param gameMode chosen game mode to see how many fields need to be created
     */
    void createFields(int size, GameMode gameMode);


    /**
     * sets the game mode chosen in the game creation menu.
     * @param chosenGameMode gameMode
     */
    void setGameMode(GameMode chosenGameMode);


    /* Returns the selected GameMode */
    GameMode getGameMode();


    /**
     * sets the game volume to newVolume.
     * @param newVolume value the game volume is set to
     */
    void adjustSoundVolume(int newVolume);


    /**
     * saves the current game.
     * @return current game state
     */
    GameState saveGame();


    /**
     * loads a saved game.
     * @param gameState game state of a saved game
     */
    void loadGame(GameState gameState) throws IllegalFormatException;


    /**
     * player concedes.
     */
    void concede();


    /**
     * displays the rules.
     */
    void displayRules();
}
