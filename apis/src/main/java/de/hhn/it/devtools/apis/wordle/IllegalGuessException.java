package de.hhn.it.devtools.apis.wordle;

/**
 * Is thrown if a guess does not have the correct length.
 */

public class IllegalGuessException extends Exception {

  public IllegalGuessException(String message) {
    super(message);
  }

}
