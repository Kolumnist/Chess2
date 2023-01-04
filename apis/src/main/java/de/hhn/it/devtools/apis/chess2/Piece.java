package de.hhn.it.devtools.apis.chess2;

import java.util.ArrayList;

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
  public Coordinate[] getPossibleMove() {
    return possibleMoves;
  }

  /**
   * The coordinate were the current piece stands is returned.
   *
   * @return coordinate of the current piece.
   */
  public Coordinate getCoordinate() {
    return coordinate;
  }

  /**
   * Sets the new coordinate.
   *
   * @param coordinate is the new coordinate.
   */
  public void setCoordinate(Coordinate coordinate) {
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

  /**
   * This method calculates the possible moves of the piece.
   */
  public abstract void calculate(Board board);

  public Piece(char color, Coordinate coordinate) {
    this.color = color;
    this.coordinate = coordinate;
  }

  /**
   * This method takes a Coordinate[] and a ArrayList of invalid index and returns the shortened
   * Coordinate[].
   *
   * @param possibleMoves all possible Coordinates the piece could move to
   * @param index         a List of index of all Coordinates that are invalid
   * @return shorted Array of possibleMoves with only contains valid moves
   */
  protected Coordinate[] shortenCoordinateArray(Coordinate[] possibleMoves,
      ArrayList<Integer> index) {
    Coordinate[] shortedArray = new Coordinate[possibleMoves.length - index.size()];
    int j = 0;
    int k = 0;

    for (int i = 0; i < possibleMoves.length; i++) {
      if (index.get(j) == i) {
        if (j < index.size() - 1) {
          j++;
        }
        continue;
      }
      shortedArray[k] = possibleMoves[i];
      k++;
    }
    return shortedArray;
  }
}


