package de.hhn.it.devtools.apis.chess2;

/**
 * Enum to describe the different fields and if there is something on it or not.
 *
 * @author Collin, Lara, Michel
 * @version 1.1
 */

public enum WinningPlayerState {

  /**
   * gives the black player as the victor back
   */
  BLACK_WIN,
  /**
   * gives the white player as the victor back
   */
  WHITE_WIN,
  /**
   * there is no victor at the moment so the game is still running
   */
  STILL_RUN;
}
