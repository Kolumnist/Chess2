package de.hhn.it.devtools.apis.battleship;

/**
 * is for the phase in a current game
 */

public enum GameState {
  /**
   * Game hasn't started yet
   */
  PREGAME,
  /**
   * Players are placing their ships
   */
  PLACINGSHIPS,
  /**
   * Players are shooting at each other's fields
   */
  FIRINGSHOTS,
  /**
   * One player has no remaining ships
   */
  GAMEOVER
}
