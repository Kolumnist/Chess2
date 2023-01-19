package de.hhn.it.devtools.apis.connectfour.enums;

/**
 * This enum contains the different match states.
 */
public enum MatchState {
  /**
   * Player A can place the disk.
   */
  PLAYER_1_IS_PLAYING(),
  /**
   * Player A places the disk.
   */
  PLAYER_2_IS_PLAYING(),
  /**
   * Player A won the game.
   */
  PLAYER_1_WON(),
  /**
   * Player B won the game.
   */
  PLAYER_2_WON(),
  /**
   * Neither Player A nor Player B won the game.
   */
  DRAW();
}
