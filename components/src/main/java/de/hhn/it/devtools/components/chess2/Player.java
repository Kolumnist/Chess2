package de.hhn.it.devtools.components.chess2;

import de.hhn.it.devtools.apis.chess2.Piece;

public class Player {

  char color;
  Piece[] myPieces = new Piece[16];

  public Player(char color) {//Could do a string instead of char for name or smth
    this.color = color;

  }

}
