package de.hhn.it.devtools.apis.battleship;

/**
 * creates ship objects.
 */

public class Ship {


    private final int size;
    private int destroyedParts = 0;
    private boolean isHorizontal = true;
    //fieldPosition is always the panel at the top or at the left of the ship
    private Position fieldPosition;


    //constructor
    Ship (Shiptypes ShipSize, Position fieldPosition){
        this.size = ShipSize.getShipSize();
        this.fieldPosition = fieldPosition;
    }


    //return the ship size of the respective ship
    public int getSize(){
        return size;
    }

}
