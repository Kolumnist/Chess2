package de.hhn.it.devtools.components.reactiongame.provider;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * This class adds and removes aim targets to or from the game.
 */
public class RgcAimTargetClock implements Runnable {


  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcAimTargetClock.class);

  public static int idCounter = 0;
  private RgcLogic logic;

  private long time; // in seconds

  private HashMap<Long, Integer> targetMap;
  
  private boolean isRunning;

  private boolean isEnded;


  /**
   * Standard constructor for aim target clock.
   *
   * @param logic RgcLogic
   */
  public RgcAimTargetClock(RgcLogic logic) {
    this.logic = logic;
    targetMap = new HashMap<>();
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
    
    while (!isEnded) {

      try { // do not delete - does not work without
        Thread.sleep(0);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      while (isRunning) {

        checkIfTargetExpired();


        if (time % 4 == 0) { // Spawnrate

          if (logic.getGameField().getTargets().size() < logic.getDifficulty().maxAimtargets) {

            logic.addAimTarget(idCounter);
            targetMap.put(time + logic.getDifficulty().aimTargetLifetime, idCounter);
            idCounter++;
          }

        }



        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }

        time++;
      }

    }
  }

  /**
   * Goes throw every entry in the map which holds the information how long a target can stay
   * on the game field.
   */
  private void checkIfTargetExpired() {

    List<Long> removers = new ArrayList<>();

    for (Entry<Long, Integer> e :
        targetMap.entrySet()) {

      if (e.getKey() == time) { // target expired
        logic.removeAimTarget(e.getValue());
        logic.playerLosesLife();

        removers.add(Long.valueOf(e.getValue()));
      }
    }

    for (Long l :
        removers) {
      targetMap.remove(l);
    }

  }


}
