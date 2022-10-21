package de.hhn.it.devtools.apis.connectfour;

/**
 * This class models the user profile.
 */
public class Profile {
  // The ID of the profile
  private final int id;

  /**
   * Create a new user profile.
   *
   * @param name the name of the profile
   */
  public Profile(String name) {
    id = 0;
  }

  /**
   * Get the ID of the user profile.
   *
   * @return ID of the profile
   */
  public int getId() {
    return id;
  }
}
