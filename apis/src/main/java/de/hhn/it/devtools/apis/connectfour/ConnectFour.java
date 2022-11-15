package de.hhn.it.devtools.apis.connectfour;

import java.util.List;

/**
 * This is an interface to the game Connect Four.
 */
public interface ConnectFour {

  /**
   * Adds a listener to get updates on the state of the game or the current match.
   *
   * @param listener object implementing the listener interface
   * @throws IllegalArgumentException if the listener reference is null
   */
  void addCallback(ConnectFourListener listener) throws IllegalArgumentException;

  /**
   * Removes a listener.
   *
   * @param listener object implementing the listener interface
   * @throws IllegalArgumentException if the listener reference is null
   */
  void removeCallback(ConnectFourListener listener) throws IllegalArgumentException;

  /**
   * Create a new user profile.
   *
   * @param name name of the profile
   * @return User profile
   */
  Profile createProfile(String name) throws IllegalNameException;

  /**
   * Change the name of the profile with the specified ID.
   *
   * @param profileId ID of the profil
   * @param name      Name of the profile
   */
  void setProfileName(long profileId, String name) throws
      ProfileNotFoundException, IllegalNameException;

  /**
   * Delete the profile with the specified ID.
   *
   * @param profileId ID of the profile.
   */
  void deleteProfile(long profileId) throws ProfileNotFoundException;

  /**
   * Get a list of all user profiles.
   *
   * @return List of user profiles
   */
  List<Profile> getProfiles();

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
   * Choose the profile which will be used in singleplayer mode or as the profile for player 1 in
   * PvP mode.
   * Player 1 begins.
   *
   * @param a Player 1
   */
  void chooseProfileA(Profile a);

  /**
   * Choose the profile, which will be used in PvP mode for player 2.
   * Player 2 is second.
   *
   * @param b Player 2
   */
  void chooseProfileB(Profile b);

  /**
   * Start the game
   */
  void startGame() throws ProfileNotSelectedException;

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

