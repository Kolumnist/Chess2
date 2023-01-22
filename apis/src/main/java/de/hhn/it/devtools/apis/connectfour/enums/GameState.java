package de.hhn.it.devtools.apis.connectfour.enums;

/**
 * This enum contains the different game states.
 */
public enum GameState {
  /**
   * The game needs to be configured.
   */
  CONFIGURING,
  /**
   * The game is configured and can be started.
   */
  READY,
  /**
   * The game is started and running.
   */
  RUNNING,
  /**
   * The game has ended.
   */
  FINISHED,
  /**
   * The game has been canceled.
   */
  CANCELED,
}
