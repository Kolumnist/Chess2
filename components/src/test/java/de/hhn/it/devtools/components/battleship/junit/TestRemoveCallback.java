package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.components.battleship.provider.Computer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test removeCallback")
public class TestRemoveCallback {

    CmpBattleshipService bsService = new CmpBattleshipService();
    BattleshipService bs = bsService;
    Player player = bsService.getPlayer();
    Field playerField = new Field(5, player);

    Computer computer = bsService.getComputer();
    Field computerField = new Field(5, computer);

    @BeforeEach
    void setup() {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
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
    @DisplayName("Test removeCallback successfully")
    public void removePotentialWinnerSuccessfully() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException, IllegalParameterException {
        BattleshipListenerInner listener = new BattleshipListenerInner();
        Ship ship = new Ship(ShipType.DESTROYER, null);
        ship.setIsVertical(false);
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        bs.placeShip(computer, ship, 0, 0);
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        bs.bombPanel(player, computer, 0, 0);
        bs.bombPanel(player, computer, 1, 0);
        if (bsService.checkWon(player)) {
            listener.outputWinner(player);
        }
        bs.addCallBack(listener);

        bs.removeCallback(listener);
        assertEquals(0, bsService.getListeners().size());
    }

}
