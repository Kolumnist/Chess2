package de.hhn.it.devtools.apis.reactiongame;

/**
 * Difficulties the runs can have. The difficulty of a run decides about how often and how many
 * obstacles spawn. It also decides about how many aim targets can be on the field and how long they
 * live.
 */
public enum Difficulty {
  EASY("Easy", 5, 10, 8,
      8, 12),
  MEDIUM("Medium", 1, 10, 9,
      6, 3),
  HARD("Hard", 2, 14, 12,
      2, 3);

  public final String label;
  public final int obstacleIntervall;
  public final int highWatermark;
  public final int lowWatermark;
  public final int maxAimtargets;
  public final int aimTargetLifetime; // in s


  Difficulty(String label, int obstacleIntervall, int highWatermark, int lowWatermark,
      int maxAimtargets, int aimTargetLifetime) {
    this.label = label;
    this.obstacleIntervall = obstacleIntervall;
    this.highWatermark = highWatermark;
    this.lowWatermark = lowWatermark;
    this.maxAimtargets = maxAimtargets;
    this.aimTargetLifetime = aimTargetLifetime;
  }
}
