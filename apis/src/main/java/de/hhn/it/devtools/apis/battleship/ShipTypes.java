package de.hhn.it.devtools.apis.battleship;

/**
 * is a class that contains the different battleships and their sizes
 */

public enum ShipTypes {

    /**
     * type: Carrier, size: 5
     */
    CARRIER(5, 1),

    /**
     * type: Battleship, size: 4
     */
    BATTLESHIP(4, 2),

    /**
     * type: Cruiser, size: 3
     */
    CRUISER(3, 3),

    /**
     * type: Submarine, size: 3
     */
    SUBMARINE(3, 4),

    /**
     * type: Destroyer, size: 2
     */
    DESTROYER(2, 5);


    final private int shipSize;
    final private int ID;

    /**
     *
     * @param shipSize
     * @param ID
     */
    ShipTypes(int shipSize, int ID) {
        this.shipSize = shipSize;
        this.ID = ID;
    }


    /**
     * @return size of a certain ship
     */
    public int getShipSize() {
        return shipSize;
    }


    public int getID(){
        return ID;
    }
}
