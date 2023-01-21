package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.components.battleship.provider.Computer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test addCallback")
public class TestAddCallback {

    CmpBattleshipService bsService = new CmpBattleshipService();
    BattleshipService bs = bsService;
    Player player = bsService.getPlayer();
    Field playerField = new Field(9, player);

    Computer computer = bsService.getComputer();
    Field computerField = new Field(9, computer);

    @BeforeEach
    void setup() {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                playerField.setPanelMarker(i, j, PanelState.NOSHIP);
                computerField.setPanelMarker(i, j, PanelState.NOSHIP);
            }
        }
        player.setShipfield(playerField);
        computer.setShipfield(computerField);
        player.setAttackField(playerField);
        computer.setAttackField(computerField);
    }

    // Bad Cases

    @Test
    @DisplayName("Test addCallback but listener is null")
    public void addCallbackWithNullListener() {
        // IllegalParameterException should be thrown
        IllegalParameterException exception = assertThrows(IllegalParameterException.class,
                () -> bs.addCallBack(null));
    }

    @Test
    @DisplayName("Test addCallback but two times the same listener")
    public void addCallbackWithTheSameListenerTwice() throws IllegalParameterException {
        // IllegalParameterException should be thrown
        BattleshipListenerInner listener = new BattleshipListenerInner();
        bs.addCallBack(listener);
        IllegalParameterException exception = assertThrows(IllegalParameterException.class,
                () -> bs.addCallBack(listener));
    }

}

// inner class as a BattleshipListener
class BattleshipListenerInner implements BattleshipListener {

    public List<GameState> states;
    public List<Field> fields;
    public List<Ship> shipsPossible;
    public List<Ship> shipsNotPossible;
    public List<Ship> shipsPlaced;
    public List <Ship> shipsMovable;
    public List <Position> bombedSuccesful;
    public List <Position> bombedUnSuccesful;
    public List <Boolean> playerWin;

    BattleshipListenerInner() {
        states = new ArrayList<>();
        fields = new ArrayList<>();
        shipsPossible = new ArrayList<>();
        shipsNotPossible = new ArrayList<>();
        shipsPlaced = new ArrayList<>();
        shipsMovable = new ArrayList<>();
        bombedSuccesful = new ArrayList<>();
        bombedUnSuccesful = new ArrayList<>();
        playerWin = new ArrayList<>();
    }

    @Override
    public void outputWinner(Player potentialWinner) {

    }

    @Override
    public void updateUnplacedShips() {

    }

    @Override
    public void updateFiringShotsButton() {

    }

    @Override
    public void allShipsPlaced() {

    }

    @Override
    public void updateField() {

    }

    @Override
    public void resetShipSelected() {

    }

}
