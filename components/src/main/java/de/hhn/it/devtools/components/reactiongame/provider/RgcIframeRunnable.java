package de.hhn.it.devtools.components.reactiongame.provider;

/**
 * This class is a runnable which counts down the time the player is in his iFrames.
 */
public class RgcIframeRunnable implements Runnable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcIframeRunnable.class);
  private final RgcRun run;

  /**
   * Standard constructor for the IFrameRunnable.
   *
   * @param logic Logic
   */
  public RgcIframeRunnable(RgcRun logic) {
    this.run = logic;
  }

  @Override
  public void run() {
    logger.info("IFRAME timer started");

    try {
      Thread.sleep(1500); // sleep 1.5 seconds
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    run.setInvincible(false); // i frames stop

    logger.info("IFRAME timer ended");

    run.playerHitObstacle(); // check again if player is in an obstacles
  }
}
