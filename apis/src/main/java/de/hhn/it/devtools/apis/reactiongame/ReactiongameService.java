package de.hhn.it.devtools.apis.reactiongame;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.SortedMap;

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

  /**
   * Highscorelist.
   *
   * @return Highscores with format: player - score
   */
  SortedMap<String, Integer> getHighscoreTable();

  /**
   * Sets a player name to the current highscore.
   *
   * @param playerName new player name
   */
  void setCurrentPlayerName(String playerName);
}
