package de.hhn.it.devtools.components.chess2;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

public class ChessGame implements Chess2Service {

  private Board gameBoard;

  public ChessGame() {
    gameBoard = new Board();

  }

  @Override
  public Board startNewGame() {
    return null;
  }

  @Override
  public Board reset() {
    return null;
  }

  @Override
  public void endGame() {

  }

  @Override
  public void giveUp() {

  }

  @Override
  public char getWinningPlayer() {
    return 0;
  }

  @Override
  public Coordinate[] getCurrentFields() {
    return new Coordinate[0];
  }

  @Override
  public Coordinate[] getPossibleMoves(Coordinate selectedPieceCoordinate)
      throws IllegalParameterException {
    return new Coordinate[0];
  }

  @Override
  public void setPieceInJail(Coordinate otherCoordinate, Coordinate jailCoordinate)
      throws IllegalParameterException {

  }

  @Override
  public void moveSelectedPiece(Coordinate newCoordinate) throws IllegalParameterException {

  }

  @Override
  public FieldState getFieldState(Coordinate selectedCoordinate) throws IllegalParameterException {
    return null;
  }
}
