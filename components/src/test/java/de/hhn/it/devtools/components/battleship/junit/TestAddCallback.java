package de.hhn.it.devtools.components.battleship.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.apis.battleship.BattleshipListener;
import de.hhn.it.devtools.apis.battleship.BattleshipService;
import de.hhn.it.devtools.apis.battleship.Field;
import de.hhn.it.devtools.apis.battleship.GameState;
import de.hhn.it.devtools.apis.battleship.IllegalGameStateException;
import de.hhn.it.devtools.apis.battleship.IllegalPositionException;
import de.hhn.it.devtools.apis.battleship.IllegalShipStateException;
import de.hhn.it.devtools.apis.battleship.PanelState;
import de.hhn.it.devtools.apis.battleship.Player;
import de.hhn.it.devtools.apis.battleship.Ship;
import de.hhn.it.devtools.apis.battleship.ShipType;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.components.battleship.provider.Computer;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test addCallback")
public class TestAddCallback {

  CmpBattleshipService bsService = new CmpBattleshipService();
  BattleshipService bs = bsService;
  Player player = bsService.getPlayer();
  Field playerField = new Field(5, player);

  Computer computer = bsService.getComputer();
  Field computerField = new Field(5, computer);

  public Player getPlayer() {
    return this.player;
  }

  @BeforeEach
  void setup() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
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
  @DisplayName("Test addCallback but listener is null")
  public void addCallbackWithNullListener() {
    // IllegalParameterException should be thrown
    assertThrows(IllegalParameterException.class,
        () -> bs.addCallBack(null));
  }

  @Test
  @DisplayName("Test addCallback but two times the same listener")
  public void addCallbackWithTheSameListenerTwice() throws IllegalParameterException {
    // IllegalParameterException should be thrown
    BattleshipListenerInner listener = new BattleshipListenerInner();
    bs.addCallBack(listener);
    assertThrows(IllegalParameterException.class,
        () -> bs.addCallBack(listener));
  }

  // Good Cases

  @Test
  @DisplayName("Test addCallback for potential winner")
  public void addCallbackPotentialWinner()
      throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
    BattleshipListenerInner listener = new BattleshipListenerInner();
    Ship ship = new Ship(ShipType.DESTROYER, null);
    ship.setIsVertical(false);
    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    bs.placeShip(computer, ship, 0, 0);
    bsService.setCurrentGameState(GameState.FIRINGSHOTS);
    bs.bombPanel(player, computer, 0, 0);
    bs.bombPanel(player, computer, 1, 0);
    if (bsService.checkWon(player)) {
      listener.outputWinner(player);
    }
    assertEquals(1, listener.winners.size());
  }

  @Test
  @DisplayName("Test addCallback for unplaced ships - player")
  public void addCallbackUnplacedShip()
      throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
    BattleshipListenerInner listener1 = new BattleshipListenerInner();
    BattleshipListenerInner listener2 = new BattleshipListenerInner();
    bsService.setCurrentGameState(GameState.PREGAME);

    Ship ship1 = new Ship(ShipType.DESTROYER, null);
    ship1.setPlaced(false);
    player.setOwnedShips(ship1);

    Ship ship2 = new Ship(ShipType.DESTROYER, null);
    ship2.setPlaced(false);
    player.setOwnedShips(ship2);

    listener1.updateUnplacedShips(player);

    assertEquals(2, listener1.unplacedShips.size());

    bsService.setCurrentGameState(GameState.PLACINGSHIPS);
    bs.placeShip(player, ship1, 0, 0);

    listener2.updateUnplacedShips(player);
    assertEquals(1, listener2.unplacedShips.size());
  }

}

// inner class as a BattleshipListener
class BattleshipListenerInner implements BattleshipListener {

  public ArrayList<Player> winners;
  public ArrayList<Ship> unplacedShips;
  //        int unplacedShips = 0;
  public ArrayList<Field> shotsFired;
  public ArrayList<Field> placedAllShips;
  public ArrayList<Field> updatedFields;
  public ArrayList<Field> resetShipSelectedButtons;

  BattleshipListenerInner() {
    winners = new ArrayList<>();
    unplacedShips = new ArrayList<>();
    shotsFired = new ArrayList<>();
    placedAllShips = new ArrayList<>();
    updatedFields = new ArrayList<>();
    resetShipSelectedButtons = new ArrayList<>();
  }

  @Override
  public void outputWinner(Player potentialWinner) {
    winners.add(potentialWinner);
  }

  @Override
  public void updateUnplacedShips(Player player) {
    for (int i = 0; i < player.getOwnedShips().size(); i++) {
      if (!player.getOwnedShips().get(i).getPlaced()) {
        unplacedShips.add(player.getOwnedShips().get(i));
      }
    }
  }

  @Override
  public void updateFiringShotsButton() {

  }

  @Override
  public void allShipsPlaced() {

  }

  @Override
  public void updateField() {

  }

  @Override
  public void resetShipSelected() {

  }

}

