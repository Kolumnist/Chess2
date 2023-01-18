package de.hhn.it.devtools.apis.connectfour;

/**
 * This Class models a profile not found exception, which might occur when the user tries to edit or
 * delete a profile with an unknown profile ID.
 */
public class ProfileNotFoundException extends Throwable {
  public ProfileNotFoundException(String message) {
    super(message);
  }
}
