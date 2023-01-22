package de.hhn.it.devtools.components.battleship.junit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.devtools.apis.battleship.BattleshipService;
import de.hhn.it.devtools.apis.battleship.GameState;
import de.hhn.it.devtools.apis.battleship.IllegalGameStateException;
import de.hhn.it.devtools.apis.battleship.IllegalPositionException;
import de.hhn.it.devtools.apis.battleship.IllegalShipStateException;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestComPlaceShip {

  CmpBattleshipService bsService = new CmpBattleshipService();

  BattleshipService bs = bsService;

  @Test
  @DisplayName("Test check comShipPlacement - 5x5 field")
  public void comPlacementSuccessful5()
      throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
    bsService.setCurrentGameState(GameState.PREGAME);
    bs.createFields(5);
    for (int i = 0; i < bsService.getComputer().getOwnedShips().size(); i++) {
      assertTrue(bsService.getComputer().getOwnedShips().get(i).getPlaced());
    }
  }

  @Test
  @DisplayName("Test check comShipPlacement - 10x10 field")
  public void comPlacementSuccessful10()
      throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
    bsService.setCurrentGameState(GameState.PREGAME);
    bs.createFields(10);
    for (int i = 0; i < bsService.getComputer().getOwnedShips().size(); i++) {
      assertTrue(bsService.getComputer().getOwnedShips().get(i).getPlaced());
    }
  }

  @Test
  @DisplayName("Test check comShipPlacement - 15x15 field")
  public void comPlacementSuccessful15()
      throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
    bsService.setCurrentGameState(GameState.PREGAME);
    bs.createFields(15);
    for (int i = 0; i < bsService.getComputer().getOwnedShips().size(); i++) {
      assertTrue(bsService.getComputer().getOwnedShips().get(i).getPlaced());
    }
  }
}
