package de.hhn.it.devtools.apis.battleship;

/**
 * Trigger if method is prompted at a Wrong GameState
 */
public class IllegalGameStateException extends Exception {

  public IllegalGameStateException(String exceptionMessage) {
    super(exceptionMessage);
  }
}
