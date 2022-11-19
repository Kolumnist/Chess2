package de.hhn.it.devtools.apis.battleship;

/**
 * creates field object.
 */

public final class Field {
    private static int size;
    Field (int size){
        this.size= size;
    }

    public static int getSize(){
        return size;
    }
}
