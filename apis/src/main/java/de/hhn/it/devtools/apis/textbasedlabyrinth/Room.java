package de.hhn.it.devtools.apis.textbasedlabyrinth;


import java.util.HashMap;

/**
 * Room Class for the Game, defines the Rooms of the Game with assigned next-door Rooms and Items inside of it
 */
public class Room {


  private HashMap<Integer, Item> items;
  private int roomId;

  public Room toTheNorth;
  public Room toTheSouth;
  public Room toTheEast;
  public Room toTheWest;

  public Boolean isNorthAssigned;
  public Boolean isSouthAssigned;
  public Boolean isEastAssigned;
  public Boolean isWestAssigned;


  /**
   * Constructor of Room
   * @param id ID of the room
   */
  public Room(int id) {
    this.roomId = id;
    this.isNorthAssigned = false;
    this.isSouthAssigned = false;
    this.isEastAssigned = false;
    this.isWestAssigned = false;
  }

  public void addItem(Item item) {
    items.put(item.getItemId(), item);

  }

  public void removeItem(int itemId) {
    items.remove(itemId);
  }

  public int getRoomId() {
    return roomId;
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
