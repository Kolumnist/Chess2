package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.Piece;
import java.util.ArrayList;

/**
 * This class implements the King which inherits from Piece.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */

public class King extends Piece {

  boolean hasBanana;

  public King(char color, Coordinate coordinate, boolean hasBanana) {
    super(color, coordinate);
    this.hasBanana = hasBanana;
  }

  /**
   * This method calculates all the possible movements of the king piece. It also add the movements
   * which are not possible to go into an arraylist.
   *
   * @param board the board of the game
   */
  @Override
  public void calculate(Board board) {
    possibleMoves = new Coordinate[9];
    int k = 0;
    for (int i = coordinate.getX() - 1; i <= coordinate.getX() + 1; i++) {
      for (int j = coordinate.getY() - 1; j <= coordinate.getY() + 1; j++) {
        possibleMoves[k++] = new Coordinate(i, j);
      }
    }

    ArrayList<Integer> index = new ArrayList<>();

    //This code checks that the king can't go outside the board
    //(we add this movements into an arraylist)
    for (int i = 0; i < possibleMoves.length; i++) {
      if ((possibleMoves[i].getX() == coordinate.getX()
          && possibleMoves[i].getY() == coordinate.getY())
          || possibleMoves[i].getY() < 0
          || possibleMoves[i].getX() < 0
          || possibleMoves[i].getY() > 7
          || possibleMoves[i].getX() > 7
          || board.getSpecificField(possibleMoves[i]).getFieldState()
          == FieldState.HAS_CURRENT_PIECE) {
        index.add(i);
      }
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, index);

    //Testing if the Crow can defeat the enemy King.
    for (int i = 0; i < possibleMoves.length; i++) {
      if (board.getSpecificField(possibleMoves[i]).getFieldState() == FieldState.OTHER_KING) {
        canDefeatKing = true;
        break;
      }
    }
  }
}
