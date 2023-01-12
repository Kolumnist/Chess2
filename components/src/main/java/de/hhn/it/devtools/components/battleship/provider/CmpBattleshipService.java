package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;

// TODO durchs Feld durch iterieren und gucken ob es Schiffs-Felder gibt, die nicht gebombt wurden, falls nein -> gegner gewinnt & GameState zu GameOver
// TODO Button, der kommt (isVisible zu true in dem Fenster, wo "Ships left to place", davor ist der Button isVisible = false), wenn alle Schiffe platziert sind, wenn gedrückt wird -> GameState zu FiringShots
// Write Computer AI

public class CmpBattleshipService implements BattleshipService {
    static GameState currentGameState = GameState.PREGAME;
    static int gameVolume;
    private final Player player = new Player();
    private final Computer computer = new Computer();;
    private ArrayList<BattleshipListener> listeners;
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(CmpBattleshipService.class);
    private final Map<Owner, Player> owner2PlayerMap;

    private final Map<Player, Owner> player2OwnerMap;

    public CmpBattleshipService(){
        listeners = new ArrayList<>();
        owner2PlayerMap = new HashMap<>();
        owner2PlayerMap.put(Owner.PLAYER, player);
        owner2PlayerMap.put(Owner.COMPUTER, computer);
        player2OwnerMap = new HashMap<>();
        player2OwnerMap.put(player, Owner.PLAYER);
        player2OwnerMap.put(computer, Owner.COMPUTER);
    }

    public Player getPlayer(){
        return player;
    }

    public Computer getComputer(){
        return computer;
    }

