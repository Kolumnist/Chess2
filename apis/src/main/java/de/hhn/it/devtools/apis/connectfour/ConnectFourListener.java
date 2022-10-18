package de.hhn.it.devtools.apis.connectfour;

/**
 * Callback to notify observers about a state change of the game.
 */
public interface ConnectFourListener {

  /**
   * Informs the listener that the game has changed its state.
   *
   * @param state actual state of the game
   */
  void newState(State state);
}
