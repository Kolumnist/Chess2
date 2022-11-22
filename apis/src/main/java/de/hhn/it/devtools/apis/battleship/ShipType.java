package de.hhn.it.devtools.apis.battleship;

/**
 * is a class that contains the different battleships and their sizes
 */

public enum ShipType {

    /**
     * type: Carrier, size: 5
     */
    CARRIER(5),

    /**
     * type: Battleship, size: 4
     */
    BATTLESHIP(4),

    /**
     * type: Cruiser, size: 3
     */
    CRUISER(3),

    /**
     * type: Submarine, size: 3
     */
    SUBMARINE(3),

    /**
     * type: Destroyer, size: 2
     */
    DESTROYER(2);


    final private int shipSize;

    /**
     *
     * @param shipSize
     */
    ShipType(int shipSize) {
        this.shipSize = shipSize;
    }


    /**
     * @return size of a certain ship
     */
    public int getShipSize() {
        return shipSize;
    }


}
