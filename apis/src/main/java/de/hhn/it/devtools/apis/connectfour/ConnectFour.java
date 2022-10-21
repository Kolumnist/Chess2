package de.hhn.it.devtools.apis.connectfour;

import java.util.List;

/**
 * This is an interface to the game Connect Four.
 */
public interface ConnectFour {
  /**
   * Create a new user profile.
   *
   * @param name name of the profile
   * @return User profile
   */
  Profile createProfile(String name);

  /**
   * Change the name of the profile with the specified ID.
   *
   * @param profileId ID of the profil
   * @param name      Name of the profile
   */
  void setProfileName(int profileId, String name);

  /**
   * Delete the profile with the specified ID.
   *
   * @param profileId ID of the profile.
   */
  void deleteProfile(int profileId);

  /**
   * Get a list of all user profiles.
   *
   * @return List of user profiles
   */
  List<Profile> getProfiles();

  /**
   * Show the help dialogue.
   */
  void help();

  /**
   * Change the mode of the game.
   *
   * @param mode Game mode
   */
  void setMode(Mode mode);

  /**
   * Change the difficulty of the game.
   *
   * @param difficulty Difficulty level
   */
  void setDifficulty(Difficulty difficulty);

  /**
   * Start the game.
   */
  void startGame();

  /**
   * Place a disc at the specified position.
   *
   * @param column Column in which the disc should be placed in
   * @throws IllegalOperationException If column is full
   */
  void placeDiscAt(int column) throws IllegalOperationException;

  /**
   * End the current game.
   */
  void quitGame();

}
