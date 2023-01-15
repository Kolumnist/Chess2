package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.components.battleship.provider.Computer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestComBombPanel {

    CmpBattleshipService bsService = new CmpBattleshipService();
    BattleshipService bs = bsService;

    Computer computer = bsService.getComputer();

    @Test
    @DisplayName("Test check comBomb shoots and misses")
    public void comBombBasicMiss() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        bsService.setCurrentGameState(GameState.PREGAME);
        bs.createFields(5);
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        computer.comBomb(null, bsService.getPlayer(), -1);

        boolean miss = false;
        for(int i = 0; i < Field.getSize(); i++){
            for(int j = 0; j < Field.getSize(); j++) {
                if(computer.getAttackField().getPanelMarker(i, j).equals(PanelState.MISSED)){
                    miss = true;
                    break;
                }
            }
        }
        assertEquals(true, miss);
    }

    @Test
    @DisplayName("Test check comBomb shoots after hitting")
    public void comBombBasicHit() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        bsService.setCurrentGameState(GameState.PREGAME);
        bs.createFields(5);
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        bsService.getPlayer().getShipField().setPanelMarker(3, 3, PanelState.SHIP);
        bsService.getPlayer().getShipField().setPanelMarker(2, 3, PanelState.SHIP);
        bsService.getPlayer().getShipField().setPanelMarker(4, 3, PanelState.SHIP);
        bsService.getPlayer().getShipField().setPanelMarker(3, 2, PanelState.SHIP);
        bsService.getPlayer().getShipField().setPanelMarker(3, 4, PanelState.SHIP);
        computer.comBomb(new Position(3, 3), bsService.getPlayer(), -1);

        boolean miss = false;
        boolean hit = false;
        for(int i = 0; i < Field.getSize(); i++){
            for(int j = 0; j < Field.getSize(); j++) {
                if(computer.getAttackField().getPanelMarker(i, j).equals(PanelState.MISSED)){
                    miss = true;
                }
                else if(computer.getAttackField().getPanelMarker(i, j).equals(PanelState.HIT)){
                    hit = true;
                }
                if(hit && miss){
                    break;
                }
            }
        }
        assertEquals(true, miss);
        assertEquals(true, hit);
    }

    @Test
    @DisplayName("Test check comBomb hits a field that has already been hit")
    public void comBombHitAlreadyHitField() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        bsService.setCurrentGameState(GameState.PREGAME);
        bs.createFields(5);
        computer.getAttackField().setPanelMarker(2, 2, PanelState.MISSED);
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        computer.comBomb(new Position(2, 2), bsService.getPlayer(), -1);

        int miss = 0;
        for(int i = 0; i < Field.getSize(); i++){
            for(int j = 0; j < Field.getSize(); j++) {
                if(computer.getAttackField().getPanelMarker(i, j).equals(PanelState.MISSED)){
                    miss +=1;
                    if(miss == 2){
                        break;
                    }
                }
            }
        }
        assertEquals(2, miss);
    }
}
