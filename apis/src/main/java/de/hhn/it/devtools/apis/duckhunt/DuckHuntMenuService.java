package de.hhn.it.devtools.apis.duckhunt;

/**
 * Allows menu ui interaction.
 */
public interface DuckHuntMenuService {
  Integer id = null;

  /**
   * Is called when mouse button is clicked on a button and provides button id.
   *
   * @param buttonId id of the button that was pressed
   */
  void onButtonClicked(int buttonId);
}
