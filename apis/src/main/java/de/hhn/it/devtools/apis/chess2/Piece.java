package de.hhn.it.devtools.apis.chess2;

/**
 * This abstract class defines attributes of every piece.
 *
 * @author Collin, Lara, Michel
 * @version 1.0
 */

public abstract class Piece {

  /**
   * Holds all possible x,y coordinates of the piece where it can move.
   */
  Coordinate[] possibleMoves = new Coordinate[0];

  /**
   * Current position of the piece.
   */
  Coordinate coordinate = new Coordinate();

  /**
   * gives the piece a color
   */
  char color;


  /**
   * All possible moves are returned as a List of x, y coordinates.
   *
   * @return possibleMoves of the current piece
   */
  Coordinate[] getPossibleMove() {
    return possibleMoves;
  }

  /*private void calculate() {   -> muss in die einzelnen Piece Klassen

  }*/

  /**
   * Sets the new coordinate.
   *
   * @param coordinate is the new coordinate
   */
  void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }
}


