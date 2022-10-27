package de.hhn.it.devtools.apis.battleship;

public enum Shiptypes {

    /**
     * Battleshiptype with its size
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
     * @return size of a certain ship
     */
    public int getShipSize() {
        return shipSize;
    }
}
