package de.hhn.it.devtools.apis.battleship;

/**
 * creates ship objects.
 */

public class Ship {


    private final int size;
    private int destroyedParts = 0;
    private boolean isVertical;
    //fieldPosition is always the panel at the top or at the left of the ship
    private Position fieldPosition;
    private boolean placed;
    private Owner owner;
    private static int counterId = 0;
    private int id;

    /**
     * Constructor
     * @param ShipSize sets the size
     * @param fieldPosition sets the Position on the field
     */
    public Ship (ShipType ShipSize, Position fieldPosition){
        this.isVertical = false;
        this.size = ShipSize.getShipSize();
        this.fieldPosition = fieldPosition;
        this.id = counterId;
        counterId++;
    }


    /**
     * @return the ship size of the respective ship
     */
    public int getSize(){
        return this.size;
    }


    /**
     * @return the id of the ship
     */
    public int getId(){
        return this.id;
    }


    /**
     * @param placeState to set state of a ship to (un)placed
     */
    public void setPlaced(boolean placeState){
        this.placed = placeState;
    }


    /**
     * @return if the ship is placed
     */
    public boolean getPlaced(){
        return placed;
    }


    /**
     * @param vertical if true: sets the ship vertical, if false: sets the ship horizontal
     */
    public void setIsVertical(Boolean vertical){
        this.isVertical = vertical;
    }


    /**
     * @return true if ship is vertical, false if ship is horizontal
     */
    public boolean getIsVertical(){
        return this.isVertical;
    }


    /**
     * @param x new x coordinate of the ship
     * @param y new y coordinate of the ship
     */
    public void setFieldPosition(int x, int y){
        this.fieldPosition = new Position(x, y);
    }


    public Position getFieldPosition(){
        return this.fieldPosition;
    }


}
