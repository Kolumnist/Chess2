package de.hhn.it.devtools.apis.chess2;

/** //Interface??//
 * This class handles all communication between player and components
 *
 * @author Collin, Lara, Michel
 * @version 0.1
 */
public class Player {

    private Piece[] myPieces;
    protected boolean lostPiece = false;

    /**
     * Returns boolean of player losing a piece, on the opponent players turn
     *
     * @return boolean of player losing a piece, on the opponent players turn
     */
    public boolean hasLostPiece() {
        return lostPiece;
    }

    /**
     * Sets if the player lost a piece or not, on the opponent players turn
     *
     * @param lostPiece player lost a piece or not, on the opponent players turn
     */
    public void setLostPiece(boolean lostPiece) {
        this.lostPiece = lostPiece;
    }

    /**
     * The game ends, the currentPlayer who presses lost
     *
     * @param currentPlayer who presses "give up" and ends/loses the game
     */
    public void giveUp(Player currentPlayer){

    }
}
