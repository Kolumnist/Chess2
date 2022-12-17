package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.components.battleship.provider.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test saveGame")
public class TestSaveGame {

    // Field size for tests set to 9

    cmpBattleshipService bsService = new cmpBattleshipService();
    BattleshipService bs = bsService;
    Player player = new Player();
    ShipField playerSField = new ShipField(9, player);
    AttackField playerAttack = new AttackField(9, player);
    Field pField = new Field(9, player);

    Computer computer = new Computer();
    ShipField computerField = new ShipField(9, computer);
    AttackField computerAttack = new AttackField(9, computer);
    Field cField = new Field(9, computer);

    @BeforeEach
    void setup() {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                playerSField.setPanelMarker(i, j, PanelState.NOSHIP);
                computerField.setPanelMarker(i, j, PanelState.NOSHIP);
                playerAttack.setPanelMarker(i, j, PanelState.NOSHIP);
                computerAttack.setPanelMarker(i, j, PanelState.NOSHIP);
            }
        }
        player.setShipfield(playerSField);
        computer.setShipfield(computerField);
        player.setAttackField(playerAttack);
        computer.setAttackField(computerAttack);
    }

    // Bad Case

    @Test
    @DisplayName("Test saveGame in wrong GameState")
    public void saveGameInWrongGameState() {
        bsService.setCurrentGameState(GameState.PREGAME);
        IllegalGameStateException exception = assertThrows(IllegalGameStateException.class,
                () -> bs.saveGame());
    }

    // Good Case

    @Test
    @DisplayName("Test saveGame successfully")
    public void saveGameSuccessful() throws IllegalGameStateException {
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        SavedGame save1 = bs.saveGame();
        SavedGame save2 = bs.saveGame();

        assertEquals(save1, save2);
    }

}
