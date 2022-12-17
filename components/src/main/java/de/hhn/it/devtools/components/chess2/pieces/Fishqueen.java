package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.Piece;
import java.util.ArrayList;

public class Fishqueen extends Piece {

  public Fishqueen(char color, Coordinate coordinate) {
    super(color, coordinate);
  }

  @Override
  protected void calculate() {
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
