package de.hhn.it.devtools.apis.wordle;

/**
 * Callback to notify observers that a WordlePanel changed its state.
 */
public interface WordlePanelListener {

  /**
   * Informs the Listener about a change in the state of a WordlePanel.
   *
   * @param state new state of the WordlePanel
   */
  void newState(State state);
}
