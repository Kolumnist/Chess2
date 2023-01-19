package de.hhn.it.devtools.apis.connectfour.interfaces;

/**
 * Callback to notify observers about a state change of the game.
 */
public interface IConnectFourListener {
  /**
   * Called when the match description changes.
   *
   * @param description The match description.
   */
  void descriptionChanged(String description);

  /**
   * Called when the match board changes.
   *
   * @param column The affected column.
   * @param row    The affected row.
   * @param color  The new color.
   */
  void boardChanged(int column, int row, String color);
}
