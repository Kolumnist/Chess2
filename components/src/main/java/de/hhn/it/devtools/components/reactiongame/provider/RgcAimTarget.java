package de.hhn.it.devtools.components.reactiongame.provider;

/**
 *
 */
public class RgcAimTarget implements AimTarget {

  public static int RADIUS = 25; // in px

  private final int x;
  private final int y;
  private final int r;
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
    this.x = x;
    this.y = y;
    this.r = r;
    this.key = key;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getR() {
    return r;
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
    return "AimTarget{" +
        "x=" + x +
        ", y=" + y +
        ", r=" + r +
        ", key=" + key +
        ", id=" + id +
        '}';
  }
}
