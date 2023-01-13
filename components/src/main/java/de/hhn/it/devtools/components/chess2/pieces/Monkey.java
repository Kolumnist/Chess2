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
 * @version 1.4
 */

public class Monkey extends Piece {

  private Coordinate[] possibleJump;

  public Monkey(char color, Coordinate coordinate) {
    super(color, coordinate);
  }

  public Coordinate[] getPossibleJump() {
    return possibleJump;
  }

  @Override
  public void calculate(Board board) {
    ArrayList<Integer> index = new ArrayList<>();

    calculateJump(board);

    int k = 0;
    //Replacing one Coordinate through a jumpCoordinate if a Piece stands on it
    for (int i = 0; i < possibleMoves.length; i++) {
      if (k < possibleJump.length
          && (board.getSpecificField(possibleMoves[i]).getFieldState()
          != FieldState.FREE_FIELD
          && board.getSpecificField(possibleMoves[i]).getFieldState()
          != FieldState.SELECTED)) {
        replaceJumpCoordinate(possibleMoves, possibleMoves[i], possibleJump[k++]);
      }
    }

    //Testing if there are still Pieces in the way and adding them to a list to remove them.
    for (int i = 0; i < possibleMoves.length; i++) {
      if (board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.OTHER_KING
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_CURRENT_PIECE
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_OTHER_PIECE
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_BEAR) {
        index.add(i);
      }
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, index);
    canDefeatKing = false;
  }

  /**
   * Calculates the Coordinates if the Monkey jumps over a nearby Piece.
   *
   * @param board the board of the game
   */
  public void calculateJump(Board board) {
    calculateMovement();

    possibleJump = new Coordinate[8];
    int k = 0;
    //Testing if there is a Piece nearby to jump over and calculating the new Coordinate.
    for (int i = 0; i < possibleMoves.length; i++) {
      if (board.getSpecificField(possibleMoves[i]).getFieldState() != FieldState.FREE_FIELD
          && board.getSpecificField(possibleMoves[i]).getFieldState() != FieldState.SELECTED) {
        possibleJump[k++] = calculateJumpCoordinate(possibleMoves[i]);
      }
    }

    //Testing for invalid Coordinates and adding them to a list to remove them.
    ArrayList<Integer> index = new ArrayList<>();
    for (int i = 0; i < possibleJump.length; i++) {
      if (possibleJump[i] == null
          || possibleJump[i].getY() < 0
          || possibleJump[i].getX() < 0
          || possibleJump[i].getY() > 7
          || possibleJump[i].getX() > 7) {
        index.add(i);
      }
    }
    possibleJump = shortenCoordinateArray(possibleJump, index);

    //Testing if the Monkey can defeat the enemy King.
    for (int i = 0; i < possibleJump.length; i++) {
      if (board.getSpecificField(possibleJump[i]).getFieldState() == FieldState.OTHER_KING) {
        canDefeatKing = true;
        break;
      }
    }

    //Testing if the own King is in jail and replacing the jumpCoordinate
    // if the Monkey stands on the right Field.
    if (isKingInJail(board)) {
      if (color == 'b'
          && (board.getSpecificField(new Coordinate(5, 4)).getFieldState()
          == FieldState.SELECTED
          || board.getSpecificField(new Coordinate(5, 4)).getFieldState()
          == FieldState.HAS_OTHER_PIECE
          || board.getSpecificField(new Coordinate(5, 4)).getFieldState()
          == FieldState.HAS_CURRENT_PIECE)) {
        replaceJumpCoordinate(possibleJump, new Coordinate(7, 4),
            new Coordinate(9, 4));
      } else if (color == 'w'
          && (board.getSpecificField(new Coordinate(2, 3)).getFieldState()
          == FieldState.SELECTED
          || board.getSpecificField(new Coordinate(2, 3)).getFieldState()
          == FieldState.HAS_OTHER_PIECE
          || board.getSpecificField(new Coordinate(2, 3)).getFieldState()
          == FieldState.HAS_CURRENT_PIECE)) {
        replaceJumpCoordinate(possibleJump, new Coordinate(0, 3),
            new Coordinate(8, 3));
      }
    }

  }

  /**
   * Calculates the normal movement of the Monkey if he does not jump over another Piece.
   */
  private void calculateMovement() {
    ArrayList<Integer> index = new ArrayList<>();
    possibleMoves = new Coordinate[9];
    int k = 0;
    //Calculating the Coordinates through off-putting the own Coordinate
    // by one in X and Y direction.
    for (int i = coordinate.getX() - 1; i <= coordinate.getX() + 1; i++) {
      for (int j = coordinate.getY() - 1; j <= coordinate.getY() + 1; j++) {
        possibleMoves[k++] = new Coordinate(i, j);
      }
    }

    //Testing for invalid Coordinates and adding them to a list to remove them.
    for (int i = 0; i < possibleMoves.length; i++) {
      if ((possibleMoves[i].compareCoordinates(coordinate))
          || possibleMoves[i].getY() < 0
          || possibleMoves[i].getX() < 0
          || possibleMoves[i].getY() > 7
          || possibleMoves[i].getX() > 7) {
        index.add(i);
      }
    }

    possibleMoves = shortenCoordinateArray(possibleMoves, index);
  }

  /**
   * Returns the Coordinate of the jump destination through adding the difference of
   * otherPieceCoordinate and the Coordinate of the Monkey to the otherPieceCoordinate.
   *
   * @param otherPieceCoordinate the Coordinate of the Piece over which the Monkey jumps
   * @return the Coordinate of the Field if the Monkey jumps over another Piece
   */
  private Coordinate calculateJumpCoordinate(Coordinate otherPieceCoordinate) {
    int newX = otherPieceCoordinate.getX() + (otherPieceCoordinate.getX() - coordinate.getX());
    int newY = otherPieceCoordinate.getY() + (otherPieceCoordinate.getY() - coordinate.getY());

    return new Coordinate(newX, newY);
  }

  /**
   * Returns true if the King of the specific color is in jail.
   *
   * @param board the board of the game to test if the King is in jail
   * @return a boolean if the King is in jail or not
   */
  private boolean isKingInJail(Board board) {
    if (color == 'b') {
      if (board.getSpecificField(new Coordinate(9, 4)).getFieldState()
          == FieldState.JAIL_KING) {
        return true;
      }
    } else if (color == 'w') {
      if (board.getSpecificField(new Coordinate(8, 3)).getFieldState()
          == FieldState.JAIL_KING) {
        return true;
      }
    }
    return false;
  }

  /**
   * Replaces one Coordinate with another Coordinate in the given Coordinate[].
   *
   * @param array             the Coordinate[] in which Coordinate get replaced
   * @param oldCoordinate     the Coordinate which gets be replaced
   * @param replaceCoordinate the Coordinate that replaces the other
   */
  private void replaceJumpCoordinate(Coordinate[] array, Coordinate oldCoordinate,
      Coordinate replaceCoordinate) {
    for (int i = 0; i < array.length; i++) {
      if (array[i].compareCoordinates(oldCoordinate)) {
        array[i] = replaceCoordinate;
      }
    }
  }
}
