package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.components.battleship.provider.Computer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestComPlaceShip {

    CmpBattleshipService bsService;

    @BeforeEach
    void setup() {
        bsService = new CmpBattleshipService();
    }

    @Test
    @DisplayName("Test check comShipPlacement - 5x5 field")
    public void comPlacementSuccessful5() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        bsService.setCurrentGameState(GameState.PREGAME);
        bsService.createFields(5);
        for(int i = 0; i < bsService.getComputer().getOwnedShips().size(); i++){
            assertEquals(true, bsService.getComputer().getOwnedShips().get(i).getPlaced());
        }
    }

    @Test
    @DisplayName("Test check comShipPlacement - 10x10 field")
    public void comPlacementSuccessful10() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        bsService.setCurrentGameState(GameState.PREGAME);
        bsService.createFields(10);
        for(int i = 0; i < bsService.getComputer().getOwnedShips().size(); i++){
            assertEquals(true, bsService.getComputer().getOwnedShips().get(i).getPlaced());
        }
    }

    @Test
    @DisplayName("Test check comShipPlacement - 15x15 field")
    public void comPlacementSuccessful15() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        bsService.setCurrentGameState(GameState.PREGAME);
        bsService.createFields(15);
        for(int i = 0; i < bsService.getComputer().getOwnedShips().size(); i++){
            assertEquals(true, bsService.getComputer().getOwnedShips().get(i).getPlaced());
        }
    }
}
