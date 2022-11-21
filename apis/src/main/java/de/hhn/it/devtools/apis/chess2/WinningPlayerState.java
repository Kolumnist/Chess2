package de.hhn.it.devtools.apis.chess2;

/**
 * Enum to describe the different winning player states.
 * Is there a winning or is the game still running.
 *
 * @author Collin, Lara, Michel
 * @version 1.0
 */

public enum WinningPlayerState {

  /** The black player is the victor. */
  BLACK_WIN,
  /** The white player is the victor. */
  WHITE_WIN,
  /** There is no victor at the moment so the game is still running. */
  NO_WINNER

}
