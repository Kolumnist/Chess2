package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameService;
import java.util.IllegalFormatException;
import java.util.SortedMap;


/**
 * Realisation of ReactionGameService.
 */
public class RgcService implements ReactiongameService {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcService.class);

  private final RgcPlayer currentPlayer;
  private RgcRun run;


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

    run.getObstacleClock().setRunning(true);
    run.getAimTargetClock().setRunning(true);

    run.setState(GameState.RUNNING);
  }

  @Override
  public void pauseRun() throws IllegalStateException {
    if (run.getState() == GameState.PAUSED) {
      logger.info("Pause run illegal", new IllegalStateException());
      throw new IllegalStateException();
    }
    logger.info("Pause run");

    run.pauseClocks();

    run.setState(GameState.PAUSED);
  }

  @Override
  public void continueRun() throws IllegalStateException {
    if (run.getState() != GameState.PAUSED) {
      logger.info("Continue run illegal", new IllegalStateException());
      throw new IllegalStateException();
    }

    logger.info("Continue run");

    run.continueClocks();

    run.setState(GameState.RUNNING);

  }

  @Override
  public void endRun() throws IllegalStateException {
    if (run.getState() == GameState.FINISHED) {
      logger.info("End run illegal", new IllegalStateException());
      throw new IllegalStateException();
    }

    logger.info("End run");

    run.endRun();

    run.setState(GameState.FINISHED);
  }

  @Override
  public void keyPressed(char key) throws IllegalStateException {
    logger.info("Key pressed \"" + key + "\"");

    run.setpKey(key);

    run.checkForTargetHit();
  }

  @Override
  public void playerEnteredAimTarget(int aimtargetId) throws IllegalParameterException {
    logger.info("Player entered aim target (" + aimtargetId + ")");

    if (aimtargetId < 0) {
      throw new IllegalParameterException();
    }

    run.setpObstacle(null);
    run.setpAimTarget(run.getField().getTargetMap().get(aimtargetId));

  }

  @Override
  public void playerEnteredObstacle(int obstacleId) throws IllegalParameterException {
    if (obstacleId < 0) {
      logger.info("Invalid obstacleId", new IllegalParameterException());
      throw new IllegalParameterException();
    }
    logger.info("Player entered obstacle (" + obstacleId + ")");

    run.setpAimTarget(null);
    run.setpObstacle(run.getField().getObstacleMap().get(obstacleId));

    run.playerHitObstacle();
  }

  @Override
  public void playerLeftGameObject() {
    logger.info("Player left game object");

    run.setpObstacle(null);
    run.setpAimTarget(null);
  }

  @Override
  public void setCurrentPlayerName(String playerName) {
    logger.info("Set player name to \"" + playerName + "\"");

    run.getPlayer().setName(playerName);
  }

  @Override
  public void loadHighscoreTable(SortedMap<String, Integer> newHighScoreTable) {

  }

  @Override
  public SortedMap<String, Integer> saveHighscoreTable() throws IllegalFormatException {
    return null;
  }
}
