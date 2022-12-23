package de.hhn.it.devtools.components.battleship.junit;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.battleship.provider.Computer;
import de.hhn.it.devtools.components.battleship.provider.Player;
import de.hhn.it.devtools.components.battleship.provider.ShipField;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test addCallback")
public class TestAddCallback {

    CmpBattleshipService bsService = new CmpBattleshipService();
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
    @DisplayName("Test addCallback but listener is null")
    public void addCallbackWithNullListener() {
        // IllegalParameterException should be thrown
        IllegalParameterException exception = assertThrows(IllegalParameterException.class,
                () -> bs.addCallBack(null));
    }

    @Test
    @DisplayName("Test addCallback but two times the same listener")
    public void addCallbackWithTheSameListenerTwice() throws IllegalParameterException {
        // IllegalParameterException should be thrown
        BattleshipListenerInner listener = new BattleshipListenerInner();
        bs.addCallBack(listener);
        IllegalParameterException exception = assertThrows(IllegalParameterException.class,
                () -> bs.addCallBack(listener));
    }

    // Good Cases

    @Test
    @DisplayName("Test addCallback for the GameStates successfully")
    public void addCallbackForGameStatesSuccessfully() throws IllegalParameterException {
        BattleshipListenerInner listener = new BattleshipListenerInner();
        listener.newState(GameState.PREGAME);
        listener.newState(GameState.PLACINGSHIPS);
        listener.newState(GameState.FIRINGSHOTS);
        listener.newState(GameState.GAMEOVER);
        bs.addCallBack(listener);
        assertEquals(4, listener.states.size());
        // Wenn man hier mit bsService.getListeners().size() bekommt man nur 1 als Wert zurück da nur einmal geadded wird (Zeile 72), aber der eine geaddede listener hat die 4 GameStates drin
    }

    @Test
    @DisplayName("Test addCallback for ship that can be placed")
    public void addCallbackForPossibleShip() throws IllegalParameterException, IllegalGameStateException {
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        BattleshipListenerInner listener = new BattleshipListenerInner();
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        listener.outputPlacingPossible(ship, bs.isPlacementPossible(player, ship, 3, 2, false));
        bs.addCallBack(listener);
        assertEquals(1, listener.shipsPossible.size());
    }

    @Test
    @DisplayName("Test addCallback for ship that can't be placed")
    public void addCallbackForNotPossibleShip() throws IllegalGameStateException, IllegalParameterException {
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        BattleshipListenerInner listener = new BattleshipListenerInner();
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        listener.outputPlacingPossible(ship, bs.isPlacementPossible(player, ship, 6, 2, false));
        bs.addCallBack(listener);
        assertEquals(1, listener.shipsNotPossible.size());
    }

    @Test
    @DisplayName("Test addCallback for placed ship")
    public void addCallbackForPlacedShip() throws IllegalParameterException, IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        BattleshipListenerInner listener = new BattleshipListenerInner();
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        bs.placeShip(player, ship, 8, 0);
        listener.outputShipPlaced(ship);
        bs.addCallBack(listener);
        assertEquals(1, listener.shipsPlaced.size());
    }

    @Test
    @DisplayName("Test addCallback for an unplaced ship that can be moved")
    public void addCallbackForRotatableShip() throws IllegalParameterException {
        bsService.setCurrentGameState(GameState.PLACINGSHIPS);
        BattleshipListenerInner listener = new BattleshipListenerInner();
        Position pos = new Position(null, null);
        Ship ship = new Ship(ShipType.BATTLESHIP, pos);
        ship.setIsVertical(true);
        ship.setPlaced(false);
        listener.outputShipMovable(ship);
        bs.addCallBack(listener);
        assertEquals(1, listener.shipsMovable.size());
    }

    @Test
    @DisplayName("Test addCallback for bombed successfully")
    public void addCallbackForSuccessfullyBombed() {
        // TODO ERST BOMB PANEL DURCHGEHEN UND PRÜFEN DANN HIER WEITER
    }

    @Test
    @DisplayName("Test addCallback for bombed unsuccessfully")
    public void addCallbackForUnsuccessfullyBombed() {
        // TODO ERST BOMB PANEL DURCHGEHEN UND PRÜFEN DANN HIER WEITER
    }

    @Test
    @DisplayName("Test addCallback for player won")
    public void addCallbackForPlayerWon() throws IllegalParameterException {
        BattleshipListenerInner listener = new BattleshipListenerInner();
        listener.outputWinner(true);
        bs.addCallBack(listener);
        assertEquals(true, listener.playerWin.get(0));
    }

    @Test
    @DisplayName("Test addCallback for player lost - gives up")
    public void addCallbackForPlayerLost() throws IllegalGameStateException, IllegalParameterException {
        BattleshipListenerInner listener = new BattleshipListenerInner();
        bsService.concede();
        listener.outputWinner(false);
        bs.addCallBack(listener);
        assertAll(
                () -> assertEquals(false, listener.playerWin.get(0)),
                () -> assertEquals(GameState.GAMEOVER, bsService.getCurrentGameState())
        );
    }


}


// inner class as a BattleshipListener
class BattleshipListenerInner implements BattleshipListener {

    public List<GameState> states;
    public List<Field> fields;
    public List<Ship> shipsPossible;
    public List<Ship> shipsNotPossible;
    public List<Ship> shipsPlaced;
    public List <Ship> shipsMovable;
    public List <Position> bombedSuccesful;
    public List <Position> bombedUnSuccesful;
    public List <Boolean> playerWin;

    BattleshipListenerInner() {
        states = new ArrayList<>();
        fields = new ArrayList<>();
        shipsPossible = new ArrayList<>();
        shipsNotPossible = new ArrayList<>();
        shipsPlaced = new ArrayList<>();
        shipsMovable = new ArrayList<>();
        bombedSuccesful = new ArrayList<>();
        bombedUnSuccesful = new ArrayList<>();
        playerWin = new ArrayList<>();
    }

    @Override
    public void newState(GameState state) {
        states.add(state);
    }

    @Override
    public void newFieldCreated(Field createdField) {
        fields.add(createdField);
    }

    @Override
    public void outputPlacingPossible(Ship ship, boolean possible) {
        if(possible) {
            shipsPossible.add(ship);
        }
        if(!possible){
            shipsNotPossible.add(ship);
        }
    }

    @Override
    public void outputShipPlaced(Ship ship) {
        shipsPlaced.add(ship);
    }

    @Override
    public void outputShipMovable(Ship ship) {
        shipsMovable.add(ship);
    }

    @Override
    public void outputBombingSuccessful(Position bombedPosition, boolean successful) {
        if(successful){
            bombedSuccesful.add(bombedPosition);
        }
        if(!successful){
            bombedUnSuccesful.add(bombedPosition);
        }
    }

    @Override
    public void outputWinner(boolean playerWon) {
        playerWin.add(playerWon);
    }

}
