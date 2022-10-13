package de.hhn.it.devtools.apis.battleship;

/**
 *  This BattleshipService is an interface for the interaction of a player with components.
 */

    //TODO add exceptions to methods and add parameters to changeKeybinding()

public interface BattleshipService {
    /**
     * check if possible to place ship at given location.
     * @param shipType type of ship to determine size
     * @param x1 x start coordinate of the ship
     * @param y1 y start coordinate of the ship
     * @param x2 x end coordinate of the ship
     * @param y2 y end coordinate of the ship
     * @return placing (not)successful
     */
    boolean checkShipPlacingLocation(Ship shipType,int x1, int y1, int x2, int y2);

    /**
     * places ship at given location
     * @param shipToPlace ship object
     * @param x1 x start coordinate of the ship
     * @param y1 y start coordinate of the ship
     * @param x2 x end coordinate of the ship
     * @param y2 y end coordinate of the ship
     */
    void placeShip(Ship shipToPlace,int x1, int y1, int x2, int y2);

    /**
     * rotates the ship to left or right
     */
    void rotateShip();

    /**
     *
     * @param x x coordinate of panel to be bombed
     * @param y y coordinate of panel to be bombed
     * @return  bombing (not)successful
     */
    boolean bombPanel(int x, int y);

    /**
     * sets the size of the field chosen in the game creation menu.
     * @param width width of the field
     * @param height height of the field
     */
    void setFieldSize(int width, int height);

    /**
     * sets the game mode chosen in the game creation menu.
     * @param chosenGameMode gameMode
     */
    void setGameMode(GameMode chosenGameMode);

    /**
     * adds the type and amount of ships to each player inventory chosen in the game creation menu.
     * @param typeOfShip type of ship that will be added
     * @param numbeOfShips amount of ships of the certain type that will be added
     */
    void addTypeOfShip(Ship typeOfShip, int numbeOfShips);

    void changeKeybinding ();

    /**
     * sets the game volume to newVolume.
     * @param newVolume value the game volume is set to
     */
    void adjustSoundVolume(int newVolume);

    /**
     * saves the current game.
     */
    void saveGame();

    /**
     * displays the rules.
     */
    void displayRules();


}
