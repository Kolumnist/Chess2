package de.hhn.it.devtools.apis.connectfour.interfaces;

/**
 * Callback to notify observers about a state change of the game.
 */
public interface IConnectFourListener {

  /**
   * Return the name of the listener.
   *
   * @return Name of the listener.
   */
  String getName();

  /**
   * Called when the match state changes. Updates game.
   *
   * @param description The new match description.
   */
  void updateDescription(String description);

  /**
   * Updates the specified tile.
   *
   * @param column The affected column.
   * @param row    The affected row.
   * @param color  The new color.
   */
  void updateTile(int column, int row, String color);

  /**
   * Locks the board. No user input.
   */
  void lock();

  /**
   * Unlocks the board. User input allowed.
   */
  void unlock();
}
