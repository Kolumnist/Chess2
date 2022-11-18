package de.hhn.it.devtools.apis.textbasedlabyrinth;


import de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions.NoSuchItemFoundException;
import de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions.RoomFailedException;
import java.util.ArrayList;
import java.util.List;

/**
 * Game class.
 */
public class Game implements GameService {

  public Room currentRoom;
  public Player player;
  public Layout layout;
  public Seed seed;

  /**
   * Constructor for game.
   */
  public Game(Seed seed) {
    this.seed = seed;
    this.player = new Player("Placeholder");
    this.layout = new Layout(player, seed);
    this.currentRoom = layout.startRoom;
  }

  /**
   * Command the player to move to the room to the south.
   */
  public String moveSouth() throws RoomFailedException {
    if (currentRoom.isSouthAssigned.equals(true)) {
      Door checkDoor = currentRoom.getSouthDoor();
      String message = checkDoor.open();
      if (!checkDoor.checkIfLocked()) {
        if (checkDoor.checkIfFake()) {
          //This is part of the demo for a fake door.
          message = message + "The open door reveals a wall. You can not go south.";
        } else {
          currentRoom = currentRoom.toTheSouth;
          player.setCurrentRoomOfPlayer(currentRoom);
        }
      }
      return message;

    } else {
      throw new RoomFailedException("No room found in the southern direction.");
    }
  }

  /**
   * Command the player to move to the room to the north.
   */
  public String moveNorth() throws RoomFailedException {
    if (currentRoom.isNorthAssigned.equals(true)) {
      Door checkDoor = currentRoom.getNorthDoor();
      String message = checkDoor.open();
      if (!checkDoor.checkIfLocked()) {
        currentRoom = currentRoom.toTheNorth;
        player.setCurrentRoomOfPlayer(currentRoom);
      }
      return message;
    } else {
      throw new RoomFailedException("No room found in the northern direction.");
    }
  }

  /**
   * Command the player to move to the room to the west.
   */
  public String moveWest() throws RoomFailedException {
    if (currentRoom.isWestAssigned.equals(true)) {
      Door checkDoor = currentRoom.getWestDoor();
      String message = checkDoor.open();
      if (!checkDoor.checkIfLocked()) {
        currentRoom = currentRoom.toTheWest;
        player.setCurrentRoomOfPlayer(currentRoom);
      }
      return message;
    } else {
      throw new RoomFailedException("No room found in the western direction.");
    }
  }

  /**
   * Command the player to move to the room to the east.
   */
  public String moveEast() throws RoomFailedException {
    if (currentRoom.isEastAssigned.equals(true)) {
      Door checkDoor = currentRoom.getEastDoor();
      String message = checkDoor.open();
      if (checkDoor.checkIfLocked()) {
        currentRoom = currentRoom.toTheEast;
        player.setCurrentRoomOfPlayer(currentRoom);
      }
      return message;
    } else {
      throw new RoomFailedException("No room found in the eastern direction.");
    }
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
    boolean succeeded = false;
    String message = "";

    if (direction == Direction.NORTH) {
      message = currentRoom.getNorthDoor().getInspectMessage();
      succeeded = true;
    }
    if (direction == Direction.WEST) {
      message = currentRoom.getWestDoor().getInspectMessage();
      succeeded = true;
    }
    if (direction == Direction.EAST) {
      message = currentRoom.getEastDoor().getInspectMessage();
      succeeded = true;
    }
    if (direction == Direction.SOUTH) {
      message = currentRoom.getSouthDoor().getInspectMessage();
      succeeded = true;
    }

    if (!succeeded) {
      throw new IllegalArgumentException("Direction was not valid.");
    }
    return message;
  }

  /**
   * Check which door is the next to interact with.
   *
   * @param direction gets the direction to the next door.
   * @param item gets item if need
   * @throws IllegalArgumentException direction not null.
   */
  public String interaction(Direction direction, Item item) throws IllegalArgumentException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }
    if (item == null) {
      throw new IllegalArgumentException("Item should not be null.");
    }
    boolean succeeded = false;
    String successMessage = "";

    if (direction == Direction.NORTH) {
      boolean unlocked = currentRoom.getNorthDoor().unlock(item);
      succeeded = true;
      Door door = currentRoom.getNorthDoor();
      if (unlocked) {
        successMessage = door.getPuzzle().getUnlockMessage();
      } else {
        successMessage = door.getPuzzle().getLockedMessage();

      }
    }
    if (direction == Direction.WEST) {
      boolean unlocked = currentRoom.getWestDoor().unlock(item);
      succeeded = true;
      Door door = currentRoom.getWestDoor();
      if (unlocked) {
        successMessage = door.getPuzzle().getUnlockMessage();
      } else {
        successMessage = door.getPuzzle().getLockedMessage();
      }
    }
    if (direction == Direction.EAST) {
      boolean unlocked = currentRoom.getEastDoor().unlock(item);
      succeeded = true;
      Door door = currentRoom.getEastDoor();
      if (unlocked) {
        successMessage = door.getPuzzle().getUnlockMessage();
      } else {
        successMessage = door.getPuzzle().getLockedMessage();
      }
    }
    if (direction == Direction.SOUTH) {
      boolean unlocked = currentRoom.getSouthDoor().unlock(item);
      succeeded = true;
      Door door = currentRoom.getSouthDoor();
      if (unlocked) {
        successMessage = door.getPuzzle().getUnlockMessage();
      } else {
        successMessage = door.getPuzzle().getLockedMessage();
      }
    }

    if (!succeeded) {
      throw new IllegalArgumentException("Direction was not valid.");
    }

    return successMessage;
  }

  /**
   * Gets the next room.
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

  /**
   * Gets Item for player.
   *
   * @param item gets an item
   * @return gives item to player
   * @throws NoSuchItemFoundException if item not found.
   * @throws NullPointerException if item cant be null.
   */
  public Item pickUpItem(Item item) throws NoSuchItemFoundException {

    List<Item> items = new ArrayList<>();


    try {
      items = itemSearcher();
    } catch (NullPointerException n) {
      throw new NoSuchItemFoundException(n.getMessage());
    }
    if (!items.contains(item)) {
      throw new NoSuchItemFoundException("The item " + item.getName() + " was not found.");
    } else {
      return item;
    }

  }


  /**
   * Method to remove an item from the player inventory.
   *
   * @param itemName the name of the item to be removed.
   * @return the message, which is about the success or failure of the operation.
   */
  public String removeItem(String itemName) {
    String message = "";
    try {
      player.removeItem(itemName);
      message = "You lay the item carefully on the ground.";
    } catch (NoSuchItemFoundException n) {
      message = n.getMessage();
    }
    return message;
  }



  /**
   * Message to be given to the player after (probably) every action
   * and every time the player moves between rooms.
   */
  public String check() {
    String message = "You find yourself in " + currentRoom.getDescription();
    message = message + ("You can search the room or move on.");
    return message;
  }

  /**
   * Text to be given to the player at the start of the game.
   */
  public String startText() {
    String message = "You are " + player.getName()
            + " and you are in the depths of a labyrinth.";
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
}
