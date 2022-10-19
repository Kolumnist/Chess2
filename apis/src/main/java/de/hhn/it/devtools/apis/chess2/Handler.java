package de.hhn.it.devtools.apis.chess2;

/**
 * This class handles all communication between player and components
 *
 * @author Collin, Lara, Michel
 * @version 0.1
 */
public interface Handler {

    /**
     * build board with all fields and the 4 jail fields, place pieces on the board,
     * set the starting player and inform the players about who starts.
     */
    void startGame();

    /**
     * resets the board and pieces and the kings bananas. Assigns the pieces to their players
     * and sets the starting player, informs the players about who starts
     */
    void reset();

    /**
     * displays the rules as text
     * <p>
     * TODO: new window? pictures as well?
     */
    void openRules();

    /**
     * The king/queen gets send to jail upon defeat, the jail field is chosen
     * by the player who defeated the piece
     *
     * @param piece gets placed in the
     * @param jail for 0-3
     */
    void setPieceInJail(Piece piece, int[] jail);
}
