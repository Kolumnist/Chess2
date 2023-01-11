package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.*;

import java.util.IllegalFormatException;
import java.util.SortedMap;


/**
 * Realisation of ReactionGameService.
 */
public class RgcService implements ReactiongameService {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcService.class);
  private RgcLogic gameLogic;


  public RgcLogic getGameLogic() {
    return gameLogic;
  }

  @Override
  public void newRun(Difficulty difficulty) throws IllegalParameterException {
    gameLogic = new RgcLogic(difficulty);
    gameLogic.getClock().setRunning(true);

    logger.info("New run created ("  + difficulty + ")");
  }

  @Override
  public void pauseRun() throws IllegalStateException {
    if (gameLogic.getState() == GameState.PAUSED) {
      logger.info("Pause run illegal", new IllegalStateException());
      throw new IllegalStateException();
    }
    gameLogic.getClock().setRunning(false);
    gameLogic.setState(GameState.PAUSED);
    logger.info("Paused run");
  }

  @Override
  public void continueRun() throws IllegalStateException {
    if (gameLogic.getState() != GameState.PAUSED) {
      logger.info("Continue run illegal", new IllegalStateException());

      throw new IllegalStateException();
    }
    gameLogic.getClock().setRunning(true);
    gameLogic.setState(GameState.RUNNING);

    logger.info("Continued run");
  }

  @Override
  public void endRun() {
    gameLogic.getClock().setRunning(false);

    gameLogic.setState(GameState.FINISHED);

  }

  @Override
  public void keyPressed(char key) throws IllegalStateException {
    gameLogic.setpKey(key);

    logger.info("Player pressed key \"" + key + "\"");

    gameLogic.checkForTargetHit();
  }

  @Override
  public void playerEnteredAimTarget(int id) throws IllegalParameterException{
    gameLogic.setpObstacle(null);
    gameLogic.setpAimTarget(gameLogic.getGameField().getTargets().get(id));

    logger.info("Player entered aimtarget (" + id + ")");
  }

  @Override
  public void playerEnteredObstacle(int id) throws IllegalParameterException {
    gameLogic.setpAimTarget(null);
    gameLogic.setpObstacle(gameLogic.getGameField().getObstacles().get(id));

    logger.info("Player entered obstacle (" + id + ")");

    gameLogic.playerHitObstacle();
  }

  @Override
  public void playerLeftGameObject() {
    gameLogic.setpObstacle(null);
    gameLogic.setpAimTarget(null);

    logger.info("Player left game object");
  }

  @Override
  public void setCurrentPlayerName(String playerName) {
    gameLogic.getPlayer().setName(playerName);

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

    service.newRun(Difficulty.MEDIUM);
  }

}
