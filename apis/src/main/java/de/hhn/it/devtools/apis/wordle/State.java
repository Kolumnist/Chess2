package de.hhn.it.devtools.apis.wordle;

/**
 * Enum that contains all possible states a WordlePanel can have.
 */
public enum State {
  /**
   * Initial state of a Wordle Panel where its background is coloured black.
   */
  INITIAL,
  /**
   * State where the solution contains the letter at the same spot as in the guess.
   */
  CORRECT,
  /**
   * State where the solution contains the letter but in a different spot.
   */
  PARTIALLY_CORRECT,
  /**
   * State where the solution does not contain the letter.
   */
  FALSE
}
