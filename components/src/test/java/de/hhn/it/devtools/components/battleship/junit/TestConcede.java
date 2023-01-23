package de.hhn.it.devtools.components.battleship.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.apis.battleship.BattleshipService;
import de.hhn.it.devtools.apis.battleship.GameState;
import de.hhn.it.devtools.apis.battleship.IllegalGameStateException;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test concede")
public class TestConcede {

  CmpBattleshipService bsService = new CmpBattleshipService();
  BattleshipService bs = bsService;

  @Test
  @DisplayName("Test concede but in wrong GameState - GameState.PREGAME")
  public void concedeInPregame() {
    // IllegalGameStateException should be thrown
    bsService.setCurrentGameState(GameState.PREGAME);
    assertThrows(IllegalGameStateException.class,
        () -> bs.concede());
  }

  @Test
  @DisplayName("Test concede but in wrong GameState - GameState.GAMEOVER")
  public void concedeInGameOver() {
    // IllegalGameStateException should be thrown
    bsService.setCurrentGameState(GameState.GAMEOVER);
    assertThrows(IllegalGameStateException.class,
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
