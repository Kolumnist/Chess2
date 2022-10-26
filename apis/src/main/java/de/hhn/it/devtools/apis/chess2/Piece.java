package de.hhn.it.devtools.apis.chess2;

import java.util.ArrayList;

/**
 * This interface defines attributes of every piece
 *
 * @author Collin, Lara, Michel
 * @version 1.0
 */

public interface Piece {

  /** Holds all possible x,y coordinates of the piece where it can move */
  ArrayList<int[]> possibleMoves = new ArrayList<>();

  /** Current position of the piece */
  int[] pos = new int[2];

  /**
   * All possible moves are returned as a List of x, y coordinates
   * @return possibleMoves of the current piece
   */
  ArrayList<int[]> getPossibleMove();

  /*private void calculate() {

  }*/

  /**
   * @param pos is the new position
   */
  void setPos(int[] pos);
}
