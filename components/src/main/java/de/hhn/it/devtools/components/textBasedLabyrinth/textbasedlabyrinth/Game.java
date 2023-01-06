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
  public OutputListener outputListener;
  public ArrayList<Layout> layouts;
  private ArrayList<OutputListener> listeners;
  private ArrayList<Map> allMaps;
  public Map map;


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
  }


  /**
   * This method deals with the player moving from room to room.
   * @param direction the direction in which the player is moving.
   */
  public void move(Direction direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }
    boolean stop = false;

    Door checkDoor = null;
    try {
      checkDoor = currentRoom.getDoor(direction);
    } catch (RoomFailedException e) {
      stop = true;
      outputListener.sendOutputNavigation("There is no way in that direction.");
    }

    if (!stop) {
      String message = checkDoor.open();
      outputListener.sendOutputPlayer(message);
      if (!checkDoor.checkIfLocked()) {
        if (checkDoor.checkIfFake()) {
          String fake = "The door reveals no path, but a wall. You cannot move in this direction.";
          outputListener.sendOutputNavigation(fake);
        } else {
          try {
            currentRoom = currentRoom.getRoom(direction);
          } catch (RoomFailedException e) {
            stop = true;
            outputListener.sendOutputNavigation("There is no room in that direction.");
          }

        }
      }

    }

    if (!stop) {
      player.setCurrentRoomOfPlayer(currentRoom);
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
    outputListener.sendOutputNavigation(message);
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
    outputListener.sendOutputPlayerInteract(successMessage);
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

    if (items.size() == 1) {
      if (items.get(0).getItemId() == 10077001) {
        items = null;
      }
    }
    return items;

  }

  @Override
  public void setLayout(Layout layout) {
    this.currentLayout = layout;
  }

  /**
   * Gets Item for player.
   *
   * @param itemId gets an item
   * @return gives item to player
   * @throws NoSuchItemFoundException if item not found.
   * @throws NullPointerException if item cant be null.
   */
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
      outputListener.sendOutputPlayer(searchedItem.getName());
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
    player.removeItem(itemId);
    String message = "You lay the item carefully on the ground.";
    outputListener.sendOutputPlayer(message);
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
    outputListener.sendOutputInventory(message);
  }

  /**
   *
   * @param name the new name.
   * @return
   */
  @Override
  public boolean setPlayerName(String name) {
    boolean success = true;
    if (name.isEmpty() || name.isBlank()) {
      success = false;
    }

    if (success) {
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
    outputListener.sendOutputRoom(message);
    return message;
  }

  @Override
  public int getScore() {
    return SCORE_BOARD;
  }

  @Override
  public int updateScore(int newScore) {
    return SCORE_BOARD;
  }

  @Override
  public void addListener(OutputListener listener) {

  }

  @Override
  public void removeListener(OutputListener listener) {

  }

  /**
   * Text to be given to the player at the start of the game.
   */
  public String startText() {
    String message = "You are " + player.getName() + " and you are in the depths of a labyrinth.";
    outputListener.sendOutputRoom(message);
    return message;
  }

  /**
   *
   * @return
   */
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
}
