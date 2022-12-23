package de.hhn.it.devtools.apis.connectfour;

/**
 * Callback to notify observers about a state change of the game.
 */
public interface ConnectFourListener {
  /**
   * Informs the listener that the game has changed its state.
   *
   * @param gameState State of the game
   */
  void gameStateChanged(GameState gameState);

  /**
   * Informs the listener that the match has changed its state.
   *
   * @param matchState State of the game
   */
  void playerStateChanged(MatchState matchState);
}
