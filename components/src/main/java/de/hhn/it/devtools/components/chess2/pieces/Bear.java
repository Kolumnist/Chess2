package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.Piece;
import java.util.ArrayList;

/**
 * This class implements the Bear which inherits from Piece.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */
public class Bear extends Piece {

  public Bear(char color, Coordinate coordinate) {
    super(color, coordinate);
  }

  @Override
  public void calculate(Board board) {
    possibleMoves = new Coordinate[9];
    int k = 0;
    //Calculating the Coordinates through off-putting the own Coordinate by one in X and Y direction.
    for (int i = coordinate.getX() - 1; i <= coordinate.getX() + 1; i++) {
      for (int j = coordinate.getY() - 1; j <= coordinate.getY() + 1; j++) {
        possibleMoves[k++] = new Coordinate(i, j);
      }
    }

    //Testing for invalid Coordinates and adding them to a list to remove them.
    ArrayList<Integer> index = new ArrayList<>();
    for (int i = 0; i < possibleMoves.length; i++) {
      if ((possibleMoves[i].compareCoordinates(coordinate))
          || possibleMoves[i].getY() < 0
          || possibleMoves[i].getX() < 0
          || possibleMoves[i].getY() > 7
          || possibleMoves[i].getX() > 7
          || board.getSpecificField(possibleMoves[i]).getFieldState() == FieldState.HAS_CURRENT_PIECE
              || board.getSpecificField(possibleMoves[i]).getFieldState() == FieldState.HAS_OTHER_PIECE) {
        index.add(i);
      }
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, index);
  }
}
