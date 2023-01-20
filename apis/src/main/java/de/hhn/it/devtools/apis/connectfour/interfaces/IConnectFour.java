package de.hhn.it.devtools.apis.connectfour.interfaces;

import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * This is an interface to the game Connect Four.
 */
public interface IConnectFour {

  /**
   * Add a listener.
   *
   * @param listener Object implementing the listener interface.
   * @throws IllegalArgumentException If the listener reference is null.
   */
  void addCallback(IConnectFourListener listener) throws IllegalArgumentException;

  /**
   * Remove a listener.
   *
   * @param listener Object implementing the listener interface.
   * @throws IllegalArgumentException If the listener reference is null.
   */
  void removeCallback(IConnectFourListener listener) throws IllegalArgumentException;

  /**
   * Remove all listeners.
   */
  void removeAllCallbacks();

  /**
   * Create a new user profile.
   *
   * @param name name of the profile.
   * @return User profile.
   * @throws IllegalArgumentException If the name isn't valid or is already in use.
   */
  Profile createProfile(String name) throws IllegalArgumentException;

  /**
   * Change the name of the profile with the specified ID.
   *
   * @param id   ID of the profil.
   * @param name Name of the profile.
   * @throws IllegalArgumentException If the nome or the id aren't valid.
   * @throws NoSuchElementException   If no profile with the ID exists.
   */
  void setProfileName(UUID id, String name) throws IllegalArgumentException;

  /**
   * Delete the profile with the specified ID.
   *
   * @param id ID of the profile.
   * @throws IllegalArgumentException If the id isn't valid.
   * @throws NoSuchElementException   If no profile with the ID exists.
   */
  void deleteProfile(UUID id) throws IllegalArgumentException, NoSuchElementException;

  /**
   * Get the user profile with the specified ID.
   *
   * @return The user profile with the specified ID.
   * @throws IllegalArgumentException If the ID isn't valid.
   * @throws NoSuchElementException   If no profile with the ID exists.
   */
  Profile getProfile(UUID id) throws IllegalArgumentException, NoSuchElementException;

  /**
   * Get all user profiles.
   *
   * @return List of user profiles.
   * @throws NoSuchElementException If no profiles are registered.
   */
  HashMap<UUID, Profile> getProfiles() throws NoSuchElementException;

  /**
   * Set all user profiles.
   *
   * @param profiles The profiles.
   */
  void setProfiles(HashMap<UUID, Profile> profiles) throws IllegalArgumentException;

  /**
   * Create new singleplayer game.
   *
   * @param player         The profile of the player.
   * @param player1IsFirst True, if player 1 begins. Otherwise, false.
   * @throws IllegalArgumentException If the argument isn't a valid player profile.
   */
  void playSingleplayerGame(Profile player, boolean player1IsFirst) throws IllegalArgumentException;

  /**
   * Create new multiplayer game.
   *
   * @param player1        Player 1.
   * @param player2        Player 2.
   * @param player1IsFirst True, if player 1 begins. Otherwise, false.
   * @throws IllegalArgumentException If the arguments are not valid player profiles.
   */
  void playMultiplayerGame(Profile player1, Profile player2, boolean player1IsFirst)
      throws IllegalArgumentException;

  /**
   * Place a disc at the specified position.
   *
   * @param column Column in which the disc should be placed in.
   * @throws IllegalArgumentException  If column index is invalid.
   * @throws IllegalOperationException If column is full.
   */
  void placeDiscInColumn(int column) throws IllegalArgumentException, IllegalOperationException;

  /**
   * Restart the game with its current configuration. Switches players if game was FINISHED.
   */
  void restart();
}

