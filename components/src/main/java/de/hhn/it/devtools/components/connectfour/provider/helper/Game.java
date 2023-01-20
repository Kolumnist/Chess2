package de.hhn.it.devtools.components.connectfour.provider.helper;

import de.hhn.it.devtools.apis.connectfour.enums.GameState;
import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Descriptor;
import de.hhn.it.devtools.apis.connectfour.interfaces.IConnectFourListener;

/**
 * This class models the game.
 */
public abstract class Game {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Game.class);

  Descriptor descriptor = new Descriptor();
  Board board;
  GameState gameState = GameState.CONFIGURING; // Listener needs to be set.
  IConnectFourListener listener;

  /**
   * Places the disc of the current player in the specified column.
   *
   * @param column The column in which the disc is to be placed in.
   * @throws IllegalOperationException If column is full.
   */
  public abstract void placeDiscInColumn(int column)
      throws IllegalOperationException;

  /**
   * Restarts the game.
   */
  public abstract void restart();

  /**
   * Starts the game.
   */
  public abstract void start();

  /**
   * Set the listener.
   *
   * @param listener The listener.
   */
  public void setListener(IConnectFourListener listener) {
    logger.info("setListener: listener = {}", listener);
    this.listener = listener;
  }
}
