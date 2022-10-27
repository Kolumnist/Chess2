package de.hhn.it.devtools.apis.duckhunt;

/**
 * Callback to notify observers about the change of GameState.
 */
public interface DuckHuntMenuListener {
  /**
   * Informs the listener that DuckHuntMenu has changed its state.
   *
   * @param gameSettingsDescriptor hands over game-information
   */
  void newState(GameSettingsDescriptor gameSettingsDescriptor);
}
