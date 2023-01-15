package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;


import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.NoSuchItemFoundException;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.RoomFailedException;
import java.util.ArrayList;
import java.util.List;

/**
 * Game class.
 */
public class Game implements GameService {


  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Game.class);


  public Room currentRoom;
  public Player player;
  public Layout currentLayout;
  public ArrayList<Layout> layouts;
  private ArrayList<OutputListener> listeners;
  private ArrayList<Map> allMaps;
  public Map map;
  private int score;



  public Game() {

  }


  /**
   * This method exists to initialize the player and the lists,
   * and other things.
   * This method should only be done once per launch.
   */
  public void startup() {
    this.player = new Player("Jones");
    layouts = new ArrayList<>();
    listeners = new ArrayList<>();
    allMaps = new ArrayList<>();
    logger.info("Game initialized.");
    score = 0;
  }


  public void start() {
    score = 0;
    for (OutputListener outputListener : listeners) {
      outputListener.listenerStart();
    }
    logger.info("Game started.");
  }

  public void end() {
    for (OutputListener outputListener : listeners) {
      outputListener.listenerEnd();
    }
  }

  public void reset() {
    player.reset();
    score = 0;
    for (OutputListener outputListener : listeners) {
      outputListener.listenerReset();
    }

    logger.info("Game reset.");

  }


  /**
   * This method deals with the player moving from room to room.
   * @param direction the direction in which the player is moving.
   */
  public void move(Direction direction) throws IllegalArgumentException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }
    boolean stop = false;

    Door checkDoor = null;
    try {
      checkDoor = currentRoom.getDoor(direction);
    } catch (RoomFailedException e) {
      stop = true;
      for (OutputListener outputListener : listeners) {
        outputListener.sendOutputNavigation("There is no way in that direction.");
      }
    }

    if (!stop) {
      String message = checkDoor.open();
      for (OutputListener outputListener : listeners) {
        outputListener.sendOutputPlayer(message);
      }
      if (!checkDoor.checkIfLocked()) {
        if (checkDoor.checkIfFake()) {
          String fake = "The door reveals no path, but a wall. You cannot move in this direction.";
          for (OutputListener outputListener : listeners) {
            outputListener.sendOutputNavigation(fake);
          }
        } else {
          try {
            currentRoom = currentRoom.getRoom(direction);
          } catch (RoomFailedException e) {
            stop = true;
            for (OutputListener outputListener : listeners) {
              outputListener.sendOutputNavigation("There is no room in that direction.");
            }
          }
        }
      }
    }
    if (!stop) {
      player.setCurrentRoomOfPlayer(currentRoom);
    }

    if(player.getCurrentRoomOfPlayer().isExit){
      //
    }
  }

  /**
   * Inspects the room for doors.
   *
   * @param direction gets doors in all directions.
   * @throws IllegalArgumentException direction should not be null.
   */
  public String inspect(Direction direction) throws RoomFailedException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }

    String message = currentRoom.getDoor(direction).getInspectMessage();
    for (OutputListener outputListener : listeners) {
      outputListener.sendOutputNavigation(message);
    }
    return message;
  }

  /**
   * Player tries to interact with a door/direction.
   *
   * @param direction gets the direction to the next door.
   * @param item item player uses.
   * @throws IllegalArgumentException direction not null.
   */
  public String interaction(Direction direction, Item item) throws IllegalArgumentException, RoomFailedException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }
    if (item == null) {
      throw new IllegalArgumentException("Item should not be null.");
    }

    String successMessage;
    boolean unlocked = currentRoom.getDoor(direction).unlock(item);
    Door door = currentRoom.getDoor(direction);
    if (unlocked) {
      successMessage = door.getPuzzle().getUnlockMessage();
    } else {
      successMessage = door.getPuzzle().getLockedMessage();
    }
    for (OutputListener outputListener : listeners) {
      outputListener.sendOutputPlayerInteract(successMessage);
    }
    return successMessage;
  }

  /**
   * Gets the next room.
   * It gives a list. Whether this list will be given to the main field in the ui or appear as a popup
   * is not decided yet.
   *
   * @throws RoomFailedException description to room
   *
   */
  public List<Item> searchRoom() throws RoomFailedException {
    List<Item> items = new ArrayList<>();
    items = itemSearcher();
    return items;
  }

  /**
   *
   * @param layout the layout to be assigned.
   */
  @Override
  public void setLayout(Layout layout) {
    this.currentLayout = layout;
  }

  /**
   * Gets item for player.
   *
   * @param itemId gets an item.
   * @return the item that was picked up.
   * @throws NoSuchItemFoundException if item not found.
   * */
  public Item pickUpItem(int itemId) throws NoSuchItemFoundException {

    List<Item> items = new ArrayList<>();

    items = itemSearcher();
    Item searchedItem = null;
    for (Item item : items) {
      if (item.getItemId() == itemId) {
        searchedItem = item;
      }
    }

    if (searchedItem == null) {
      throw new NoSuchItemFoundException("The item was not found.");
    } else {
      player.addItem(searchedItem);
      player.getCurrentRoomOfPlayer().removeItem(searchedItem.getItemId());
      if (searchedItem.getIsTreasure()) {
        score = score + ((Treasure) searchedItem).getScorePoint();
      }

      for (OutputListener outputListener : listeners) {
        outputListener.sendOutputPlayer(searchedItem.getName());
        outputListener.updateScore(score);
      }
      return searchedItem;
    }
  }

  /**
   * Method to remove an item from the player inventory.
   *
   * @param itemId the id of the item to be removed.
   * @return the message, which is about the success or failure of the operation.
   */
  public String dropItem(int itemId) throws NoSuchItemFoundException {
    Item droppedItem = player.removeItem(itemId);
    String message = "You lay the item carefully on the ground.";

    if (droppedItem.getIsTreasure()) {
      score = score - ((Treasure) droppedItem).getScorePoint();
    }
    if (score < 0) {
      score = 0;
    }
    for (OutputListener outputListener : listeners) {
      outputListener.sendOutputPlayer(message);
      outputListener.updateScore(score);
    }
    return message;
  }

  /**
   *
   * @param itemId
   * @throws NoSuchItemFoundException
   */
  @Override
  public void inspectItemInInventoryOfPlayer(int itemId) throws NoSuchItemFoundException {
    String message = player.getItem(itemId).getInfo();
    for (OutputListener outputListener : listeners) {
      outputListener.sendOutputInventory(message);
    }
  }

  /**
   * Setter for Player name
   * @param name the new name.
   * @return if the new name was accepted.
   */
  @Override
  public boolean setPlayerName(String name) {
    boolean success = !name.isEmpty() && !name.isBlank();

    if(success) {
      player.setName(name);
    }
    return success;
  }

  /**
   * Message to be given to the player after (probably) every action
   * and every time the player moves between rooms.
   */
  public String check() {
    String message = "You find yourself in " + currentRoom.getDescription();
    message = message + ("You can search the room or move on.");
    for (OutputListener outputListener : listeners) {
      outputListener.sendOutputRoom(message);
    }
    return message;
  }



  @Override
  public void addListener(OutputListener listener) {
    listeners.add(listener);
  }



  @Override
  public void removeListener(OutputListener listener) {
    listeners.remove(listener);
  }

  /**
   * Text to be given to the player at the start of the game.
   */
  public String startText() {
    String message = "You are " + player.getName() + " and you are in the depths of a labyrinth.";
    for (OutputListener outputListener : listeners) {
      outputListener.sendOutputRoom(message);
    }
    return message;
  }



  private List<Item> itemSearcher() {
    List<Item> items = new ArrayList<>();
    items = currentRoom.search();
    return items;
  }

  /**
   * Setter for current Layout
   * @param newMap Map to be selected
   * @param newSeed Seed for the Map
   */
  public void setCurrentLayout(Map newMap, Seed newSeed){
    this.currentLayout = new Layout(player);
  }



  public String getPlayerName() {
    return player.getName();
  }

  @Override
  public Player getPlayer() {
    return player;
  }
}
