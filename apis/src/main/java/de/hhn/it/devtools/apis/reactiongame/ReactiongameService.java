package de.hhn.it.devtools.apis.reactiongame;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.io.IOException;
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
   * @throws IllegalStateException if the difficulty does not exist.
   */
  void pauseRun() throws IllegalStateException;

  /**
   * Continues a paused run.
   * @throws IllegalStateException if the difficulty does not exist.
   */
  void continueRun() throws IllegalStateException;

  /**
   * End run.
   */
  void endRun();

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
   * @throws IllegalStateException if state equals the current state
   */
  void presentCourserPosition(int x, int y) throws IllegalStateException;

  /**
   * Player pressed a key.
   *
   * @param key key
   * @throws IllegalStateException if state equals the current state
   */
  void keyPressed(char key) throws IllegalStateException;

  /**
   * Sets a player name to the current highscore.
   *
   * @param playerName new player name
   */
  void setCurrentPlayerName(String playerName);

  /**
   * Loads highscoreTable from a file.
   *
   * @return Highscores with format: player - score
   * @throws IOException if IO-operation goes wrong
   */
  SortedMap<String, Integer> loadHighscoreTable() throws IOException;

  /**
   * Saves the current highscoreTable in a file.
   *
   * @param highscoreTable table with current highscores
   * @throws IOException if IO-operation goes wrong
   */
  void saveHighscoreTable(SortedMap<String, Integer> highscoreTable) throws IOException;

}
