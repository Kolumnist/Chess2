package de.hhn.it.devtools.apis.duckhunt;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * Allows game interaction.
 */
public interface DuckHuntService {
  /**
   * Is called when mouse button is clicked and provides the x and y cursor position.
   * Furthermore, it either shots or reloads the gun.
   *
   * @param x x cursor position
   * @param y y cursor position
   * @param gunButton left click (shot), right click (reload)
   */
  void onClicked(int x, int y, GunButton gunButton);

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
