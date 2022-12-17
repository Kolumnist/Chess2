package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.components.battleship.provider.Computer;
import de.hhn.it.devtools.components.battleship.provider.Player;
import de.hhn.it.devtools.components.battleship.provider.ShipField;
import de.hhn.it.devtools.components.battleship.provider.cmpBattleshipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test isPlacementPossible")
public class TestIsPlacementPossible {

    //TODO: Position auf NULL setzen???

    // Field size for tests set to 9

    cmpBattleshipService bsService = new cmpBattleshipService();
    BattleshipService bs = bsService;
    Player player = new Player();
    ShipField playerField = new ShipField(9, player);
    Field pField = new Field(9, player);

    Computer computer = new Computer();
    ShipField computerField = new ShipField(9, computer);
    Field cField = new Field(9, computer);

    @BeforeEach
    void setup() {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                playerField.setPanelMarker(i, j, PanelState.NOSHIP);
                computerField.setPanelMarker(i, j, PanelState.NOSHIP);
            }
        }
        player.setShipfield(playerField);
        computer.setShipfield(computerField);
    }

    // Bad Cases

    @Test
    @DisplayName("Test check isPlacementPossible with negative coordinates")
    public void checkNegativeCoordinates() throws IllegalGameStateException {
        // false should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        assertEquals(false, bs.isPlacementPossible(player, ship, -1, -1, true));
    }

    @Test
    @DisplayName("Test check isPlacementPossible with coordinates outside the field")
    public void checkToBigCoordinates() throws IllegalGameStateException {
        // false should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        assertEquals(false, bs.isPlacementPossible(player, ship, 9, 9, false));
    }

    @Test
    @DisplayName("Test check isPlacementPossible with wrong GameState")
    public void checkWithWrongGameState() {
        // IllegalGameStateException should be thrown
        bsService.setCurrentGameState(GameState.PREGAME);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        IllegalGameStateException exception = assertThrows(IllegalGameStateException.class,
                () -> bs.isPlacementPossible(player,ship, 2, 4, false));
    }

    @Test
    @DisplayName("Test check isPlacementPossible for no player or computer")
    public void checkWithNoAllowedPlayer() throws IllegalArgumentException {
        // IllegalArgumentException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Owner notAllowedPlayer = new Owner();
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bs.isPlacementPossible(notAllowedPlayer, ship, 2, 2, true));
    }

    @Test
    @DisplayName("Test check isPlacementPossible for ship that would stand out of the field - vertical")
    public void checkShipIsVerticalOutOfField() throws IllegalGameStateException {
        // false should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        assertEquals(false, bs.isPlacementPossible(player, ship, 1, 6, true));
    }

    @Test
    @DisplayName("Test check isPlacementPossible for ship that would stand out of the the field - horizontal")
    public void checkShipIsHorizontalOutOfField() throws IllegalGameStateException {
        // false should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        assertEquals(false, bs.isPlacementPossible(player, ship, 7, 2, false));
    }

    // Good Cases

    @Test
    @DisplayName("Test check isPlacementPossible for ships set into the field - vertical")
    public void checkShipVerticalInField() throws IllegalGameStateException {
        // true should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        assertEquals(true, bs.isPlacementPossible(player, ship, 7, 5, true));
    }

    @Test
    @DisplayName("Test check isPlacementPossible for ships set into the field - horizontal")
    public void checkShipHorizontalInField() throws IllegalGameStateException {
        // true should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        assertEquals(true, bs.isPlacementPossible(player, ship, 5, 0, false));
    }

}
