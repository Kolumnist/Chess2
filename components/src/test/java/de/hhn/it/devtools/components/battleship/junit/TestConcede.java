package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.components.battleship.provider.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test concede")
public class TestConcede {

    cmpBattleshipService bsService = new cmpBattleshipService();
    BattleshipService bs = bsService;

    @Test
    @DisplayName("Test concede but in wrong GameState - GameState.PREGAME")
    public void concedeInPregame(){
        // IllegalGameStateException should be thrown
        bsService.setCurrentGameState(GameState.PREGAME);
        IllegalGameStateException exception = assertThrows(IllegalGameStateException.class,
                () -> bs.concede());
    }

    @Test
    @DisplayName("Test concede but in wrong GameState - GameState.GAMEOVER")
    public void concedeInGameOver() {
        // IllegalGameStateException should be thrown
        bsService.setCurrentGameState(GameState.GAMEOVER);
        IllegalGameStateException exception = assertThrows(IllegalGameStateException.class,
                () -> bs.concede());
    }

    // Good Cases

    @Test
    @DisplayName("Test concede in GameState.PLACINGSHIPS")
    public void concedeInPlacingships() throws IllegalGameStateException {
        // currentGameState should be changed to GameState.GAMEOVER
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        bs.concede();
        assertEquals(GameState.GAMEOVER, bsService.getCurrentGameState());
    }

    @Test
    @DisplayName("Test concede in GameState.FIRINGSHOTS")
    public void concedeInFiringshots() throws IllegalGameStateException {
        // currentGameState should be changed to GameState.GAMEOVER
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        bs.concede();
        assertEquals(GameState.GAMEOVER, bsService.getCurrentGameState());
    }



















}
