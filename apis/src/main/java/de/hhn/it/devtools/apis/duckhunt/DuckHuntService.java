package de.hhn.it.devtools.apis.duckhunt;

/**
 * Allows ui interaction.
 */
public interface DuckHuntService {
  /**
   * Is called when mouse button is clicked and provides the x and y cursor position.
   *
   * @param x x cursor position
   * @param y y cursor position
   */
  void onClicked(int x, int y);
}
