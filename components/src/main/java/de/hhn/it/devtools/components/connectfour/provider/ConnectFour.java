package de.hhn.it.devtools.components.connectfour.provider;

import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.apis.connectfour.interfaces.IConnectFour;
import de.hhn.it.devtools.apis.connectfour.interfaces.IConnectFourListener;
import de.hhn.it.devtools.components.connectfour.provider.helper.Game;
import de.hhn.it.devtools.components.connectfour.provider.helper.MultiplayerGame;
import de.hhn.it.devtools.components.connectfour.provider.helper.SingleplayerGame;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * This class implements the IConnectFour interface.
 */
public class ConnectFour implements IConnectFour {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ConnectFour.class);

  private Game game;
  private HashMap<UUID, Profile> profiles = new HashMap<>();
  private final List<IConnectFourListener> listeners = new LinkedList<>();

  /**
   * Adds a listener to get updates on the state of the game or the current match.
   *
   * @param listener Object implementing the listener interface.
   * @throws IllegalArgumentException If the listener reference is null.
   */
  @Override
  public void addCallback(IConnectFourListener listener) throws IllegalArgumentException {
    logger.info("addCallback: {}", listener);
    if (listener == null) {
      throw new IllegalArgumentException("Listener was null reference.");
    }
    if (listeners.contains(listener)) {
      throw new IllegalArgumentException("Listener already registered.");
    }
    listeners.add(listener);
  }

  /**
   * Removes a listener.
   *
   * @param listener Object implementing the listener interface.
   * @throws IllegalArgumentException If the listener reference is null.
   */
  @Override
  public void removeCallback(IConnectFourListener listener) throws IllegalArgumentException {
    logger.info("removeCallback: listener = {}", listener);
    if (listener == null) {
      throw new IllegalArgumentException("Listener is null.");
    }
    if (!listeners.contains(listener)) {
      throw new IllegalArgumentException("Listener is not registered.");
    }
    listeners.remove(listener);
  }

  /**
   * Create a new user profile.
   *
   * @param name name of the profile.
   * @return User profile.
   * @throws IllegalArgumentException If the name isn't valid or is already in use.
   */
  @Override
  public Profile createProfile(String name) throws IllegalArgumentException {
    logger.info("createProfile: name = {}", name);
    checkName(name);
    Profile profile = new Profile(name);
    profiles.put(profile.getId(), profile);
    return profile;
  }

  /**
   * Change the name of the profile with the specified ID.
   *
   * @param id   ID of the profil.
   * @param name Name of the profile.
   * @throws IllegalArgumentException If the nome or the id aren't valid.
   * @throws NoSuchElementException   If no profile with the ID exists.
   */
  @Override
  public void setProfileName(UUID id, String name)
      throws IllegalArgumentException, NoSuchElementException {
    logger.info("setProfileName: id = {}, name = {}", id, name);
    checkId(id);
    checkName(name);
    getProfile(id).setName(name);
  }

  /**
   * Delete the profile with the specified ID.
   *
   * @param id ID of the profile.
   * @throws IllegalArgumentException If the id isn't valid.
   * @throws NoSuchElementException   If no profile with the ID exists.
   */
  @Override
  public void deleteProfile(UUID id) {
    logger.info("deleteProfile: id = {}", id);
    profiles.remove(id);
  }

  /**
   * Get the user profile with the specified ID.
   *
   * @return The user profile with the specified ID.
   * @throws IllegalArgumentException If the ID isn't valid.
   * @throws NoSuchElementException   If no profile with the ID exists.
   */
  @Override
  public Profile getProfile(UUID id) {
    logger.info("getProfile: id = {}", id);
    return profiles.get(id);
  }

  /**
   * Get all user profiles.
   *
   * @return List of user profiles.
   * @throws NoSuchElementException If no profiles are registered.
   */
  @Override
  public HashMap<UUID,Profile> getProfiles() {
    logger.info("getProfiles: no params");
    return profiles;
  }

  /**
   * Set all user profiles.
   *
   * @param profiles The profiles.
   */
  @Override
  public void setProfiles(HashMap<UUID,Profile> profiles) {
    logger.info("setProfiles: profiles = {}", profiles);
    this.profiles = profiles;
  }

  /**
   * Create new singleplayer game.
   *
   * @param player         The profile of the player.
   * @param player1IsFirst True, if player 1 begins. Otherwise, false.
   * @throws IllegalArgumentException If the argument isn't a valid player profile.
   */
  @Override
  public void playSingleplayerGame(Profile player, boolean player1IsFirst)
      throws IllegalArgumentException {
    logger.info("playSingleplayerGame: player = {}", player);
    if (player == null) {
      throw new IllegalArgumentException("Player must not be null.");
    }
    game = new SingleplayerGame(player, player1IsFirst);
  }

  /**
   * Create new multiplayer game.
   *
   * @param player1        Player 1.
   * @param player2        Player 2.
   * @param player1IsFirst True, if player 1 begins. Otherwise, false.
   * @throws IllegalArgumentException If the arguments are not valid player profiles.
   */
  @Override
  public void playMultiplayerMode(Profile player1, Profile player2, boolean player1IsFirst)
      throws IllegalArgumentException {
    logger.info("playSingleplayerGame: player1 = {}, player2 = {}", player1, player2);
    if (player1 == null || player2 == null) {
      throw new IllegalArgumentException("Players must not be null.");
    }
    game = new MultiplayerGame(player1, player2, player1IsFirst);
  }

  /**
   * Place a disc at the specified position.
   *
   * @param column Column in which the disc should be placed in.
   * @throws IllegalArgumentException  If column index is invalid.
   * @throws IllegalOperationException If column is full.
   */
  @Override
  public void placeDiscInColumn(int column) throws IllegalOperationException {
    logger.info("placeDiscIn: column = {}", column);
    game.placeDiscInColumn(column);
    listeners.get(0).update(
        game.getMatchState(),
        game.getDescription(),
        game.getBoard().getAffectedColumn(),
        game.getBoard().getAffectedRow(),
        game.getBoard().getAffectedColor()
    );
  }

  /**
   * Restarts the game with its current configuration. Switches players if game was FINISHED.
   */
  @Override
  public void restart() {
    logger.info("restart: no params");
    game.restart();
  }

  /**
   * Check if the name is valid.
   *
   * @param name The profile name.
   * @throws IllegalArgumentException If name is not valid.
   */
  private void checkName(String name) throws IllegalArgumentException {
    logger.info("checkName: name = {}", name);
    // Null?
    if (name == null) {
      throw new IllegalArgumentException("Name must not be null");
    } else if (name.isBlank()) {
      // Empty or blanks?
      throw new IllegalArgumentException(
          "Name must not be an empty string and only consist of spaces");
    }
    // Name already taken?
    for (Profile value : profiles.values()) {
      if (name.equalsIgnoreCase(value.getName())) {
        throw new IllegalArgumentException("Name already taken.");
      }
    }
  }

  /**
   * Check if ID is valid.
   *
   * @param id The profile ID.
   * @throws IllegalArgumentException If ID is null.
   * @throws NoSuchElementException   If no profile with the specified ID exists.
   */
  private void checkId(UUID id) throws IllegalArgumentException, NoSuchElementException {
    logger.info("checkId: id = {}", id);
    if (id == null) {
      throw new IllegalArgumentException("Id must not be null.");
    }
    if (!profiles.containsKey(id)) {
      throw new NoSuchElementException("Profile with specified id does not exist.");
    }
  }
}
