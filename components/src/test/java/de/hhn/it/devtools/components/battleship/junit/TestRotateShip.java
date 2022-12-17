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

public class TestRotateShip {

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
    @DisplayName("Test check rotateShip but it's already placed")
    public void rotateShipButAlreadyPlaced() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // IllegalShipStateException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        bs.placeShip(player, ship, 8, 5);
        IllegalShipStateException exception = assertThrows(IllegalShipStateException.class,
                () -> bs.rotateShip(player, ship));
    }

    @Test
    @DisplayName("Test check rotateShip in wrong GameState")
    public void rotateShipInWrongGameState() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // IllegalGameStateException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(1, 4);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(false);
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        IllegalGameStateException exception = assertThrows(IllegalGameStateException.class,
                () -> bs.rotateShip(player, ship));
    }

    @Test
    @DisplayName("Test check rotateShip with no player or computer")
    public void rotateShipWithNoAllowedPlayer() {
        // IllegalArguementException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Owner notAllowedOwner = new Owner();
        Position pos = new Position(3, 5);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bs.rotateShip(notAllowedOwner, ship));
    }


    @Test
    @DisplayName("Test check rotateShip that would stand out of the field - vertical to horizontal")
    public void rotateShipToHorizontalButOutOfField() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // IllegalPositionException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        bs.placeShip(player, ship, 7, 5);
        bs.unPlace(player, ship);
        IllegalPositionException exception = assertThrows(IllegalPositionException.class,
                () -> bs.rotateShip(player, ship));
    }

    @Test
    @DisplayName("Test check rotateShip that would stand out of the field - horizontal to vertical")
    public void rotateShipToVerticalButOutOfField() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // IllegalPositionException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(false);
        bs.placeShip(player, ship, 5, 1);
        bs.unPlace(player, ship);
        IllegalPositionException exception = assertThrows(IllegalPositionException.class,
                () -> bs.rotateShip(player, ship));
    }

    // Good Cases

    @Test
    @DisplayName("Test check rotateShip - vertical to horizontal")
    public void rotateShipToHorizontal() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // false should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        bs.placeShip(player, ship, 2, 1);
        bs.unPlace(player, ship);
        bs.rotateShip(player, ship);
        assertEquals(false, ship.getIsVertical());
    }

    @Test
    @DisplayName("Test check rotateShip - horizontal to vertical")
    public void rotateShipToVertical() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // true should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(false);
        bs.placeShip(player, ship, 5, 8);
        bs.unPlace(player, ship);
        bs.rotateShip(player, ship);
        assertEquals(true, ship.getIsVertical());
    }

}
