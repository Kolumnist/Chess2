package de.hhn.it.devtools.apis.reactiongame;

/**
 * Difficulties the runs can have. The difficulty of a run decides about how often and how many
 * obstacles spawn. It also decides about how many aim targets can be on the field and how long they
 * live.
 */
public enum Difficulty {
  /**
   * Parameter for "Easy" difficulty
   */
  EASY("Easy", 5, 10, 8,
      8, 3, 10),
  /**
   * Parameter for "Medium" difficulty
   */
  MEDIUM("Medium", 1, 10, 9,
      6, 3, 15),
  /**
   * Parameter for "Hard" difficulty
   */
  HARD("Hard", 1, 14, 12,
      2, 2, 20),
  /**
   * Parameter for "dev" difficulty for easier testing
   */
  DEVELOPER("Dev", 1, 3, 2,
      2,3, 0); // Developer Difficulty for testing

  /**
   * Name descriptor
   */
  public final String label;
  /**
   * intervall for spawning obstacles
   */
  public final int obstacleIntervall;
  /**
   * variable for maximum amount of simultaneously living obstacles
   */
  public final int highWatermark;
  /**
   * variable for minimum amount of simultaneously living obstacles
   */
  public final int lowWatermark;
  /**
   * variable for maximum amount of simultaneously living targets
   */
  public final int maxAimtargets;
  /**
   * variable for the lifetime of aim targets in seconds
   */
  public final int aimTargetLifetime; // in s

  /**
   * multiplier to calculate the points
   */
  public final int scoreMultiplier;


  Difficulty(String label, int obstacleIntervall, int highWatermark, int lowWatermark,
      int maxAimtargets, int aimTargetLifetime, int scoreMultiplier) {
    this.label = label;
    this.obstacleIntervall = obstacleIntervall;
    this.highWatermark = highWatermark;
    this.lowWatermark = lowWatermark;
    this.maxAimtargets = maxAimtargets;
    this.aimTargetLifetime = aimTargetLifetime;
    this.scoreMultiplier = scoreMultiplier;
  }
}
