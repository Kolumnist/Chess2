package de.hhn.it.devtools.apis.connectfour.interfaces;

import de.hhn.it.devtools.apis.connectfour.enums.MatchState;

/**
 * Callback to notify observers about a state change of the game.
 */
public interface IConnectFourListener {

  /**
   * Called when the match state changes. Updates game.
   *
   * @param description The new match description.
   * @param matchState  The new match state.
   * @param column      The affected column.
   * @param row         The affected row.
   * @param color       The new color.
   */
  void update(MatchState matchState, String description, int column, int row, String color);
}
