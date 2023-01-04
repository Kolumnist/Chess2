package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;



import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.RoomFailedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Room class for the game, defines the rooms of the game
 * with assigned next-door rooms and items inside it.
 */
public class Room {


  protected HashMap<Integer, Item> items;
  private HashMap<Direction, Room> roomMap;
  private HashMap<Direction, Door> doorMap;
  protected int roomId;

  private final String description;

  // protected Room toTheNorth;
  // protected Room toTheSouth;
  // protected Room toTheEast;
  // protected Room toTheWest;

  // protected Boolean isNorthAssigned;
  // protected Boolean isSouthAssigned;
  // protected Boolean isEastAssigned;
  // protected Boolean isWestAssigned;

  // protected Door westDoor;
  // protected Door eastDoor;
  // protected Door northDoor;
  // protected Door southDoor;
  // private boolean hasDoorN;
  // private boolean hasDoorW;
  // private boolean hasDoorE;
  // private boolean hasDoorS;

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
    // this.hasDoorS = false;
    // this.hasDoorE = false;
    // this.hasDoorN = false;
    // this.hasDoorW = false;

    items = new HashMap<>();
    roomMap = new HashMap<>();
    doorMap = new HashMap<>();
    this.description = description;
  }

  public void addItem(Item item) {
    items.put(item.getItemId(), item);
  }

  public Room getRoom(Direction direction) throws RoomFailedException {
    if(!roomMap.containsKey(direction)) {
      throw new RoomFailedException("No Room in this direction" + direction);
    }
    return roomMap.get(direction);
  }

  /**
   * Gets the doors for the new room.
   *
  public void setDoors() {

    if(roomMap.containsKey(Direction.SOUTH)){
      if(roomMap.get(Direction.SOUTH).doorMap.containsKey(Direction.SOUTH)){
        southDoor = roomMap.get(Direction.SOUTH).doorMap.get(Direction.SOUTH)
      }
      else {
        southDoor = new Door();
      }
    }





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

  }*/


  protected void setFakeInDirection(Direction direction) throws IllegalArgumentException {
    if(!roomMap.containsKey(direction)){
      doorMap.put(direction, new Door());
      doorMap.get(direction).isFake();
    }
    else {
      throw new IllegalArgumentException("Direction was invalid for a fake door: " + direction.toString());
    }
  }

  public Door getDoor(Direction direction) throws RoomFailedException {
    if (!doorMap.containsKey(direction)) {
      throw new RoomFailedException("No door found in this direction: " + direction.toString());
    }
    else {
      return doorMap.get(direction);
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


  /**
   * Get number of doors.
   *
   * @return number of doors.
   *
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
  }*/

  /**
   * Searching for an item in the room.
   *
   * @return item
   * @throws NullPointerException room does not have items
   */
  public List<Item> search() throws NullPointerException {
    List<Item> items1 = items.values().stream().collect(Collectors.toList());
    return items1;

  }

  /**
   * Set a room as the room next door for the current room and vis versa.
   *
   * @param room room to be assigned as the next door room
   */
  public void setNextDoorRoom(Room room,Direction direction) {
    if(roomMap.containsKey(direction)){
      if(direction.equals(Direction.SOUTH)){
        roomMap.replace(direction, room);
        roomMap.get(direction).roomMap.replace(Direction.NORTH, this);
      }
      else if(direction.equals(Direction.NORTH)) {
        roomMap.replace(direction, room);
        roomMap.get(direction).roomMap.replace(Direction.SOUTH, this);
      }
      else if(direction.equals(Direction.EAST)) {
        roomMap.replace(direction, room);
        roomMap.get(direction).roomMap.replace(Direction.WEST, this);
      }
      else {
        roomMap.replace(direction, room);
        roomMap.get(direction).roomMap.replace(Direction.EAST, this);
      }
    }
    else {
      if(direction.equals(Direction.SOUTH)){
        roomMap.put(direction, room);
        roomMap.get(direction).roomMap.put(Direction.NORTH, this);
      }
      else if(direction.equals(Direction.NORTH)) {
        roomMap.put(direction, room);
        roomMap.get(direction).roomMap.put(Direction.SOUTH, this);
      }
      else if(direction.equals(Direction.EAST)) {
        roomMap.put(direction, room);
        roomMap.get(direction).roomMap.put(Direction.WEST, this);
      }
      else {
        roomMap.put(direction, room);
        roomMap.get(direction).roomMap.put(Direction.EAST, this);
      }
    }
  }

  public void setExit(){

  }

}

/**
 * if (direction.equals(Direction.EAST)) {
 *       this.toTheEast = room;
 *       this.isEastAssigned = true;
 *       room.toTheWest = this;
 *       room.isWestAssigned = true;
 *     } else if (direction.equals(Direction.WEST)) {
 *       this.toTheWest = room;
 *       this.isWestAssigned = true;
 *       room.toTheWest = this;
 *       room.isEastAssigned = true;
 *     } else if (direction.equals(Direction.NORTH)) {
 *       this.toTheNorth = room;
 *       this.isNorthAssigned = true;
 *       room.toTheSouth = this;
 *       room.isSouthAssigned = true;
 *     } else if (direction.equals(Direction.SOUTH)) {
 *       this.toTheSouth = room;
 *       this.isSouthAssigned = true;
 *       room.toTheNorth = this;
 *       room.isNorthAssigned = true;
 *     }
 *
 *     if (direction.equals(Direction.SOUTH) && !isSouthAssigned) {
 *       southDoor = new Door();
 *       isSouthAssigned = true;
 *       southDoor.isFake();
 *     } else if (direction.equals(Direction.NORTH) && !isNorthAssigned) {
 *       northDoor = new Door();
 *       isNorthAssigned = true;
 *       northDoor.isFake();
 *     } else if (direction.equals(Direction.EAST) && !isEastAssigned) {
 *       eastDoor = new Door();
 *       isEastAssigned = true;
 *       eastDoor.isFake();
 *     } else if (direction.equals(Direction.WEST) && !isWestAssigned) {
 *       westDoor = new Door();
 *       isWestAssigned = true;
 *       westDoor.isFake();
 *
 *       public boolean hasSouthDoor() {
 *     return hasDoorS;
 *   }
 *
 *   public boolean hasDoorNorth() {
 *     return hasDoorN;
 *   }
 *
 *   public boolean hasDoorWest() {
 *     return hasDoorW;
 *   }
 *
 *   public boolean hasDoorEast() {
 *     return hasDoorE;
 *   }
 */