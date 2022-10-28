package de.hhn.it.devtools.apis.battleship;

/**
 * is a class that contains the different battleships and their sizes
 */

public enum ShipTypes {

    /**
     * name of the certain battleship with its size
     */
    CARRIER(5),
    BATTLESHIP(4),
    CRUISER(3),
    SUBMARINE(3),
    DESTROYER(2);


    final private int shipSize;

    /**
     *
     * @param shipSize
     */
    ShipTypes(int shipSize) {
        this.shipSize = shipSize;
    }


    /**
     * @return size of a certain ship
     */
    public int getShipSize() {
        return shipSize;
    }
}
