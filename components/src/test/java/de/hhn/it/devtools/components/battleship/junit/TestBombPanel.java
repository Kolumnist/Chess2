package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.components.battleship.provider.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test bombPanel")
public class TestBombPanel {

    // Field size for tests set to 9

    CmpBattleshipService bsService = new CmpBattleshipService();
    BattleshipService bs = bsService;
    Player player = bsService.getPlayer();
    Field playerField = new Field(9, player);

    Computer computer = bsService.getComputer();
    Field computerField = new Field(9, computer);

    private Map<Player, Owner> player2OwnerMap;

    @BeforeEach
    void setup() {
        player2OwnerMap = new HashMap<>();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                playerField.setPanelMarker(i, j, PanelState.NOSHIP);
                computerField.setPanelMarker(i, j, PanelState.NOSHIP);
            }
        }
        player.setShipfield(playerField);
        computer.setShipfield(computerField);
        player.setAttackField(playerField);
        computer.setAttackField(computerField);
        player2OwnerMap.put(player, Owner.PLAYER);
        player2OwnerMap.put(computer, Owner.COMPUTER);
    }

    // Bad Cases

    @Test
    @DisplayName("Test check bombPanel in wrong GameState")
    public void bombPanelInWrongGameState() {
        // IllegalGameStateException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        IllegalGameStateException exception = assertThrows(IllegalGameStateException.class,
                () -> bs.bombPanel(player2OwnerMap.get(player), player2OwnerMap.get(computer), 5, 6));
    }

    @Test
    @DisplayName("Test check bombPanel for negative coordinates")
    public void bombPanelNegative() throws IllegalGameStateException {
        // IllegalArgumentException should be thrown
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bs.bombPanel(player2OwnerMap.get(player), player2OwnerMap.get(computer), -2 ,-7));
    }

    @Test
    @DisplayName("Test check bombPanel for coordinates out of field")
    public void bombPanelOutOfField() {
        // IllegalArgumentException should be thrown
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bs.bombPanel(player2OwnerMap.get(player), player2OwnerMap.get(computer), 9, 12));
    }

    // Good Cases

    @Test
    @DisplayName("Test check bombPanel - player hits")
    public void bombPanelPlayerHitsShip() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // true should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        bs.placeShip(player2OwnerMap.get(computer), ship, 8, 5);

        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        assertEquals(true, bs.bombPanel(player2OwnerMap.get(player), player2OwnerMap.get(computer), 8, 7));
    }

    @Test
    @DisplayName("Test check bombPanel - player misses")
    public void bombPanelPlayerMissesShip() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // false should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(false);
        bs.placeShip(player2OwnerMap.get(computer), ship, 0, 0);

        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        assertEquals(false, bs.bombPanel(player2OwnerMap.get(player), player2OwnerMap.get(computer), 0, 4));
    }

    @Test
    @DisplayName("Test check bombPanel - computer hits")
    public void bombPanelComputerHitsShip() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // true should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        bs.placeShip(player2OwnerMap.get(player), ship, 3, 2);

        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        assertEquals(true, bs.bombPanel(player2OwnerMap.get(computer), player2OwnerMap.get(player), 3, 4));
    }

    @Test
    @DisplayName("Test check bombPanel - player misses")
    public void bombPanelComputerMissesShip() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // false should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(false);
        bs.placeShip(player2OwnerMap.get(player), ship, 0, 5);

        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        assertEquals(false, bs.bombPanel(player2OwnerMap.get(computer), player2OwnerMap.get(player), 0, 4));
    }

}
