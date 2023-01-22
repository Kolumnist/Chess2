package de.hhn.it.devtools.components.battleship.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.apis.battleship.BattleshipService;
import de.hhn.it.devtools.apis.battleship.GameState;
import de.hhn.it.devtools.apis.battleship.IllegalGameStateException;
import de.hhn.it.devtools.apis.battleship.SavedGame;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    assertThrows(IllegalGameStateException.class,
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
