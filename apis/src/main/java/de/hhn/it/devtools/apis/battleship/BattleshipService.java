package de.hhn.it.devtools.apis.battleship;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.IllegalFormatException;

/**
 *  This BattleshipService is an interface for the interaction of a player with components.
 */

public interface BattleshipService {


    /**
     * Adds a listener to get updates on the state of the game.
     *
     * @param listener object implementing the listener interface
     * @throws IllegalParameterException if the the listener is null
     */
    void addCallBack(BattleshipListener listener) throws IllegalParameterException;


    /**
     * Removes a listener.
     *
     * @param listener listener to be removed
     * @throws IllegalParameterException if the the listener is null
     */
    void removeCallback(BattleshipListener listener) throws IllegalParameterException;


    /**
     * check if possible to place
     *
     * @param shipToPlace ship object
     * @param x1          x start coordinate of the ship
     * @param y1          y start coordinate of the ship
     * @param isVertical  true if ship is vertical, false if ship is horizontal
     * @return placing (not)possible
     * @throws IllegalGameStateException if the GameState is not at PLACINGSHIPS
     */
    boolean isPlacementPossible(Owner owner, Ship shipToPlace, int x1, int y1, boolean isVertical) throws IllegalGameStateException;


    /**
     * places ship at given location
     *
     * @param shipToPlace ship object
     * @param x1          x start coordinate of the ship
     * @param y1          y start coordinate of the ship
     * @throws IllegalPositionException  if ship is not allowed to place at location
     * @throws IllegalShipStateException if the ship is already placed
     * @throws IllegalGameStateException if the GameState is not at PLACINGSHIPS
     */
    void placeShip(Owner owner, Ship shipToPlace, int x1, int y1)
            throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException
    ;


    /**
     * allows the ship to be moved
     *
     * @param shipToMove selected ship to be moved
     * @throws IllegalGameStateException if the GameState is not at PLACINGSHIPS
     */
    void unPlace(Owner owner,Ship shipToMove) throws IllegalArgumentException, IllegalGameStateException;


    /**
     * rotates the ship horizontal or vertical (isHorizontal changes its boolean value)
     *
     * @param shipToRotate ship object to rotate around the ship fieldPosition
     * @throws IllegalPositionException  if ship is not allowed to rotate to certain location
     * @throws IllegalShipStateException ship cant be rotated if it's placed
     * @throws IllegalGameStateException if the GameState is not at PLACINGSHIPS
     */
    void rotateShip(Owner owner, Ship shipToRotate)
            throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException;


    /**
     * Bombs selected panel
     * @return bombing (not)successful
     * @param x x coordinate of the target panel
     * @param y y coordinate of the target panel
     * @param attacker player that performs the bombing
     * @throws IllegalArgumentException if field does not exist
     * @throws  IllegalGameStateException if the GameState is not FIRINGSHOTS
     */
    boolean bombPanel(Owner attacker,int x, int y) throws IllegalArgumentException, IllegalGameStateException;


    /**
     * sets the size of the field chosen in the game creation menu.
     * @param size width and height of the field
     * @throws IllegalArgumentException if player enters something else instead of numbers
     * @throws IllegalGameStateException if the Gamestate is not PREGAME
     */
    void createFields(int size) throws IllegalArgumentException, IllegalGameStateException;


    /**
     * sets the game volume to newVolume.
     * @param newVolume value the game volume is set to
     * @throws IllegalArgumentException if soundvolume is negative or over 100
     */
    void adjustSoundVolume(int newVolume) throws  IllegalArgumentException;


    /**
     * saves the current game.
     * @return current game state
     * @throws IllegalGameStateException if the gameState is PREGAME or GAMEOVER
     */
    SavedGame saveGame() throws IllegalGameStateException;


    /**
     * loads a saved game.
     * @param  savedGame state of a saved game
     * @throws IllegalFormatException when wrong file format is loaded
     */
    void loadGame(SavedGame savedGame) throws IllegalFormatException;


    /**
     * player concedes.
     *
     * @throws  IllegalGameStateException if the GAMESTATE is GAMEOVER
     */
    void concede() throws IllegalGameStateException;


    /**
     * displays the rules.
     * @return rules for the game
     */
    String displayRules();
}
