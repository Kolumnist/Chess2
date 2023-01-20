package de.hhn.it.devtools.apis.chess2;

/**
 * Enum to describe the different winning player states.
 * Is there a winning or is the game still running.
 *
 * @author Collin Hoss, Michel Jouaux, Lara Mangi
 * @version 1.0
 */

public enum WinningPlayerState {

  /** The black player is the victor. */
  BLACK_WIN,
  /** The red player is the victor. */
  RED_WIN,
  /** No one won it is a draw. */
  NO_WINNER,

  /** There is no victor at the moment so the game is still running. */
  STILL_RUNNING;
}
