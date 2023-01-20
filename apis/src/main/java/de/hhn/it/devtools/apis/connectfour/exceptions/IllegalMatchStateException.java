package de.hhn.it.devtools.apis.connectfour.exceptions;

/**
 * This Class models an illegal match state exception, which indicates an invalid match state.
 */
public class IllegalMatchStateException extends Throwable {
  public IllegalMatchStateException() {
  }

  public IllegalMatchStateException(String message) {
    super(message);
  }
}
