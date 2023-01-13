package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;
import java.util.ArrayList;

/**
 * Class communicate between the service and components. Also notifies the callbacks.
 */
public class RgcLogic {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcLogic.class);

  private final ArrayList<ReactiongameListener> callbacks = new ArrayList<>();
  private final RgcField gameField = new RgcField();
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

  private final Thread iFrameThread;


  /**
   * Standard constructor for the logic.
   *
   * @param difficulty difficulty of the run.
   */
  public RgcLogic(Difficulty difficulty) {
    this.difficulty = difficulty;
    player = new RgcPlayer("");

    aimTargetClock = new RgcAimTargetClock(this);
    obstacleClock = new RgcObstacleClock(this);

    iFrameThread = new Thread(new RgcIFrameRunnable(this));

    logger.info("created");
  }

  public ArrayList<ReactiongameListener> getCallbacks() {
    return callbacks;
  }

  public RgcField getGameField() {
    return gameField;
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
    logger.info("invis state = " + isInvincible);
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


  /**
   * Pauses the clocks.
   */
  public void pauseClocks() {
    obstacleClock.setRunning(false);
    aimTargetClock.setRunning(false);
  }

  /**
   * Continues clocks.
   */
  public void continueClocks() {
    obstacleClock.setRunning(true);
    aimTargetClock.setRunning(true);
  }

  /**
   * Ends / stops clocks.
   */
  public void endRun() {
    obstacleClock.setRunning(false);
    obstacleClock.setEnded(true);

    aimTargetClock.setRunning(false);
    aimTargetClock.setEnded(true);

    iFrameThread.stop();

    for (ReactiongameListener callback :
        callbacks) {
      callback.gameOver();
    }

    logger.info("Game Over");
  }


  /**
   * Methods gets called when player runs into an obstacle or after his iframes end.
   */
  public void playerHitObstacle() {
    if (isInvincible || pObstacle == null) {
      return; // if player is not in an object or invincible - do nothing
    }
    // player is in iFrames OR no longer in an obstacle

    logger.info("Player hit obstacle");

    isInvincible = true;
    iFrameThread.start();

    playerLosesLife();
  }

  /**
   * Lowers the player lives by one. If it falls below 1, the game is over.
   */
  public void playerLosesLife() {
    player.setCurrentLife(player.getCurrentLife() - 1);

    if (player.getCurrentLife() < 1) { // is player game over?
      endRun();
    }

    logger.info("Current life updated: " + player.getCurrentLife());

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
      score += 100;

      for (ReactiongameListener callback :
          callbacks) { // raise score
        callback.changeScore(score);

        logger.info("Score updated: " + score);
      }
    }
  }


  /**
   * Adds an obstacle.
   *
   * @param obstacleId identifier
   */
  public void addObstacle(int obstacleId) {
    gameField.addRandomObstacle(obstacleId);

    for (ReactiongameListener callback :
        callbacks) {

      callback.addObstacle(RgcObstacle.toObstacleDescriptor(gameField.getObstacles()
          .get(gameField.getObstacles().size())));

    }

    logger.info("Added obstacle (id = " + obstacleId + ")");
  }

  /**
   * Removes an obstacle.
   *
   * @param obstacleId identifier
   */
  public void removeObstacle(int obstacleId) {
    gameField.removeObstacle(obstacleId);

    for (ReactiongameListener callback :
        callbacks) {

      callback.removeObstacle(obstacleId);
    }

    logger.info("Removed obstacle (id = " + obstacleId + ")");
  }

  /**
   * Adds an aim target.
   *
   * @param aimTargetId identifier
   */
  public void addAimTarget(int aimTargetId) {
    gameField.addRandomAimTarget(aimTargetId);

    for (ReactiongameListener callback :
        callbacks) {

      callback.removeAimTarget(aimTargetId);
    }

    logger.info("Added aim target (id = " + aimTargetId + ")");
  }

  /**
   * Removes an aim target.
   *
   * @param aimTargetId identifier
   */
  public void removeAimTarget(int aimTargetId) {
    gameField.removeAimTarget(aimTargetId);

    for (ReactiongameListener callback :
        callbacks) {

      callback.removeAimTarget(aimTargetId);
    }

    logger.info("Removed aim target (id = " + aimTargetId + ")");
  }


  public static void main(String[] args) {
    RgcLogic logic = new RgcLogic(Difficulty.MEDIUM);
  }
}
