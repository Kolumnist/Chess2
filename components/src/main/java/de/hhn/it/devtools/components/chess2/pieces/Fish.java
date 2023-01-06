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

  @Override
  public void calculate(Board board) {
    possibleMoves = new Coordinate[8];
    int k = 0;
    if (color == 'w') {
      for (int i = coordinate.getX() - 1; i <= coordinate.getX() + 1; i++) {
        for (int j = coordinate.getY(); j <= coordinate.getY() + 1; j++) {
          if ((i == coordinate.getX()) && (j == coordinate.getY() + 1)) {
            continue;
          }
          possibleMoves[k++] = new Coordinate(i, j);
          // darf nicht hin zu coordinate.getX() & coordinate.getY()+1
        }
      }
    } else if (color == 'b') {
      for (int i = coordinate.getX() - 1; i <= coordinate.getX() + 1; i++) {
        for (int j = coordinate.getY() - 1; j <= coordinate.getY(); j++) {
          if ((i == coordinate.getX()) && (j == coordinate.getY() - 1)) {
            continue;
          }
          possibleMoves[k++] = new Coordinate(i, j);
          // darf nicht hin zu coordinate.getX() & coordinate.getY()-1
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
    Set<Integer> IndexSet = new TreeSet<>();

    for (int i = 0; i < possibleMoves.length; i++) {
      if ((possibleMoves[i].getX() == coordinate.getX()
          && possibleMoves[i].getY() == coordinate.getY())
          || possibleMoves[i].getY() < 0
          || possibleMoves[i].getX() < 0
          || possibleMoves[i].getY() > 7
          || possibleMoves[i].getX() > 7
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_CURRENT_PIECE) {
        IndexSet.add(i);
      }
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
        IndexSet.add(i);
      }
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, IndexSet);
  }
}

