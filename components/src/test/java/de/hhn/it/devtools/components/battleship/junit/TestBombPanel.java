package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.components.battleship.provider.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test bombPanel")
public class TestBombPanel {

    // Field size for tests set to 9

    cmpBattleshipService bsService = new cmpBattleshipService();
    BattleshipService bs = bsService;
    Player player = new Player();
    ShipField playerSField = new ShipField(9, player);
    AttackField playerAttack = new AttackField(9, player);
    Field pField = new Field(9, player);

    Computer computer = new Computer();
    ShipField computerField = new ShipField(9, computer);
    AttackField computerAttack = new AttackField(9, computer);
    Field cField = new Field(9, computer);

    @BeforeEach
    void setup() {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                playerSField.setPanelMarker(i, j, PanelState.NOSHIP);
                computerField.setPanelMarker(i, j, PanelState.NOSHIP);
                playerAttack.setPanelMarker(i, j, PanelState.NOSHIP);
                computerAttack.setPanelMarker(i, j, PanelState.NOSHIP);
            }
        }
        player.setShipfield(playerSField);
        computer.setShipfield(computerField);
        player.setAttackField(playerAttack);
        computer.setAttackField(computerAttack);
    }

    // Bad Cases

    @Test
    @DisplayName("Test check bombPanel in wrong GameState")
    public void bombPanelInWrongGameState() {
        // IllegalGameStateException should be thrown
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        IllegalGameStateException exception = assertThrows(IllegalGameStateException.class,
                () -> bs.bombPanel(player, computer, 5, 6));
    }

    @Test
    @DisplayName("Test check bombPanel for negative coordinates")
    public void bombPanelNegative() throws IllegalGameStateException {
        // IllegalArgumentException should be thrown
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bs.bombPanel(player, computer, -2 ,-7));
    }

    @Test
    @DisplayName("Test check bombPanel for coordinates out of field")
    public void bombPanelOutOfField() {
        // IllegalArgumentException should be thrown
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bs.bombPanel(player, computer, 9, 12));
    }

    @Test
    @DisplayName("Test check bombPanel where attacker is not a player or computer and is shot at the player's field")
    public void bombPanelForNoAllowedPlayerToPlayer() {
        // IllegalArgumentException should be thrown
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        Owner notAllowedAttacker = new Owner();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bs.bombPanel(notAllowedAttacker, player, 1, 5));
    }

    @Test
    @DisplayName("Test check bombPanel where attacker is not a player or computer and is shot at the computer's field")
    public void bombPanelForNoAllowedPlayerToComputer() {
        // IllegalArgumentException should be thrown
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        Owner notAllowedAttacker = new Owner();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bs.bombPanel(notAllowedAttacker, computer, 1, 5));
    }

    @Test
    @DisplayName("Test check bombPanel  where the attacker is not a player or computer and shoots at a field of a non-player or computer")
    public void bombPanelForNoAllowedPlayerToNotAllowedPlayer2() {
        // IllegalArgumentException should be thrown
        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        Owner notAllowedAttacker = new Owner();
        Owner notAllowedAttacker2 = new Owner();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bs.bombPanel(notAllowedAttacker, notAllowedAttacker2, 1, 5));
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
        bs.placeShip(computer, ship, 8, 5);

        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        assertEquals(true, bs.bombPanel(player, computer, 8, 7));
    }

    @Test
    @DisplayName("Test check bombPanel - player misses")
    public void bombPanelPlayerMissesShip() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // false should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(false);
        bs.placeShip(computer, ship, 0, 0);

        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        assertEquals(false, bs.bombPanel(player, computer, 0, 4));
    }

    @Test
    @DisplayName("Test check bombPanel - computer hits")
    public void bombPanelComputerHitsShip() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // true should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        bs.placeShip(player, ship, 3, 2);

        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        assertEquals(true, bs.bombPanel(computer, player, 3, 4));
    }

    @Test
    @DisplayName("Test check bombPanel - player misses")
    public void bombPanelComputerMissesShip() throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        // false should be returned
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(false);
        bs.placeShip(player, ship, 0, 5);

        bsService.setCurrentGameState(GameState.FIRINGSHOTS);
        assertEquals(false, bs.bombPanel(computer, player, 0, 4));
    }

}
