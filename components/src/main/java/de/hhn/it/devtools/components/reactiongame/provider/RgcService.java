package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameService;
import de.hhn.it.devtools.apis.reactiongame.HighscoreTupel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IllegalFormatException;


/**
 * Realisation of ReactionGameService.
 */
public class RgcService implements ReactiongameService {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcService.class);

  private final RgcPlayer currentPlayer;
  private RgcRun run;

  private ArrayList<HighscoreTupel> highScoreSets;


  public RgcService() {
    this.currentPlayer = new RgcPlayer("Player");
  }

  public RgcRun getRun() {
    return run;
  }

  public RgcPlayer getCurrentPlayer() {
    return currentPlayer;
  }

  @Override
  public void addCallback(ReactiongameListener listener) throws IllegalStateException {
    logger.info("Added listener: " + listener.toString());
    run.getCallbacks().add(listener);
  }

  @Override
  public void removeCallback(ReactiongameListener listener) throws IllegalParameterException {
    if (!run.getCallbacks().contains(listener)) {
      throw new IllegalParameterException();
    }
    logger.info("Removed listener: " + listener.toString());
    run.getCallbacks().remove(listener);
  }

  @Override
  public void newRun(Difficulty difficulty) throws IllegalParameterException {
    logger.info("newRun ("  + difficulty + ")");
    run = new RgcRun(difficulty, currentPlayer);


    run.setGameState(GameState.RUNNING);
  }

  @Override
  public void pauseRun() throws IllegalStateException {
    if (run.getGameState() == GameState.PAUSED) {
      logger.info("Pause run illegal", new IllegalStateException());
      throw new IllegalStateException();
    }
    logger.info("Pause run");

    run.setGameState(GameState.PAUSED);
  }

  @Override
  public void continueRun() throws IllegalStateException {
    if (run.getGameState() != GameState.PAUSED) {
      logger.info("Continue run illegal", new IllegalStateException());
      throw new IllegalStateException();
    }

    logger.info("Continue run");

    run.setGameState(GameState.RUNNING);
  }

  @Override
  public void endRun() throws IllegalStateException {
    if (run.getGameState() == GameState.FINISHED) {
      logger.info("End run illegal", new IllegalStateException());
      throw new IllegalStateException();
    }

    logger.info("End run");

    run.setGameState(GameState.FINISHED);
  }

  @Override
  public void keyPressed(char key) throws IllegalStateException {
    logger.info("Key pressed \"" + key + "\"");

    if (run.getGameState() != GameState.RUNNING) {
      return;
    }

    run.setKey(key);

    run.checkForTargetHit();
  }

  @Override
  public void playerEnteredAimTarget(int aimTargetId) throws IllegalParameterException {
    logger.info("Player entered aim target (" + aimTargetId + ")");

    if (aimTargetId < 0) {
      throw new IllegalParameterException();
    }

    if (run.getGameState() != GameState.RUNNING) {
      return;
    }

    run.setObstacle(null);
    run.setAimTarget(aimTargetId);

  }

  @Override
  public void playerEnteredObstacle(int obstacleId) throws IllegalParameterException {
    if (obstacleId < 0) {
      logger.info("Invalid obstacleId", new IllegalParameterException());
      throw new IllegalParameterException();
    }
    logger.info("Player entered obstacle (" + obstacleId + ")");

    if (run.getGameState() == GameState.RUNNING) {
      run.setAimTarget(null);
      run.setObstacle(obstacleId);

      run.playerHitObstacle();
    }
  }

  @Override
  public void playerLeftGameObject() {
    logger.info("Player left game object");

    run.setObstacle(null);
    run.setAimTarget(null);
  }

  @Override
  public void playerLeftField() {
    logger.info("Player left field");
    if (run.getGameState() == GameState.RUNNING) {
      run.playerLosesLife();
    }
  }

  @Override
  public void setCurrentPlayerName(String playerName) {
    logger.info("Set player name to \"" + playerName + "\"");

    run.getPlayer().setName(playerName);
  }

  @Override
  public void loadHighscoreTable(ArrayList<HighscoreTupel> newHighScoreTable) {
    highScoreSets = newHighScoreTable;
  }

  @Override
  public ArrayList<HighscoreTupel> saveHighscoreTable() throws IllegalFormatException {

    if (run != null ) {
      highScoreSets.add(new HighscoreTupel(currentPlayer.getName(), run.getScore()));
      Collections.sort(highScoreSets);

      run = null;
    }


    return highScoreSets;
  }
}
