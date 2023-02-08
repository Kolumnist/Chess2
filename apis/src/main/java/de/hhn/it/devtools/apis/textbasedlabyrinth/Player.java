package de.hhn.it.devtools.apis.textbasedlabyrinth;


import java.util.HashMap;
import java.util.Set;

/**
 * Player Class for the Game, the Player can move to other Rooms and interact with environment.
 */
public class Player {

  private String name;
  private HashMap<Integer, Item> inventory;
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

  /**
   * adds item to player inventory.
   * @param item item to be added.
   */
  public void addItem(Item item) {
    inventory.put(item.getItemId(), item);
  }

  /**
   * removes item from player inventory.
   *
   * @param itemId the item to be removed.
   * @throws NoSuchItemFoundException if the item is not contained.
   * @return the removed item.
   */
  public Item removeItem(int itemId) throws NoSuchItemFoundException {
    if (!inventory.containsKey(itemId)) {
      throw new NoSuchItemFoundException("The item was not found.");
    }
    Item returnItem = inventory.get(itemId);
    inventory.remove(itemId);
    return returnItem;
  }

  /**
   * Get item from player inventory.
   *
   * @param itemId id of Item
   * @return item
   * @throws NoSuchItemFoundException if the item is not contained.
   */
  public Item getItem(int itemId) throws NoSuchItemFoundException {
    if (!inventory.containsKey(itemId)) {
      throw new NoSuchItemFoundException();
    }
    return inventory.get(itemId);
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

  /**
   * reset player inventory.
   */
  public void reset() {
    inventory.clear();
  }

  public HashMap<Integer, Item> getInventory() {
    return inventory;
  }

  /**
   *
   * @return
   */
  public HashMap<String, Item> getInventoryWithNames() {
    Set<Integer> keySet = inventory.keySet();
    HashMap<String, Item> items = new HashMap<>();
    for (Integer i : keySet) {
      items.put(inventory.get(i).getName(), inventory.get(i));
    }
    return items;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
