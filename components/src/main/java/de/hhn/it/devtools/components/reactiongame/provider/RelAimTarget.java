package de.hhn.it.devtools.components.reactiongame.provider;

public class AimTarget {

  private final int x;
  private final int y;
  private final int r;
  private final char key;
  private final int id;


  /**
   * Basic constructor for an aim target.
   *
   * @param x position
   * @param y position
   * @param r radius
   * @param key keyboard key
   */
  public AimTarget(int x, int y, int r, char key, int id) {
    this.x = x;
    this.y = y;
    this.r = r;
    this.key = key;
    this.id = id;
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
