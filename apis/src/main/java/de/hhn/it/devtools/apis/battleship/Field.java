package de.hhn.it.devtools.apis.battleship;

/**
 * creates field object.
 */

public class Field {

    private static int size;
    //Index is false if no ship is on panel
    protected boolean[][] panelMarker;
    private Owner owner;


    public Field (int size, Owner owner){
        this.owner = owner;
        Field.size= size;
    }


    public static int getSize(){
        return size;
    }


    public boolean getPanelMarker(int x, int y){
        return panelMarker[x][y];
    }


    public void setPanelMarker(int x, int y, boolean newPanelState){
        panelMarker[x][y] = newPanelState;
    }

}
