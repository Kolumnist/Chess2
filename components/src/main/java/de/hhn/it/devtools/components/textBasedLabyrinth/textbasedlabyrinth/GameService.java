package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.InvalidSeedException;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.NoSuchItemFoundException;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.RoomFailedException;
import java.util.List;

/**
 * Interface for the game.
 */
public interface GameService {

  /**
   * Let the user move the room in the given direction (if possible).
   */
  void move(Direction direction) throws RoomFailedException;


  /**
   * Gets item for player.
   *
   * if the Item is a Treasure, Scoreboard will be updated instead of being send to the inventory
   *
   * @param itemId gets an item
   * @return gives item to player
   * @throws NoSuchItemFoundException if item not found.
   * @throws NullPointerException if item cant be null.
   */
  Item pickUpItem(int itemId) throws NoSuchItemFoundException;

  /**
   * Method to remove an item from the player inventory.
   *
   * @param itemId the id of the item to be removed.
   * @return the message, which is about the success or failure of the operation.
   */
  String dropItem(int itemId) throws NoSuchItemFoundException;


  void inspectItemInInventoryOfPlayer(int itemId) throws NoSuchItemFoundException;

  /**
   * Inspect a door/pathway.
   */
  String inspect(Direction direction) throws RoomFailedException;;

  /**
   * Let the user interact with the environment.
   * (Solve puzzles)
   */
  String interaction(Direction direction, Item item) throws RoomFailedException;;

  /**
   * Let the user search through the room.
   */
  List<Item> searchRoom() throws RoomFailedException;


  /**
   * Method for setting the layout class.
   * @param layout the layout to be assigned.
   */
  void setLayout(Layout layout);

  /**
   * Setter for current layout. This will invoke the layoutGenerator, then the setLayout(Layout layout).
   * @param newMap Map to be selected
   * @param newSeed Seed for the Map
   */
  void setCurrentLayout(Map newMap, Seed newSeed) throws RoomFailedException, InvalidSeedException;


  /**
   * Sets the current player name to the new String given if it is not null.
   * @param name the new name.
   * @return if it changed the name, true, else false.
   */
  boolean setPlayerName(String name);

  /**
   * Text to be given to the player at the start of the game.
   */
  String startText();

  /**
   * Message to be given to the player after (probably) every action
   * and every time the player moves between rooms.
   */
  String check();

  int getScore();

  void addListener(OutputListener listener);

  void removeListener(OutputListener listener);

  Player getPlayer();

  Room getCurrentRoom();



  void startup();

  void start();

  void end();

  void reset();

  public String getPlayerName();

  public Map getMap();

}
