package de.hhn.it.devtools.apis.reactiongame;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * Interface for the reaction game.
 */
public interface ReactiongameService {

  /**
   * Starts a new run and the timer.
   *
   * @param difficulty sets the difficulty of the starting run.
   * @throws IllegalParameterException if the difficulty does not exist.
   */
  void newRun(Difficulty difficulty) throws IllegalParameterException;

  /**
   * Pauses the run and the timer.
   */
  void pauseRun();

  /**
   * Continues a paused run.
   */
  void continueRun();

  /**
   * Exit the run, to return to the main menu.
   */
  void endRun();

  /**
   * Starts the timer when a new run starts.
   */
  void startTimer();

  /**
   * Pauses the timer when a run is paused.
   */
  void pauseTimer();

  /**
   * Adds a listener to the service.
   *
   * @param listener listener
   * @param id identifier
   */
  void addCallback(ReactiongameListener listener, int id);

  /**
   * Removes listener with the given id.
   *
   * @param id identifier
   */
  void removeCallback(int id);


  /**
   * Changes the game state to given parameter.
   *
   * @param state new game state
   * @return the new game state (possibility the game does not allow a change)
   */
  GameState setGameState(GameState state);

}
