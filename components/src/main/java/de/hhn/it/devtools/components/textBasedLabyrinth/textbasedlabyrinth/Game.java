package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;


import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.NoSuchItemFoundException;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.RoomFailedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Game class.
 */
public class Game implements GameService {

  public Room currentRoom;
  public Player player;
  public Layout currentLayout;
  public OutputListener outputListener;
  public ArrayList<Layout> layouts;
  public Map map;


  /**
   * Constructor for game.
   */
  public Game(OutputListener listener) {
    this.player = new Player("Placeholder");
    outputListener = listener;
  }


  public void move(Direction direction) throws RoomFailedException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }

    Door checkDoor = null;
    String message = "";
    try {
      checkDoor = currentRoom.getDoor(direction);
    } catch (RoomFailedException e) {
      throw new RoomFailedException(e.getMessage());
    }
    message = checkDoor.open();
    if (!checkDoor.checkIfLocked()) {
      currentRoom = currentRoom.getRoom(direction);
      player.setCurrentRoomOfPlayer(currentRoom);
    }
    outputListener.sendOutputPlayer(message);
  }

  /**
   * Inspects the room for doors.
   *
   * @param direction gets doors in all directions.
   * @throws IllegalArgumentException direction should not be null.
   */
  public String inspect(Direction direction) throws IllegalArgumentException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }
    String message = "";
    try {
      message = currentRoom.getDoor(direction).getInspectMessage();
    } catch (RoomFailedException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
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
  public String interaction(Direction direction, Item item) throws IllegalArgumentException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }
    if (item == null) {
      throw new IllegalArgumentException("Item should not be null.");
    }

    String successMessage = "";
    boolean unlocked = false;
    Door door = null;

    try {
      unlocked = currentRoom.getDoor(direction).unlock(item);
    } catch (RoomFailedException e) {
      throw new IllegalArgumentException(e.getMessage());
    } try {
      door = currentRoom.getDoor(direction);
    } catch (RoomFailedException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
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

    try {
      items = itemSearcher();
    } catch (NullPointerException n) {
      throw new RoomFailedException(n.getMessage());
    }

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


    try {
      items = itemSearcher();
    } catch (NullPointerException n) {
      throw new NoSuchItemFoundException(n.getMessage());
    }
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
  public String dropItem(int itemId) {
    String message = "";
    try {
      player.removeItem(itemId);
      message = "You lay the item carefully on the ground.";
    } catch (NoSuchItemFoundException n) {
      message = n.getMessage();
    }
    outputListener.sendOutputPlayer(message);
    return message;
  }


  @Override
  public void inspectItemInInventoryOfPlayer(int itemId) throws NoSuchItemFoundException {
    String message = null;

    try {
      message = player.getItem(itemId).getInfo();
    } catch (NoSuchItemFoundException i) {
      throw new NoSuchItemFoundException(i.getMessage());
    }

    outputListener.sendOutputInventory(message);
  }

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

  /**
   * Text to be given to the player at the start of the game.
   */
  public String startText() {
    String message = "You are " + player.getName()
            + " and you are in the depths of a labyrinth.";
    outputListener.sendOutputRoom(message);
    return message;
  }


  private List<Item> itemSearcher() throws NullPointerException {
    List<Item> items = new ArrayList<>();

    try {
      items = currentRoom.search();
    } catch (NullPointerException n) {
      throw new NullPointerException(n.getMessage());
    }

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
