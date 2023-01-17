package de.hhn.it.devtools.apis.reactiongame;

/**
 * Describes an obstacle: x and y of the top left and bottom right corner.
 */
public class ObstacleDescriptor implements GameObstacleDescriptor {


  private final int id;
  private final int x1;
  private final int y1;

  private final int x2;
  private final int y2;



  /**
   * Basic constructor for an obstacle.
   *
   * @param x1 top left
   * @param y1 top left y
   * @param x2 bottom right x
   * @param y2 bottom right y
   */
  public ObstacleDescriptor(int id, int x1, int y1, int x2, int y2) {
    this.id = id;
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }


  public int getX1() {
    return x1;
  }

  public int getY1() {
    return y1;
  }

  public int getX2() {
    return x2;
  }

  public int getY2() {
    return y2;
  }

  @Override
  public String toString() {
    return "ObstacleDescriptor{" +
        "Point 1: (" + x1 +
        "|" + y1 +
        "), Point 2: (" + x2 +
        "|" + y2 +
        ")";
  }

  @Override
  public int getId() {
    return id;
  }
}
