package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.Piece;
import java.util.ArrayList;

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
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_OTHER_PIECE
          || board.getSpecificField(possibleMoves[i]).getFieldState() == FieldState.HAS_BEAR) {
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
                    index.add(u);
                    m++;
                    System.out.println("if (x == -h && y == -g)\n");
                    System.out.println(u);
                    System.out.println(
                        "U: [" + possibleMoves[u].getX() + "]" + "[" + possibleMoves[u].getY()
                            + "]");
                    System.out.println(
                        "I: [" + possibleMoves[i].getX() + "]" + "[" + possibleMoves[i].getY()
                            + "]");
                    System.out.println(
                        "_______________________________________________________________________");
                  }
                }
              }
            if (x == 0 && y == -g) {
              for (int u = 0; u < possibleMoves.length; u++) {
                if ((possibleMoves[u].getX() == possibleMoves[i].getX()) && (
                    possibleMoves[u].getY() == possibleMoves[i].getY() - r)) {
                  index.add(u);
                  r++;
                  System.out.println("if (x == 0 && y == -g)\n");
                  System.out.println(u);
                  System.out.println(
                      "U: [" + possibleMoves[u].getX() + "]" + "[" + possibleMoves[u].getY() + "]");
                  System.out.println(
                      "I: [" + possibleMoves[i].getX() + "]" + "[" + possibleMoves[i].getY() + "]");
                  System.out.println(
                      "_______________________________________________________________________");
                }
              }
            }
            if (x == -h && y == 0) {
              for (int u = 0; u < possibleMoves.length; u++) {
                if ((possibleMoves[u].getX() == possibleMoves[i].getX() - rr) && (
                    possibleMoves[u].getY() == possibleMoves[i].getY())) {
                  index.add(u);
                  rr++;
                  System.out.println("if (x == -h && y == 0)\n");
                  System.out.println(u);
                  System.out.println(
                      "U: [" + possibleMoves[u].getX() + "]" + "[" + possibleMoves[u].getY() + "]");
                  System.out.println(
                      "I: [" + possibleMoves[i].getX() + "]" + "[" + possibleMoves[i].getY() + "]");
                  System.out.println(
                      "_______________________________________________________________________");
                }
              }
            }
            if (x == h && y == g) {
              for (int u = 0; u < possibleMoves.length; u++) {
                  if ((possibleMoves[u].getX() == possibleMoves[i].getX() + mm) && (
                      possibleMoves[u].getY() == possibleMoves[i].getY() + mm)) {
                    index.add(u);
                    mm++;
                    System.out.println("if (x == h && y == g)\n");
                    System.out.println(u);
                    System.out.println(
                        "U: [" + possibleMoves[u].getX() + "]" + "[" + possibleMoves[u].getY()
                            + "]");
                    System.out.println(
                        "I: [" + possibleMoves[i].getX() + "]" + "[" + possibleMoves[i].getY()
                            + "]");
                    System.out.println(
                        "_______________________________________________________________________");
                  }
                }
              }
            if (x == 0 && y == g) {
              for (int u = 0; u < possibleMoves.length; u++) {
                if ((possibleMoves[u].getX() == possibleMoves[i].getX()) && (
                    possibleMoves[u].getY() == possibleMoves[i].getY() + n)) {
                  index.add(u);
                  n++;
                  System.out.println("if (x == 0 && y == g)\n");
                  System.out.println(u);
                  System.out.println(
                      "U: [" + possibleMoves[u].getX() + "]" + "[" + possibleMoves[u].getY() + "]");
                  System.out.println(
                      "I: [" + possibleMoves[i].getX() + "]" + "[" + possibleMoves[i].getY() + "]");
                  System.out.println(
                      "_______________________________________________________________________");
                }
              }
            }
            if (x == h && y == 0) {
              for (int u = 0; u < possibleMoves.length; u++) {
                if ((possibleMoves[u].getX() == possibleMoves[i].getX() + nn) && (
                    possibleMoves[u].getY() == possibleMoves[i].getY())) {
                  index.add(u);
                  nn++;
                  System.out.println("if (x == h && y == 0)\n");
                  System.out.println(u);
                  System.out.println(
                      "U: [" + possibleMoves[u].getX() + "]" + "[" + possibleMoves[u].getY() + "]");
                  System.out.println(
                      "I: [" + possibleMoves[i].getX() + "]" + "[" + possibleMoves[i].getY() + "]");
                  System.out.println(
                      "_______________________________________________________________________");
                }
              }
            }
            if (x == -h && y == g) {
              for (int u = 0; u < possibleMoves.length; u++) {
                  if ((possibleMoves[u].getX() == possibleMoves[i].getX() - d) && (
                      possibleMoves[u].getY() == possibleMoves[i].getY() + d)) {
                    index.add(u);
                    System.out.println("d: "+d);
                    d++;
                    System.out.println("if (x == -h && y == g)\n");
                    System.out.println(u);
                    System.out.println(
                        "U: [" + possibleMoves[u].getX() + "]" + "[" + possibleMoves[u].getY()
                            + "]");
                    System.out.println(
                        "I: [" + possibleMoves[i].getX() + "]" + "[" + possibleMoves[i].getY()
                            + "]");
                    System.out.println(
                        "_______________________________________________________________________");
                  }
                }
              }
            if (x == h && y == -g) {
              for (int u = 0; u < possibleMoves.length; u++) {
                  if ((possibleMoves[u].getX() == possibleMoves[i].getX() + dd) && (
                      possibleMoves[u].getY() == possibleMoves[i].getY() - dd)) {
                    index.add(u);
                    dd++;
                    System.out.println("if (x == h && y == -g)\n");
                    System.out.println(u);
                    System.out.println(
                        "U: [" + possibleMoves[u].getX() + "]" + "[" + possibleMoves[u].getY()
                            + "]");
                    System.out.println(
                        "I: [" + possibleMoves[i].getX() + "]" + "[" + possibleMoves[i].getY()
                            + "]");
                    System.out.println(
                        "_______________________________________________________________________");
                  }
                }
              }
            if (board.getSpecificField(possibleMoves[i]).getFieldState()
                == FieldState.HAS_CURRENT_PIECE) {
              index.add(i);
            }
          }

          //endregion never look at this code
        }
      }
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, index);
  }
}