    public void setCurrentGameState(GameState state){
        currentGameState = state;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public ArrayList<BattleshipListener> getListeners() {
        return listeners;
    }

    public int getGameVolume(){
        return gameVolume;
    }

    // nuri
    @Override
    public void addCallBack(BattleshipListener listener) throws IllegalParameterException {
        logger.info("addCallBack: listener = {}", listener);
        if(listener == null){
            throw new IllegalParameterException("Listener was null");
        }

        if(listeners.contains(listener)){
            throw new IllegalParameterException("Listener already registered");
        }

        listeners.add(listener);
    }

    // nuri
    @Override
    public void removeCallback(BattleshipListener listener) throws IllegalParameterException {
        logger.info("removeCallback: listener = {}", listener);

        if(listener == null){
            throw new IllegalParameterException("Listener was null");
        }

        if(listeners.contains(listener)) {
            listeners.remove(listener);
            return;
        }

        throw new IllegalParameterException("Listener was not added!");
    }

    // nedim
    @Override
    public boolean isPlacementPossible(Owner owner, Ship shipToPlace, int x1, int y1, boolean isVertical) throws IllegalGameStateException {
        logger.info("isPlacementPossible: owner = {}, ship = {}, x-value = {}, y-value = {}, isVertical = {}", owner, shipToPlace, x1, y1, isVertical);
        Player player = owner2PlayerMap.get(owner);
        int shipSize = shipToPlace.getSize();
        // wenn x1 der Endpunkt (linkeste Punkt) des Schiffes ist dann diese Rechnung:
        int endX = (x1 + shipSize) - 1;
        // wenn y1 der Endpunkt (oberste Punkt) des Schiffes ist dann diese Rechnung:
        int endY = (y1 + shipSize) - 1;
        int fieldSize = Field.getSize();
        PanelState[][] shipField;

        // Check if coordinates of ship is outside of field
        if((x1 < 0) || (y1 < 0) || (x1 >= fieldSize) || (y1 >= fieldSize) || (endX >= fieldSize && !isVertical) || (endY >= fieldSize && isVertical)){
            return false;
        }
        else if(currentGameState != GameState.PLACINGSHIPS){
            throw new IllegalGameStateException("Wrong GameState! Required GameState is PlacingShips");
        }
        shipField = player.getShipField().getPanelMarkerMat();

        if(isVertical){
            for(int i = y1; i <= endY; i++){
                if(shipField[i][x1] == PanelState.SHIP){
                    return false;
                }
            }
            if(endY < fieldSize){  // < fieldSize, da wenn fielSize = 5 -> 0 bis 4
                return true;
            }
            else{
                return false;
            }
        }
        else if(!isVertical){
            for(int i = x1; i <= endX; i++){
                if(shipField[y1][i] == PanelState.SHIP){
                    return false;
                }
            }
            if (endX < fieldSize){  // < fieldSize, da wenn fielSize = 5 -> 0 bis 4
                return true;
            }
            else{
                return false;
            }
        }
        // Muss rein sonst Fehlermeldung
        return false;
    }

    // nedim
    @Override
    public void placeShip(Owner owner, Ship shipToPlace, int x1, int y1) throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException, IllegalArgumentException{
        Player player = owner2PlayerMap.get(owner);
        boolean isPlaced = shipToPlace.getPlaced();
        boolean isVertical = shipToPlace.getIsVertical();
        int shipSize = shipToPlace.getSize();
        // wenn x1 der Endpunkt (linkeste Punkt) des Schiffes ist dann diese Rechnung:
        int endX = (x1 + shipSize) - 1;
        // wenn y1 der Endpunkt (oberste Punkt) des Schiffes ist dann diese Rechnung:
        int endY = (y1 + shipSize) - 1;
        PanelState[][] panelStateField;
        Field shipField;

        logger.info("placeShip: owner = {}, ship = {}, x-value = {}, y-value = {}, isVertical = {}, endX = {}, endY = {}, isPlaced = {}, currentGameState = {}, isPlacementPossible = {}", owner, shipToPlace, x1, y1, isVertical, endX, endY, isPlaced, currentGameState, isPlacementPossible(owner, shipToPlace, x1, y1, isVertical));

        if(isPlaced){
            throw new IllegalShipStateException("Ship is already placed");
        }
        else if(currentGameState != GameState.PLACINGSHIPS){
            throw new IllegalGameStateException("Wrong GameState! Required GameState is PlacingShips");
        }
        else if(!isPlacementPossible(owner, shipToPlace, x1, y1, isVertical)){
            throw new IllegalPositionException("Ship cannot be placed");
        }
        else if(isPlacementPossible(owner, shipToPlace, x1, y1, isVertical)){
            panelStateField = player.getShipField().getPanelMarkerMat();
            shipField = player.getShipField();
            // set ship on field and change placed state to true
            shipToPlace.setPlaced(true);
            shipToPlace.setFieldPosition(x1, y1);
            if(isVertical){
                for(int i = y1; i <= endY; i++){
                    panelStateField[i][x1] = PanelState.SHIP;
                    shipField.setPanelMarker(x1, i, PanelState.SHIP);
                }
            }
            else if(!isVertical){
                for(int i = x1; i <= endX; i++){
                    panelStateField[y1][i] = PanelState.SHIP;
                    shipField.setPanelMarker(i, y1, PanelState.SHIP);
                }
            }
        }
    }

    // nedim
    @Override
    public void unPlace(Owner owner, Ship shipToMove) throws IllegalArgumentException, IllegalGameStateException {
        Player player = owner2PlayerMap.get(owner);
        logger.info("unPlace: owner = {}, ship = {}", owner, shipToMove);
        shipToMove.setPlaced(false);
        Position position = shipToMove.getFieldPosition();
        int x = position.getX(), y = position.getY();
        int shipSize = shipToMove.getSize();
        // wenn x1 der Endpunkt (linkeste Punkt) des Schiffes ist dann diese Rechnung:
        int endX = (x + shipSize) - 1;
        // wenn y1 der Endpunkt (oberste Punkt) des Schiffes ist dann diese Rechnung:
        int endY = (y + shipSize) - 1;
        boolean isVertical = shipToMove.getIsVertical();
        PanelState[][] panelStateField;
        Field shipField;


        if(currentGameState != GameState.PLACINGSHIPS){
            throw new IllegalGameStateException("Wrong GameState! Required GameState is PlacingShips");
        }
        panelStateField = player.getShipField().getPanelMarkerMat();
        shipField = player.getShipField();
        if(isVertical){
            for(int i = y; i <= endY; i++){
                panelStateField[i][x] = PanelState.NOSHIP;
                shipField.setPanelMarker(x, i, PanelState.NOSHIP);
            }
        }
        else if(!isVertical){
            for(int i = x; i <= endX; i++){
                panelStateField[y][i] = PanelState.NOSHIP;
                shipField.setPanelMarker(i, y, PanelState.NOSHIP);
            }
        }
    }

    // nedim
    @Override
    public void rotateShip(Owner owner, Ship shipToRotate) throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException, IllegalArgumentException {
        Player player = owner2PlayerMap.get(owner);
        // für die neuen koordinaten vielleicht eine berechnung?
        // wenn Schiff vertikal liegt, dann ist x wert gleich aber y zwischen front und heck verschieden,
        // wenn Schiff horizontal liegt, dann ist y wert gleich aber x zwischen front und heck verschieden

        boolean isVertical = shipToRotate.getIsVertical();
        boolean isPlaced = shipToRotate.getPlaced();

        logger.info("rotateShip: owner = {}, ship = {}", owner, shipToRotate);

        if(isPlaced){
            throw new IllegalShipStateException("Ship is already placed");
        }
        else if(currentGameState != GameState.PLACINGSHIPS){
            throw new IllegalGameStateException("Wrong GameState! Required GameState is PlacingShips");
        }

        // check if ship is vertical and can be placed horizontally
        if(isVertical){
            shipToRotate.setIsVertical(false);
        }
        else if(!isVertical){
            shipToRotate.setIsVertical(true);
        }
    }

    // nuri
    @Override
    public boolean bombPanel(Owner attacker, Owner target, int x, int y) throws IllegalArgumentException, IllegalGameStateException {
        Player playerAttack = owner2PlayerMap.get(attacker);
        Player playerTarget = owner2PlayerMap.get(target);
        logger.info("bombPanel: attacker = {}, attacked = {}, x = {}, y = {}", attacker, target, x, y);
        if(currentGameState != GameState.FIRINGSHOTS){
            throw  new IllegalGameStateException("Wrong GameState! Required GameState is FiringShots");
        }
        if((x < 0) || (y < 0) || (x >= Field.getSize()) || (y >= Field.getSize())){
            throw new IllegalArgumentException();
        }
        PanelState isShipOnPosition;
        isShipOnPosition = playerTarget.getShipField().getPanelMarker(x,y);
        if (isShipOnPosition == PanelState.SHIP){
            // set ship part on position to bombed
            playerTarget.getShipField().setPanelMarker(x, y, PanelState.HIT);
            playerAttack.getAttackField().setPanelMarker(x, y,PanelState.HIT);
            return true;
        }
        else {
            //set position to bombed (not necessary hit)
            playerTarget.getShipField().setPanelMarker(x, y, PanelState.MISSED);
            playerAttack.getAttackField().setPanelMarker(x,y,PanelState.MISSED);
            return false;
        }
    }

    // nuri
    @Override
    public void createFields(int size) throws IllegalArgumentException, IllegalGameStateException, IllegalShipStateException, IllegalPositionException {

        if(currentGameState != GameState.PREGAME){
            throw  new IllegalGameStateException("Wrong GameState! Required GameState is PreGame");
        }

        logger.info("createFields: size = {}", size);

        player.setShipfield(new Field(size,player));
        player.setAttackField(new Field(size,player));
        
        computer.setShipfield(new Field(size,computer));
        computer.setAttackField(new Field(size,computer));

        // @TODO moutassem macht verteilung von ships abhängig von der Feldgröße
        // 1x5er, 2x4er, 3er Variabel, 1x2er
        if(size == 5){
            player.setOwnedShips(new Ship(ShipType.DESTROYER, null ));
            player.setOwnedShips(new Ship(ShipType.DESTROYER, null ));

            computer.setOwnedShips(new Ship(ShipType.DESTROYER, null ));
            computer.setOwnedShips(new Ship(ShipType.DESTROYER, null ));
        }
        else if(size == 10){
            player.setOwnedShips(new Ship(ShipType.CARRIER, null ));
            player.setOwnedShips(new Ship(ShipType.BATTLESHIP, null ));
            player.setOwnedShips(new Ship(ShipType.CRUISER, null ));
            player.setOwnedShips(new Ship(ShipType.SUBMARINE, null ));
            player.setOwnedShips(new Ship(ShipType.DESTROYER, null ));

            computer.setOwnedShips(new Ship(ShipType.CARRIER, null ));
            computer.setOwnedShips(new Ship(ShipType.BATTLESHIP, null ));
            computer.setOwnedShips(new Ship(ShipType.CRUISER, null ));
            computer.setOwnedShips(new Ship(ShipType.SUBMARINE, null ));
            computer.setOwnedShips(new Ship(ShipType.DESTROYER, null ));
        }
        else if(size == 15){
            player.setOwnedShips(new Ship(ShipType.CARRIER, null ));
            player.setOwnedShips(new Ship(ShipType.CARRIER, null ));
            player.setOwnedShips(new Ship(ShipType.BATTLESHIP, null ));
            player.setOwnedShips(new Ship(ShipType.BATTLESHIP, null ));
            player.setOwnedShips(new Ship(ShipType.CRUISER, null ));
            player.setOwnedShips(new Ship(ShipType.CRUISER, null ));
            player.setOwnedShips(new Ship(ShipType.SUBMARINE, null ));
            player.setOwnedShips(new Ship(ShipType.SUBMARINE, null ));
            player.setOwnedShips(new Ship(ShipType.DESTROYER, null ));
            player.setOwnedShips(new Ship(ShipType.DESTROYER, null ));
            player.setOwnedShips(new Ship(ShipType.DESTROYER, null ));

            computer.setOwnedShips(new Ship(ShipType.CARRIER, null ));
            computer.setOwnedShips(new Ship(ShipType.CARRIER, null ));
            computer.setOwnedShips(new Ship(ShipType.BATTLESHIP, null ));
            computer.setOwnedShips(new Ship(ShipType.BATTLESHIP, null ));
            computer.setOwnedShips(new Ship(ShipType.CRUISER, null ));
            computer.setOwnedShips(new Ship(ShipType.CRUISER, null ));
            computer.setOwnedShips(new Ship(ShipType.SUBMARINE, null ));
            computer.setOwnedShips(new Ship(ShipType.SUBMARINE, null ));
            computer.setOwnedShips(new Ship(ShipType.DESTROYER, null ));
            computer.setOwnedShips(new Ship(ShipType.DESTROYER, null ));
            computer.setOwnedShips(new Ship(ShipType.DESTROYER, null ));
        }

        currentGameState = GameState.PLACINGSHIPS;

        computer.comShipPlacement(player2OwnerMap, this, size);
    }

    // nuri
    @Override
    public void adjustSoundVolume(int newVolume) throws IllegalArgumentException {
        logger.info("adjustSoundVolume: newVolume = {}", newVolume);
        if(newVolume < 0){
            throw new IllegalArgumentException("Volume can't be negative");
        }
        gameVolume = newVolume;
    }

    // moutassem
    @Override
    public SavedGame saveGame() throws IllegalGameStateException {
        if(currentGameState == GameState.GAMEOVER || currentGameState == GameState.PREGAME){
            throw new IllegalGameStateException("You can not save when the game is not going");
        }
        //SavedGame Objekt erstellen
        SavedGame saveData = new SavedGame();
        //TODO: Name des files vom Spieler eingeben
        String fileName = "X.ser";

        //Serialisierung
        try {
            //Streams zum speichern des Objektes
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            //Methode zur Serialisierung
            out.writeObject(saveData);

            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }

        return null;
    }

    // moutassem
    @Override
    public void loadGame(SavedGame savedGame) throws IllegalFormatException {
        //TODO: Name der ausgewählten Datei via File Chooser
        Object saveFile = new Object();
        //Deserialisierung
        try
        {
            //Streams zum Einlesen des Files
            FileInputStream file = new FileInputStream("saveFile");
            ObjectInputStream in = new ObjectInputStream(file);

            //Methode zur Deserialisierung des Objektes
            savedGame = (SavedGame) in.readObject();

            in.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
    }

    // moutassem
    @Override
    public void concede() throws IllegalGameStateException {
        //TODO: braucht zuerst UI
        if(currentGameState == GameState.PREGAME || currentGameState == GameState.GAMEOVER){
            throw new IllegalGameStateException("You can not give up when the game is not going");
        }
        if(currentGameState == GameState.PLACINGSHIPS || currentGameState == GameState.FIRINGSHOTS) {
            currentGameState = GameState.GAMEOVER;
        }
        //Anzeigen, dass CPU gewinnt
        //Unter der Anzeige Knopf für rematch und Knopf für Rückkehr zum Hauptmenü
    }

    // moutassem
    @Override
    public String displayRules() {
        //TODO: braucht zuerst UI
        //Regelfenster anzeigen
        return null;
    }
}
