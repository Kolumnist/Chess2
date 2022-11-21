package de.hhn.it.devtools.apis.connectfour;

/**
 * This enum contains the different match states.
 */
public enum MatchState {
  /**
   * Player A can place the disk
   */
  PLAYER_A_IS_NEXT,
  /**
   * Player A places the disk
   */
  PLAYER_B_IS_NEXT,
  /**
   * Player A won the game
   */
  PLAYER_A_WON,
  /**
   * Player B won the game
   */
  PLAYER_B_WON,
  /**
   * Neither Player A nor Player B won the game
   */
  DRAW,
}
