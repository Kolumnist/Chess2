package de.hhn.it.devtools.apis.chess2;

import java.util.ArrayList;

/**
 * This class handles all communication between piece and components
 *
 * @author Collin, Lara, Michel
 * @version 0.1
 */
public class Piece {
    private int [] position;
    private ArrayList<int[]> possiblePositions;

    /**
     * Sets the position of the piece if it was moved.
     *
     * @param pPosition the position of the piece on the board.
     */
    public void setPosition(int[] pPosition){position = pPosition;}

    /**
     * Returns the position of the piece on the board as an int[].
     *
     * @return int[] of the position of the piece on the board.
     */
    public int[] getPosition(){return position;}

    /**
     * Returns all the possible positions the piece can have.
     *
     * @return ArrayList of all the possible positions the piece can go to.
     */
    public ArrayList<int[]> showPossibleMove(){return possiblePositions;}

    /**
     * Ist das nicht einfach nur ein Setter?
     *
     * @return int[] of the new position of the piece on the borad.
     */
    //public int[] move(){return  newPosition;}
}
