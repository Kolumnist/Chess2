package de.hhn.it.devtools.components.reactiongame.provider;

public class Obstacle {
  private final int x1;
  private final int x2;
  private final int y1;
  private final int y2;
  private final int id;


  /**
   * Basic constructor for an aim target.
   *
   * @param x1 x-position
   * @param x2 x-position
   * @param y1 y-position
   * @param y2 y-position
   * @param id id
   */
  public Obstacle(int x1, int x2, int y1, int y2, int id) {
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
    this.id = id;
  }
  public int getX1() {
    return x1;
  }

  public int getX2() {
    return x2;
  }

  public int getY1() {
    return y1;
  }
  public int getY2() {
    return y2;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Obstacle{" +
        "Point 1: (" + x1 +
        "|" + y1 +
        "), Point 2: (" + x2 +
        "|" + y2 +
        ")";
  }
}
