package de.hhn.it.devtools.apis.battleship;

/**
 * creates field object.
 */

public class Field {
    private static int size;
    //Index is false if no ship is on panel
    private static boolean[][] carriesShip;
    private Owner owner;



    public Field (int size, Owner owner){
        this.owner = owner;
        Field.size= size;
    }


    public static int getSize(){
        return size;
    }

    public static boolean[][] getCarriesShip(){
        return carriesShip;
    }
}
