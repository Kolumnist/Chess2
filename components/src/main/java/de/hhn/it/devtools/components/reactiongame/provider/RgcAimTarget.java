package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;

/**
 * This class represents an aim target (goal) on the field.
 */
public class RgcAimTarget implements AimTarget {

  public static AimTargetDescriptor toAimTargetDescriptor(RgcAimTarget rgcAimTarget) {
    return new AimTargetDescriptor(rgcAimTarget.id, rgcAimTarget.xPosition,
                        rgcAimTarget.yPosition, RADIUS, rgcAimTarget.key);
  }

  public static int RADIUS = 25; // in px

  private final int xPosition;
  private final int yPosition;
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
    this.xPosition = x;
    this.yPosition = y;
    this.radius = r;
    this.key = key;
  }

  public int getxPosition() {
    return xPosition;
  }

  public int getyPosition() {
    return yPosition;
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
        + "x=" + xPosition
        + ", y=" + yPosition
        + ", r=" + radius
        + ", key=" + key
        + ", id=" + id
        + '}';
  }
}
