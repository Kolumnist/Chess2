package de.hhn.it.devtools.components.chess2;

import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.Piece;
import de.hhn.it.devtools.components.chess2.pieces.Crow;
import de.hhn.it.devtools.components.chess2.pieces.Elephant;
import de.hhn.it.devtools.components.chess2.pieces.Fish;
import de.hhn.it.devtools.components.chess2.pieces.King;
import de.hhn.it.devtools.components.chess2.pieces.Monkey;
import de.hhn.it.devtools.components.chess2.pieces.Queen;

public class Player {

  char color;
  Piece[] myPieces = new Piece[16];

  /**
   * The Constructor for a player participating on a ChessGame
   *
   * @param color
   */
  public Player(char color) {//Could do a string instead of char for name or smth
    this.color = color;
    initializeMyPieces();
    for(Piece piece : myPieces) {
      piece.setColor(color);
    }
  }

  private void initializeMyPieces() {
    int yOffset;
    if(color == 'w') {
      yOffset = -1;
      myPieces[14] = new Queen(color, new Coordinate(3, 0));
      myPieces[15] = new King(color, new Coordinate(4, 0));
    }
    else {
      yOffset = 6;
      myPieces[14] = new Queen(color, new Coordinate(4, 7));
      myPieces[15] = new King(color, new Coordinate(3, 7));
    }

    myPieces[0] = new Fish(color, new Coordinate(0, yOffset+2));
    myPieces[1] = new Fish(color, new Coordinate(1, yOffset+2));
    myPieces[2] = new Fish(color, new Coordinate(2, yOffset+1));
    myPieces[3] = new Fish(color, new Coordinate(3, yOffset+2));
    myPieces[4] = new Fish(color, new Coordinate(4, yOffset+2));
    myPieces[5] = new Fish(color, new Coordinate(5, yOffset+1));
    myPieces[6] = new Fish(color, new Coordinate(6, yOffset+2));
    myPieces[7] = new Fish(color, new Coordinate(7, yOffset+2));

    myPieces[8] = new Crow(color, new Coordinate(0, yOffset+1));
    myPieces[9] = new Crow(color, new Coordinate(7, yOffset+1));

    myPieces[10] = new Monkey(color, new Coordinate(1, yOffset+1));
    myPieces[11] = new Monkey(color, new Coordinate(6, yOffset+1));

    myPieces[12] = new Elephant(color, new Coordinate(2, yOffset+2));
    myPieces[13] = new Elephant(color, new Coordinate(5, yOffset+2));
  }
}
