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
  private RgcLogic logic;


  public RgcLogic getLogic() {
    return logic;
  }

  @Override
  public void addCallback(ReactiongameListener listener) {
    logic.getCallbacks().add(listener);
  }

  @Override
  public void removeCallback(ReactiongameListener listener) {
    logic.getCallbacks().remove(listener);
  }

  @Override
  public void newRun(Difficulty difficulty) throws IllegalParameterException {
    logic = new RgcLogic(difficulty);

    logic.getObstacleClock().setRunning(true);
    logic.getAimTargetClock().setRunning(true);

    logger.info("New run created ("  + difficulty + ")");
  }

  @Override
  public void pauseRun() throws IllegalStateException {
    if (logic.getState() == GameState.PAUSED) {
      logger.info("Pause run illegal", new IllegalStateException());
      throw new IllegalStateException();
    }

    logic.pauseClocks();

    logic.setState(GameState.PAUSED);

    logger.info("Paused run");
  }

  @Override
  public void continueRun() throws IllegalStateException {
    if (logic.getState() != GameState.PAUSED) {
      logger.info("Continue run illegal", new IllegalStateException());
      throw new IllegalStateException();
    }

    logic.continueClocks();

    logic.setState(GameState.RUNNING);

    logger.info("Continued run");
  }

  @Override
  public void endRun() {

    logic.endRun();

    logic.setState(GameState.FINISHED);

    logger.info("Run ended");
  }

  @Override
  public void keyPressed(char key) throws IllegalStateException {
    logic.setpKey(key);

    logger.info("Player pressed key \"" + key + "\"");

    logic.checkForTargetHit();
  }

  @Override
  public void playerEnteredAimTarget(int aimtargetId) throws IllegalParameterException {

    if (aimtargetId > 0) {
      throw new IllegalParameterException();
    }

    logic.setpObstacle(null);
    logic.setpAimTarget(logic.getGameField().getTargets().get(aimtargetId));

    logger.info("Player entered aim target (" + aimtargetId + ")");
  }

  @Override
  public void playerEnteredObstacle(int obstacleId) throws IllegalParameterException {

    if (obstacleId > 0) {
      throw new IllegalParameterException();
    }

    logic.setpAimTarget(null);
    logic.setpObstacle(logic.getGameField().getObstacles().get(obstacleId));

    logger.info("Player entered obstacle (" + obstacleId + ")");

    logic.playerHitObstacle();
  }

  @Override
  public void playerLeftGameObject() {
    logic.setpObstacle(null);
    logic.setpAimTarget(null);

    logger.info("Player left game object");
  }

  @Override
  public void setCurrentPlayerName(String playerName) {
    logic.getPlayer().setName(playerName);

    logger.info("Player name changed to \"" + playerName + "\"");
  }

  @Override
  public void loadHighscoreTable(SortedMap<String, Integer> newHighScoreTable) {

  }

  @Override
  public SortedMap<String, Integer> saveHighscoreTable() throws IllegalFormatException {
    return null;
  }


  public static void main(String[] args) throws IllegalParameterException {
    RgcService service = new RgcService();

    service.newRun(Difficulty.HARD);


    try {
      Thread.sleep(35000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    service.endRun();


  }

}
