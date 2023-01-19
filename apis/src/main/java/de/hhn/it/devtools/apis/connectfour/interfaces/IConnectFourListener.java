package de.hhn.it.devtools.apis.connectfour.interfaces;

import de.hhn.it.devtools.apis.connectfour.enums.MatchState;

/**
 * Callback to notify observers about a state change of the match.
 */
public interface IConnectFourListener {

  /**
   * Informs the listener that the match has changed its state.
   *
   * @param matchState State of the match.
   */
  void matchStateChanged(MatchState matchState);
}
