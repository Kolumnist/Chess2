package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;
import java.util.ArrayList;

/**
 * Class communicate between the service and components. Also notifies the callbacks.
 */
public class RgcRun {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcRun.class);

  private ArrayList<ReactiongameListener> callbacks = new ArrayList<>();
  private final RgcField field = new RgcField();
  private final RgcPlayer player;
  private final RgcObstacleClock obstacleClock; // 2 verschiedene Timer für front und backend?
  private final RgcAimTargetClock aimTargetClock;
  private final Difficulty difficulty;
  private GameState state;

  private RgcObstacle pObstacle; // player is in this obstacle
  private RgcAimTarget pAimTarget; // player is in this aimtarget
  private char pKey;
  private int score;
  private boolean isInvincible = false;
  private Thread iFrameThread;

  private boolean isRunning = true;


  /**
   * Standard constructor for the logic.
   *
   * @param difficulty difficulty of the run.
   */
  public RgcRun(Difficulty difficulty, RgcPlayer player) {
    logger.info("Create run with difficulty: " + difficulty);
    this.difficulty = difficulty;
    this.player = player;
    player.setCurrentLife(3);

    callbacks = new ArrayList<>();

    aimTargetClock = new RgcAimTargetClock(this);
    obstacleClock = new RgcObstacleClock(this);

    iFrameThread = new Thread(new RgcIFrameRunnable(this));
  }


  public ArrayList<ReactiongameListener> getCallbacks() {
    return callbacks;
  }

  public RgcField getField() {
    return field;
  }

  public RgcPlayer getPlayer() {
    return player;
  }

  public GameState getState() {
    return state;
  }

  public void setState(GameState state) {
    this.state = state;
  }

  public void setpObstacle(RgcObstacle pObstacle) {
    this.pObstacle = pObstacle;
  }

  public void setpAimTarget(RgcAimTarget pAimTarget) {
    this.pAimTarget = pAimTarget;
  }

  public void setpKey(char pKey) {
    this.pKey = pKey;
  }

  public void setInvincible(boolean invincible) {
    isInvincible = invincible;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public RgcObstacleClock getObstacleClock() {
    return obstacleClock;
  }

  public RgcAimTargetClock getAimTargetClock() {
    return aimTargetClock;
  }

  public int getScore() {
    return score;
  }

  public char getpKey() {
    return pKey;
  }

  public RgcAimTarget getpAimTarget() {
    return pAimTarget;
  }

  public RgcObstacle getpObstacle() {
    return pObstacle;
  }

  public boolean isRunning() {
    return isRunning;
  }

  /**
   * Pauses the clocks.
   */
  public void pauseClocks() {
    logger.info("Pause clocks");

    obstacleClock.setRunning(false);
    aimTargetClock.setRunning(false);
  }

  /**
   * Continues clocks.
   */
  public void continueClocks() {
    logger.info("Continue clocks");

    obstacleClock.setRunning(true);
    aimTargetClock.setRunning(true);
  }

  /**
   * Ends / stops clocks.
   */
  public void endRun() {
    logger.info("End run");

    isRunning = false;

    obstacleClock.setRunning(false);
    obstacleClock.setEnded(true);

    aimTargetClock.setRunning(false);
    aimTargetClock.setEnded(true);

    for (ReactiongameListener callback :
        callbacks) {
      callback.gameOver();
    }
  }


  /**
   * Methods gets called when player runs into an obstacle or after his iframes end.
   */
  public void playerHitObstacle() {
    if (isInvincible || pObstacle == null) {
      return; // if player is not in an object or invincible - do nothing
    }
    logger.info("Player hit obstacle");
    // player is in iFrames OR no longer in an obstacle

    isInvincible = true;
    iFrameThread = new Thread(new RgcIFrameRunnable(this));
    iFrameThread.start();

    playerLosesLife();
  }

  /**
   * Lowers the player lives by one. If it falls below 1, the game is over.
   */
  public void playerLosesLife() {
    logger.info("Player loses life (" + (player.getCurrentLife() - 1) + ")");
    player.setCurrentLife(player.getCurrentLife() - 1);

    if (player.getCurrentLife() < 1) { // is player game over?
      endRun();
    }

    for (ReactiongameListener callback :
        callbacks) { // player loses a life
      callback.currentLife(player.getCurrentLife());
    }
  }

  /**
   * Methods checks if the player is in an aimtarget and pressed the right key.
   */
  public void checkForTargetHit() {
    if (pAimTarget != null && pAimTarget.getKey() == pKey) {
      logger.info(pAimTarget.getId() + " | " + pKey);
      score += 100;

      field.removeAimTarget(pAimTarget.getId());

      aimTargetClock.removeAimTarget(pAimTarget.getId());

      for (ReactiongameListener callback :
          callbacks) { // raise score
        callback.removeAimTarget(pAimTarget.getId());
        callback.changeScore(score);
      }
    }
  }


  /**
   * Adds an obstacle.
   *
   * @param obstacleId identifier
   */
  public void addObstacle(int obstacleId) {
    if (!isRunning) {
      return;
    }

    logger.info("Add obstacle (" + obstacleId + ")");

    field.addRandomObstacle(obstacleId);

    for (ReactiongameListener callback :
        callbacks) {

      RgcObstacle obstacle = field.getObstacles().get(obstacleId);
      callback.addObstacle(RgcObstacle.toObstacleDescriptor(obstacle));

    }
  }

  /**
   * Removes an obstacle.
   *
   * @param obstacleId identifier
   */
  public void removeObstacle(int obstacleId) {
    if (!isRunning) {
      return;
    }
    logger.info("Remove obstacle (" + obstacleId + ")");

    field.removeObstacle(obstacleId);

    for (ReactiongameListener callback :
        callbacks) {

      callback.removeObstacle(obstacleId);
    }

  }

  /**
   * Adds an aim target.
   *
   * @param aimTargetId identifier
   */
  public void addAimTarget(int aimTargetId) {
    if (!isRunning) {
      return;
    }
    RgcAimTarget aimTarget = field.addRandomAimTarget(aimTargetId);
    logger.info(
        "Add aim target (" + aimTargetId + ") (" + aimTarget.getX() + "|" + aimTarget.getY() + ")");

    for (ReactiongameListener callback :
        callbacks) {

      callback.addAimTarget(RgcAimTarget.toAimTargetDescriptor(aimTarget));
    }
  }

  /**
   * Removes an aim target.
   *
   * @param aimTargetId identifier
   */
  public void removeAimTarget(int aimTargetId) {
    if (!isRunning) {
      return;
    }
    logger.info("Removed aim target (" + aimTargetId + ")");

    field.removeAimTarget(aimTargetId);

    for (ReactiongameListener callback :
        callbacks) {

      callback.removeAimTarget(aimTargetId);
    }
  }

}
