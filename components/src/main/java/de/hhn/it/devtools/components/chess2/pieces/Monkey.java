package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.Piece;
import java.util.ArrayList;

/**
 * This class implements the Monkey which inherits from Piece.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */

public class Monkey extends Piece {

  Coordinate[] possibleJump;

  @Override
  protected void calculate() {
    int k = 0;
    for (int i = coordinate.getX() - 1; i <= coordinate.getX() + 1; i++) {
      for (int j = coordinate.getY() - 1; j <= coordinate.getY() + 1; j++) {
        possibleMoves[k++] = new Coordinate(i, j);
      }
    }
  }

  protected void calculate(Board board, boolean kingInJail) {
    ArrayList<Integer> index = new ArrayList<>();
    possibleMoves = new Coordinate[9];

    calculate();

    for (int i = 0; i < possibleMoves.length; i++) {
      if ((possibleMoves[i].compareCoordinates(coordinate))
          || possibleMoves[i].getY() < 0
          || possibleMoves[i].getX() < 0
          || possibleMoves[i].getY() > 7
          || possibleMoves[i].getX() > 7
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_OTHER_PIECE
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_CURRENT_PIECE) {
        index.add(i);
      }
    }

    possibleMoves = shortenCoordinateArray(possibleMoves, index);

    calculateJump(board, kingInJail);
  }

  private Coordinate calculateJumpCoordinate(Coordinate otherPieceCoordinate) {
    int newXValue = otherPieceCoordinate.getX() + (otherPieceCoordinate.getX() - coordinate.getX());
    int newYValue = otherPieceCoordinate.getY() + (otherPieceCoordinate.getY() - coordinate.getY());

    return new Coordinate(newXValue, newYValue);
  }

  protected void calculateJump(Board board, boolean kingInJail) {
    possibleJump = new Coordinate[possibleMoves.length];
    int k = 0;
    for (int i = 0; i < possibleMoves.length; i++) {
      if (board.getSpecificField(possibleMoves[i]).getFieldState() == FieldState.HAS_OTHER_PIECE
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_CURRENT_PIECE) {
        possibleJump[k] = calculateJumpCoordinate(possibleMoves[i]);
        k++;
      }
    }

    ArrayList<Integer> index = new ArrayList<>();
    for (int i = 0; i < possibleJump.length; i++) {
      if (possibleJump[i] == null) {
        index.add(i);
      }
    }
    possibleJump = shortenCoordinateArray(possibleJump, index);
  }
}
