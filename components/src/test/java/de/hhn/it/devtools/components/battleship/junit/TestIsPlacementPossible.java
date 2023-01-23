package de.hhn.it.devtools.components.battleship.junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.devtools.apis.battleship.BattleshipService;
import de.hhn.it.devtools.apis.battleship.Field;
import de.hhn.it.devtools.apis.battleship.GameState;
import de.hhn.it.devtools.apis.battleship.IllegalGameStateException;
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

@DisplayName("Test isPlacementPossible")
public class TestIsPlacementPossible {

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
  @DisplayName("Test check isPlacementPossible with negative coordinates")
  public void checkNegativeCoordinates() throws IllegalGameStateException {
    // false should be returned
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    Position pos = new Position(null, null);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    assertFalse(bs.isPlacementPossible(player, ship, -1, -1, true));
  }

  @Test
  @DisplayName("Test check isPlacementPossible with coordinates outside the field")
  public void checkToBigCoordinates() throws IllegalGameStateException {
    // false should be returned
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    Position pos = new Position(null, null);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    assertFalse(bs.isPlacementPossible(player, ship, 9, 9, false));
  }

  @Test
  @DisplayName("Test check isPlacementPossible with wrong GameState")
  public void checkWithWrongGameState() {
    // IllegalGameStateException should be thrown
    bsService.setCurrentGameState(GameState.PREGAME);
    Position pos = new Position(null, null);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    assertThrows(IllegalGameStateException.class,
        () -> bs.isPlacementPossible(player, ship, 2, 4, false));
  }

  @Test
  @DisplayName("Test check isPlacementPossible for ship that would stand out of the field - vertical")
  public void checkShipIsVerticalOutOfField() throws IllegalGameStateException {
    // false should be returned
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    Position pos = new Position(null, null);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    assertFalse(bs.isPlacementPossible(player, ship, 1, 8, true));
  }

  @Test
  @DisplayName("Test check isPlacementPossible for ship that would stand out of the the field - horizontal")
  public void checkShipIsHorizontalOutOfField() throws IllegalGameStateException {
    // false should be returned
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    Position pos = new Position(null, null);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    assertFalse(bs.isPlacementPossible(player, ship, 7, 2, false));
  }

  // Good Cases

  @Test
  @DisplayName("Test check isPlacementPossible for ships set into the field - vertical")
  public void checkShipVerticalInField() throws IllegalGameStateException {
    // true should be returned
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    Position pos = new Position(null, null);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    assertTrue(bs.isPlacementPossible(player, ship, 7, 5, true));
  }

  @Test
  @DisplayName("Test check isPlacementPossible for ships set into the field - horizontal")
  public void checkShipHorizontalInField() throws IllegalGameStateException {
    // true should be returned
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    Position pos = new Position(null, null);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    assertTrue(bs.isPlacementPossible(player, ship, 5, 0, false));
  }

}
