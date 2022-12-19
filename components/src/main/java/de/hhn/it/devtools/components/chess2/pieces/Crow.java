package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.Piece;
import java.util.ArrayList;

/**
 * This class implements the Crow which inherits from Piece.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */

public class Crow extends Piece {

  public Crow(char color, Coordinate coordinate) {
    super(color, coordinate);
  }

  @Override
  protected void calculate() {
  }

  protected void calculate(Board board, boolean pieceGotDefeated) {
    ArrayList<Integer> index = new ArrayList<>();
    int k = 0;
    boolean nearEnemy = false;

    Coordinate[] temp = defeatPieceMovement(board);
    for (int i = 0; i < temp.length; i++) {
      if (board.getSpecificField(temp[i]).getFieldState() == FieldState.HAS_OTHER_PIECE) {
        nearEnemy = true;
        break;
      }
    }

    if (pieceGotDefeated && nearEnemy) {
      possibleMoves = defeatPieceMovement(board);
    } else {
      possibleMoves = new Coordinate[64];
      for (int i = 0; i <= 7; i++) {
        for (int j = 0; j <= 7; j++) {
          possibleMoves[k++] = new Coordinate(i, j);
        }
      }

      for (int i = 0; i < possibleMoves.length; i++) {
        if (possibleMoves[i].compareCoordinates(coordinate)
            ||
            board.getSpecificField(possibleMoves[i]).getFieldState() == FieldState.HAS_OTHER_PIECE
            ||
            board.getSpecificField(possibleMoves[i]).getFieldState()
                == FieldState.HAS_CURRENT_PIECE) {
          index.add(i);
        }
      }
      possibleMoves = shortenCoordinateArray(possibleMoves, index);
    }
  }

  // TODO: Daran denken noch auf Figuren zu prüfen, wenn es übernommen wird
  private Coordinate[] defeatPieceMovement(Board board) {
    Coordinate[] movement = new Coordinate[9];

    int k = 0;
    for (int i = coordinate.getX() - 1; i <= coordinate.getX() + 1; i++) {
      for (int j = coordinate.getY() - 1; j <= coordinate.getY() + 1; j++) {
        movement[k++] = new Coordinate(i, j);
      }
    }

    ArrayList<Integer> index = new ArrayList<>();
    for (int i = 0; i < possibleMoves.length; i++) {
      if ((possibleMoves[i].compareCoordinates(coordinate))
          || possibleMoves[i].getY() < 0
          || possibleMoves[i].getX() < 0
          || possibleMoves[i].getY() > 7
          || possibleMoves[i].getX() > 7
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_CURRENT_PIECE) {
        index.add(i);
      }
    }

    return shortenCoordinateArray(movement, index);
  }

}
