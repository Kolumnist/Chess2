package de.hhn.it.devtools.components.battleship.junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

@DisplayName("Test unPlace")
public class TestUnPlace {

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
  @DisplayName("Test check unPLace a ship with wrong GameState")
  public void unPlaceInWrongGameState()
      throws IllegalGameStateException, IllegalShipStateException, IllegalPositionException {
    // IllegalGameStateException should be thrown
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    Position pos = new Position(null, null);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    ship.setIsVertical(true);
    bs.placeShip(player, ship, 8, 5);
    bsService.setCurrentGameState(GameState.FIRINGSHOTS);
    assertThrows(IllegalGameStateException.class,
        () -> bs.unPlace(player, ship));
  }

  // Good Cases

  @Test
  @DisplayName("Test check unPlace a ship - vertical")
  public void unPlaceVertical()
      throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
    // false should be returned
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    Position pos = new Position(null, null);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    ship.setIsVertical(true);
    bs.placeShip(player, ship, 0, 5);
    bs.unPlace(player, ship);
    assertFalse(ship.getPlaced());
  }

  @Test
  @DisplayName("Test check unPlace a ship - horizontal")
  public void unPlaceHorizontal()
      throws IllegalGameStateException, IllegalShipStateException, IllegalPositionException {
    // false should be returned
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    Position pos = new Position(null, null);
    Ship ship = new Ship(ShipType.BATTLESHIP, pos);
    ship.setIsVertical(false);
    bs.placeShip(player, ship, 5, 4);
    bs.unPlace(player, ship);
    assertFalse(ship.getPlaced());
  }

}
