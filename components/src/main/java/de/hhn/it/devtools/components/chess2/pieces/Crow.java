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

  private Coordinate[] movement;

  public Crow(char color, Coordinate coordinate) {
    super(color, coordinate);
  }

  @Override
  public void calculate(Board board) {
    boolean nearEnemy = false;

    defeatPieceMove(board);

    //Testing if the Crow stands near an enemy Piece.
    for (int i = 0; i < movement.length; i++) {
      if (board.getSpecificField(movement[i]).getFieldState() == FieldState.HAS_OTHER_PIECE) {
        nearEnemy = true;
        break;
      }
    }

    //Testing if a Piece got defeated in the last turn and if an enemy Piece is nearby.
    if (board.lostPiece && nearEnemy) {
      //Testing if the Crow can defeat the enemy King.
      for (int i = 0; i < movement.length; i++) {
        if (board.getSpecificField(movement[i]).getFieldState() == FieldState.OTHER_KING){
          canDefeatKing = true;
          break;
        }
      }

      possibleMoves = movement;
    } else {
      ArrayList<Integer> index = new ArrayList<>();
      possibleMoves = new Coordinate[64];
      int k = 0;
      //Calculating every possible Coordinates where the Crow can fly to.
      for (int i = 0; i <= 7; i++) {
        for (int j = 0; j <= 7; j++) {
          possibleMoves[k++] = new Coordinate(i, j);
        }
      }

      //Testing for invalid Coordinates and adding them to a list to remove them.
      for (int i = 0; i < possibleMoves.length; i++) {
        if (possibleMoves[i].compareCoordinates(coordinate)
            || board.getSpecificField(possibleMoves[i]).getFieldState()
            != FieldState.FREE_FIELD) {
          index.add(i);
        }
      }
      possibleMoves = shortenCoordinateArray(possibleMoves, index);
    }
  }

  /**
   * Calculates the Coordinates wear a Piece has to stand so that the Crow can defeat it.
   *
   * @param board the board of the game
   */
  public void defeatPieceMove(Board board) {
    ArrayList<Integer> index = new ArrayList<>();
    movement = new Coordinate[9];
    int k = 0;
    //Calculating the Coordinates through off-putting the own Coordinate
    // by one in X and Y direction.
    for (int i = coordinate.getX() - 1; i <= coordinate.getX() + 1; i++) {
      for (int j = coordinate.getY() - 1; j <= coordinate.getY() + 1; j++) {
        movement[k++] = new Coordinate(i, j);
      }
    }

    //Testing for invalid Coordinates and adding them to a list to remove them.
    for (int i = 0; i < movement.length; i++) {
      if ((movement[i].compareCoordinates(coordinate))
          || movement[i].getY() < 0
          || movement[i].getX() < 0
          || movement[i].getY() > 7
          || movement[i].getX() > 7
          || board.getSpecificField(movement[i]).getFieldState()
          == FieldState.HAS_CURRENT_PIECE) {
        index.add(i);
      }
    }
    movement = shortenCoordinateArray(movement, index);
  }
}
