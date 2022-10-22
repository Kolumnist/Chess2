package de.hhn.it.devtools.apis.textbasedlabyrinth;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Room Class for the Game, defines the Rooms of the Game with assigned next-door Rooms and Items inside of it
 */
public class Room {


  private HashMap<Integer, Item> items;
  private int roomId;

  private String description;

  public Room toTheNorth;
  public Room toTheSouth;
  public Room toTheEast;
  public Room toTheWest;

  public Boolean isNorthAssigned;
  public Boolean isSouthAssigned;
  public Boolean isEastAssigned;
  public Boolean isWestAssigned;

  private Door leftDoor;
  private Door rightDoor;
  private Door doorStraightAhead;
  private Door backdoor;

  /**
   * Constructor of Room
   * @param id ID of the room
   */
  public Room(int id, String description) {
    this.roomId = id;
    this.isNorthAssigned = false;
    this.isSouthAssigned = false;
    this.isEastAssigned = false;
    this.isWestAssigned = false;
    items = new HashMap<>();
    this.description = description;

  }

  public void addItem(Item item) {
    items.put(item.getItemId(), item);

  }

  public void setDoors(boolean hasPuzzle, Item key) {
    backdoor = new Door(false, null);
    int number = 0;
    if (hasPuzzle) {
      Random random = new Random();
      number = random.nextInt(1, 4);
    }

    leftDoor = new Door(number == 1, key);
    rightDoor = new Door(number == 2, key);
    doorStraightAhead = new Door(number == 3, key);


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

  public List<Item> search() throws NullPointerException {
    if (items == null) {
      throw new NullPointerException("Room could not hold items.");
    }
    boolean fail = false; // May need later.

    List<Item> items1 = items.values().stream().toList();

    if (items1.isEmpty()) {
      Item item = new Item(10077001, "NoItem");
      ArrayList<Item> items2 = new ArrayList<>();
              items2.add(item);
      return items2;
    } else {
      return items1;
    }
  }

  /**
   * Set a Room as the Room next door for the current Room and vis versa.
   * @param room Room to be assigned as the next door Room
   * @param isEast Boolean check to see if the new Room will be to the east of current room
   * @param isWest Boolean check to see if the new Room will be to the west of current room
   * @param isNorth Boolean check to see if the new Room will be to the north of current room
   */
  public void setNextDoorRoom(Room room, Boolean isEast, Boolean isWest, Boolean isNorth){
    if(isEast && !isWest && !isNorth){
      this.toTheEast = room;
      this.isEastAssigned = true;
      room.toTheWest = this;
      room.isWestAssigned = true;
    }
    else if(isWest && !isEast && !isNorth){
      this.toTheWest = room;
      this.isWestAssigned = true;
      room.toTheWest = this;
      room.isEastAssigned = true;
    }
    else if(isNorth && !isEast && !isWest){
      this.toTheNorth = room;
      this.isNorthAssigned = true;
      room.toTheSouth = this;
      room.isSouthAssigned = true;
    }
    else {
      this.toTheSouth = room;
      this.isSouthAssigned = true;
      room.toTheNorth = this;
      room.isNorthAssigned = true;
    }
  }

}
