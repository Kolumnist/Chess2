package de.hhn.it.devtools.apis.textbasedlabyrinth;


import de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions.NoSuchItemFoundException;

import java.util.HashMap;

/**
 * Player Class for the Game, the Player can move to other Rooms and interact with environment.
 */
public class Player {

  private final String name;
  private HashMap<String, Item> inventory;
  public Room currentRoomOfPlayer;

  /**
   * Constructor of Player.
   *
   * @param name name of the Player
   */
  public Player(String name) {
    this.name = name;
    inventory = new HashMap<>();
  }


  public void addItem(Item item) {

    inventory.put(item.getName(), item);
  }

  /**
   * Javadoc.
   *
   * @param itemName new Item
   * @throws NoSuchItemFoundException Exception
   */
  public void removeItem(String itemName) throws NoSuchItemFoundException {
    if (itemName.isEmpty() || itemName.isBlank()) {
      throw new NoSuchItemFoundException("Name of the item cannot be zero or blank.");
    }
    if (!inventory.containsKey(itemName)) {
      throw new NoSuchItemFoundException();
    }
    inventory.remove(itemName);
  }

  /**
   * Javadoc.
   *
   * @param itemName Item
   * @return return
   * @throws NoSuchItemFoundException exception
   */
  public Item getItem(String itemName) throws NoSuchItemFoundException {
    if (itemName.isEmpty() || itemName.isBlank()) {
      throw new NoSuchItemFoundException("Name of the item cannot be zero or blank.");
    }
    if (!inventory.containsKey(itemName)) {
      throw new NoSuchItemFoundException();
    }
    return inventory.get(itemName);
  }

  public String getName() {

    return name;
  }

  /**
   * Getter for currentRoomOfPlayer.
   *
   * @return Room where the Player is inside right now
   */
  public Room getCurrentRoomOfPlayer() {
    return currentRoomOfPlayer;
  }

  /**
   * Setter for currentRoomOfPlayer.
   * Sets the selected Room as the Room the Player is in
   *
   * @param currentRoomOfPlayer Room with the Player inside
   */
  public void setCurrentRoomOfPlayer(Room currentRoomOfPlayer) {
    this.currentRoomOfPlayer = currentRoomOfPlayer;
  }
}