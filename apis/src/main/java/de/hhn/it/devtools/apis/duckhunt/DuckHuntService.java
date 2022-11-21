package de.hhn.it.devtools.apis.duckhunt;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * Allows game interaction.
 */
public interface DuckHuntService {
  /**
   * Is called when shoot button is clicked and provides the x and y cursor position.
   *
   * @param x x cursor position
   * @param y y cursor position
   */
  void shoot(int x, int y);

  /**
   * Is called when reload button is clicked reloads the gun.
   */
  void reload();

  /**
   * Is called when the game shall be started.
   */
  void startGame();

  /**
   * Is called when the game shall be stopped.
   */
  void stopGame();

  /**
   * Is called when the game shall be paused.
   */
  void pauseGame();

  /**
   * Is called when the game shall be continued.
   */
  void continueGame();

  /**
   * Changes the game settings.
   *
   * @param gameSettings object with the chosen game settings
   * @throws IllegalParameterException if the gameSettings is a null reference.
   */
  void changeGameSettings(GameSettingsDescriptor gameSettings) throws IllegalParameterException;

  /**
   * Adds a listener to get updates for the game.
   *
   * @param listener object implementing the listener interface
   * @throws IllegalParameterException if the listener is a null reference.
   */
  void addCallback(DuckHuntListener listener) throws IllegalParameterException;

  /**
   * Removes a listener.
   *
   * @param listener object implementing the listener interface
   * @throws IllegalParameterException if the listener is a null reference.
   */
  void removeCallback(DuckHuntListener listener) throws IllegalParameterException;
}
