package de.hhn.it.devtools.apis.chess2;

/**
 * Enum to describe the different game states.
 * Is the game still running, in check or is it at the end (in checkmate).
 *
 * @author Collin Hoss, Michel Jouaux, Lara Mangi
 * @version 1.0
 */

public enum GameState {

  /**
   * The game is running.
   */
  RUNNING,
  /**
   * The queen or the king of a player stands in check.
   */
  CHECK,
  /**
   * The king and queen of a player are both in jail.
   * The game is at the end.
   */
  CHECKMATE;
}
