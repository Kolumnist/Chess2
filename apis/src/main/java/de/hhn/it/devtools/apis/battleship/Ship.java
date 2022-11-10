package de.hhn.it.devtools.apis.battleship;

import java.util.UUID;

/**
 * creates ship objects.
 */

public class Ship {


    private final int size;
    private int destroyedParts = 0;
    private boolean isHorizontal = true;
    //fieldPosition is always the panel at the top or at the left of the ship
    private Position fieldPosition;
    private boolean placed;
    private Player besitzer;
    //private String id;


    /**
     * Constructor
     * @param ShipSize sets the size
     * @param fieldPosition sets the Position on the field
     */
    Ship (ShipTypes ShipSize, Position fieldPosition){
        this.size = ShipSize.getShipSize();
        this.fieldPosition = fieldPosition;
        //this.setID();
    }


    /**
     * @return the ship size of the respective ship
     */
    public int getSize(){
        return size;
    }

    /*
     * Sets the ID of the ship to a unique id (only guarantees distinctivness inside 1 jvm
     * not sure if this is already too much of implementation for the faccade
    private void setID(){
        this.id = UUID.randomUUID().toString();
    }
     */
}
