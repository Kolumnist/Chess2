package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.Piece;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class implements the Queen which inherits from Piece.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */

public class Queen extends Piece {

  public Queen(char color, Coordinate coordinate) {
    super(color, coordinate);
  }

  /**
   * This method calculates all the possible movements of the queen piece. It also add the movements
   * which are not possible to go into an arraylist/ set.
   *
   * @param board the board of the game
   */
  @Override
  public void calculate(Board board) {
    canDefeatKing = false;
    possibleMoves = new Coordinate[64];
    int k = 0;
    for (int g = 1; g <= 7; g++) {
      for (int i = coordinate.getX() - g; i <= coordinate.getX() + g; i += g) {
        for (int j = coordinate.getY() - g; j <= coordinate.getY() + g; j += g) {
          possibleMoves[k++] = new Coordinate(i, j);
        }
      }
    }
    ArrayList<Integer> index = new ArrayList<>();

    //Testing if the Queen can defeat the enemy King.
    for (int i = 0; i < possibleMoves.length; i++) {
      if (board.getSpecificField(possibleMoves[i]).getFieldState() == FieldState.OTHER_KING) {
        canDefeatKing = true;
      }
      break;
    }

    for (int i = 0; i < possibleMoves.length; i++) {
      if (possibleMoves[i] == null) {
        index.add(i);
      }
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, index);

    Set<Integer> indexSet = new TreeSet<>();

    //This code checks that the queen can't go outside the board
    //(we add this movements into a set)
    for (int i = 0; i < possibleMoves.length; i++) {
      if ((possibleMoves[i].getX() == coordinate.getX()
          && possibleMoves[i].getY() == coordinate.getY())
          || possibleMoves[i].getY() < 0
          || possibleMoves[i].getX() < 0
          || possibleMoves[i].getY() > 7
          || possibleMoves[i].getX() > 7) {
        indexSet.add(i);
      }

      //region never look at this code

      //This code checks if there are other pieces/the bear/current pieces in the
      //possible movement of the queen because the queen can't defeat the current piece
      //and she can also not jump over a piece or defeat a piece and walks again so we add all
      //the movements the queen can't go into a set
      if (board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_CURRENT_PIECE
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_OTHER_PIECE
          || board.getSpecificField(possibleMoves[i]).getFieldState() == FieldState.HAS_BEAR
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.OTHER_KING) {
        int x = possibleMoves[i].getX() - coordinate.getX();
        int y = possibleMoves[i].getY() - coordinate.getY();
        int r = 1;
        int rr = 1;
        int n = 1;
        int nn = 1;
        int m = 1;
        int mm = 1;
        int d = 1;
        int dd = 1;
        for (int h = 1; h <= 7; h++) {
          for (int g = 1; g <= 7; g++) {

            if (x == -h && y == -g) {
              for (int u = 0; u < possibleMoves.length; u++) {
                if ((possibleMoves[u].getX() == possibleMoves[i].getX() - m)
                    && (
                    possibleMoves[u].getY() == possibleMoves[i].getY() - m)) {
                  indexSet.add(u);
                  m++;
                }
              }
            }
            if (x == 0 && y == -g) {
              for (int u = 0; u < possibleMoves.length; u++) {
                if ((possibleMoves[u].getX() == possibleMoves[i].getX()) && (
                    possibleMoves[u].getY() == possibleMoves[i].getY() - r)) {
                  indexSet.add(u);
                  r++;
                }
              }
            }
            if (x == -h && y == 0) {
              for (int u = 0; u < possibleMoves.length; u++) {
                if ((possibleMoves[u].getX() == possibleMoves[i].getX() - rr) && (
                    possibleMoves[u].getY() == possibleMoves[i].getY())) {
                  indexSet.add(u);
                  rr++;
                }
              }
            }
            if (x == h && y == g) {
              for (int u = 0; u < possibleMoves.length; u++) {
                if ((possibleMoves[u].getX() == possibleMoves[i].getX() + mm) && (
                    possibleMoves[u].getY() == possibleMoves[i].getY() + mm)) {
                  indexSet.add(u);
                  mm++;
                }
              }
            }
            if (x == 0 && y == g) {
              for (int u = 0; u < possibleMoves.length; u++) {
                if ((possibleMoves[u].getX() == possibleMoves[i].getX()) && (
                    possibleMoves[u].getY() == possibleMoves[i].getY() + n)) {
                  indexSet.add(u);
                  n++;
                }
              }
            }
            if (x == h && y == 0) {
              for (int u = 0; u < possibleMoves.length; u++) {
                if ((possibleMoves[u].getX() == possibleMoves[i].getX() + nn) && (
                    possibleMoves[u].getY() == possibleMoves[i].getY())) {
                  indexSet.add(u);
                  nn++;
                }
              }
            }
            if (x == -h && y == g) {
              for (int u = 0; u < possibleMoves.length; u++) {
                if ((possibleMoves[u].getX() == possibleMoves[i].getX() - d) && (
                    possibleMoves[u].getY() == possibleMoves[i].getY() + d)) {
                  indexSet.add(u);
                  d++;
                }
              }
            }
            if (x == h && y == -g) {
              for (int u = 0; u < possibleMoves.length; u++) {
                if ((possibleMoves[u].getX() == possibleMoves[i].getX() + dd) && (
                    possibleMoves[u].getY() == possibleMoves[i].getY() - dd)) {
                  indexSet.add(u);
                  dd++;
                }
              }
            }
          }
        }
        if (board.getSpecificField(possibleMoves[i]).getFieldState()
            == FieldState.HAS_CURRENT_PIECE) {
          indexSet.add(i);
        }
      } //endregion never look at this code
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, indexSet);
  }
}
