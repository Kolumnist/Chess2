package de.hhn.it.devtools.apis.reactiongame;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.ArrayList;
import java.util.IllegalFormatException;

/**
 * Interface for the reaction game.
 */
public interface ReactiongameService {


  /**
   * Adds a callback (ReactiongameListener) to the callbacks.
   *
   * @param listener Listener
   */
  void addCallback(ReactiongameListener listener);

  /**
   * Removes a callback (ReactiongameListener) from the callbacks.
   *
   * @param listener Listener
   * @throws IllegalParameterException exception
   */
  void removeCallback(ReactiongameListener listener) throws IllegalParameterException;

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

  /**
   * Player entered aim target.
   *
   * @param id identifier
   * @throws IllegalParameterException if aim target with id does not exist
   */
  void playerEnteredAimTarget(int id) throws IllegalParameterException;

  /**
   * Player entered obstacle.
   *
   * @param id identifier
   * @throws IllegalParameterException if obstacle with id does not exist
   */
  void playerEnteredObstacle(int id) throws IllegalParameterException;

  /**
   * Player left aim target or obstacle.
   */
  void playerLeftGameObject();

  /**
   * Player left the legal playing area.
   */
  void playerLeftField();

  /**
   * Sets a player name to the current high score.
   *
   * @param playerName new player name
   */
  void setCurrentPlayerName(String playerName);

  /**
   * Loads highscoreTable from a file.
   *
   * @param newHighScoreTable updated highscore list.
   */
  void loadHighscoreTable(ArrayList<HighscoreTupel> newHighScoreTable);

  /**
   * Saves the current highscoreTable in a file.
   *
   * @return Highscores with format: player - score.
   * @throws IllegalFormatException when format is not in line.
   */
  ArrayList<HighscoreTupel> saveHighscoreTable() throws IllegalFormatException;

}
