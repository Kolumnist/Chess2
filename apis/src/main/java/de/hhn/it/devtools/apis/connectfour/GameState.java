package de.hhn.it.devtools.apis.connectfour;

/**
 * This enum contains the different game states.
 */
public enum GameState {
  /**
   * The game is started and running
   */
  STARTED,
  /**
   * The game is paused
   */
  PAUSED,
  /**
   * The game has been canceled
   */
  CANCELED,
  /**
   * The game has ended
   */
  FINISHED
}
