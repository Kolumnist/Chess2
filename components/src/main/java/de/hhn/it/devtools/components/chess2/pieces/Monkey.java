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

  private Board board;
  private Coordinate[] possibleJump;

  public Monkey(char color, Coordinate coordinate, Board board) {
    super(color, coordinate);
    this.board = board;
  }

  public Coordinate[] getPossibleJump() {
    return possibleJump;
  }

  @Override
  public void calculate() {
    ArrayList<Integer> indexMoves = new ArrayList<>();
    ArrayList<Integer> indexJumps = new ArrayList<>();
    possibleMoves = new Coordinate[9];

    standardMovement();

    calculateJump();

    int k = 0;
    for (int i = 0; i < possibleMoves.length; i++) {
      if (board.getSpecificField(possibleMoves[i]).getFieldState()
              == FieldState.HAS_OTHER_PIECE
              || board.getSpecificField(possibleMoves[i]).getFieldState()
              == FieldState.HAS_CURRENT_PIECE) {
        possibleMoves = replaceJumpCoordinate(possibleMoves, possibleJump[k++]);
      }
    }

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
        indexMoves.add(i);
      }
    }

    possibleMoves = shortenCoordinateArray(possibleMoves, indexMoves);

    for (int i = 0; i < possibleJump.length; i++) {
      if (possibleJump[i].getY() < 0
              || possibleJump[i].getX() < 0
              || possibleJump[i].getY() > 7
              || possibleJump[i].getX() > 7
              || board.getSpecificField(possibleJump[i]).getFieldState()
              == FieldState.HAS_OTHER_PIECE
              || board.getSpecificField(possibleJump[i]).getFieldState()
              == FieldState.HAS_CURRENT_PIECE) {
        indexJumps.add(i);
      }
    }

    possibleJump = shortenCoordinateArray(possibleJump, indexJumps);
  }

  private void standardMovement() {
    int k = 0;
    for (int i = coordinate.getX() - 1; i <= coordinate.getX() + 1; i++) {
      for (int j = coordinate.getY() - 1; j <= coordinate.getY() + 1; j++) {
        possibleMoves[k++] = new Coordinate(i, j);
      }
    }
  }

  public void calculateJump() {
    possibleJump = new Coordinate[possibleMoves.length];
    int k = 0;
    for (int i = 0; i < possibleMoves.length; i++) {
      if (board.getSpecificField(possibleMoves[i]).getFieldState() == FieldState.HAS_OTHER_PIECE
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_CURRENT_PIECE) {
        possibleJump[k++] = calculateJumpCoordinate(possibleMoves[i]);
        //k++;
      }
    }

    if (isKingInJail()) {
      if (color == 'b') {
        possibleJump = replaceJumpCoordinate(possibleJump,new Coordinate(5, 4));
      } else {
        possibleJump = replaceJumpCoordinate(possibleJump, new Coordinate(2, 3));
      }
    }

    ArrayList<Integer> index = new ArrayList<>();
    for (int i = 0; i < possibleJump.length; i++) {
      if (possibleJump[i] == null
          ) {
        index.add(i);
      }
    }
    possibleJump = shortenCoordinateArray(possibleJump, index);
  }

  private Coordinate calculateJumpCoordinate(Coordinate otherPieceCoordinate) {
    int newXValue = otherPieceCoordinate.getX() + (otherPieceCoordinate.getX() - coordinate.getX());
    int newYValue = otherPieceCoordinate.getY() + (otherPieceCoordinate.getY() - coordinate.getY());

    return new Coordinate(newXValue, newYValue);
  }

  //King: w 8,3 // b 9,4
  private boolean isKingInJail(){
    if (color == 'b'){
      if (board.getSpecificField(new Coordinate(9,4)).getFieldState() == FieldState.JAIL_KING){
        return true;
      }
    }else if (color == 'w'){
      if (board.getSpecificField(new Coordinate(8,3)).getFieldState() == FieldState.JAIL_KING){
        return true;
      }
    }
    return false;
  }

  private Coordinate[] replaceJumpCoordinate(Coordinate[] array, Coordinate replaceCoordinate) {
    for (int i = 0; i < array.length; i++) {
      if (array[i].compareCoordinates(replaceCoordinate)) {
        array[i] = replaceCoordinate;
      }
    }
    return array;
  }
}
