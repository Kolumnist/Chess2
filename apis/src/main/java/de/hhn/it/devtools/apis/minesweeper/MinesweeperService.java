package de.hhn.it.devtools.apis.minesweeper;

/**
 * The Handler interface is for handling interactions between a player with a component
 * @author Lara Weller, Jason Herrmann
 * @version 0.1
 */

public interface MinesweeperService {
    
    /**
     * 
     * @param minesweeperActionListener
     */
    void setMinesweeperActionListener(MinesweeperActionListener minesweeperActionListener);
    
    /**
     * Creates a field with given size, time and mineCount.
     * Starts the counter and the game
     * @param fieldSize size of the Field
     * @param time max time for player
     * @param mineCount how many mines will be created
     */
    void startGame(int fieldSize, int time, int mineCount);

    /**
     * fills customizable size, time and minecount with static values
     * @param difficulty first letter of which difficulty get selected (e,m,h)
     */
    void chooseDifficulty(Difficulty difficulty);

    /**
     * Closes the current game and return to the menu
     */
    void menu();

    /**
     * Restarts the current game with different mine positions
     */
    void restart();

    /**
     * Reveals if the clicked field is a mine or not.
     * If there is a mine, the game will end, and a gameover screen will appear.
     * If there is no mine, display how many mines are around the clicked field.
     */
    void clickField();

    /**
     * Paint a red flag ontop of the field, and make it unclickable until it gets unmarked
     */
    void markField();
}
