package de.hhn.it.devtools.apis.battleship;

/**
 * creates field object.
 */

public class Field {
    private static int size;
    private Owner owner;



    public Field (int size, Owner owner){
        this.owner = owner;
        Field.size= size;
    }


    public static int getSize(){
        return size;
    }
}
