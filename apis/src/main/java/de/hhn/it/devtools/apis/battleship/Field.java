package de.hhn.it.devtools.apis.battleship;

/**
 * creates field object.
 */

public final class Field {
    private static int size;
    //Index is false if no ship is on panel
    private static boolean[][] carriesShip;


    Field (int size){
        Field.size= size;
       carriesShip = new boolean[Field.size][Field.size];
    }


    public static int getSize(){
        return size;
    }

    public static boolean[][] getCarriesShip(){
        return carriesShip;
    }
}
