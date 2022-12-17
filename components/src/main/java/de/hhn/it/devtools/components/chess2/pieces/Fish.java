package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.Piece;
import java.util.ArrayList;

public class Fish extends Piece {

  public Fish(char color, Coordinate coordinate) {
    super(color, coordinate);
  }

  @Override
  protected void calculate() {
    possibleMoves = new Coordinate[6];
    int k = 0;
    if (color == 'w') {
      for (int i = coordinate.getX() - 1; i < coordinate.getX() + 1; i++) {
        for (int j = coordinate.getY(); j < coordinate.getY() + 1; j++) {
          if ((i == coordinate.getX()) && (j == coordinate.getY() + 1)) {
            continue;
          }
          possibleMoves[k++] = new Coordinate(i, j);
          // darf nicht hin zu coordinate.getX() & coordinate.getY()+1
        }
      }

    } else if (color == 'b') {
      for (int i = coordinate.getX() - 1; i < coordinate.getX() + 1; i++) {
        for (int j = coordinate.getY() - 1; j < coordinate.getY() + 1; j++) {
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

