package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;



import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.RoomFailedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Room class for the game, defines the rooms of the game
 * with assigned next-door rooms and items inside it.
 */
public class Room {


  protected HashMap<Integer, Item> items;
  private HashMap<Direction, Room> roomMap;
  protected int roomId;

  private String description;

  protected Room toTheNorth;
  protected Room toTheSouth;
  protected Room toTheEast;
  protected Room toTheWest;

  protected Boolean isNorthAssigned;
  protected Boolean isSouthAssigned;
  protected Boolean isEastAssigned;
  protected Boolean isWestAssigned;

  protected Door westDoor;
  protected Door eastDoor;
  protected Door northDoor;
  protected Door southDoor;
  private boolean hasDoorN;
  private boolean hasDoorW;
  private boolean hasDoorE;
  private boolean hasDoorS;

  private boolean setFakeNorth;
  private boolean setFakeSouth;
  private boolean setFakeWest;
  private boolean setFakeEast;

  protected boolean isExit;

  /**
   * Constructor of room.
   *
   * @param id ID of the room.
   * @param description the description of the rooms' appearance.
   */
  public Room(int id, String description) {
    this.roomId = id;
    this.isNorthAssigned = false;
    this.isSouthAssigned = false;
    this.isEastAssigned = false;
    this.isWestAssigned = false;
    this.hasDoorS = false;
    this.hasDoorE = false;
    this.hasDoorN = false;
    this.hasDoorW = false;

    items = new HashMap<>();
    this.description = description;
  }

  public void addItem(Item item) {
    items.put(item.getItemId(), item);

  }

  public Room getRoom(Direction direction) throws RoomFailedException {
    if(!roomMap.containsKey(direction)) {
      throw new RoomFailedException("No Room in this direction" + direction);
    }

    Room room = null;
    if(direction.equals(Direction.SOUTH)){
      room = toTheSouth;
    }
    else if(direction.equals(Direction.NORTH)){
      room = toTheNorth;
    }
    else if(direction.equals(Direction.EAST)){
      room = toTheEast;
    }
    else{
      room = toTheSouth;
    }
    return room;
  }

  /**
   * Gets the doors for the new room.
   */
  public void setDoors() {

    if (isSouthAssigned) {
      if (toTheSouth.hasSouthDoor()) {
        southDoor = toTheSouth.getSouthDoor();
      } else {
        southDoor = new Door();
      }
      hasDoorS = true;
    }
    if (isWestAssigned) {
      if (toTheWest.hasDoorWest()) {
        westDoor = toTheWest.getWestDoor();
      } else {
        westDoor = new Door();
      }
      hasDoorW = true;
    }
    if (isNorthAssigned) {
      if (toTheNorth.hasDoorNorth()) {
        northDoor = toTheNorth.getNorthDoor();
      } else {
        northDoor = new Door();
      }
      hasDoorN = true;
    }
    if (isEastAssigned) {
      if (toTheEast.hasDoorEast()) {
        eastDoor = toTheEast.getEastDoor();
      } else {
        eastDoor = new Door();
      }
      hasDoorE = true;
    }

  }


  protected void setFakeInDirection(Direction direction) throws IllegalArgumentException {
    if (direction.equals(Direction.SOUTH) && !isSouthAssigned) {
      southDoor = new Door();
      isSouthAssigned = true;
      southDoor.isFake();
    } else if (direction.equals(Direction.NORTH) && !isNorthAssigned) {
      northDoor = new Door();
      isNorthAssigned = true;
      northDoor.isFake();
    } else if (direction.equals(Direction.EAST) && !isEastAssigned) {
      eastDoor = new Door();
      isEastAssigned = true;
      eastDoor.isFake();
    } else if (direction.equals(Direction.WEST) && !isWestAssigned) {
      westDoor = new Door();
      isWestAssigned = true;
      westDoor.isFake();
    } else {
      throw new IllegalArgumentException("Direction was invalid for a fake door: " + direction.toString());
    }
  }

  public Door getDoor(Direction direction) throws RoomFailedException {
    if (direction.equals(Direction.SOUTH) && isSouthAssigned) {
      return southDoor;
    } else if (direction.equals(Direction.NORTH) && isNorthAssigned) {
      return northDoor;
    } else if (direction.equals(Direction.EAST) && isEastAssigned) {
      return eastDoor;
    } else if (direction.equals(Direction.WEST) && isWestAssigned) {
      return westDoor;
    } else {
      throw new RoomFailedException("No door found in this direction: " + direction.toString());
    }


  }

  public void removeItem(int itemId) {
    items.remove(itemId);
  }

  public int getRoomId() {
    return roomId;
  }

  public String getDescription() {
    return description;
  }

  public Door getWestDoor() {
    return westDoor;
  }

  public Door getEastDoor() {
    return eastDoor;
  }

  public Door getNorthDoor() {
    return northDoor;
  }

  public Door getSouthDoor() {
    return southDoor;
  }

  public boolean hasSouthDoor() {
    return hasDoorS;
  }

  public boolean hasDoorNorth() {
    return hasDoorN;
  }

  public boolean hasDoorWest() {
    return hasDoorW;
  }

  public boolean hasDoorEast() {
    return hasDoorE;
  }

  /**
   * Get number of doors.
   *
   * @return number of doors.
   */
  public int getNumberOfDoors() {
    int a = 0;
    if (hasDoorS) {
      a++;
    }
    if (hasDoorW) {
      a++;
    }
    if (hasDoorE) {
      a++;
    }
    if (hasDoorN) {
      a++;
    }
    return a;
  }



  /**
   * Searching for an item in the room.
   *
   * @return item
   * @throws NullPointerException room does not have items
   */
  public List<Item> search() throws NullPointerException {
    if (items == null) {
      throw new NullPointerException("Room could not hold items.");
    }
    boolean fail = false; // May need later.

    List<Item> items1 = items.values().stream().toList();

    if (items1.isEmpty()) {
      Item item = new Item(10077001, "NoItem", "opens door");
      ArrayList<Item> items2 = new ArrayList<>();
      items2.add(item);
      return items2;
    } else {
      return items1;
    }
  }

  /**
   * Set a room as the room next door for the current room and vis versa.
   *
   * @param room room to be assigned as the next door room
   * @param isEast boolean check to see if the new room will be to the east of current room
   * @param isWest boolean check to see if the new room will be to the west of current room
   * @param isNorth boolean check to see if the new room will be to the north of current room
   */
  public void setNextDoorRoom(Room room, Boolean isEast, Boolean isWest, Boolean isNorth) {
    if (isEast && !isWest && !isNorth && !isEastAssigned) {
      this.toTheEast = room;
      this.isEastAssigned = true;
      room.toTheWest = this;
      room.isWestAssigned = true;
    } else if (isWest && !isEast && !isNorth && !isWestAssigned) {
      this.toTheWest = room;
      this.isWestAssigned = true;
      room.toTheWest = this;
      room.isEastAssigned = true;
    } else if (isNorth && !isEast && !isWest && !isNorthAssigned) {
      this.toTheNorth = room;
      this.isNorthAssigned = true;
      room.toTheSouth = this;
      room.isSouthAssigned = true;
    } else if (!isNorth && !isEast && !isWest && !isSouthAssigned) {
      this.toTheSouth = room;
      this.isSouthAssigned = true;
      room.toTheNorth = this;
      room.isNorthAssigned = true;
    }
  }

  public void setExit(){

  }

}
