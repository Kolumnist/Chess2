package de.hhn.it.devtools.apis.battleship;

/**
 * creates field object.
 */

public class Field {

    private static int size;
    //Index is false if no ship is on panel
    PanelState[][] panelMarkerMat;
    private final Player owner;


    public Field (int size, Player owner){
        this.owner = owner;
        Field.size= size;
        panelMarkerMat = new PanelState[size][size];
    }


    public static int getSize(){
        return size;
    }


    public PanelState[][] getPanelMarkerMat(){
        return panelMarkerMat;
    }


    public PanelState getPanelMarker(int x, int y){
        return panelMarkerMat[y][x];
    }


    public void setPanelMarker(int x, int y, PanelState newPanelState){
        panelMarkerMat[y][x] = newPanelState;
    }

}
