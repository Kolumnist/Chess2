package de.hhn.it.devtools.components.battleship;

import de.hhn.it.devtools.apis.battleship.BattleshipListener;
import de.hhn.it.devtools.apis.battleship.BattleshipService;
import de.hhn.it.devtools.apis.battleship.Field;
import de.hhn.it.devtools.apis.battleship.GameMode;
import de.hhn.it.devtools.apis.battleship.GameState;
import de.hhn.it.devtools.apis.battleship.IllegalPositionException;
import de.hhn.it.devtools.apis.battleship.RotationDirection;
import de.hhn.it.devtools.apis.battleship.Ship;

import java.util.IllegalFormatException;

public class cmpBattleshipService implements BattleshipService {
    /**
     * Adds a listener to get updates on the state of the game.
     *
     * @param listener object implementing the listener interface
     */
    @Override
    public void addCallBack(BattleshipListener listener) {

    }

    /**
     * places ship at given location
     *
     * @param shipToPlace ship object
     * @param x1          x start coordinate of the ship
     * @param y1          y start coordinate of the ship
     * @param x2          x end coordinate of the ship
     * @param y2          y end coordinate of the ship
     * @throws IllegalPositionException if ship is not allowed to place at location
     */
    @Override
    public void placeShip(Ship shipToPlace, int x1, int y1, int x2, int y2) throws IllegalPositionException {

    }

    /**
     * rotates the ship to left or right (front of the ship is the anchor point)
     *
     * @param shipToRotate ship object to rotate
     * @param direction    direction to turn to
     */
    @Override
    public void rotateShip(Ship shipToRotate, RotationDirection direction) {

    }

    /**
     * Bombs selected panel
     *
     * @param x x coordinate of the target panel
     * @param y y coordinate of the target panel
     * @return bombing (not)successful
     * @throws IllegalArgumentException if field does not exist
     */
    @Override
    public boolean bombPanel(int x, int y) throws IllegalArgumentException {
        return false;
    }

    /**
     * sets the size of the field chosen in the game creation menu.
     *
     * @param size     width and height of the field
     * @param gameMode chosen game mode to see how many fields need to be created
     * @throws IllegalArgumentException if player enters something else instead of numbers
     */
    @Override
    public void createFields(int size, GameMode gameMode) throws IllegalArgumentException {

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
     */
    @Override
    public GameState saveGame() {
        return null;
    }

    /**
     * loads a saved game.
     *
     * @param gameState game state of a saved game
     * @throws IllegalFormatException when wrong file format is loaded
     */
    @Override
    public void loadGame(GameState gameState) throws IllegalFormatException {

    }

    /**
     * player concedes.
     */
    @Override
    public void concede() {

    }

    /**
     * displays the rules.
     */
    @Override
    public String displayRules() {
        return null;
    }
}
