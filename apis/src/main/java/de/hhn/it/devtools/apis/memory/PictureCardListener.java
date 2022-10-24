package de.hhn.it.devtools.apis.memory;

/**
 * Callback to notify about the changing state of a card in the memory game.
 */
public interface PictureCardListener {

  /**
   * Informs the listener that the card has changed its current state.
   *
   * @param state the current state of the card
   */
  void currentState(State state);

}
