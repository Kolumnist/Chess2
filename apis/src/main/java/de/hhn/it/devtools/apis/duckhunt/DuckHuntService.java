package de.hhn.it.devtools.apis.duckhunt;

/**
 * Allows ui interaction.
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
}
