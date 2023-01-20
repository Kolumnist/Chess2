package de.hhn.it.devtools.apis.connectfour.enums;

/**
 * This enum contains the different singleplayer states.
 */
public enum SingleplayerState {
  /**
   * Player is placing the disk.
   */
  HUMAN_PLAYING,
  /**
   * Computer is placing the disk.
   */
  COMPUTER_IS_PLAYING,
  /**
   * Player won the game.
   */
  HUMAN_WON,
  /**
   * Computer won the game.
   */
  COMPUTER_WON,
  /**
   * Neither the player nor the computer won the game.
   */
  DRAW,
}
