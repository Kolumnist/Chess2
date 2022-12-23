package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.Piece;

public class King extends Piece {

  public King(char color, Coordinate coordinate) {
    super(color, coordinate);
  }

  @Override
  protected void calculate() {
  }
}
