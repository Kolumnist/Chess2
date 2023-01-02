package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.Piece;
import java.util.ArrayList;

/**
 * This class implements the Fishqueen which inherits from Piece.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */

public class Fishqueen extends Piece {

  public Fishqueen(char color, Coordinate coordinate) {
    super(color, coordinate);
  }

  @Override
  public void calculate(Board board) {
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

    for (int i = 0; i < possibleMoves.length; i++) {
      if (possibleMoves[i] == null) {
        index.add(i);
      }
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, index);
    index = new ArrayList<>();

    for (int i = 0; i < possibleMoves.length; i++) {
      if ((possibleMoves[i].getX() == coordinate.getX()
          && possibleMoves[i].getY() == coordinate.getY())
          || possibleMoves[i].getY() < 0
          || possibleMoves[i].getX() < 0
          || possibleMoves[i].getY() > 7
          || possibleMoves[i].getX() > 7) {
        index.add(i);
      }
      //region never look at this code
      if (board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_CURRENT_PIECE
          || board.getSpecificField(possibleMoves[i]).getFieldState() == FieldState.HAS_OTHER_PIECE
          || board.getSpecificField(possibleMoves[i]).getFieldState() == FieldState.HAS_BEAR) {
        int x = possibleMoves[i].getX() - coordinate.getX();
        int y = possibleMoves[i].getY() - coordinate.getY();
        for (int h = 1; h <= 7; h++) {
          for (int g = 1; g <= 7; g++) {

            if (x == -h && y == -g) {
              for (int t = 1; t <= 7; t++) {
                for (int u = 0; u < possibleMoves.length; u++) {
                  if ((possibleMoves[u].getX() == possibleMoves[i].getX() - t) && (
                      possibleMoves[u].getY() == possibleMoves[i].getY() - t)) {
                    index.add(u);
                  }
                }
              }
            }
            if (x == 0 && y == -g) {
              for (int t = 1; t <= 7; t++) {
                for (int u = 0; u < possibleMoves.length; u++) {
                  if ((possibleMoves[u].getX() == possibleMoves[i].getX()) && (
                      possibleMoves[u].getY() == possibleMoves[i].getY() - t)) {
                    index.add(u);
                  }
                }
              }
            }
            if (x == -h && y == 0) {
              for (int t = 1; t <= 7; t++) {
                for (int u = 0; u < possibleMoves.length; u++) {
                  if ((possibleMoves[u].getX() == possibleMoves[i].getX() - t) && (
                      possibleMoves[u].getY() == possibleMoves[i].getY())) {
                    index.add(u);
                  }
                }
              }
            }
            if (x == h && y == g) {
              for (int t = 1; t <= 7; t++) {
                for (int u = 0; u < possibleMoves.length; u++) {
                  if ((possibleMoves[u].getX() == possibleMoves[i].getX() + t) && (
                      possibleMoves[u].getY() == possibleMoves[i].getY() + t)) {
                    index.add(u);
                  }
                }
              }
            }
            if (x == 0 && y == g) {
              for (int t = 1; t <= 7; t++) {
                for (int u = 0; u < possibleMoves.length; u++) {
                  if ((possibleMoves[u].getX() == possibleMoves[i].getX()) && (
                      possibleMoves[u].getY() == possibleMoves[i].getY() + t)) {
                    index.add(u);
                  }
                }
              }
            }
            if (x == h && y == 0) {
              for (int t = 1; t <= 7; t++) {
                for (int u = 0; u < possibleMoves.length; u++) {
                  if ((possibleMoves[u].getX() == possibleMoves[i].getX() + t) && (
                      possibleMoves[u].getY() == possibleMoves[i].getY())) {
                    index.add(u);
                  }
                }
              }
            }
            if (x == -h && y == g) {
              for (int t = 1; t <= 7; t++) {
                for (int u = 0; u < possibleMoves.length; u++) {
                  if ((possibleMoves[u].getX() == possibleMoves[i].getX() - t) && (
                      possibleMoves[u].getY() == possibleMoves[i].getY() + t)) {
                    index.add(u);
                  }
                }
              }
            }
            if (x == h && y == -g) {
              for (int t = 1; t <= 7; t++) {
                for (int u = 0; u < possibleMoves.length; u++) {
                  if ((possibleMoves[u].getX() == possibleMoves[i].getX() + t) && (
                      possibleMoves[u].getY() == possibleMoves[i].getY() - t)) {
                    index.add(u);
                  }
                }
              }
            }
            if (board.getSpecificField(possibleMoves[i]).getFieldState()
                == FieldState.HAS_CURRENT_PIECE) {
              index.add(i);
            }
          }
        }
      }
      //endregion never look at this code
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, index);
  }
}
