package de.hhn.it.devtools.apis.duckhunt;

/**
 * Represents the orientation of the duck.
 */
public enum DuckOrientation {
  NORTH(0, -1),
  NORTHEAST(1, -1),
  EAST(1, 0),
  SOUTHEAST(1, 1),
  SOUTH(0, 1),
  SOUTHWEST(-1, 1),
  WEST(-1, 0),
  NORTHWEST(-1, -1);

  private final int x;
  private final int y;

  DuckOrientation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
