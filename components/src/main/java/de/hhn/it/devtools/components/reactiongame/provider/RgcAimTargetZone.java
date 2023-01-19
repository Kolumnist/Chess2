package de.hhn.it.devtools.components.reactiongame.provider;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the zone on the screen where AimTargets can spawn
 */
public class RgcAimTargetZone {


  private final int x1; // Point A x
  private final int y1; // Point A y

  private final int x2; // Point B x
  private final int y2; // Point B y

  private ArrayList<RgcAimTarget> aimTargets; // List of aimTargets currently in this zone

  public RgcAimTargetZone(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;

    aimTargets = new ArrayList<>();

  }


  public ArrayList<RgcAimTarget> getAimTargets() {
    return aimTargets;
  }

  public RgcAimTarget addRandomAimTarget(int aimTargetId) {

    int x = new Random().nextInt(x2 - x1) + x1;
    int y = new Random().nextInt(y2 - y1) + y1;

    RgcAimTarget aimTarget = new RgcAimTarget(aimTargetId, x, y, RgcAimTarget.RADIUS,
        generateRandomKeyforAimTarget());

    aimTargets.add(aimTarget);

    return aimTarget;
  }

  public char generateRandomKeyforAimTarget() {
    char[] chars = {'q', 'w', 'e', 'r'};
    return chars[new Random().nextInt(chars.length)];
  }

}
