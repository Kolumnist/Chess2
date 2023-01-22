package de.hhn.it.devtools.components.battleship.junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.devtools.apis.battleship.BattleshipService;
import de.hhn.it.devtools.apis.battleship.Field;
import de.hhn.it.devtools.apis.battleship.GameState;
import de.hhn.it.devtools.apis.battleship.IllegalGameStateException;
import de.hhn.it.devtools.apis.battleship.IllegalPositionException;
import de.hhn.it.devtools.apis.battleship.IllegalShipStateException;
import de.hhn.it.devtools.apis.battleship.PanelState;
import de.hhn.it.devtools.apis.battleship.Player;
import de.hhn.it.devtools.apis.battleship.Position;
import de.hhn.it.devtools.apis.battleship.Ship;
import de.hhn.it.devtools.apis.battleship.ShipType;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.components.battleship.provider.Computer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test rotateShip")
public class TestRotateShip {

  // Field size for tests set to 9

  CmpBattleshipService bsService = new CmpBattleshipService();
  BattleshipService bs = bsService;
  Player player = bsService.getPlayer();
  Field playerField = new Field(9, player);

  Computer computer = bsService.getComputer();
  Field computerField = new Field(9, computer);

  @BeforeEach
  void setup() {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        playerField.setPanelMarker(i, j, PanelState.NOSHIP);
        computerField.setPanelMarker(i, j, PanelState.NOSHIP);
      }
    }
    player.setShipField(playerField);
    computer.setShipField(computerField);
    player.setAttackField(playerField);
    computer.setAttackField(computerField);
  }

  // Bad Cases

  @Test
  @DisplayName("Test check rotateShip but it's already placed")
  public void rotateShipButAlreadyPlaced()
      throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
    // IllegalShipStateException should be thrown
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    Position pos = new Position(null, null);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    ship.setIsVertical(true);
    bs.placeShip(player, ship, 8, 5);
    assertThrows(IllegalShipStateException.class,
        () -> bs.rotateShip(player, ship));
  }

  @Test
  @DisplayName("Test check rotateShip in wrong GameState")
  public void rotateShipInWrongGameState() {
    // IllegalGameStateException should be thrown
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    Position pos = new Position(1, 4);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    ship.setIsVertical(false);
    bsService.setCurrentGameState(GameState.FIRINGSHOTS);
    assertThrows(IllegalGameStateException.class,
        () -> bs.rotateShip(player, ship));
  }

  // Good Cases

  @Test
  @DisplayName("Test check rotateShip - vertical to horizontal")
  public void rotateShipToHorizontal()
      throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
    // false should be returned
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    Position pos = new Position(null, null);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    ship.setIsVertical(true);
    bs.rotateShip(player, ship);
    bs.placeShip(player, ship, 2, 1);
    assertFalse(ship.getIsVertical());
  }

  @Test
  @DisplayName("Test check rotateShip - horizontal to vertical")
  public void rotateShipToVertical()
      throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
    // true should be returned
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    Position pos = new Position(null, null);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    ship.setIsVertical(false);
    bs.rotateShip(player, ship);
    bs.placeShip(player, ship, 2, 1);
    assertTrue(ship.getIsVertical());
  }

}
