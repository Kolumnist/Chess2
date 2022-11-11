package de.hhn.it.devtools.apis.reactiongame;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

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
}
