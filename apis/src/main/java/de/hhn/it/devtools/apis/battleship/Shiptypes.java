package de.hhn.it.devtools.apis.battleship;

public enum Shiptypes {
    Carrier(5),
    Battleship(4),
    Cruiser(3),
    Submarine(3),
    Destroyer(2);


    final private int shipSize;


    Shiptypes(int shipSize) {
        this.shipSize = shipSize;
    }


    public int getShipSize() {
        return shipSize;
    }
}
