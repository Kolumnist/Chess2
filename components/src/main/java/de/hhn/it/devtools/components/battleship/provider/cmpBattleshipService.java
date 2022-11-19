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
        return false;
    }

    // nedim
    @Override
    public void placeShip(Ship shipToPlace, int x1, int y1) throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException {

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
