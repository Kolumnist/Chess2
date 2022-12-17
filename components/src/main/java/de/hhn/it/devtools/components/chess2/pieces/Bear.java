package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.Piece;
import java.util.ArrayList;

public class Bear extends Piece {

  Bear(char color, Coordinate coordinate) {
    super(color, coordinate);
  }

  @Override
  protected void calculate() {
    // mein Bär steht erst mal auf der x-Achse mit der Coordinate 4 und der y- Coordinate 3
    // Daduch, das wir sagen coordinate.get(x) -1 & coordinate.get(y) -1 geben wir das min an, zu dem der Bär laufen kann
    // bei coordinate.getX() + 1 & coordinate.getY() + 1 geben wir das max an
    // wir setzen den Bär also zuerst auf 32 und laufen dann auf 33, 34
    // -> dann ist das max der y achse erreicht und wir starten bei 42, 43, und dann gehts hoch auf die 44
    possibleMoves = new Coordinate[9];
    int k = 0;
    for (int i = coordinate.getX() - 1; i < coordinate.getX() + 1; i++) {
      for (int j = coordinate.getY() - 1; j < coordinate.getY() + 1; j++) {
        possibleMoves[k++] = new Coordinate(i, j);
        // 678 -> y=1
        // 501 -> y=2
        // 432 -> y=3
      }
    }

    // ist eine der Koorinaten die ich im Array habe

    ArrayList<Integer> index = new ArrayList<>();
    for (int i = 0; i < possibleMoves.length; i++) {
      if ((possibleMoves[i].getX() == coordinate.getX() && possibleMoves[i].getY() == coordinate.getY())
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
