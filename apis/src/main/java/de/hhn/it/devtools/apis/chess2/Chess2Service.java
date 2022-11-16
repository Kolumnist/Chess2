package de.hhn.it.devtools.apis.chess2;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * This class handles all communication between player and components.
 *
 * @author Collin, Lara, Michel
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
   * Resets the board and pieces and the kings bananas. Assigns the pieces to their players and sets
   * the starting player, informs the players about who starts.
   *
   * @return Board of the game
   */
  Board reset();

  /**
   * Destroys an instance of ChessGame and brings scene back to main menu.
   */
  void endGame();

  /**
   * Calls the method showWinningPlayer and after that calls reset or returns to the main menu.
   */
  void giveUp();

  /**
   * Returns 'b', 'w', 'r' as a char that defines which player won or red for no one.
   *
   * @return char which defines the player won or no player won
   */
  char getWinningPlayer();

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
   * The king/queen gets send to jail upon defeat, the jail field is chosen by the player who
   * defeated the piece.
   *
   * @param otherCoordinate of the king or queen piece that stands on a field, which has the
   *                        FieldState "HAS_OTHER_PIECE"  that got selected
   * @param jailCoordinate  for the position of the jail on the board
   * @throws IllegalParameterException if piece or pos is a null reference or incomplete
   */
  void setPieceInJail(Coordinate otherCoordinate, Coordinate jailCoordinate)
      throws IllegalParameterException;

  /**
   * Changes the position of the selected piece.
   *
   * @param newCoordinate the new position of the piece
   * @throws IllegalParameterException if newPos is a null reference or incomplete
   */
  void moveSelectedPiece(Coordinate newCoordinate) throws IllegalParameterException;

  /**
   * Returns the FieldState of the selected field.
   *
   * @param selectedCoordinate the position of the selected Field
   * @return FieldState of the selected field
   * @throws IllegalParameterException if pos is a null reference or incomplete
   */
  FieldState getFieldState(Coordinate selectedCoordinate) throws IllegalParameterException;

}
