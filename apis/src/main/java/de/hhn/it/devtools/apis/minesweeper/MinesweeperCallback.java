package de.hhn.it.devtools.apis.minesweeper;

/**
 * Observer to handle callbacks
 * @author Lara Weller, Jason Herrmann
 * @version 0.1
 */

public interface MinesweeperCallback{
    /**
     * updates fields
     * @param coordinate of the field that has changed
     */
    void updateField(MinesweeperKoordinaten coordinate, Status status);
}
