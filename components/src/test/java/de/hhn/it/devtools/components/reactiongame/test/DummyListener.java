package de.hhn.it.devtools.components.reactiongame.test;

import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.apis.reactiongame.ObstacleDescriptor;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;
import de.hhn.it.devtools.components.example.coffeemakerservice.junit.DummyCallback;
import java.util.ArrayList;

public class DummyListener implements ReactiongameListener {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DummyCallback.class);


  private ArrayList<AimTargetDescriptor> aimTargets;

  private ArrayList<ObstacleDescriptor> obstacles;

  @Override
  public void addAimTarget(AimTargetDescriptor aimTarget) {
    logger.info("Callback called with " + aimTarget);
  }

  @Override
  public void removeAimTarget(int aimTargetId) {
    logger.info("Callback called with " + aimTargetId);
  }

  @Override
  public void addObstacle(ObstacleDescriptor obstacle) {
    logger.info("Callback called with " + obstacle);
  }

  @Override
  public void removeObstacle(int obstacleId) {
    logger.info("Callback called with " + obstacleId);
  }

  @Override
  public void hitObstacle(int obstacleId) {
    logger.info("Callback called with " + obstacleId);
  }

  @Override
  public void currentLife(int numberOfLifes) {
    logger.info("Callback called with " + numberOfLifes);
  }

  @Override
  public void aimTargetHit(char keyPressed, int aimTargetId) {
    logger.info("Callback called with " + keyPressed + ", " + aimTargetId);

  }

  @Override
  public void changeGameState(GameState state) {
    logger.info("Callback called with " + state);
  }

  @Override
  public void changeScore(int score) {
   logger.info("Callback called with " + score);
  }

  @Override
  public void pauseRun() {
    logger.info("Callback called");
  }

  @Override
  public void continueRun() {
    logger.info("Callback called");
  }

  @Override
  public void gameOver() {
    logger.info("Callback called");
  }
}
