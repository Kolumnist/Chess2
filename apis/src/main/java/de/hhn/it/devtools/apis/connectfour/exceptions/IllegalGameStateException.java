package de.hhn.it.devtools.apis.connectfour.exceptions;

/**
 * This Class models an illegal game state exception, which indicates an invalid game state.
 */
public class IllegalGameStateException extends Throwable {
  public IllegalGameStateException() {
  }

  public IllegalGameStateException(String message) {
    super(message);
  }
}
