package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.components.battleship.provider.Computer;
import de.hhn.it.devtools.apis.battleship.Player;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test unPlace")
public class TestUnPlace {

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
    @DisplayName("Test check unPLace a ship with wrong GameState")
    public void unPlaceInWrongGameState() throws IllegalGameStateException, IllegalShipStateException, IllegalPositionException {
        // IllegalGameStateException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        bs.placeShip(player2OwnerMap.get(player), ship, 8, 5);
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        IllegalGameStateException exception = assertThrows(IllegalGameStateException.class,
                () -> bs.unPlace(player2OwnerMap.get(player), ship));
    }

    // Good Cases

    @Test
    @DisplayName("Test check unPlace a ship - vertical")
    public void unPlaceVertical() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // false should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        bs.placeShip(player2OwnerMap.get(player), ship, 0, 5);
        bs.unPlace(player2OwnerMap.get(player), ship);
        assertEquals(false, ship.getPlaced());
    }

    @Test
    @DisplayName("Test check unPlace a ship - horizontal")
    public void unPlaceHorizontal() throws IllegalGameStateException, IllegalShipStateException, IllegalPositionException {
        // false should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(false);
        bs.placeShip(player2OwnerMap.get(player), ship, 5, 4);
        bs.unPlace(player2OwnerMap.get(player), ship);
        assertEquals(false, ship.getPlaced());
    }

}
