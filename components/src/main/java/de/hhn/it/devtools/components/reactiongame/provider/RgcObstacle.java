package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.reactiongame.ObstacleDescriptor;

/**
 * Obstacle class.
 */
public class RgcObstacle implements Obstacle, Comparable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcObstacle.class);

  public static int WIDTH = 70; // in px

  public static ObstacleDescriptor toObstacleDescriptor(RgcObstacle rgcObstacle) {
    return new ObstacleDescriptor(rgcObstacle.id,rgcObstacle.x1,
        rgcObstacle.y1, rgcObstacle.x2, rgcObstacle.y2);
  }

  private final int id;
  private final int x1;
  private final int y1;
  private final int x2;
  private final int y2;


  /**
   * Basic constructor for an aim target.
   *
   * @param id id
   * @param x1 x-position Point A
   * @param y1 y-position Point A
   * @param x2 x-position Point B
   * @param y2 y-position Point B
   */
  public RgcObstacle(int id, int x1, int y1, int x2, int y2) {
    this.id = id;
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
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

   @Override
  public int getId() {
    return id;
  }


  @Override
  public String toString() {
    return "Obstacle{"
        + "Point 1: ("
        + x1
        + "|"
        + y1
        + "), Point 2: ("
        + x2
        + "|"
        + y2
        + ")";
  }

  /**
   * Compares RelObstacle about their highest y coordinate.
   *
   * @param o the object to be compared.
   * @return -1, 0 and 1 - o is higher, both are equal high, this is higher
   */
  @Override
  public int compareTo(Object o) {
    if (((RgcObstacle) o).getY1() == y1) {
      return 0;
    }
    if (((RgcObstacle) o).getY1() < y1) {
      return 1;
    }
    return -1;
  }
}
