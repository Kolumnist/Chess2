package de.hhn.it.devtools.apis.chess2;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.ArrayList;

/**
 * This class handles all communication between player and components
 *
 * @author Collin, Lara, Michel
 * @version 1.1
 */
public interface Chess2Service {

  /**
   * build board with all fields, place pieces on the board, sets fields,
   * set the starting player and inform the players about who starts.
   */
  void startNewGame();

  /**
   * resets the board and pieces and the kings bananas. Assigns the pieces to their players
   * and sets the starting player, informs the players about who starts
   */
  void reset();

  /**
   * destroys an instance of ChessGame and
   * brings scene back to main menu
   */
  void endGame();

  /**
   * calls the method showWinningPlayer and after that calls reset or returns to the main menu
   */
  void giveUp();

  /**
   * creates a window and displays the rules as text
   */
  void openRules();

  /**
   * Returns 'b', 'w', 'r' as a char that defines which player won or red for no one
   *
   * @return char which defines the player won or no player won
   */
  char getWinningPlayer();

  /**
   * Returns all positions with the state "HAS_CURRENT_PIECE"
   *
   * @return ArrayList<int[]> all positions with the state "HAS_CURRENT_PIECE"
   */
  ArrayList<int[]> getCurrentFields();

  /**
   * Returns all positions that the selected Piece can move to
   *
   * @return ArrayList<int[]> all positions that the selected Piece can move to
   * @param selectedPiecePos Integer Array of the positions of the selected Pieces
   * @throws IllegalParameterException if piecePos is a null reference or incomplete.
   */
  ArrayList<int[]> getPossibleMoves(int[] selectedPiecePos) throws IllegalParameterException;

  /**
   * The king/queen gets send to jail upon defeat, the jail field is chosen
   * by the player who defeated the piece
   *
   * @param newPos of the selected piece
   * @param jailPos for the position of the jail on the board
   * @throws IllegalParameterException if piece or pos is a null reference or incomplete.
   */
  void setPieceInJail(int[] newPos, int[] jailPos) throws IllegalParameterException;

  /**
   * changes the position of the current piece
   *
   * @param newPos the new position of the piece
   * @throws IllegalParameterException if newPos is a null reference or incomplete
   */
  void moveSelectedPiece(int[] newPos) throws IllegalParameterException;

  /**
   * Returns the FieldState of the selected field
   *
   * @return FieldState of the chosen field
   * @param pos the position of the selected Field
   * @throws IllegalParameterException if pos is a null reference or incomplete
   */
  FieldState getFieldState(int[] pos) throws IllegalParameterException;

}
