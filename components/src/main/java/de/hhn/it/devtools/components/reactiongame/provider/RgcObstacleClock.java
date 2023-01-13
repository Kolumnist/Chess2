package de.hhn.it.devtools.components.reactiongame.provider;

import java.util.Comparator;
import java.util.Random;


/**
 * This class puts obstacles into the game and removes them on the basis of the timer.
 */
public class RgcObstacleClock implements Runnable {


  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcObstacleClock.class);

  private final RgcLogic logic;

  private long time; // in seconds

  private boolean isRunning;

  private boolean isEnded;


  /**
   * Standard constructor for obstacle clock.
   *
   * @param logic RgcLogic
   */
  public RgcObstacleClock(RgcLogic logic) {
    this.logic = logic;
    isRunning = true;
    isEnded = false;

    new Thread(this).start();

    logger.info("created");
  }

  public void setRunning(boolean running) {
    isRunning = running;
  }

  public void setEnded(boolean ended) {
    isEnded = ended;
  }


  @Override
  public void run() {

    logger.info("Started");

    boolean isHighMarkReached = false;

    while (!isEnded) {

      try { // do not delete - does not work without
        Thread.sleep(0);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }


      while (isRunning) {



        if (time % logic.getDifficulty().obstacleIntervall == 0) { // Every intervall...
          if (isHighMarkReached) { // HM reached -> remove an obstacle
            deleteRandomObstacle();

            if (logic.getGameField().getObstacles().size()
                == logic.getDifficulty().lowWatermark) { // LM reached, add now
              isHighMarkReached = false;
            }

          } else {
            addNewObstacle();

            if (logic.getGameField().getObstacles().size()
                == logic.getDifficulty().highWatermark) {
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

    logger.info("Ended");

  }

  /**
   * Deletes a random obstacle.
   */
  private void deleteRandomObstacle() {
    int i = logic.getGameField().getObstacles()
        .get(new Random().nextInt(logic.getGameField().getObstacles().size())).getId();

    logic.removeObstacle(i);

  }

  /**
   * Looks for the lowest free identifier, then adds an obstacle with the free identifier.
   */
  private void addNewObstacle() {

    if (logic.getGameField().getObstacles().size() == 0) {
      logic.addObstacle(0);

      return;
    }

    logic.getGameField().getObstacles()
        .sort(Comparator.comparingInt(RgcObstacle::getId)); // sort List by IDs

    for (int i = 0; i < logic.getGameField().getObstacles().size(); i++) {
      if (logic.getGameField().getObstacles().get(i).getId() != i) { // search for free index
        logic.addObstacle(i);

        return;
      }
    }

    int lastIndex = (logic.getGameField().getObstacles().size());

    logic.addObstacle(lastIndex);
  }


}
