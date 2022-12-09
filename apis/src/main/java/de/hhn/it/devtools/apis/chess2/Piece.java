package de.hhn.it.devtools.apis.chess2;

/**
 * This abstract class defines attributes of every piece.
 *
 * @author Collin Hoss, Michel Jouaux, Lara Mangi
 * @version 1.0
 */

public abstract class Piece {

  /**
   * Holds all possible x,y coordinates of the piece where it can move.
   */
  protected Coordinate[] possibleMoves = new Coordinate[0];

  /**
   * Current position of the piece.
   */
  protected Coordinate coordinate = new Coordinate();

  /**
   * Gives the piece a color.
   */
  protected char color;


  /**
   * All possible moves are returned as a List of x, y coordinates.
   *
   * @return possibleMoves of the current piece.
   */
  Coordinate[] getPossibleMove() {
    return possibleMoves;
  }


  /**
   * This method calculates the possible moves of the piece.
   */
  protected abstract void calculate();


  /**
   * Sets the new coordinate.
   *
   * @param coordinate is the new coordinate.
   */
  void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  /**
   * Returned a color.
   *
   * @return the color of the current piece.
   */
  public char getColor() {
    return color;
  }

  /**
   * Sets the new color.
   *
   * @param color is the new color.
   */
  public void setColor(char color) {
    this.color = color;
  }
}


