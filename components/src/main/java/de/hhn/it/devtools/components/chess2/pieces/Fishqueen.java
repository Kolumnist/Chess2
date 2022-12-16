package de.hhn.it.devtools.components.chess2.pieces;

import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.Piece;

public class Fishqueen extends Piece {

  public Fishqueen(char color, Coordinate coordinate) {
    this.color = color;
    this.coordinate = coordinate;
  }

  @Override
  protected void calculate() {

  }
}
