package de.hhn.it.devtools.apis.chess2;

/**
 * This class is a Coordinate.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */

public class Coordinate {

  private int x;
  private int y;

  /**
   * Constructor that initializes the x and y values.
   *
   * @param x the x value of the coordinate.
   * @param y the y value of the coordinate.
   */
  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Default Constructor.
   */
  public Coordinate() {
  }

  /**
   * Returns the xValue of the Coordinate.
   *
   * @return the xValue as int
   */
  public int getX() {
    return x;
  }

  /**
   * Returns the yValue of the Coordinate.
   *
   * @return the yValue as int
   */
  public int getY() {
    return y;
  }

  /**
   * Returns true if the coordinates are the same and false if not.
   *
   * @param coordinate the coordinate you want to compare with your coordinate
   * @return the result of the comparison as a boolean
   */
  public boolean compareCoordinates(Coordinate coordinate) {
    if (x == coordinate.getX() && y == coordinate.getY()) {
      return true;
    }
    return false;
  }

  /**
   * Returns x and y as a String in the format "xy".
   *
   * @return the coordinate as a String
   */
  public String coordinateToString() {
    return "" + x + y;
  }

  /**
   * Returns x and y as a String in the format "{ x, y }".
   *
   * @return the coordinate as a String
   */
  public String toString() {
    return "{ X = " + x + ", Y = " + y + " }";
  }
}
