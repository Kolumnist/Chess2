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

public class TestUnPlace {

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

    @Test
    @DisplayName("Test check unPLace a ship with wrong GameState")
    public void unPlaceInWrongGameState() throws IllegalGameStateException, IllegalShipStateException, IllegalPositionException {
        // IllegalGameStateException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        bs.placeShip(player, ship, 8, 5);
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        IllegalGameStateException exception = assertThrows(IllegalGameStateException.class,
                () -> bs.unPlace(player, ship));
    }

    @Test
    @DisplayName("Test check unPlace ship with no player or computer")
    public void unPlaceWithNoAllowedPlayer() {
        // IllegalArguementException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Owner notAllowedOwner = new Owner();
        Position pos = new Position(3, 5);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        ship.setPlaced(true);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bs.unPlace(notAllowedOwner, ship));
    }

    @Test
    @DisplayName("Test check unPlace a ship - vertical")
    public void unPlaceVertical() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        bs.placeShip(player, ship, 0, 5);
        bs.unPlace(player, ship);
        assertEquals(false, ship.getPlaced());
    }

    @Test
    @DisplayName("Test check unPlace a ship - horizontal")
    public void unPlaceHorizontal() throws IllegalGameStateException, IllegalShipStateException, IllegalPositionException {
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(false);
        bs.placeShip(player, ship, 5, 4);
        bs.unPlace(player, ship);
        assertEquals(false, ship.getPlaced());
    }

}
