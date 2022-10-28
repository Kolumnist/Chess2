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
  public void moveSouth() throws RoomFailedException {
    if (currentRoom.isSouthAssigned.equals(true)) {
      Door checkDoor = currentRoom.getSouthDoor();
      String message = checkDoor.open();
      if (checkDoor.checkIfLocked()) {
        System.out.println(message);
        check();
      } else {
        System.out.println(message);
        if (checkDoor.checkIfFake()) {
          System.out.println("The open door reveals a wall. You can not go south.");
          check();
        } else {
          currentRoom = currentRoom.toTheSouth;
          player.setCurrentRoomOfPlayer(currentRoom);
          check();
        }
      }
    } else {
      throw new RoomFailedException("No room found in the southern direction.");
    }
  }

  /**
   * Command the player to move to the room to the north.
   */
  public void moveNorth() throws RoomFailedException {
    if (currentRoom.isNorthAssigned.equals(true)) {
      Door checkDoor = currentRoom.getNorthDoor();
      String message = checkDoor.open();
      if (checkDoor.checkIfLocked()) {
        System.out.println(message);
        check();
      } else {
        System.out.println(message);
        currentRoom = currentRoom.toTheNorth;
        player.setCurrentRoomOfPlayer(currentRoom);
        check();
      }
    } else {
      throw new RoomFailedException("No room found in the northern direction.");
    }
  }

  /**
   * Command the player to move to the room to the west.
   */
  public void moveWest() throws RoomFailedException {
    if (currentRoom.isWestAssigned.equals(true)) {
      Door checkDoor = currentRoom.getWestDoor();
      String message = checkDoor.open();
      if (checkDoor.checkIfLocked()) {
        System.out.println(message);
        check();
      } else {
        System.out.println(message);
        currentRoom = currentRoom.toTheWest;
        player.setCurrentRoomOfPlayer(currentRoom);
        check();
      }
    } else {
      throw new RoomFailedException("No room found in the western direction.");
    }
  }

  /**
   * Command the player to move to the room to the east.
   */
  public void moveEast() throws RoomFailedException {
    if (currentRoom.isEastAssigned.equals(true)) {
      Door checkDoor = currentRoom.getEastDoor();
      String message = checkDoor.open();
      if (checkDoor.checkIfLocked()) {
        System.out.println(message);
        check();
      } else {
        System.out.println(message);
        currentRoom = currentRoom.toTheEast;
        player.setCurrentRoomOfPlayer(currentRoom);
        check();
      }
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
  public void inspect(Direction direction) throws IllegalArgumentException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }
    boolean succeeded = false;

    if (direction == Direction.NORTH) {
      currentRoom.getNorthDoor().getInspectMessage();
      succeeded = true;
    }
    if (direction == Direction.WEST) {
      currentRoom.getWestDoor().getInspectMessage();
      succeeded = true;
    }
    if (direction == Direction.EAST) {
      currentRoom.getEastDoor().getInspectMessage();
      succeeded = true;
    }
    if (direction == Direction.SOUTH) {
      currentRoom.getSouthDoor().getInspectMessage();
      succeeded = true;
    }

    if (!succeeded) {
      throw new IllegalArgumentException("Direction was not valid.");
    }
  }

  /**
   * Checkt which door is the next to interact with.
   *
   * @param direction gets the direction to the next door.
   * @param item gets item if need
   * @throws IllegalArgumentException direction not null.
   */
  public void interaction(Direction direction, Item item) throws IllegalArgumentException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }
    if (item == null) {
      throw new IllegalArgumentException("Item should not be null.");
    }
    boolean succeeded = false;

    if (direction == Direction.NORTH) {
      boolean unlocked = currentRoom.getNorthDoor().unlock(item);
      succeeded = true;
      Door door = currentRoom.getNorthDoor();
      if (unlocked) {
        String successMessage = door.getPuzzle().getUnlockMessage();
        System.out.println(successMessage);
      } else {
        String lockedMessage = door.getPuzzle().getLockedMessage();
        System.out.println(lockedMessage);
      }
    }
    if (direction == Direction.WEST) {
      boolean unlocked = currentRoom.getWestDoor().unlock(item);
      succeeded = true;
      Door door = currentRoom.getWestDoor();
      if (unlocked) {
        String successMessage = door.getPuzzle().getUnlockMessage();
        System.out.println(successMessage);
      } else {
        String lockedMessage = door.getPuzzle().getLockedMessage();
        System.out.println(lockedMessage);
      }
    }
    if (direction == Direction.EAST) {
      boolean unlocked = currentRoom.getEastDoor().unlock(item);
      succeeded = true;
      Door door = currentRoom.getEastDoor();
      if (unlocked) {
        String successMessage = door.getPuzzle().getUnlockMessage();
        System.out.println(successMessage);
      } else {
        String lockedMessage = door.getPuzzle().getLockedMessage();
        System.out.println(lockedMessage);
      }
    }
    if (direction == Direction.SOUTH) {
      boolean unlocked = currentRoom.getSouthDoor().unlock(item);
      succeeded = true;
      Door door = currentRoom.getSouthDoor();
      if (unlocked) {
        String successMessage = door.getPuzzle().getUnlockMessage();
        System.out.println(successMessage);
      } else {
        String lockedMessage = door.getPuzzle().getLockedMessage();
        System.out.println(lockedMessage);
      }
    }

    if (!succeeded) {
      throw new IllegalArgumentException("Direction was not valid.");
    }
  }

  /**
   * Gets the next Room.
   *
   * @throws RoomFailedException description to room
   *
   */
  public void searchRoom() throws RoomFailedException {
    List<Item> items = new ArrayList<>();

    try {
      items = itemSearcher();
    } catch (NullPointerException n) {
      throw new RoomFailedException(n.getMessage());
    }

    if (items.size() == 1) {
      if (items.get(0).getItemId() == 10077001) {
        System.out.println("There are no items in this room.");
      } else {
        System.out.println("In this room, you find: ");
        for (Item item : items) {
          System.out.println(item.getName());
        }

      }
    }

  }

  /**
   * Gets Item for Player.
   *
   * @param item gets an item
   * @return gives item to player
   * @throws NoSuchItemFoundException itom not found
   * @throws NullPointerException item cant be null
   */
  public Item pickUpItem(Item item) throws NoSuchItemFoundException,
            NullPointerException {

    List<Item> items = new ArrayList<>();

    if (item == null) {
      throw new NullPointerException("Item cannot be null.");
    }

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
   * Checks the game for its current status.
   * Prints out what is currently going on.
   */
  public void check() {
    System.out.println("You are " + player.getName());
    System.out.println("You are in " + currentRoom.getDescription());
    System.out.println("You are alone.");
    System.out.println("You can search the room or move on.");
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
