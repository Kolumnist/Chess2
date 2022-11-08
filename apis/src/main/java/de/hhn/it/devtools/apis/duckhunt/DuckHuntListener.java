package de.hhn.it.devtools.apis.duckhunt;

/**
 * Callback to notify observers about the changes of the game.
 */
public interface DuckHuntListener {
  /**
   * Informs the listener that DuckHunt has changed its state.
   *
   * @param gameState hands over game-information
   */
  void newState(GameState gameState) throws IllegalGameStateException;

  /**
   * Informs the listener that DuckState has been updated.
   *
   * @param duckPosition hands over array of duck data
   */
  void newDuckPosition(DucksState duckPosition) throws IllegalDuckPositionException;

  /**
   * Informs the listener that a duck was hit.
   *
   * @param id id of duck that got hit
   */
  void duckHit(int id) throws IllegalDuckIdException;
}
