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
   *
   * @throws IllegalStateException if the game is already paused
   */
  void pauseRun() throws IllegalStateException;

  /**
   * Continues a paused run.
   *
   * @throws IllegalStateException if the game is not paused
   */
  void continueRun();

  /**
   * End run.
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
}
