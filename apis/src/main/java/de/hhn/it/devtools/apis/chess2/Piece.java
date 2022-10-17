package de.hhn.it.devtools.apis.chess2;

import java.util.ArrayList;

/**
 * This class handles all communication between piece and components
 *
 * @author Collin, Lara, Michel
 * @version 0.1
 */
public abstract class Piece {
    private int [] position;
    private boolean defeated;

    /**
     * Sets the position of the piece if it was moved.
     *
     * @param position the position of the piece on the board.
     */
    public void setPosition(int[] position){this.position = position;}

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
    public abstract ArrayList<int[]> showPossibleMove();

    /**
     * Not sure if this methode is needed.
     */
    //protected abstract void move(int[] position);
}
