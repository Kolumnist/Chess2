package de.hhn.it.devtools.apis.connectfour.exceptions;

/**
 * This Class models an illegal operation exception, which might occur when the user tries to place
 * a disc in a row without any empty slots.
 */
public class IllegalOperationException extends Throwable {
  public IllegalOperationException() {
  }

  public IllegalOperationException(String message) {
    super(message);
  }
}