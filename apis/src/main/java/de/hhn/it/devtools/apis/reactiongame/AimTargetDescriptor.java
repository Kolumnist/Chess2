package de.hhn.it.devtools.apis.reactiongame;

/**
 * Describes an aim target: x and y position, the radius and the required key.
 */
public class AimTargetDescriptor implements GameObstacleDescriptor{

  private final int id;
  private final int x;
  private final int y;
  private final int r;
  private final char key;


  /**
   * Basic constructor for an aim target.
   *
   * @param x position
   * @param y position
   * @param r radius
   * @param key keyboard key
   */
  public AimTargetDescriptor(int id, int x, int y, int r, char key) {
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
  public String toString() {
    return "AimTargetDescriptor{" +
        "x=" + x +
        ", y=" + y +
        ", key=" + key +
        '}';
  }

  @Override
  public void getId() {

  }
}
