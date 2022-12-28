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
import java.util.Random;

/**
 * The Player class should display a player that has his pieces
 * and a color.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.2
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
  public Player(char color, Board board) { //Could do a string instead of char for name or smth
    this.color = color;
    initializeMyPieces(board);
    this.gameBoard = board;

    for (Piece piece : myPieces) {
      piece.setColor(color);
    }
  }

  /**
   * All pieces that the players has get initialized here it works with a offset to check
   * which color the player has.
   *
   * @param board is needed for the pieces
   */
  protected void initializeMyPieces(Board board) {
    int coordOffset;
    if (color == 'w') {
      coordOffset = -1;
      myPieces[14] = new Queen(color, new Coordinate(3, 0));
      myPieces[15] = new King(color, new Coordinate(4, 0));
    } else {
      coordOffset = 5;
      myPieces[14] = new Queen(color, new Coordinate(4, 7));
      myPieces[15] = new King(color, new Coordinate(3, 7));
    }

    myPieces[0] = new Fish(color, new Coordinate(0, coordOffset + 2));
    myPieces[1] = new Fish(color, new Coordinate(1, coordOffset + 2));
    myPieces[2] = new Fish(color, new Coordinate(2, coordOffset + 1));
    myPieces[3] = new Fish(color, new Coordinate(3, coordOffset + 2));
    myPieces[4] = new Fish(color, new Coordinate(4, coordOffset + 2));
    myPieces[5] = new Fish(color, new Coordinate(5, coordOffset + 1));
    myPieces[6] = new Fish(color, new Coordinate(6, coordOffset + 2));
    myPieces[7] = new Fish(color, new Coordinate(7, coordOffset + 2));

    myPieces[8] = new Crow(color, new Coordinate(0, coordOffset + 1));
    myPieces[9] = new Crow(color, new Coordinate(7, coordOffset + 1));

    myPieces[10] = new Monkey(color, new Coordinate(1, coordOffset + 1));
    myPieces[11] = new Monkey(color, new Coordinate(6, coordOffset + 1));

    myPieces[12] = new Elephant(color, new Coordinate(2, coordOffset + 2));
    myPieces[13] = new Elephant(color, new Coordinate(5, coordOffset + 2));
  }

  /**
   * This method puts the King in a beforehand assigned jail.
   *
   * @param jailPiece is the piece that gets put in the jail.
   */
  protected void setKingOnJail(Piece jailPiece) {
    Coordinate jailCoordinate;
    if (this.color == 'w') {
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
    if (this.color == 'w') {
      jailCoordinate = new Coordinate(8, 4);
    } else {
      jailCoordinate = new Coordinate(9, 3);
    }
    gameBoard.getSpecificField(jailCoordinate).setFieldState(FieldState.JAIL_KING);
    gameBoard.getSpecificField(jailCoordinate).setPiece(Optional.of(jailPiece));
  }

}
