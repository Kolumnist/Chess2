package de.hhn.it.devtools.apis.textbasedlabyrinth;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

  /**
   * Searching for an item in the room.
   *
   * @return copy of list of items in the room.
   * @throws NullPointerException room does not have items
   */
  public List<Item> search() throws NullPointerException {
    List<Item> items1 = items.values().stream().collect(Collectors.toList());
    return items1;
  }

  /**
   * Sets a room as the next door room for a specified direction.
   *
   * @param room room to be set as neighbor.
   * @param direction specified direction.
   */
  public void setNextDoorRoom(Room room, Direction direction) {
    if (roomMap.containsKey(direction)) {
      roomMap.get(direction).getRoomMap().remove(direction.getOpposite());
      roomMap.replace(direction, room);
    } else {
      roomMap.put(direction, room);
    }
    room.getRoomMap().put(direction.getOpposite(), this);
  }

  /**
   * Create doors for room in directions where there are next door room set.
   */
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

  /**
   * Returns next door room from specified direction.
   * @param direction specified direction.
   * @return room of specified direction.
   * @throws RoomFailedException if no room is found.
   */
  public Room getRoom(Direction direction) throws RoomFailedException {
    if (!roomMap.containsKey(direction)) {
      throw new RoomFailedException("No Room in this direction" + direction);
    }
    return roomMap.get(direction);
  }

  /**
   * Returns next door room's door from specified direction.
   *
   * @param direction specified direction.
   * @return door of specified direction.
   * @throws RoomFailedException if no room is found.
   */
  public Door getDoor(Direction direction) throws RoomFailedException {
    if (!doorMap.containsKey(direction)) {
      throw new RoomFailedException("No door found in this direction: " + direction.toString());
    } else {
      return doorMap.get(direction);
    }
  }

  /**
   * add items to room.
   *
   * @param item to be added.
   */
  public void addItem(Item item) {
    items.put(item.getItemId(), item);
  }

  /**
   * remove item from room.
   *
   * @param itemId id of item to be removed.
   * @throws NoSuchItemFoundException if no such item found.
   */
  public void removeItem(int itemId) throws NoSuchItemFoundException {
    if (!items.containsKey(itemId)) {
      throw new NoSuchItemFoundException("No such Item found in this Room");
    }
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

  public HashMap<Direction, Room> getRoomMap() {
    return roomMap;
  }

  public void setExit() {
    this.isExit = true;
  }

  public boolean isExit() {
    return isExit;
  }

  public HashMap<Integer, Item> getItems() {
    return items;
  }

  /**
   * make a copy of a list of items inside the room.
   * @return list of items inside room.
   */
  public List<Item> getItemList() {
    List<Item> list = items.values().stream().toList();
    return list;
  }

  public ArrayList<Direction> getDirections() {
    return directions;
  }
}