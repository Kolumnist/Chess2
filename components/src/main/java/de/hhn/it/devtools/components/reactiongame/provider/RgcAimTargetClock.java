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

  int idCounter = 0;
  private RgcRun run;

  private long time; // in seconds

  private final HashMap<Long, Integer> targetMap; // time - aimtargetID

  private List<Long> removers;
  
  private boolean isRunning;

  private boolean isEnded;


  /**
   * Standard constructor for aim target clock.
   *
   * @param run RgcRun
   */
  public RgcAimTargetClock(RgcRun run) {
    this.run = run;
    targetMap = new HashMap<>();
    isRunning = true;
    isEnded = false;

    Thread t = new Thread(this);
    t.setDaemon(true);
    t.start();

    logger.info("created");
  }

  public long getTime() {
    return time;
  }

  public boolean getIsRunning() {return isRunning;}

  public boolean getIsEnded() {return isEnded;}

  public void setRunning(boolean running) {
    isRunning = running;
  }

  public void setEnded(boolean ended) {
    isEnded = ended;
  }

  public HashMap<Long, Integer> getTargetMap() {
    return targetMap;
  }

  @Override
  public void run() {
    logger.info("Started");

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    while (!isEnded) {

      try { // do not delete - does not work without
        Thread.sleep(0);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      while (isRunning) {

        checkIfTargetExpired();


        if (time % 2 == 0) { // Spawnrate

          if (run.getField().getTargetCount() < run.getDifficulty().maxAimtargets) {

            addTarget();
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

    logger.info("Ended");
  }

  public void addTarget() {
    if (run.getField().getTargetCount() < run.getDifficulty().highWatermark) {
      run.addAimTarget(idCounter);
      targetMap.put(time + run.getDifficulty().aimTargetLifetime, idCounter);
      idCounter++;
    }
  }

  /**
   * Goes throw every entry in the map which holds the information how long a target can stay
   * on the game field.
   */
  private void checkIfTargetExpired() {
    synchronized (targetMap) {
      removers = new ArrayList<>();


      for (Entry<Long, Integer> e :
          targetMap.entrySet()) {

        if (e.getKey() == time) { // target expired
          run.removeAimTarget(e.getValue());
          run.playerLosesLife();

          removers.add(e.getKey());
        }
      }

      for (Long l :
          removers) {
        targetMap.remove(l);
      }
    }
  }

  public void removeAimTarget(int aimTargetId) {
    synchronized (targetMap) {
      removers = new ArrayList<>();


      for (Entry<Long, Integer> e :
          targetMap.entrySet()) {

        if (e.getValue() == aimTargetId) { // target expired
          removers.add(e.getKey());
        }
      }

      for (Long l :
          removers) {
        targetMap.remove(l);
      }
    }
  }




}
