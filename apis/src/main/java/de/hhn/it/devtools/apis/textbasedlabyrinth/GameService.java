package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for the game.
 */
public interface GameService {

  /**
   * let the user move in the selected direction if possible.
   *
   * @param direction direction the player wants to move to.
   * @throws RoomFailedException if there is no room in the selected direction.
   */
  void move(Direction direction) throws RoomFailedException;


  /**
   * Picks up item from the room and adds it into player inventory.
   * if the Item is a Treasure, Scoreboard will be updated instead of being send to the inventory
   *
   * @param itemId id of item to be picked up
   * @return picked up item
   * @throws NoSuchItemFoundException if item not found.
   */
  Item pickUpItem(int itemId) throws NoSuchItemFoundException;

  /**
   * removes item from player inventory and adds to the inventory of the current room.
   *
   * @param itemId the id of the item to be removed.
   * @return removed item
   * @throws NoSuchItemFoundException if item not found.
   */
  Item dropItem(int itemId) throws NoSuchItemFoundException;

  /**
   * Inspect the item in the inventory of the player, showing its item description and info.
   *
   * @param itemId id of the item to be inspected.
   * @return info message of inspected item.
   * @throws NoSuchItemFoundException if no such item found.
   */
  String inspectItemInInventoryOfPlayer(int itemId) throws NoSuchItemFoundException;

  /**
   * inspect a direction to check if there is an open passage or not.
   *
   * @param direction direction to be inspected.
   * @return inspect message of the door in the selected direction.
   * @throws RoomFailedException if no room found.
   */
  String inspect(Direction direction) throws RoomFailedException;

  /**
   * Lets the user interact with a door, trying to unlock it.
   *
   * @param direction direction to be interacted with.
   * @param item item used for interaction.
   * @return true if interaction was a success.
   * @throws RoomFailedException if no such room was found.
   */
  boolean interaction(Direction direction, Item item) throws RoomFailedException;

  /**
   * Search current room for items.
   *
   * @return list of items in current room of player.
   * @throws RoomFailedException if no such room is found.
   */
  List<Item> searchRoom() throws RoomFailedException;


  /**
   * Method for setting the layout of the game.
   *
   * @param layout the layout to be assigned.
   */
  void setLayout(Layout layout);

  /**
   * Setter for current layout. This will invoke the layoutGenerator, then the setLayout(Layout layout).
   *
   * @param newMap Map to be selected.
   * @param newSeed Seed for the Map.
   * @throws RoomFailedException if no such room is found.
   * @throws InvalidSeedException if newSeed is invalid.
   */
  void setCurrentLayout(Map newMap, Seed newSeed) throws RoomFailedException, InvalidSeedException;


  /**
   * Sets the current player name to the new String given if it is not null.
   *
   * @param name the new name.
   * @return if it changed the name, true, else false.
   */
  boolean setPlayerName(String name);

  /**
   * Text message for the player at the start of the game.
   *
   * @return start text for the player.
   */
  String startText();

  /**
   * Message to be given to the player after (probably) every action
   * and every time the player moves between rooms.
   *
   * @return message to player of current room description.
   */
  String check();

  /**
   * sends inspect message to player.
   *
   * @param item item to be inspected.
   * @param requester decides if the info comes from either Playerinventory, Roominventory or the interaction window.
   */
  void inspectItem(Item item, CurrentScreenRequesting requester);

  /**
   * Adds callback listener to listener list.
   *
   * @param listener listener to be added.
   */
  void addListener(OutputListener listener);

  /**
   * removes callback listener from listener list.
   *
   * @param listener listener to be removed.
   */
  void removeListener(OutputListener listener);

  /**
   * initialize player and lists for smooth game building.
   */
  void startup();

  /**
   * start the game.
   */
  void start();

  /**
   * end the game.
   */
  void end();

  /**
   * reset all game data.
   */
  void reset();

  Player getPlayer();

  Room getCurrentRoom();

  String getPlayerName();

  Map getMap();

  List<Map> getMaps();

  ArrayList<OutputListener> getListeners();

  int getScore();

}
