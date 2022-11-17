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
   * End run.
   */
  void endRun();

  /**
   * Game is finished.
   */
  void gameOver();

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
   * Sets the courser position.
   *
   * @param x position
   * @param y position
   */
  void presentCourserPosition(int x, int y);

  /**
   * Player pressed a key.
   *
   * @param key key
   */
  void keyPressed(char key);
}
