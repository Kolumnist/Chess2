package de.hhn.it.devtools.apis.chess2;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.ArrayList;

/**
 * This class handles all communication between player and components
 *
 * @author Collin, Lara, Michel
 * @version 1.0
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
     * calls the method showWinningPlayer and after that calls reset or returns to the main menu
     */
    void giveUp();

    /**
     * creates a window and shows which player has won and calls reset or returns to the main menu after that
     */
    void showWinningPlayer();

    /**
     * creates a window and displays the rules as text
     */
    void openRules();

    /**
     * activates all fields with the state "HAS_CURRENT_PIECE"
     *
     * @param piecePos ArrayList of the positions of all Pieces
     * @throws IllegalParameterException if piecePos is a null reference or incomplete.
     */
    void activatePieceButton(ArrayList<int[]> piecePos) throws IllegalParameterException;

    /**
     * The king/queen gets send to jail upon defeat, the jail field is chosen
     * by the player who defeated the piece
     *
     * @param piece gets placed in the
     * @param pos for the position of the jail on the board
     * @throws IllegalParameterException if piece or pos is a null reference or incomplete.
     */
    void setPieceInJail(Piece piece, int[] pos) throws IllegalParameterException;

    /**
     * changes the position of the current piece
     *
     * @param pos the new position of the piece
     * @throws IllegalParameterException if pos is a null reference or incomplete.
     */
    void moveSelectedPiece(int[] pos) throws IllegalParameterException;

    /**
     * Returns the FieldState of the chosen field
     *
     * @return FieldState of the chosen field
     */
    FieldState getFieldState();
}
