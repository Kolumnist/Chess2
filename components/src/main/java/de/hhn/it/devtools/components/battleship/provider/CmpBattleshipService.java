package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.BattleshipListener;
import de.hhn.it.devtools.apis.battleship.BattleshipService;
import de.hhn.it.devtools.apis.battleship.Field;
import de.hhn.it.devtools.apis.battleship.GameState;
import de.hhn.it.devtools.apis.battleship.IllegalGameStateException;
import de.hhn.it.devtools.apis.battleship.IllegalPositionException;
import de.hhn.it.devtools.apis.battleship.IllegalShipStateException;
import de.hhn.it.devtools.apis.battleship.PanelState;
import de.hhn.it.devtools.apis.battleship.Player;
import de.hhn.it.devtools.apis.battleship.Position;
import de.hhn.it.devtools.apis.battleship.SavedGame;
import de.hhn.it.devtools.apis.battleship.Ship;
import de.hhn.it.devtools.apis.battleship.ShipType;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.IllegalFormatException;

public class CmpBattleshipService implements BattleshipService {

  //
  //public static CmpBattleshipService service = new CmpBattleshipService();


  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CmpBattleshipService.class);
  static GameState currentGameState = GameState.PREGAME;
  private final Player player = new Player();
  private final Computer computer = new Computer(this);
  int gameVolume;
  private final ArrayList<BattleshipListener> listeners;

  public CmpBattleshipService() {
    listeners = new ArrayList<>();
  }

  public Player getPlayer() {
    return player;
  }

  public Computer getComputer() {
    return computer;
  }

  public GameState getCurrentGameState() {
    return currentGameState;
  }

  public void setCurrentGameState(GameState state) {
    logger.info("setCurrentGameState: state = {}", state);
    currentGameState = state;
  }

  public ArrayList<BattleshipListener> getListeners() {
    return listeners;
  }

  public int getGameVolume() {
    return gameVolume;
  }


  // nuri
  @Override
  public void addCallBack(BattleshipListener listener) throws IllegalParameterException {
    logger.info("addCallBack: listener = {}", listener);
    if (listener == null) {
      throw new IllegalParameterException("Listener was null");
    }

    if (listeners.contains(listener)) {
      throw new IllegalParameterException("Listener already registered");
    }

    listeners.add(listener);
  }

  // nuri
  @Override
  public void removeCallback(BattleshipListener listener) throws IllegalParameterException {
    logger.info("removeCallback: listener = {}", listener);

    if (listener == null) {
      throw new IllegalParameterException("Listener was null");
    }

    if (listeners.contains(listener)) {
      listeners.remove(listener);
      return;
    }

    throw new IllegalParameterException("Listener was not added!");
  }

  // nedim
  @Override
  public boolean isPlacementPossible(Player player, Ship shipToPlace, int x1, int y1,
      boolean isVertical) throws IllegalGameStateException {
    logger.info(
        "isPlacementPossible: player = {}, ship = {}, x-value = {}, y-value = {}, isVertical = {}",
        player, shipToPlace, x1, y1, isVertical);
    // if x1 is the endpoint (leftmost point) of the ship then this calculation:
    int endX = (x1 + shipToPlace.getSize()) - 1;
    // if y1 is the end point (top point) of the ship then this calculation:
    int endY = (y1 + shipToPlace.getSize()) - 1;

    // Check if coordinates of ship is outside of field
    if ((x1 < 0) || (y1 < 0) || (x1 >= Field.getSize()) || (y1 >= Field.getSize()) || (
        endX >= Field.getSize() && !isVertical) || (endY >= Field.getSize() && isVertical)) {
      return false;
    } else if (currentGameState != GameState.PLACINGSHIPS) {
      throw new IllegalGameStateException("Wrong GameState! Required GameState is PlacingShips");
    }

    if (isVertical) {
      return checkShipOnPanel(player, true, y1, endY, x1);
    } else {
      return checkShipOnPanel(player, false, x1, endX, y1);
    }
  }

  /**
   * @param isVertical            true if to placing ship is vertical, false if horizontal
   * @param startCoordinate       one of the start coordinates where the ship should be placed
   * @param endCoordinate         End coordinate of the ship to be placed
   * @param secondStartCoordinate second of the start coordinates where the ship should be placed
   *                              (if startCoordinate is x then secondStartCoordinate is y and the
   *                              other way around)
   * @return true if ship can be placed, false if not
   */
  public boolean checkShipOnPanel(Player player, boolean isVertical, int startCoordinate,
      int endCoordinate, int secondStartCoordinate) {
    for (int i = startCoordinate; i <= endCoordinate; i++) {
      if (isVertical) {
        if (player.getShipField().getPanelMarkerMat()[i][secondStartCoordinate]
            == PanelState.SHIP) {
          return false;
        }
      } else {
        if (player.getShipField().getPanelMarkerMat()[secondStartCoordinate][i]
            == PanelState.SHIP) {
          return false;
        }
      }
    }
    return endCoordinate < Field.getSize();
  }

  // nedim
  @Override
  public void placeShip(Player player, Ship shipToPlace, int x1, int y1)
      throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException, IllegalArgumentException {
    logger.info("placeShip: player = {}, ship = {}, x-value = {}, y-value = {}, ", player,
        shipToPlace, x1, y1);
    boolean isPlaced = shipToPlace.getPlaced();
    boolean isVertical = shipToPlace.getIsVertical();
    int endX = (x1 + shipToPlace.getSize()) - 1;
    int endY = (y1 + shipToPlace.getSize()) - 1;

    if (isPlaced) {
      throw new IllegalShipStateException("Ship is already placed");
    } else if (currentGameState != GameState.PLACINGSHIPS) {
      throw new IllegalGameStateException("Wrong GameState! Required GameState is PlacingShips");
    } else if (!isPlacementPossible(player, shipToPlace, x1, y1, isVertical)) {
      throw new IllegalPositionException("Ship cannot be placed");
    } else if (isPlacementPossible(player, shipToPlace, x1, y1, isVertical)) {
      shipToPlace.setPlaced(true);
      shipToPlace.setFieldPosition(x1, y1);
      if (isVertical) {
        for (int i = y1; i <= endY; i++) {
          player.getShipField().setPanelMarker(x1, i, PanelState.SHIP);
          player.getShipField().setShipsOnField(shipToPlace, x1, i);

        }
      } else {
        for (int i = x1; i <= endX; i++) {
          player.getShipField().setPanelMarker(i, y1, PanelState.SHIP);
          player.getShipField().setShipsOnField(shipToPlace, i, y1);

        }
      }
    }
    for (BattleshipListener listener : listeners) {
      listener.updateField();
      if (player.equals(this.player)) {
        listener.resetShipSelected();
        listener.updateUnplacedShips(player);
      }
      if (!this.player.hasUnplacedShipsLeft()) {
        listener.allShipsPlaced();
      }
    }

  }

  // nedim
  @Override
  public void unPlace(Player player, Ship shipToMove)
      throws IllegalArgumentException, IllegalGameStateException {
    logger.info("unPlace: player = {}, ship = {}", player, shipToMove);
    shipToMove.setPlaced(false);
    Position position = shipToMove.getFieldPosition();
    int x = position.getX(), y = position.getY();
    int shipSize = shipToMove.getSize();
    // if x1 is the endpoint (leftmost point) of the ship then this calculation:
    int endX = (x + shipSize) - 1;
    // if y1 is the end point (top point) of the ship then this calculation:
    int endY = (y + shipSize) - 1;
    boolean isVertical = shipToMove.getIsVertical();
    Field shipField;

    if (currentGameState != GameState.PLACINGSHIPS) {
      throw new IllegalGameStateException("Wrong GameState! Required GameState is PlacingShips");
    }
    shipField = player.getShipField();
    if (isVertical) {
      for (int i = y; i <= endY; i++) {
        shipField.setPanelMarker(x, i, PanelState.NOSHIP);
        player.getShipField().setShipsOnField(null, x, i);


      }
    } else {
      for (int i = x; i <= endX; i++) {
        shipField.setPanelMarker(i, y, PanelState.NOSHIP);
        player.getShipField().setShipsOnField(null, i, y);
      }
    }

    for (BattleshipListener listener : listeners) {
      listener.updateField();
      listener.updateFiringShotsButton();

      if (player.equals(this.player)) {
        listener.updateUnplacedShips(player);
      }

    }
  }

  // nedim
  @Override
  public void rotateShip(Player player, Ship shipToRotate)
      throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException, IllegalArgumentException {
    // if ship is vertical, then x value is equal but y between front and rear is different,
    // if ship is horizontal, then y value is equal but x between front and rear is different
    logger.info("rotateShip: player = {}, ship = {}", player, shipToRotate);

    boolean isVertical = shipToRotate.getIsVertical();
    boolean isPlaced = shipToRotate.getPlaced();

    if (isPlaced) {
      throw new IllegalShipStateException("Ship is already placed");
    } else if (currentGameState != GameState.PLACINGSHIPS) {
      throw new IllegalGameStateException("Wrong GameState! Required GameState is PlacingShips");
    }

    // check if ship is vertical and can be placed horizontally
    shipToRotate.setIsVertical(!isVertical);
  }

  // nuri
  @Override
  public boolean bombPanel(Player attacker, Player target, int x, int y)
      throws IllegalArgumentException, IllegalGameStateException {
    logger.info("bombPanel: attacker = {}, attacked = {}, x = {}, y = {}", attacker, target, x, y);
    if (currentGameState != GameState.FIRINGSHOTS) {
      throw new IllegalGameStateException("Wrong GameState! Required GameState is FiringShots");
    }
    if ((x < 0) || (y < 0) || (x >= Field.getSize()) || (y >= Field.getSize())) {
      throw new IllegalArgumentException();
    }
    PanelState isShipOnPosition;
    isShipOnPosition = target.getShipField().getPanelMarker(x, y);
    if (isShipOnPosition == PanelState.SHIP) {
      // set ship part on position to bombed
      target.getShipField().setPanelMarker(x, y, PanelState.HIT);
      attacker.getAttackField().setPanelMarker(x, y, PanelState.HIT);

      for (BattleshipListener listener : listeners) {
        listener.updateField();
        listener.outputWinner(attacker);
      }

      return true;
    } else {
      //set position to bombed (not necessary hit)
      target.getShipField().setPanelMarker(x, y, PanelState.MISSED);
      attacker.getAttackField().setPanelMarker(x, y, PanelState.MISSED);

      for (BattleshipListener listener : listeners) {
        listener.updateField();
        listener.outputWinner(attacker);
      }

      return false;
    }
  }

  public boolean checkWon(Player winner) {
    int cnt = 0;
    Player checkEnemiesField;
    if (winner.equals(computer)) {
      checkEnemiesField = player;
    } else {
      checkEnemiesField = computer;
    }
    for (int i = 0; i < Field.getSize(); i++) {
      for (int j = 0; j < Field.getSize(); j++) {
        if (checkEnemiesField.getShipField().getPanelMarker(i, j) == PanelState.SHIP) {
          cnt++;
        }
      }
    }
    if (cnt > 0) {
      return false;
    } else {
      logger.info("{} WON", winner);
      setCurrentGameState(GameState.GAMEOVER);

      for (BattleshipListener listener : listeners) {
        listener.updateField();
      }

      return true;
    }
  }

  // nuri
  @Override
  public void createFields(int size)
      throws IllegalArgumentException, IllegalGameStateException, IllegalShipStateException, IllegalPositionException {
    logger.info("createFields: size = {}", size);

    if (currentGameState != GameState.PREGAME) {
      throw new IllegalGameStateException("Wrong GameState! Required GameState is PreGame");
    }

    player.setShipField(new Field(size, player));
    player.setAttackField(new Field(size, player));

    computer.setShipField(new Field(size, computer));
    computer.setAttackField(new Field(size, computer));

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        player.getShipField().setPanelMarker(i, j, PanelState.NOSHIP);
        player.getAttackField().setPanelMarker(i, j, PanelState.NOSHIP);

        computer.getShipField().setPanelMarker(i, j, PanelState.NOSHIP);
        computer.getAttackField().setPanelMarker(i, j, PanelState.NOSHIP);
      }
    }

    if (size == 5) {
      player.setOwnedShips(new Ship(ShipType.DESTROYER, null));
      player.setOwnedShips(new Ship(ShipType.DESTROYER, null));

      computer.setOwnedShips(new Ship(ShipType.DESTROYER, null));
      computer.setOwnedShips(new Ship(ShipType.DESTROYER, null));
    } else if (size == 10) {
      player.setOwnedShips(new Ship(ShipType.CARRIER, null));
      player.setOwnedShips(new Ship(ShipType.BATTLESHIP, null));
      player.setOwnedShips(new Ship(ShipType.CRUISER, null));
      player.setOwnedShips(new Ship(ShipType.SUBMARINE, null));
      player.setOwnedShips(new Ship(ShipType.DESTROYER, null));

      computer.setOwnedShips(new Ship(ShipType.CARRIER, null));
      computer.setOwnedShips(new Ship(ShipType.BATTLESHIP, null));
      computer.setOwnedShips(new Ship(ShipType.CRUISER, null));
      computer.setOwnedShips(new Ship(ShipType.SUBMARINE, null));
      computer.setOwnedShips(new Ship(ShipType.DESTROYER, null));
    } else if (size == 15) {
      player.setOwnedShips(new Ship(ShipType.CARRIER, null));
      player.setOwnedShips(new Ship(ShipType.CARRIER, null));
      player.setOwnedShips(new Ship(ShipType.BATTLESHIP, null));
      player.setOwnedShips(new Ship(ShipType.BATTLESHIP, null));
      player.setOwnedShips(new Ship(ShipType.CRUISER, null));
      player.setOwnedShips(new Ship(ShipType.CRUISER, null));
      player.setOwnedShips(new Ship(ShipType.SUBMARINE, null));
      player.setOwnedShips(new Ship(ShipType.SUBMARINE, null));
      player.setOwnedShips(new Ship(ShipType.DESTROYER, null));
      player.setOwnedShips(new Ship(ShipType.DESTROYER, null));
      player.setOwnedShips(new Ship(ShipType.DESTROYER, null));

      computer.setOwnedShips(new Ship(ShipType.CARRIER, null));
      computer.setOwnedShips(new Ship(ShipType.CARRIER, null));
      computer.setOwnedShips(new Ship(ShipType.BATTLESHIP, null));
      computer.setOwnedShips(new Ship(ShipType.BATTLESHIP, null));
      computer.setOwnedShips(new Ship(ShipType.CRUISER, null));
      computer.setOwnedShips(new Ship(ShipType.CRUISER, null));
      computer.setOwnedShips(new Ship(ShipType.SUBMARINE, null));
      computer.setOwnedShips(new Ship(ShipType.SUBMARINE, null));
      computer.setOwnedShips(new Ship(ShipType.DESTROYER, null));
      computer.setOwnedShips(new Ship(ShipType.DESTROYER, null));
      computer.setOwnedShips(new Ship(ShipType.DESTROYER, null));
    }

    currentGameState = GameState.PLACINGSHIPS;

    computer.comShipPlacement(size);
  }

  // nuri
  @Override
  public void adjustSoundVolume(int newVolume) throws IllegalArgumentException {
    logger.info("adjustSoundVolume: newVolume = {}", newVolume);
    if (newVolume < 0) {
      throw new IllegalArgumentException("Volume can't be negative");
    }
    gameVolume = newVolume;
  }

  // moutassem
  @Override
  public SavedGame saveGame() throws IllegalGameStateException {
    if (currentGameState == GameState.GAMEOVER || currentGameState == GameState.PREGAME) {
      throw new IllegalGameStateException("You can not save when the game is not going");
    }
    //SavedGame create object
    SavedGame saveData = new SavedGame();
    //TODO: Enter name of the file from the player
    String fileName = "X.ser";

    //Serialization
    try {
      //Streams to store the object
      FileOutputStream file = new FileOutputStream(fileName);
      ObjectOutputStream out = new ObjectOutputStream(file);

      //Serialization method
      out.writeObject(saveData);

      out.close();
      file.close();
    } catch (IOException ex) {
      System.out.println("IOException is caught");
    }

    return null;
  }

  // moutassem
  @Override
  public void loadGame(SavedGame savedGame) throws IllegalFormatException {
    logger.info("loadGame: savedGame = {}", savedGame);

    //TODO: Enter the name of the file from the playerName of the selected file via File Chooser
    Object saveFile = new Object();
    //Deserialization
    try {
      //Streams for reading the file
      FileInputStream file = new FileInputStream("saveFile");
      ObjectInputStream in = new ObjectInputStream(file);

      //Method for deserialization of the object
      savedGame = (SavedGame) in.readObject();

      in.close();
      file.close();
    } catch (IOException ex) {
      System.out.println("IOException is caught");
    } catch (ClassNotFoundException ex) {
      System.out.println("ClassNotFoundException is caught");
    }
  }

  // moutassem
  @Override
  public void concede() throws IllegalGameStateException {
    if (currentGameState == GameState.PREGAME || currentGameState == GameState.GAMEOVER) {
      throw new IllegalGameStateException("You can not give up when the game is not going");
    }
    if (currentGameState == GameState.PLACINGSHIPS || currentGameState == GameState.FIRINGSHOTS) {
      currentGameState = GameState.GAMEOVER;
    }
  }

  // moutassem
  @Override
  public String displayRules() {
    //TODO: needs first UI
    //Show rule window
    return null;
  }

}
