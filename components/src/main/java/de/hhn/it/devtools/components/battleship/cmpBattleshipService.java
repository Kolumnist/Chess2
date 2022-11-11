package de.hhn.it.devtools.components.battleship;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerListener;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import java.util.IllegalFormatException;

public class cmpBattleshipService implements BattleshipService {


    /**
     * Adds a listener to get updates on the state of the game.
     *
     * @param listener object implementing the listener interface
     * @throws IllegalParameterException if the the listener is null
     */
    @Override
    public void addCallBack(BattleshipListener listener) throws IllegalParameterException {

    }

    /**
     * Removes a listener.
     *
     * @param listener listener to be removed
     * @throws IllegalParameterException if the the listener is null
     */
    @Override
    public void removeCallback(BattleshipListener listener) throws IllegalParameterException {

    }

    /**
     * check if possible to place
     *
     * @param shipToPlace ship object
     * @param x1          x start coordinate of the ship
     * @param y1          y start coordinate of the ship
     * @return placing (not)possible
     * @throws IllegalGameStateException if the GameState is not at PLACINGSHIPS
     */
    @Override
    public boolean isPlacementPossible(Ship shipToPlace, int x1, int y1, boolean isVertical) throws IllegalGameStateException {
        return false;
    }

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
    @Override
    public void placeShip(Ship shipToPlace, int x1, int y1) throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException {

    }


    /**
     * allows the ship to be moved
     *
     * @param shipToMove selected ship to be moved
     * @throws IllegalGameStateException if the GameState is not at PLACINGSHIPS
     */
    @Override
    public void unPlace(Ship shipToMove) throws IllegalArgumentException, IllegalGameStateException {

    }

    /**
     * rotates the ship horizontal or vertical (isHorizontal changes its boolean value)
     *
     * @param shipToRotate ship object to rotate around the ship fieldPosition
     * @throws IllegalPositionException  if ship is not allowed to rotate to certain location
     * @throws IllegalShipStateException ship cant be rotated if it's placed
     * @throws IllegalGameStateException if the GameState is not at PLACINGSHIPS
     */
    @Override
    public void rotateShip(Ship shipToRotate) throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException {

    }

    /**
     * Bombs selected panel
     *
     * @param x x coordinate of the target panel
     * @param y y coordinate of the target panel
     * @return bombing (not)successful
     * @throws IllegalArgumentException  if field does not exist
     * @throws IllegalGameStateException if the GameState is not FIRINGSHOTS
     */
    @Override
    public boolean bombPanel(int x, int y) throws IllegalArgumentException, IllegalGameStateException {
        return false;
    }

    /**
     * sets the size of the field chosen in the game creation menu.
     *
     * @param size width and height of the field
     * @throws IllegalArgumentException  if player enters something else instead of numbers
     * @throws IllegalGameStateException if the Gamestate is not PREGAME
     */
    @Override
    public void createFields(int size) throws IllegalArgumentException, IllegalGameStateException {

    }

    /**
     * sets the game volume to newVolume.
     *
     * @param newVolume value the game volume is set to
     * @throws IllegalArgumentException if soundvolume is negative or over 100
     */
    @Override
    public void adjustSoundVolume(int newVolume) throws IllegalArgumentException {

    }

    /**
     * saves the current game.
     *
     * @return current game state
     * @throws if the gameState is PREGAME or GAMEOVER
     */
    @Override
    public SavedGame saveGame() throws IllegalGameStateException {
        return null;
    }

    /**
     * loads a saved game.
     *
     * @param savedGame state of a saved game
     * @throws IllegalFormatException when wrong file format is loaded
     */
    @Override
    public void loadGame(SavedGame savedGame) throws IllegalFormatException {

    }

    /**
     * player concedes.
     *
     * @throws IllegalGameStateException if the GAMESTATE is GAMEOVER
     */
    @Override
    public void concede() throws IllegalGameStateException {

    }

    /**
     * displays the rules.
     *
     * @return rules for the game
     */
    @Override
    public String displayRules() {
        return null;
    }
}
