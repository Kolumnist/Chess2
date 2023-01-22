package de.hhn.it.devtools.apis.battleship;

/**
 * creates field object.
 */
public class Field {

  private static int size;
  private final Player owner;
  //Index is false if no ship is on panel
  private final PanelState[][] panelMarkerMat;
  private final Ship[][] shipsOnField;


  public Field(int size, Player owner) {
    this.owner = owner;
    Field.size = size;
    panelMarkerMat = new PanelState[size][size];
    shipsOnField = new Ship[size][size];
  }

  public static int getSize() {
    return size;
  }

  public Ship getShipsOnField(int x, int y) {
    return shipsOnField[y][x];
  }

  public void setShipsOnField(Ship ship, int x, int y) {
    this.shipsOnField[y][x] = ship;
  }

  public PanelState[][] getPanelMarkerMat() {
    return panelMarkerMat;
  }


  public PanelState getPanelMarker(int x, int y) {
    return panelMarkerMat[y][x];
  }


  public void setPanelMarker(int x, int y, PanelState newPanelState) {
    panelMarkerMat[y][x] = newPanelState;
  }


}
