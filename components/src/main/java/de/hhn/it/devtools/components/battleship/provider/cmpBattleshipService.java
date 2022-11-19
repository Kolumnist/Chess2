package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.IllegalFormatException;

public class cmpBattleshipService implements BattleshipService {

    // nuri
    @Override
    public void addCallBack(BattleshipListener listener) throws IllegalParameterException {

    }

    // nuri
    @Override
    public void removeCallback(BattleshipListener listener) throws IllegalParameterException {

    }

    // nedim
    @Override
    public boolean isPlacementPossible(Ship shipToPlace, int x1, int y1, boolean isVertical) throws IllegalGameStateException {
        int shipSize = shipToPlace.getSize();
        int endX, endY;
        int fieldSize = Field.getSize();

        // Check if coordinates of ship is outside of field
        if((x1 < 0) || (y1 < 0) || (x1 > fieldSize) || (y1 > fieldSize)){
            //Exception schmeißen
        }

        else if(isVertical){
            // wenn y1 der Endpunkt (unterste Punkt) des Schiffes ist dann diese Rechnung:
            endY = y1 + shipSize;

            // TODO: Vielleicht ein Attribut bei Feld um zu checken ob die Felder die das Schiff besetzen würde belegt / frei sind?
            //Check if ship would be outside of the field if placed
            if(endY <= fieldSize){
                return true;
            }
            else{
                return false;
            }
        }

        else if(!isVertical) {
            // wenn x1 der Endpunkt (linkeste Punkt) des Schiffes ist dann diese Rechnung:
            endX = x1 + shipSize;

            // TODO: Vielleicht ein Attribut bei Feld um zu checken ob die Felder die das Schiff besetzen würde belegt / frei sind?
            //Check if ship would be outside of the field if placed
            if (endX <= fieldSize) {
                return true;
            } else {
                return false;
            }
        }

        // Muss rein sonst Fehlermeldung
        return false;
    }

    // nedim
    @Override
    public void placeShip(Ship shipToPlace, int x1, int y1) throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException {
        boolean isPlaced = shipToPlace.getPlaced();
        boolean isVertical = shipToPlace.getIsVertical();

        if(isPlaced){
            throw new IllegalShipStateException("Ship is already placed");
        }

        else if(!isPlacementPossible(shipToPlace, x1, y1, isVertical)){
            throw new IllegalPositionException("Ship cannot be placed");
        }

        // set ship on field and change placed state to true
        else if(isPlacementPossible(shipToPlace, x1, y1, isVertical)){
            shipToPlace.setPlaced(true);
            shipToPlace.setFieldPosition(x1, y1);
        }

    }

    // nedim
    @Override
    public void unPlace(Ship shipToMove) throws IllegalArgumentException, IllegalGameStateException {

    }
    // nedim
    @Override
    public void rotateShip(Ship shipToRotate) throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException {

    }

    // nuri
    @Override
    public boolean bombPanel(int x, int y) throws IllegalArgumentException, IllegalGameStateException {
        return false;
    }

    // nuri
    @Override
    public void createFields(int size) throws IllegalArgumentException, IllegalGameStateException {

    }

    // nuri
    @Override
    public void adjustSoundVolume(int newVolume) throws IllegalArgumentException {

    }

    // moutassem
    @Override
    public SavedGame saveGame() throws IllegalGameStateException {
        return null;
    }

    // moutassem
    @Override
    public void loadGame(SavedGame savedGame) throws IllegalFormatException {

    }

    // moutassem
    @Override
    public void concede() throws IllegalGameStateException {

    }

    // moutassem
    @Override
    public String displayRules() {
        return null;
    }
}
