package de.hhn.it.devtools.components.reactiongame.junit;

import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.apis.reactiongame.ObstacleDescriptor;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;
import de.hhn.it.devtools.components.example.coffeemakerservice.junit.DummyCallback;
import java.util.HashMap;
import java.util.Map;

public class DummyListener implements ReactiongameListener {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DummyCallback.class);

  private final Map<String, Integer> methodCallCountMap;

  public DummyListener() {
    methodCallCountMap = new HashMap<>();

    methodCallCountMap.put("addAimTarget", 0);
    methodCallCountMap.put("removeAimTarget", 0);

    methodCallCountMap.put("addObstacle", 0);
    methodCallCountMap.put("removeObstacle", 0);

    methodCallCountMap.put("hitObstacle", 0);
    methodCallCountMap.put("currentLife", 0);
    methodCallCountMap.put("aimTargetHit", 0);

    methodCallCountMap.put("changeGameState", 0);
    methodCallCountMap.put("changeScore", 0);

    methodCallCountMap.put("pauseRun", 0);
    methodCallCountMap.put("continueRun", 0);
    methodCallCountMap.put("gameOver", 0);
  }

  public Map<String, Integer> getMethodCallCountMap() {
    return methodCallCountMap;
  }

  @Override
  public void addAimTarget(AimTargetDescriptor aimTarget) {
    logger.info("Callback called with " + aimTarget);
    methodCallCountMap.compute("addAimTarget", (k, v) -> (v==null) ? 1 : v + 1);
  }

  @Override
  public void removeAimTarget(int aimTargetId) {
    logger.info("Callback called with " + aimTargetId);
    methodCallCountMap.compute("removeAimTarget", (k, v) -> (v==null) ? 1 : v + 1);
  }

  @Override
  public void addObstacle(ObstacleDescriptor obstacle) {
    logger.info("Callback called with " + obstacle);
    methodCallCountMap.compute("addObstacle", (k, v) -> (v==null) ? 1 : v + 1);
  }

  @Override
  public void removeObstacle(int obstacleId) {
    logger.info("Callback called with " + obstacleId);
    methodCallCountMap.compute("removeObstacle", (k, v) -> (v==null) ? 1 : v + 1);
  }

  @Override
  public void hitObstacle(int obstacleId) {
    logger.info("Callback called with " + obstacleId);
    methodCallCountMap.compute("hitObstacle", (k, v) -> (v==null) ? 1 : v + 1);
  }

  @Override
  public void currentLife(int numberOfLifes) {
    logger.info("Callback called with " + numberOfLifes);
    methodCallCountMap.compute("currentLife", (k, v) -> (v==null) ? 1 : v + 1);
  }

  @Override
  public void aimTargetHit(char keyPressed, int aimTargetId) {
    logger.info("Callback called with " + keyPressed + ", " + aimTargetId);
    methodCallCountMap.compute("aimTargetHit", (k, v) -> (v==null) ? 1 : v + 1);
  }

  @Override
  public void changeGameState(GameState state) {
    logger.info("Callback called with " + state);
    methodCallCountMap.compute("changeGameState", (k, v) -> (v==null) ? 1 : v + 1);
  }

  @Override
  public void changeScore(int score) {
   logger.info("Callback called with " + score);
    methodCallCountMap.compute("changeScore", (k, v) -> (v==null) ? 1 : v + 1);
  }

  @Override
  public void pauseRun() {
    logger.info("Callback called");
    methodCallCountMap.compute("pauseRun", (k, v) -> (v==null) ? 1 : v + 1);
  }

  @Override
  public void continueRun() {
    logger.info("Callback called");
    methodCallCountMap.compute("continueRun", (k, v) -> (v==null) ? 1 : v + 1);
  }

  @Override
  public void gameOver() {
    logger.info("Callback called");
    methodCallCountMap.compute("gameOver", (k, v) -> (v==null) ? 1 : v + 1);
  }
}
