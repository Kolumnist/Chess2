package de.hhn.it.devtools.apis.chess2;

/**
 * This class is a Coordinate.
 *
 * @author Collin, Lara, Michel
 * @version 1.0
 */

public class Coordinate {
  private int xValue;
  private int yValue;

  /**
   * Constructor.
   *
   * @param xValue the x value of the coordinate
   * @param yValue the y value of the coordinate
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
   * Returns the x value of the coordinate.
   *
   * @return int the x value of the coordinate
   */
  public int getX() {
    return xValue;
  }

  /**
   * Returns the y value of the coordinate.
   *
   * @return int the y value of the coordinate
   */
  public int getY() {
    return yValue;
  }
}
