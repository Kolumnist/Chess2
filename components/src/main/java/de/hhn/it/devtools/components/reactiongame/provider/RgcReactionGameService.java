package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.*;

import java.util.IllegalFormatException;
import java.util.SortedMap;


/**
 * Realisation of ReactionGameService.
 */
public class RgcReactionGameService implements ReactiongameService {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcReactionGameService.class);
  private GameLogic gameLogic;

  @Override
  public void newRun(Difficulty difficulty) throws IllegalParameterException {
    gameLogic = new GameLogic(difficulty);
    gameLogic.getTimer().start();

    logger.info("New run created ("  + difficulty + ")");
  }

  @Override
  public void pauseRun() throws IllegalStateException {
    if (gameLogic.getState() == GameState.PAUSED) {
      logger.info("Pause run illegal", new IllegalStateException());
      throw new IllegalStateException();
    }
    gameLogic.getTimer().stop();
    gameLogic.setState(GameState.PAUSED);
    logger.info("Paused run");
  }

  @Override
  public void continueRun() throws IllegalStateException {
    if (gameLogic.getState() != GameState.PAUSED) {
      logger.info("Continue run illegal", new IllegalStateException());

      throw new IllegalStateException();
    }
    gameLogic.getTimer().restart();
    gameLogic.setState(GameState.RUNNING);

    logger.info("Continued run");
  }

  @Override
  public void endRun() {

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



}
