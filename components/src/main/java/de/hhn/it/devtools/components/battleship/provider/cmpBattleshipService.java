package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import java.io.*;
import java.util.IllegalFormatException;

public class cmpBattleshipService implements BattleshipService {
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
    public boolean isPlacementPossible(Ship shipToPlace, int x1, int y1, boolean isVertical) throws IllegalGameStateException {
        int shipSize = shipToPlace.getSize();
        int endX, endY;
        int fieldSize = Field.getSize();
        boolean[][] boolField = Field.getCarriesShip();

        // Check if coordinates of ship is outside of field
        if((x1 < 0) || (y1 < 0) || (x1 > fieldSize) || (y1 > fieldSize)){
            //Exception schmeißen
        }

        else if(isVertical){
            // wenn y1 der Endpunkt (unterste Punkt) des Schiffes ist dann diese Rechnung:
            endY = y1 - shipSize;

            for(int i = y1; i > endY; i--){
                if(boolField[i][x1]){
                    return false;
                }
            }

            // TODO: Vielleicht ein Attribut bei Feld um zu checken ob die Felder die das Schiff besetzen würde belegt / frei sind?
            //Check if ship would be outside of the field if placed
            if(endY <= fieldSize){
                return true;
            }
            else{
                return false;
            }
        }

        else if(!isVertical){
            // wenn x1 der Endpunkt (linkeste Punkt) des Schiffes ist dann diese Rechnung:
            endX = x1 + shipSize;

            for(int i = x1; i < boolField[y1].length; i++){
                if(boolField[y1][i]){
                    return false;
                }
            }

            // TODO: Vielleicht ein Attribut bei Feld um zu checken ob die Felder die das Schiff besetzen würde belegt / frei sind?
            //Check if ship would be outside of the field if placed
            if (endX <= fieldSize) {
                return true;
            } else {
                return false;
            }
        }

        // Muss rein sonst Fehlermeldung
        return false;
    }

    // nedim
    @Override
    public void placeShip(Ship shipToPlace, int x1, int y1) throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException {
        boolean isPlaced = shipToPlace.getPlaced();
        boolean isVertical = shipToPlace.getIsVertical();
        int shipSize = shipToPlace.getSize();
        int endX, endY;
        int fieldSize = Field.getSize();
        boolean[][] boolField = Field.getCarriesShip();

        if(isPlaced){
            throw new IllegalShipStateException("Ship is already placed");
        }

        else if(!isPlacementPossible(shipToPlace, x1, y1, isVertical)){
            throw new IllegalPositionException("Ship cannot be placed");
        }

        // set ship on field and change placed state to true
        else if(isPlacementPossible(shipToPlace, x1, y1, isVertical)){
            shipToPlace.setPlaced(true);
            shipToPlace.setFieldPosition(x1, y1);
            if(isVertical){
                endY = y1 - shipSize;
                for(int i = y1; i > endY; i--){
                    boolField[i][x1] = true;
                }
            }
            else if(!isVertical){
                endX = x1 + shipSize;
                for(int i = x1; i < boolField[y1].length; i++){
                    boolField[y1][i] = true;
                }
            }
        }

    }

    // nedim
    @Override
    public void unPlace(Ship shipToMove) throws IllegalArgumentException, IllegalGameStateException {
        shipToMove.setPlaced(false);
        Position position = shipToMove.getFieldPosition();
        int x = position.getX(), y = position.getY();
        int shipSize = shipToMove.getSize();
        int endX, endY;
        boolean isVertical = shipToMove.getIsVertical();
        boolean[][] boolField = Field.getCarriesShip();

        if(isVertical){
            endY = y - shipSize;
            for(int i = y; i > endY; i--){
                boolField[i][x] = false;
            }
        }
        else if(!isVertical){
            endX = x + shipSize;
            for(int i = x; i < boolField[y].length; i++){
                boolField[y][i] = false;
            }
        }
    }

    // nedim
    @Override
    public void rotateShip(Ship shipToRotate) throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException {
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

        // check if ship is vertical and can be placed horizontally
        else if(isVertical){
            // wenn am heck gedreht wird zu horizontal dann bleibt y und x ändert sich
            int xNew = xCurrent + shipToRotate.getSize();
            if(isPlacementPossible(shipToRotate, xNew, yCurrent, false)){
                // rotate the ship
                shipToRotate.setIsVertical(false);
                // place ship
                placeShip(shipToRotate, xNew, yCurrent);
            }
            else{
                throw new IllegalPositionException("Ship cannot be placed");
            }
        }

        else if(!isVertical){
            // wenn am heck gedreht wird zu vertikal dann bleibt x und y ändert sich
            int yNew = yCurrent + shipToRotate.getSize();
            if(isPlacementPossible(shipToRotate, xCurrent, yNew, true)){
                // rotate the ship
                shipToRotate.setPlaced(true);
                // place ship
                placeShip(shipToRotate, xCurrent, yNew);
            }
            else{
                throw new IllegalPositionException("Ship cannot be placed");
            }
        }
    }

    // nuri
    @Override
    public boolean bombPanel(Owner attacker,int x, int y) throws IllegalArgumentException, IllegalGameStateException {
        // get enemyShipField and check if a ship is on the position
        // Demo code muss noch verallgemeinert werden so dass man einfach enemy benutzen kann
        //computer.getcShipField().getCarriesShip()
        //Owner.getEnemyShipField.
        // mark attackers attackField at position hit / not hit

        // if yes return true if no return false
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
