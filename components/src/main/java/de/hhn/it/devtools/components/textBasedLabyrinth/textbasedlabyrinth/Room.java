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
  protected HashMap<Direction, Room> roomMap;
  protected HashMap<Direction, Door> doorMap;
  protected ArrayList<Direction> directions;
  protected int roomId;
  private final String description;
  protected boolean isExit;

  /**
   * Constructor of room.
   *
   * @param id ID of the room.
   * @param description the description of the rooms' appearance.
   */
  public Room(int id, String description) {
    this.roomId = id;
    items = new HashMap<>();
    roomMap = new HashMap<>();
    doorMap = new HashMap<>();
    directions = new ArrayList<>();
    directions.add(Direction.NORTH);
    directions.add(Direction.EAST);
    directions.add(Direction.SOUTH);
    directions.add(Direction.WEST);
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


  public void setDoors() {
    for (Direction direction : directions) {
      if (roomMap.containsKey(direction)) {
        Direction oppositeDirection = direction.getOpposite();
        if (roomMap.get(direction).getDoorMap().containsKey(oppositeDirection)) {
          doorMap.put(direction, roomMap.get(direction).getDoorMap().get(oppositeDirection));
        } else {
          doorMap.put(direction, new Door());
        }
      }
    }
  }


  protected void setFakeInDirection(Direction direction) throws IllegalArgumentException {
    if(roomMap.containsKey(direction)){
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

  public HashMap<Direction, Door> getDoorMap() {
    return doorMap;
  }

  public String getDescription() {
    return description;
  }


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
      roomMap.replace(direction, room);
      room.getRoomMap().replace(direction.getOpposite(), this);
    }
    else {
      roomMap.put(direction, room);
      room.getRoomMap().put(direction.getOpposite(), this);
    }
  }



  public HashMap<Direction, Room> getRoomMap() {
    return roomMap;
  }



  public void setExit(){
    this.isExit = true;
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