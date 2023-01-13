package de.hhn.it.devtools.components.reactiongame.provider;

/**
 * This class is a runnable which counts down the time the player is in his iFrames.
 */
public class RgcIFrameRunnable implements Runnable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcIFrameRunnable.class);
  private final RgcLogic logic;

  /**
   * Standard constructor for the IFrameRunnable.
   *
   * @param logic Logic
   */
  public RgcIFrameRunnable(RgcLogic logic) {
    this.logic = logic;
  }

  @Override
  public void run() {
    logger.info("IFRAME timer started");

    try {
      Thread.sleep(1500); // sleep 1.5 seconds
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    logic.setInvincible(false); // i frames stop

    logger.info("IFRAME timer ended");

    logic.playerHitObstacle(); // check again if player is in an obstacles
  }
}
