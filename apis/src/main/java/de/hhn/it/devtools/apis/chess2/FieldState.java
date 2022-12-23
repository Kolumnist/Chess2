package de.hhn.it.devtools.apis.chess2;

/**
 * Enum for a field that has a state in which different things happen.
 *
 * @author Collin, Lara, Michel
 * @version 1.1
 */

public enum FieldState {

  /** Is a Jail Field and has a King on it. */
  JAIL_KING,
  /** Is a Jail Field and has a Queen on it. */
  JAIL_QUEEN,
  /** Is a Jail without any piece on it. */
  JAIL,

  /** (NO JAIL) The selected field where a current Piece stands on. */
  SELECTED,

  /** (NO JAIL) A Piece (besides bear) of the current Player is on the Field. */
  HAS_CURRENT_PIECE,
  /** (NO JAIL) Any other Piece (besides bear) is on the Field. */
  HAS_OTHER_PIECE,
  /** (NO JAIL) Bear on Field (this can be selected). */
  HAS_BEAR,
  /** Any other Field. */
  FREE_FIELD
}