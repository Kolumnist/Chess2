package de.hhn.it.devtools.apis.game2048;

/**
 * Callback to notify observers about a state change of the game 2048
 */
public interface Game2048Listener {

  /**
   *  Informs the listener that the Component has changed its state.
   *
   *  @param state actual state of the game 2048.
   */
  void newState(State state);
}
