package de.hhn.it.devtools.apis.battleship;

/**
 * creates field object.
 */

public class Field {

    private static int size;
    //Index is false if no ship is on panel
    protected boolean[][] panelMarkerMat;
    private Owner owner;


    public Field (int size, Owner owner){
        this.owner = owner;
        Field.size= size;
    }


    public static int getSize(){
        return size;
    }


    public boolean[][] getPanelMarkerMat(){
        return panelMarkerMat;
    }


    public boolean getPanelMarker(int x, int y){
        return panelMarkerMat[x][y];
    }


    public void setPanelMarker(int x, int y, boolean newPanelState){
        panelMarkerMat[x][y] = newPanelState;
    }

}
