package de.hhn.it.devtools.apis.chess2;

/** //Interface??//
 * This class handles all communication between player and components
 *
 * @author Collin, Lara, Michel
 * @version 0.1
 */
public class Player {

    protected boolean lostPiece = false;
    protected boolean startingPlayer = true;

    /**
     * Returns boolean of player losing a piece, on the opponent players turn
     *
     * @return boolean of player losing a piece, on the opponent players turn
     */
    public boolean isLostPiece() {
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
     *
     *
     */
    public void giveUp()
    {

    }

}
