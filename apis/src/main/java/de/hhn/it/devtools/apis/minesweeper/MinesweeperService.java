package de.hhn.it.devtools.apis.minesweeper;

/**
 * The Handler interface is for handling interactions between a player with a component.
 *
 * @author Lara Weller, Jason Herrmann
 * @version 0.1
 */

public interface MinesweeperService {

  /**
   * Creates a field with given size, time and mineCount.
   * Starts the counter and the game.
   *
   * @param fieldSize size of the Field
   * @param time max time for player
   * @param bombCount how many mines will be created
   */
  void startGame(int fieldSize, int time, int bombCount);


  /**
   * Restarts the current game with different mine positions.
   */
  void restart();

  /**
   * Reveals if the clicked field is a mine or not.
   * If there is a mine, the game will end, and a game over screen will appear.
   * If there is no mine, display how many mines are around the clicked field.
   */
  Status clickField(MinesweeperCoordinates coords);

  /**
   * Paint a red flag on top of the field, and make it un-clickable until it gets unmarked.
   */
  Status markField(MinesweeperCoordinates coords);
}
