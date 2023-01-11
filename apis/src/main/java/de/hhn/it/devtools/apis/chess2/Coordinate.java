package de.hhn.it.devtools.apis.chess2;

/**
 * This class is a Coordinate.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */

public class Coordinate {
  private int xValue;
  private int yValue;

  /**
   * Constructor that initializes the x and y values.
   *
   * @param xValue the x value of the coordinate.
   * @param yValue the y value of the coordinate.
   */
  public Coordinate(int xValue, int  yValue) {
    this.xValue = xValue;
    this.yValue = yValue;
  }

  /**
   * Default Constructor.
   */
  public Coordinate() {}

  /**
   * Returns the xValue of the Coordinate.
   *
   * @return the xValue as int
   */
  public int getX() {
    return xValue;
  }

  /**
   * Returns the yValue of the Coordinate.
   *
   * @return the yValue as int
   */
  public int getY() {
    return yValue;
  }

  /**
   * Returns true if the coordinates are the same and false if not.
   *
   * @param coordinate the coordinate you want to compare with your coordinate
   * @return the result of the comparison as a boolean
   */
  public boolean compareCoordinates(Coordinate coordinate) {
    if (xValue == coordinate.getX() && yValue == coordinate.getY()) {
      return true;
    }
    return false;
  }
}
