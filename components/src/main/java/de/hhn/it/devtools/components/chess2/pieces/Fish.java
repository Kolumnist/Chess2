package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.Piece;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class implements the Fish which inherits from Piece.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */

public class Fish extends Piece {

  public Fish(char color, Coordinate coordinate) {
    super(color, coordinate);
  }

  /**
   * This method calculates all the possible movements of the fish piece. It also add the movements
   * which are not possible to go into an arraylist/ set.
   *
   * @param board the board of the game
   */
  @Override
  public void calculate(Board board) {
    canDefeatKing = false;
    possibleMoves = new Coordinate[8];
    int k = 0;
    if (color == 'r') {
      for (int i = coordinate.getX() - 1; i <= coordinate.getX() + 1; i++) {
        for (int j = coordinate.getY(); j <= coordinate.getY() + 1; j++) {
          //because the fish shoudn't go straight forward
          if ((i == coordinate.getX()) && (j == coordinate.getY() + 1)) {
            continue;
          }
          possibleMoves[k++] = new Coordinate(i, j);
        }
      }
    } else if (color == 'b') {
      for (int i = coordinate.getX() - 1; i <= coordinate.getX() + 1; i++) {
        for (int j = coordinate.getY() - 1; j <= coordinate.getY(); j++) {
          //because the fish shoudn't go straight forward
          if ((i == coordinate.getX()) && (j == coordinate.getY() - 1)) {
            continue;
          }
          possibleMoves[k++] = new Coordinate(i, j);
        }
      }
    }
    ArrayList<Integer> index = new ArrayList<>();

    for (int i = 0; i < possibleMoves.length; i++) {
      if (possibleMoves[i] == null) {
        index.add(i);
      }
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, index);
    Set<Integer> indexSet = new TreeSet<>();

    //This code checks that the fish can't go outside the board
    //(we add this movements into a set)
    for (int i = 0; i < possibleMoves.length; i++) {
      if ((possibleMoves[i].getX() == coordinate.getX()
          && possibleMoves[i].getY() == coordinate.getY())
          || possibleMoves[i].getY() < 0
          || possibleMoves[i].getX() < 0
          || possibleMoves[i].getY() > 7
          || possibleMoves[i].getX() > 7
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_CURRENT_PIECE) {
        indexSet.add(i);
      }
      //This code look that the fish can't go on a field if there is another piece/ the bear
      // on the left or right side of the fish (because the fish can only
      // defeat other pieces on the diagonal
      if ((possibleMoves[i].getX() == coordinate.getX() + 1
          && possibleMoves[i].getY() == coordinate.getY())
          && (board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_OTHER_PIECE
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_BEAR)
          || (possibleMoves[i].getX() == coordinate.getX() - 1
          && possibleMoves[i].getY() == coordinate.getY())
          && (board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_OTHER_PIECE
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_BEAR)) {
        indexSet.add(i);
      }
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, indexSet);

    //Testing if the fish can defeat the enemy King.
    for (int i = 0; i < possibleMoves.length; i++) {
      if (board.getSpecificField(possibleMoves[i]).getFieldState() == FieldState.OTHER_KING) {
        canDefeatKing = true;
        break;
      }
    }
  }
}

