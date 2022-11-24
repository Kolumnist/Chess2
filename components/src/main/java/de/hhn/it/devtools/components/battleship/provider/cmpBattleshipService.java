package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import java.io.*;
import java.util.IllegalFormatException;

public class cmpBattleshipService implements BattleshipService {
    static GameState currentGameState = GameState.PREGAME;
    Player player = new Player();
    Computer computer = new Computer();
    // nuri
    @Override
    public void addCallBack(BattleshipListener listener) throws IllegalParameterException {

    }

    // nuri
    @Override
    public void removeCallback(BattleshipListener listener) throws IllegalParameterException {

    }

    // nedim
    @Override
    public boolean isPlacementPossible(Owner owner, Ship shipToPlace, int x1, int y1, boolean isVertical) throws IllegalGameStateException, IllegalArgumentException {
        int shipSize = shipToPlace.getSize();
        // wenn x1 der Endpunkt (linkeste Punkt) des Schiffes ist dann diese Rechnung:
        int endX = x1 + shipSize;
        // wenn y1 der Endpunkt (oberste Punkt) des Schiffes ist dann diese Rechnung:
        int endY = y1 + shipSize;
        int fieldSize = Field.getSize();
        boolean[][] boolField;

        // Check if coordinates of ship is outside of field
        if((x1 < 0) || (y1 < 0) || (x1 > fieldSize) || (y1 > fieldSize)){
            return false;
        }
        else if(currentGameState != GameState.PLACINGSHIPS){
            throw new IllegalGameStateException();
        }
        if(owner.equals(player)) {
            boolField = player.getpShipField().getPanelMarkerMat();
        }
        else if(owner.equals(computer)){
            boolField = computer.getcShipField().getPanelMarkerMat();
        }
        else{
            throw new IllegalArgumentException();
        }
        if(isVertical){
            for(int i = y1; i < endY; i++){
                if(boolField[i][x1]){
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
            for(int i = x1; i < endX; i++){
                if(boolField[y1][i]){
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
        boolean isPlaced = shipToPlace.getPlaced();
        boolean isVertical = shipToPlace.getIsVertical();
        int shipSize = shipToPlace.getSize();
        int endX, endY;
        boolean[][] boolField;

        if(isPlaced){
            throw new IllegalShipStateException("Ship is already placed");
        }
        else if(currentGameState != GameState.PLACINGSHIPS){
            throw new IllegalGameStateException();
        }
        else if(!isPlacementPossible(owner, shipToPlace, x1, y1, isVertical)){
            throw new IllegalPositionException("Ship cannot be placed");
        }
        else if(isPlacementPossible(owner, shipToPlace, x1, y1, isVertical)){
            if(owner.equals(player)){
                boolField = player.getpShipField().getPanelMarkerMat();
            }
            // Diese Statement ging nicht ka wieso: else if(owner.equals(computer)){
            else if(owner.equals(computer)){
                boolField = computer.getcShipField().getPanelMarkerMat();
            }
            else{
                throw new IllegalArgumentException();
            }
            // set ship on field and change placed state to true
            shipToPlace.setPlaced(true);
            shipToPlace.setFieldPosition(x1, y1);
            if(isVertical){
                endY = y1 + shipSize;
                for(int i = y1; i < endY; i++){
                    boolField[i][x1] = true;
                }
            }
            else if(!isVertical){
                endX = x1 + shipSize;
                for(int i = x1; i < endX; i++){
                    boolField[y1][i] = true;
                }
            }
        }
    }

    // nedim
    @Override
    public void unPlace(Owner owner, Ship shipToMove) throws IllegalArgumentException, IllegalGameStateException {
        shipToMove.setPlaced(false);
        Position position = shipToMove.getFieldPosition();
        int x = position.getX(), y = position.getY();
        int shipSize = shipToMove.getSize();
        int endX, endY;
        boolean isVertical = shipToMove.getIsVertical();
        boolean[][] boolField;

        if(currentGameState != GameState.PLACINGSHIPS){
            throw new IllegalGameStateException();
        }
        else if(owner.equals(player)){
            boolField = player.getpShipField().getPanelMarkerMat();
        }
        else if(owner.equals(computer)){
            boolField = computer.getcShipField().getPanelMarkerMat();
        }
        else{
            throw new IllegalArgumentException();
        }
        if(isVertical){
            endY = y + shipSize;
            for(int i = y; i < endY; i++){
                boolField[i][x] = false;
            }
        }
        else if(!isVertical){
            endX = x + shipSize;
            for(int i = x; i < endX; i++){
                boolField[y][i] = false;
            }
        }
    }

    // nedim
    @Override
    public void rotateShip(Owner owner, Ship shipToRotate) throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException {
        // für die neuen koordinaten vielleicht eine berechnung?
        // wenn Schiff vertikal liegt, dann ist x wert gleich aber y zwischen front und heck verschieden,
        // wenn Schiff horizontal liegt, dann ist y wert gleich aber x zwischen front und heck verschieden

        boolean isVertical = shipToRotate.getIsVertical();
        boolean isPlaced = shipToRotate.getPlaced();
        Position shipPosition = shipToRotate.getFieldPosition();
        int xCurrent = shipPosition.getX();
        int yCurrent = shipPosition.getY();

        if(isPlaced){
            throw new IllegalShipStateException("Ship is already placed");
        }
        else if(currentGameState != GameState.PLACINGSHIPS){
            throw new IllegalGameStateException();
        }
        // check if ship is vertical and can be placed horizontally
        else if(isVertical){
            // wenn am heck gedreht wird zu horizontal dann bleibt y und x ändert sich -> Drehung: im Uhrzeigersinn
            int xNew = xCurrent + shipToRotate.getSize();
            if(isPlacementPossible(owner, shipToRotate, xNew, yCurrent, false)){
                // rotate the ship
                shipToRotate.setIsVertical(false);
                // damit Felder wo das Schiff steht auf false gesetzt werden
                unPlace(owner, shipToRotate);
                // place ship   hier werden die Felder, auf die das Schiff nach dem Drehen steht, wieder auf true gesetzt
                placeShip(owner, shipToRotate, xNew, yCurrent);
            }
            else{
                throw new IllegalPositionException("Ship cannot be placed");
            }
        }
        else if(!isVertical){
            // wenn am heck gedreht wird zu vertikal dann bleibt x und y ändert sich -> Drehung: gegen Uhrzeigersinn
            int yNew = yCurrent - shipToRotate.getSize();
            if(isPlacementPossible(owner, shipToRotate, xCurrent, yNew, true)){
                // rotate the ship
                shipToRotate.setIsVertical(true);
                // damit Felder wo das Schiff steht auf false gesetzt werden
                unPlace(owner, shipToRotate);
                // place ship   hier werden die Felder, auf die das Schiff nach dem Drehen steht, wieder auf true gesetzt
                placeShip(owner, shipToRotate, xCurrent, yNew);
            }
            else{
                throw new IllegalPositionException("Ship cannot be placed");
            }
        }
    }

    // nuri
    @Override
    public boolean bombPanel(Owner attacker,int x, int y) throws IllegalArgumentException, IllegalGameStateException {

        // need to test if I can just use .equals or need to cast in some way

        if (attacker.equals(player)){

            boolean isShipOnPosition = computer.getcShipField().getPanelMarker(x,y);

            if (isShipOnPosition){
                // set ship part on position to bombed
                computer.getcShipField().setPanelMarker(x, y,false);

                player.getpAttackField().setPanelMarker(x, y,true);
                return true;
            }
            else {
                //set position to bombed (not necessary hit)
                player.getpAttackField().setPanelMarker(x,y,true);
                return false;
            }

        }


        else if (attacker.equals(computer)){

            boolean isShipOnPosition = player.getpShipField().getPanelMarker(x,y);

            if(isShipOnPosition){
                // set ship part on position to bombed
                player.getpShipField().setPanelMarker(x,y,false);
                //set position to bombed (not necessary hit)
                computer.getcAttackField().setPanelMarker(x,y,true);
                return true;
            }
            else {
                // set position to bombed (not necessary hit)
                computer.getcAttackField().setPanelMarker(x,y,true);
                return false;
            }

        }

        // necessary but should'nt be executed
        return false;
    }

    // nuri
    @Override
    public void createFields(int size) throws IllegalArgumentException, IllegalGameStateException {
        player.setShipfield(new ShipField(size,player));
        player.setAttackField(new AttackField(size,player));
        
        computer.setShipfield(new ShipField(size,computer));
        computer.setAttackField(new AttackField(size,computer));
    }

    // nuri
    @Override
    public void adjustSoundVolume(int newVolume) throws IllegalArgumentException {

    }

    // moutassem
    @Override
    public SavedGame saveGame() throws IllegalGameStateException {
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
