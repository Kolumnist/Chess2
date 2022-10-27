package de.hhn.it.devtools.apis.duckhunt;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * Allows menu ui interaction.
 */
public interface DuckHuntMenuService {
  /**
   * Is called when mouse button is clicked on a button and provides button id.
   *
   * @param buttonId id of the button that was pressed
   */
  void onButtonClicked(int buttonId) throws IllegalButtonId;

  /**
   * Adds a listener to get updates for the menu.
   *
   * @param listener object implementing the listener interface
   * @throws IllegalParameterException if the listener is a null reference.
   */
  void addCallback(DuckHuntMenuListener listener) throws IllegalParameterException;

  /**
   * Removes a listener.
   *
   * @param listener object implementing the listener interface
   * @throws IllegalParameterException if the listener is a null reference.
   */
  void removeCallback(DuckHuntMenuListener listener) throws IllegalParameterException;

  /**
   * Changes the game settings.
   *
   * @param gameSettings object with the chosen game settings
   * @throws IllegalParameterException if the gameSettings is a null reference.
   */
  void changeGameSettings(GameSettingsDescriptor gameSettings) throws IllegalParameterException;
}
