package de.hhn.it.devtools.apis.chess2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

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
   * Indicates if the piece can defeat the king.
   */
  protected boolean canDefeatKing;

  /**
   * The constructor of the piece class.
   *
   * @param color is the color of the piece
   * @param coordinate is the coordinate where the piece stands at the beginning
   */
  public Piece(char color, Coordinate coordinate) {
    this.color = color;
    this.coordinate = coordinate;
    canDefeatKing = false;
  }

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
   * Returns if the piece is able to defeat the enemy king.
   *
   * @return if the piece can defeat the enemy king.
   */
  public boolean getCanDefeatKing() {
    return canDefeatKing;
  }

  /**
   * This method calculates the possible moves of the piece.
   */
  public abstract void calculate(Board board);

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

    if (shortedArray.length == 0){
      return shortedArray;
    }

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

  /**
   * This method takes a Coordinate[] and a Set of invalid index and returns the shortened
   * Coordinate[].
   *
   * @param possibleMoves all possible Coordinates the piece could move to
   * @param index         a List of index of all Coordinates that are invalid
   * @return shorted Array of possibleMoves with only contains valid moves
   */
  protected Coordinate[] shortenCoordinateArray(Coordinate[] possibleMoves,
      Set<Integer> index) {
    Coordinate[] shortedArray;

    if (possibleMoves.length == index.size()) {
      return shortedArray = new Coordinate[0];
    }

    shortedArray = new Coordinate[Math.abs(possibleMoves.length - index.size())];
    int[] array = new int[index.size()];
    int j = 0;
    int k = 0;

    Iterator iterator;
    for (iterator = index.iterator(); iterator.hasNext();) {
      array[j++] = (Integer) iterator.next();
    }

    j = 0;
    for (int i = 0; i < possibleMoves.length; i++) {
      if (array[j] == i) {
        if (j < index.size() - 1) {
          j++;
        }
        continue;
      }
      shortedArray[k++] = possibleMoves[i];
    }

    return shortedArray;
  }
}


