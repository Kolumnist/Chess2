package de.hhn.it.devtools.apis.battleship;

/**
 * is a class that contains the different battleships and their sizes
 */

public enum Shiptypes {

    /**
     * name of the certain battleship with its size
     */
    Carrier(5),
    Battleship(4),
    Cruiser(3),
    Submarine(3),
    Destroyer(2);


    final private int shipSize;


    Shiptypes(int shipSize) {
        this.shipSize = shipSize;
    }


    /**
     * returns size of a certain ship
     */
    public int getShipSize() {
        return shipSize;
    }
}
