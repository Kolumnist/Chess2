package de.hhn.it.devtools.apis.battleship;

/**
 * creates ship objects.
 */

public enum Ship {
    //types of ships and their size
    Carrier(5),
    Battleship(4),
    Cruiser(3),
    Submarine(3),
    Destroyer(2);


    //final variable for the ship size
    private final int size;


    //constructor
    Ship (int size){
        this.size = size;
    }


    //return the ship size of the respective ship
    public int getNumber(){
        return size;
    }

}
