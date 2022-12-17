package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.battleship.provider.Computer;
import de.hhn.it.devtools.components.battleship.provider.Player;
import de.hhn.it.devtools.components.battleship.provider.ShipField;
import de.hhn.it.devtools.components.battleship.provider.cmpBattleshipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test removeCallback")
public class TestRemoveCallback {

    cmpBattleshipService bsService = new cmpBattleshipService();
    BattleshipService bs = bsService;
    Player player = new Player();
    ShipField playerField = new ShipField(9, player);
    Field pField = new Field(9, player);

    Computer computer = new Computer();
    ShipField computerField = new ShipField(9, computer);
    Field cField = new Field(9, computer);

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
    }

    // Bad Cases

    @Test
    @DisplayName("Test removeCallback with null parameter")
    public void removeNullCallback() {
        IllegalParameterException exception = assertThrows(IllegalParameterException.class,
                () -> bs.removeCallback(null));
    }

    @Test
    @DisplayName("Test removeCallback for not not added callback")
    public void removeCallbackButItsNotThere() throws IllegalParameterException {
        BattleshipListenerInner listener = new BattleshipListenerInner();
        IllegalParameterException exception = assertThrows(IllegalParameterException.class,
                () -> bs.removeCallback(listener));
    }

    // Good cases

    @Test
    @DisplayName("Test removeCallback successfully (here as example for GameState. but it works also for the others because it's the same)")
    public void removeCallbackForGameStateSuccessfully() throws IllegalParameterException {
        BattleshipListenerInner listener = new BattleshipListenerInner();
        listener.newState(GameState.PREGAME);
        listener.newState(GameState.PLACINGSHIPS);
        listener.newState(GameState.FIRINGSHOTS);
        listener.newState(GameState.GAMEOVER);
        bs.addCallBack(listener);

        bs.removeCallback(listener);
        assertEquals(0, bsService.getListeners().size());
    }

}
