package de.hhn.it.devtools.apis.chess2;

/**
 * Enum to describe the different winning player states.
 * Is there a winning or is the game still running.
 *
 * @author Collin, Lara, Michel
 * @version 1.0
 */

public enum WinningPlayerState {

  /**
   * Gives the black player as the victor back.
   */
  BLACK_WIN,
  /**
   * Gives the white player as the victor back.
   */
  WHITE_WIN,
  /**
   * There is no victor at the moment so the game is still running.
   */
  STILL_RUN;
}
