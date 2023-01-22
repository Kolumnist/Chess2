package de.hhn.it.devtools.apis.battleship;

/**
 * check if possible to place ship at given location.
 */

public class IllegalPositionException extends Exception {

  public IllegalPositionException(String exceptionMessage) {
    super(exceptionMessage);
  }
}
