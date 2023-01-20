package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;

/**
 * This class represents an aim target (goal) on the field.
 */
public class RgcAimTarget implements AimTarget {

  public static AimTargetDescriptor toAimTargetDescriptor(RgcAimTarget rgcAimTarget) {
    return new AimTargetDescriptor(rgcAimTarget.id, rgcAimTarget.xposition,
                        rgcAimTarget.yposition, RADIUS, rgcAimTarget.key);
  }

  public static int RADIUS = 25; // in px

  private final int xposition;
  private final int yposition;
  private final int radius;
  private final char key;
  private final int id;


  /**
   * Basic constructor for an aim target.
   *
   * @param id identifier
   * @param x position
   * @param y position
   * @param r radius
   * @param key keyboard key
   */
  public RgcAimTarget(int id, int x, int y, int r, char key) {
    this.id = id;
    this.xposition = x;
    this.yposition = y;
    this.radius = r;
    this.key = key;
  }

  public int getXposition() {
    return xposition;
  }

  public int getYposition() {
    return yposition;
  }

  public int getRadius() {
    return radius;
  }

  public char getKey() {
    return key;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "AimTarget{"
        + "x=" + xposition
        + ", y=" + yposition
        + ", r=" + radius
        + ", key=" + key
        + ", id=" + id
        + '}';
  }
}
