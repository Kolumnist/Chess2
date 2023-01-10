package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.*;

import java.util.IllegalFormatException;
import java.util.SortedMap;


/**
 * Realisation of ReactionGameService.
 */
public class RgcReactionGameService implements ReactiongameService {

  private GameLogic gameLogic;

  @Override
  public void newRun(Difficulty difficulty) throws IllegalParameterException {
    gameLogic = new GameLogic(difficulty);
    gameLogic.getTimer().start();
  }

  @Override
  public void pauseRun() throws IllegalStateException {
    if (gameLogic.getState() == GameState.PAUSED) {
      throw new IllegalStateException();
    }
    gameLogic.getTimer().stop();
    gameLogic.setState(GameState.PAUSED);
  }

  @Override
  public void continueRun() throws IllegalStateException {
    if (gameLogic.getState() != GameState.PAUSED) {
      throw new IllegalStateException();
    }
    gameLogic.getTimer().restart();
    gameLogic.setState(GameState.RUNNING);
  }

  @Override
  public void endRun() {

  }

  @Override
  public void keyPressed(char key) throws IllegalStateException {
    gameLogic.setpKey(key);
    gameLogic.checkForTargetHit();
  }

  @Override
  public void playerEnteredAimTarget(int id) throws IllegalParameterException{
    gameLogic.setpObstacle(null);
    gameLogic.setpAimTarget(gameLogic.getGameField().getTargets().get(id));
  }

  @Override
  public void playerEnteredObstacle(int id) throws IllegalParameterException {
    gameLogic.setpAimTarget(null);
    gameLogic.setpObstacle(gameLogic.getGameField().getObstacles().get(id));

    gameLogic.playerHitObstacle();
  }

  @Override
  public void playerLeftGameObject() {
    gameLogic.setpObstacle(null);
    gameLogic.setpAimTarget(null);
  }

  @Override
  public void setCurrentPlayerName(String playerName) {
    gameLogic.getPlayer().setName(playerName);
  }

  @Override
  public void loadHighscoreTable(SortedMap<String, Integer> newHighScoreTable) {

  }

  @Override
  public SortedMap<String, Integer> saveHighscoreTable() throws IllegalFormatException {
    return null;
  }



}
