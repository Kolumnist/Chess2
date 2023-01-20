package de.hhn.it.devtools.components.connectfour.provider.helper;

import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;

/**
 * This class models the game.
 */
public abstract class Game {

  /**
   * Restarts the game.
   */
  public abstract void restart();

  /**
   * Places the disc of the current player in the specified column.
   *
   * @param column   The column in which the disc is to be placed in.
   * @throws IllegalOperationException If column is full.
   */
  public abstract void placeDiscInColumn(int column)
      throws IllegalOperationException;
}
