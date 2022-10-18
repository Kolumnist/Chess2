package de.hhn.it.devtools.apis.battleship;

/**
 *  This BattleshipService is an interface for the interaction of a player with components.
 */

public interface BattleshipService {
    /**
     * adds the type and amount of ships to each player inventory chosen in the game creation menu.
     * @param typeOfShip type of ship that will be added
     * @param numberOfShips amount of ships of the certain type that will be added
     */
    void addTypeOfShip(Ship typeOfShip, int numberOfShips);

    /**
     * places ship at given location
     * @param shipToPlace ship object
     * @param x1 x start coordinate of the ship
     * @param y1 y start coordinate of the ship
     * @param x2 x end coordinate of the ship
     * @param y2 y end coordinate of the ship
     */
    void placeShip(Ship shipToPlace,int x1, int y1, int x2, int y2) throws IllegalPositionException;

    /**
     * rotates the ship to left or right (front of the ship is the anchor point)
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
     * @param size width and height of the field
     * @param gameMode chosen game mode to see how many fields need to be created
     */
    void createFields(int size, GameMode gameMode);

    /**
     * sets the game mode chosen in the game creation menu.
     * @param chosenGameMode gameMode
     */
    void setGameMode(GameMode chosenGameMode);

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
    void loadGame(GameState gameState);

    /**
     * player concedes.
     */
    void concede();

    /**
     * displays the rules.
     */
    void displayRules();
}
