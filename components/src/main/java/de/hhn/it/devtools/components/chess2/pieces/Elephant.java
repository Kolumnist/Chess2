package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.Piece;
import java.util.ArrayList;

public class Elephant extends Piece {

  public Elephant(char color, Coordinate coordinate) {
    super(color, coordinate);
  }

  public void calculate() {
    possibleMoves = new Coordinate[10];
    int k = 0;

    for (int i = coordinate.getX() - 2; i < coordinate.getX() + 2; i += 2) {
      for (int j = coordinate.getY() - 2; j < coordinate.getY() + 2; j += 2) {
        possibleMoves[k++] = new Coordinate(i, j);
        // darf nicht hin zu coordinate.getX() & coordinate.getY()+1
      }
    }

    ArrayList<Integer> index = new ArrayList<>();
    for (int i = 0; i < possibleMoves.length; i++) {
      if ((possibleMoves[i].getX() == coordinate.getX()
          && possibleMoves[i].getY() == coordinate.getY())
          || possibleMoves[i].getY() < 0     //steht der Bear am Rand
          || possibleMoves[i].getX() < 0     //steht der Bear am Rand
          || possibleMoves[i].getY() > 7     //steht der Bear am Rand
          || possibleMoves[i].getX() > 7) {  //steht der Bear am Rand
        index.add(i);
      }
    }
    possibleMoves = shortenCoordinateArray(possibleMoves, index);
  }
}
