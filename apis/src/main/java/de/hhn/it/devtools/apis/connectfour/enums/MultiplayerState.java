package de.hhn.it.devtools.apis.connectfour.enums;

public enum MultiplayerState {
  /**
   * Player 1 is placing the disk.
   */
  PLAYER_1_IS_PLAYING,
  /**
   * Player 2 is placing the disk.
   */
  PLAYER_2_IS_PLAYING,
  /**
   * Player 1 won the game.
   */
  PLAYER_1_WON,
  /**
   * Player 2 won the game.
   */
  PLAYER_2_WON,
  /**
   * Neither Player 1 nor Player 2 won the game.
   */
  DRAW,
}
