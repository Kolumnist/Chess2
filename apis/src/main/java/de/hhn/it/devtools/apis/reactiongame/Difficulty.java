package de.hhn.it.devtools.apis.reactiongame;

public enum Difficulty {
    EASY("Easy", 5, 10, 8, 8),
    MEDIUM("Medium", 4, 12, 10, 6),
    HARD("Hard", 3, 14, 12, 4);

    public final String label;
    public final int obstacleIntervall;
    public final int highWatermark;
    public final int lowWatermark;
    public final int maxAimtargets;

    Difficulty(String label, int obstacleIntervall, int highWatermark, int lowWatermark,
        int maxAimtargets) {
        this.label = label;
        this.obstacleIntervall = obstacleIntervall;
        this.highWatermark = highWatermark;
        this.lowWatermark = lowWatermark;
        this.maxAimtargets = maxAimtargets;
    }
}
