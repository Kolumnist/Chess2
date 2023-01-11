package de.hhn.it.devtools.components.reactiongame.provider;

import java.util.Comparator;
import java.util.Random;


/**
 * This class puts obstacles and targets into the game on the basis of the timer.
 */
public class RgcClock implements Runnable {


  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcClock.class);

  private RgcLogic gameLogic;

  private long time; // in seconds

  private boolean isRunning;


  public RgcClock(RgcLogic gameLogic) {
    this.gameLogic = gameLogic;
    isRunning = false;

    new Thread(this).start();
  }

  public void setRunning(boolean running) {
    isRunning = running;

    if (isRunning) {
      logger.info("Continued");
    } else {
      logger.info("Paused");
    }
  }

  /**
   *
   */
  @Override
  public void run() {

    logger.info("Started");

    boolean isHighMarkReached = false;

    while (isRunning) {

      if (time % gameLogic.getDifficulty().obstacleIntervall == 0) { // Every intervall...
        if (isHighMarkReached) { // HM reached -> remove an obstacle
          deleteRandomObstacle();

          if (gameLogic.getGameField().getObstacles().size() ==
              gameLogic.getDifficulty().lowWatermark) { // LM reached, add now
            isHighMarkReached = false;
          }

        } else {
          addNewObstacle();

          if (gameLogic.getGameField().getObstacles().size() ==
              gameLogic.getDifficulty().highWatermark) {
            isHighMarkReached = true;
          }
        }
      }


      try {
        Thread.sleep(1000); // Every second = tick
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      time++;
    }
  }

  /**
   * Deletes a random obstacle
   */
  private void deleteRandomObstacle() {
    int i = gameLogic.getGameField().getObstacles().remove(new Random()
        .nextInt(gameLogic.getGameField().getObstacles().size())).getId();

    logger.info("Obstacle (id = " + i + ") removed");
  }

  /**
   * Looks for the lowest free identifier, then adds an obstacle with the free identifier
   */
  private void addNewObstacle() {

    if (gameLogic.getGameField().getObstacles().size() == 0) {
      gameLogic.addObstacle(0);

      logger.info("Obstacle (id = 0) added");
      return;
    }

    gameLogic.getGameField().getObstacles()
        .sort(Comparator.comparingInt(RgcObstacle::getId)); // sort List by IDs

    for (int i = 0; i < gameLogic.getGameField().getObstacles().size(); i++) {
      if (gameLogic.getGameField().getObstacles().get(i).getId() != i) { // search for free index
        gameLogic.addObstacle(i);

        logger.info("Obstacle (id = " + i + ") added");
        return;
      }
    }

    int lastIndex = (gameLogic.getGameField().getObstacles().size());

    gameLogic.addObstacle(lastIndex);
    logger.info("Obstacle (id = "
        + lastIndex + ") added");
  }


}
