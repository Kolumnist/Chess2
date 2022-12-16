package de.hhn.it.devtools.apis.battleship;

/**
 * to give every battleship its own position on the field
 */

public class Position {
    int x;
    int y;


    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }


    /**
     * @return x coordinate of the ship
     */
    public int getX(){
        return this.x;
    }


    /**
     * @return return y coordinate of the ship
     */
    public int getY(){
        return this.y;
    }


}
