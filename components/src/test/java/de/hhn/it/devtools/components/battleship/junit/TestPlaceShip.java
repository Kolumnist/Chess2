package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.components.battleship.provider.Computer;
import de.hhn.it.devtools.apis.battleship.Player;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test placeShip")
public class TestPlaceShip {

    // Field size for tests set to 9

    CmpBattleshipService bsService = new CmpBattleshipService();
    BattleshipService bs = bsService;
    Player player = bsService.getPlayer();
    Field playerField = new Field(9, player);

    Computer computer = bsService.getComputer();
    Field computerField = new Field(9, computer);

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
        player.setAttackField(playerField);
        computer.setAttackField(computerField);
    }

    // Bad Cases

    @Test
    @DisplayName("Test check placeShip but ship is already placed")
    public void placeAnAlreadyPlacedShip() throws IllegalShipStateException {
        // IllegalShipStateException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(false);
        ship.setPlaced(true);
        IllegalShipStateException exception = assertThrows(IllegalShipStateException.class,
                () -> bs.placeShip(player, ship, 4, 3));
    }

    @Test
    @DisplayName("Test check placeShips with wrong GameState")
    public void placeInWrongGameState() throws IllegalGameStateException {
        // IllegalGameStateException should be thrown
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        IllegalGameStateException exception = assertThrows(IllegalGameStateException.class,
                () -> bs.placeShip(player, ship, 2, 5));
    }

    @Test
    @DisplayName("Test check placeShip but ship coordinates are negative")
    public void placeButCoordinatesAreNegative()throws IllegalPositionException{
        // IllegalPositionException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        IllegalPositionException exception = assertThrows(IllegalPositionException.class,
                () -> bs.placeShip(player, ship, -2, -3));
    }

    @Test
    @DisplayName("Test check placeShip but ship coordinates are out of the field")
    public void placeButCoordinatesAreOutOfField()throws IllegalPositionException {
        // IllegalPositionException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        IllegalPositionException exception = assertThrows(IllegalPositionException.class,
                () -> bs.placeShip(player, ship, 9, 10));
    }

    @Test
    @DisplayName("Test check isPlacementPossible for ship that would stand out of the the field - vertical")
    public void placeVerticalButOutOfField() throws IllegalPositionException {
        // false should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        IllegalPositionException exception = assertThrows(IllegalPositionException.class,
                () -> bs.placeShip(player, ship, 7, 7));
    }

    @Test
    @DisplayName("Test check isPlacementPossible for ship that would stand out of the field - horizontal")
    public void placeHorizontalButOutOfField() throws IllegalPositionException {
        // false should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(false);
        IllegalPositionException exception = assertThrows(IllegalPositionException.class,
                () -> bs.placeShip(player, ship, 8, 1));
    }

    // Good Cases

    @Test
    @DisplayName("Test check placeShip successful - vertical")
    public void placeSuccessfulVertical() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        bs.placeShip(player, ship, 3, 4);
        assertEquals(true, ship.getPlaced());
    }

    @Test
    @DisplayName("Test check placeShip successful - horizontal")
    public void placeSuccessfulHorizontal() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(false);
        bs.placeShip(player, ship, 3, 4);
        assertEquals(true, ship.getPlaced());
    }

}
