package de.hhn.it.devtools.apis.reactiongame;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.IllegalFormatException;
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
   *
   * @throws IllegalStateException if run is already paused.
   */
  void pauseRun() throws IllegalStateException;

  /**
   * Continues a paused run.
   *
   * @throws IllegalStateException if run is not paused.
   */
  void continueRun() throws IllegalStateException;

  /**
   * End run.
   */
  void endRun();

  /**
   * Player pressed a key.
   *
   * @param key key
   * @throws IllegalStateException if state equals the current state
   */
  void keyPressed(char key) throws IllegalStateException;

  void playerEnteredAimTarget(int id) throws IllegalParameterException;

  void playerEnteredObstacle(int id) throws IllegalParameterException;

  void playerLeftGameObject();

  /**
   * Sets a player name to the current highscore.
   *
   * @param playerName new player name
   */
  void setCurrentPlayerName(String playerName);

  /**
   * Loads highscoreTable from a file.
   *
   * @param newHighScoreTable updated highscore list.
   */
  void loadHighscoreTable(SortedMap<String, Integer> newHighScoreTable);

  /**
   * Saves the current highscoreTable in a file.
   *
   * @throws IllegalFormatException when format is not in line.
   * @return Highscores with format: player - score.
   */
  SortedMap<String, Integer> saveHighscoreTable() throws IllegalFormatException;

}
