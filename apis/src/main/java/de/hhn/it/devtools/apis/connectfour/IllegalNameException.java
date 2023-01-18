package de.hhn.it.devtools.apis.connectfour;

/**
 * This Class models an illegal name exception, which might occur when the user tries to edit the
 * name of a profile.
 */
public class IllegalNameException extends Throwable {
  public IllegalNameException(String message) {
    super(message);
  }
}