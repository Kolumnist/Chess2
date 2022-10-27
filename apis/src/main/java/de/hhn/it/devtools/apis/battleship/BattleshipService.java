package de.hhn.it.devtools.apis.battleship;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService;

import java.util.IllegalFormatException;

/**
 *  This BattleshipService is an interface for the interaction of a player with components.
 */

public interface BattleshipService {


    /**
     * Adds a listener to get updates on the state of the game.
     *
     * @param listener object implementing the listener interface
     */
    void addCallBack(BattleshipListener listener);


    /**
     * places ship at given location
     * @param shipToPlace ship object
     * @param x1 x start coordinate of the ship
     * @param y1 y start coordinate of the ship
     * @param x2 x end coordinate of the ship
     * @param y2 y end coordinate of the ship
     * @throws IllegalPositionException if ship is not allowed to place at location
     */
    void placeShip(Ship shipToPlace,int x1, int y1, int x2, int y2) throws IllegalPositionException;


    /**
     * rotates the ship horizontal or vertical (isHorizontal changes its boolean value)
     * @param shipToRotate ship object to rotate around the ship fieldPosition
     */
    void rotateShip(Ship shipToRotate);


    /**
     * Bombs selected panel
     * @return bombing (not)successful
     * @param x x coordinate of the target panel
     * @param y y coordinate of the target panel
     * @throws IllegalArgumentException if field does not exist
     */
    boolean bombPanel(int x, int y) throws IllegalArgumentException;


    /**
     * sets the size of the field chosen in the game creation menu.
     * @param size width and height of the field
     * @param gameMode chosen game mode to see how many fields need to be created
     * @throws IllegalArgumentException if player enters something else instead of numbers
     */
    void createFields(int size, GameMode gameMode) throws IllegalArgumentException;


    /**
     * sets the game volume to newVolume.
     * @param newVolume value the game volume is set to
     * @throws IllegalArgumentException if soundvolume is negative or over 100
     */
    void adjustSoundVolume(int newVolume) throws  IllegalArgumentException;


    /**
     * saves the current game.
     * @return current game state
     */
    GameState saveGame();


    /**
     * loads a saved game.
     * @param gameState game state of a saved game
     * @throws IllegalFormatException when wrong file format is loaded
     */
    void loadGame(GameState gameState) throws IllegalFormatException;


    /**
     * player concedes.
     */
    void concede();


    /**
     * displays the rules.
     * @return rules for the game
     */
    String displayRules();
}
