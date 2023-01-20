package de.hhn.it.devtools.components.chess2;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.Piece;
import de.hhn.it.devtools.components.chess2.pieces.Crow;
import de.hhn.it.devtools.components.chess2.pieces.Elephant;
import de.hhn.it.devtools.components.chess2.pieces.Fish;
import de.hhn.it.devtools.components.chess2.pieces.King;
import de.hhn.it.devtools.components.chess2.pieces.Monkey;
import de.hhn.it.devtools.components.chess2.pieces.Queen;
import java.util.Optional;

/**
 * The Player class should display a player that has his pieces
 * and a color.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.3
 */
public class Player {

  protected char color;
  protected Piece[] myPieces = new Piece[16];
  protected Board gameBoard;

  /**
   * The Constructor for a player participating on a ChessGame.
   *
   * @param color of the player who is playing
   * @param board is needed to view fields and to give the board to the pieces
   */
  public Player(char color, Board board) {
    this.color = color;
    initializeMyPieces();
    this.gameBoard = board;

    for (Piece piece : myPieces) {
      piece.setColor(color);
    }
  }

  /**
   * All pieces that the players has get initialized here it works with a offset to check
   * which color the player has.
   */
  protected void initializeMyPieces() {
    int coordOffset7 = 0;
    int coordOffset5 = 0;

    if (color == 'r') {
      myPieces[14] = new Queen(color, new Coordinate(3, 0));
      myPieces[15] = new King(color, new Coordinate(4, 0), true);
    } else {
      coordOffset5 = 5;
      coordOffset7 = 7;
      myPieces[14] = new Queen(color, new Coordinate(4, 7));
      myPieces[15] = new King(color, new Coordinate(3, 7), true);
    }

    /* 0 / 1 ____ 0 / 6*/
    myPieces[0] = new Fish(color, new Coordinate(0, coordOffset5 + 1));

    /* 1 / 1 ____ 1 / 6*/
    myPieces[1] = new Fish(color, new Coordinate(1, coordOffset5 + 1));

    /* 2 / 0 ____ 2 / 7*/
    myPieces[2] = new Fish(color, new Coordinate(2, coordOffset7 + 0));

    /* 3 / 1 ____ 3 / 6*/
    myPieces[3] = new Fish(color, new Coordinate(3, coordOffset5 + 1));

    /* 4 / 1 ____ 4 / 6*/
    myPieces[4] = new Fish(color, new Coordinate(4, coordOffset5 + 1));

    /* 5 / 0 ____ 5 / 7*/
    myPieces[5] = new Fish(color, new Coordinate(5, coordOffset7 + 0));

    /* 6 / 1 ____ 6 / 6*/
    myPieces[6] = new Fish(color, new Coordinate(6, coordOffset5 + 1));

    /* 7 / 1 ____ 7 / 6*/
    myPieces[7] = new Fish(color, new Coordinate(7, coordOffset5 + 1));

    /* 0 / 0 ____ 7 / 7*/
    myPieces[8] = new Crow(color, new Coordinate(0, coordOffset7 + 0));

    /* 7 / 0 ____ 7 / 7*/
    myPieces[9] = new Crow(color, new Coordinate(7, coordOffset7 + 0));

    /* 1 / 0 ____ 1 / 7*/
    myPieces[10] = new Monkey(color, new Coordinate(1, coordOffset7 + 0));

    /* 6 / 0 ____ 6 / 7*/
    myPieces[11] = new Monkey(color, new Coordinate(6, coordOffset7 + 0));

    /* 2 / 1 ____ 2 / 6*/
    myPieces[12] = new Elephant(color, new Coordinate(2, coordOffset5 + 1));

    /* 5 / 1 ____ 5 / 6*/
    myPieces[13] = new Elephant(color, new Coordinate(5, coordOffset5 + 1));
  }

  /**
   * This method puts the King in a beforehand assigned jail.
   *
   * @param jailPiece is the piece that gets put in the jail.
   */
  protected void setKingOnJail(Piece jailPiece) {
    Coordinate jailCoordinate;
    if (jailPiece.getColor() == 'r') {
      jailCoordinate = new Coordinate(8, 3);
    } else {
      jailCoordinate = new Coordinate(9, 4);
    }
    gameBoard.getSpecificField(jailCoordinate).setFieldState(FieldState.JAIL_KING);
    gameBoard.getSpecificField(jailCoordinate).setPiece(Optional.of(jailPiece));
  }

  /**
   * This method puts the Queen in a beforehand assigned jail.
   *
   * @param jailPiece is the piece that gets put in the jail.
   */
  protected void setQueenOnJail(Piece jailPiece) {
    Coordinate jailCoordinate;
    if (jailPiece.getColor() == 'r') {
      jailCoordinate = new Coordinate(9, 3);
    } else {
      jailCoordinate = new Coordinate(8, 4);
    }
    gameBoard.getSpecificField(jailCoordinate).setFieldState(FieldState.JAIL_QUEEN);
    gameBoard.getSpecificField(jailCoordinate).setPiece(Optional.of(jailPiece));
  }

}
