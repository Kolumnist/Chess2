package de.hhn.it.devtools.apis.reactiongame;

/**
 * Describes an aim target: x and y position, also the required key.
 */
public class AimTargetDescriptor {

  private final int x;
  private final int y;
  private final char key;


  /**
   * Basic constructor for an aim target.
   *
   * @param x position
   * @param y position
   * @param key keyboard key
   */
  public AimTargetDescriptor(int x, int y, char key) {
    this.x = x;
    this.y = y;
    this.key = key;
  }

  @Override
  public String toString() {
    return "AimTargetDescriptor{" +
        "x=" + x +
        ", y=" + y +
        ", key=" + key +
        '}';
  }
}
