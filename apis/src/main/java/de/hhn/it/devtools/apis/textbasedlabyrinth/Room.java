package de.hhn.it.devtools.apis.textbasedlabyrinth;


import java.util.HashMap;

/**
 * Javadoc.
 */
public class Room {


  private HashMap<Integer, Item> items;
  private int roomId;

  public Room toTheNorth;
  public Room toTheSouth;
  public Room toTheEast;
  public Room toTheWest;
  public Boolean isPlayerInside;


  public Room(int id) {
    this.roomId = id;
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

  public Boolean getPlayerInside() {
    return isPlayerInside;
  }

  public void setPlayerInside(Boolean playerInside) {
    isPlayerInside = playerInside;
  }

  public void setNextDoorRoom(Room room, Boolean isEast, Boolean isWest, Boolean isNorth){
    if(isEast){
      this.toTheEast = room;
      room.toTheWest = this;
    }
    else if(isWest){
      this.toTheWest = room;
      room.toTheWest = this;
    }
    else if(isNorth){
      this.toTheNorth = room;
      room.toTheSouth = this;
    }
    else {
      this.toTheSouth = room;
      room.toTheNorth = this;
    }
  }

}
