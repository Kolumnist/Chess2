package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.Piece;
import java.util.ArrayList;

/**
 * This class implements the Elephant which inherits from Piece.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */

public class Elephant extends Piece {

  public Elephant(char color, Coordinate coordinate) {
    super(color, coordinate);
  }

  /**
   * This method calculates all the possible movements of the elephant piece.
   * It also add the movements which are not possible to go into an arraylist.
   *
   * @param board the board of the game
   */
  public void calculate(Board board) {
    possibleMoves = new Coordinate[10];
    int k = 0;

    for (int i = coordinate.getX() - 2; i <= coordinate.getX() + 2; i += 2) {
      for (int j = coordinate.getY() - 2; j <= coordinate.getY() + 2; j += 2) {
        possibleMoves[k++] = new Coordinate(i, j);
      }
    }
    ArrayList<Integer> index = new ArrayList<>();

    for (int i = 0; i < possibleMoves.length; i++) {
      if (possibleMoves[i] == null) {
        index.add(i);
      }
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, index);
    index = new ArrayList<>();

    //This code checks that the fish can't go outside the board
    //(we add this movements into an arraylist)
    for (int i = 0; i < possibleMoves.length; i++) {
      if (possibleMoves[i] == null
          || possibleMoves[i].getY() < 0
          || possibleMoves[i].getX() < 0
          || possibleMoves[i].getY() > 7
          || possibleMoves[i].getX() > 7
          || possibleMoves[i].compareCoordinates(coordinate)
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_CURRENT_PIECE) {
        index.add(i);
      }
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, index);
  }
}
