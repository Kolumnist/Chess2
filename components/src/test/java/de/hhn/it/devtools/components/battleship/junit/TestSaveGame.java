package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.components.battleship.provider.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test saveGame")
public class TestSaveGame {

    // TODO: maybe find good ways how to test this method

    // Field size for tests set to 9

    CmpBattleshipService bsService = new CmpBattleshipService();
    BattleshipService bs = bsService;

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
