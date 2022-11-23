package de.hhn.it.devtools.apis.chess2;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * This class handles all communication between player and components.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.1
 */
public interface Chess2Service {

  /**
   * Build board with all fields, place pieces on the board, sets fields, set the starting player
   * and inform the players about who starts.
   *
   * @return Board of the game
   */
  Board startNewGame();

  /**
   * Destroys an instance of ChessGame and brings scene back to main menu.
   */
  void endGame();

  /**
   * Calls the method showWinningPlayer and after that calls reset or returns to the main menu.
   */
  void giveUp();

  /**
   * Returns all positions with the state "HAS_CURRENT_PIECE".
   *
   * @return Coordinate[] all positions with the state "HAS_CURRENT_PIECE"
   */
  Coordinate[] getCurrentFields();

  /**
   * Returns all positions that the selected Piece can move to.
   *
   * @param selectedPieceCoordinate Coordinate of the position of the selected Piece
   * @return Coordinate[] all positions that the selected Piece can move to
   * @throws IllegalParameterException if piecePos is a null reference or incomplete
   */
  Coordinate[] getPossibleMoves(Coordinate selectedPieceCoordinate)
      throws IllegalParameterException;

  /**
   * Changes the position of the selected piece.
   *
   * @param selectedCoordinate the position of the selected piece
   * @param newCoordinate      the new position of the piece
   * @throws IllegalParameterException if newPos is a null reference or incomplete
   */
  void moveSelectedPiece(Coordinate selectedCoordinate, Coordinate newCoordinate)
      throws IllegalParameterException, InvalidMoveException;

  /**
   * Returns the FieldState of the selected field.
   *
   * @param selectedCoordinate the position of the selected Field
   * @return FieldState of the selected field
   * @throws IllegalParameterException if pos is a null reference or incomplete
   */
  FieldState getFieldState(Coordinate selectedCoordinate) throws IllegalParameterException;

  /**
   * Returns BLACK_WIN, WHITE_WIN, NO_WINNER as an Enum that defines which player won or if there
   * is none.
   *
   * @return Enum which defines the player that won or if there is none
   */
  WinningPlayerState getWinningPlayer();

  /**
   * Returns RUNNING, CHECK or CHECKMATE which is the current GameState.
   *
   * @return the current GameState
   */
  GameState getGameState();
}
