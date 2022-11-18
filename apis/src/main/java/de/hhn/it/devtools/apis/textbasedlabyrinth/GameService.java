package de.hhn.it.devtools.apis.textbasedlabyrinth;

import de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions.NoSuchItemFoundException;
import de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions.RoomFailedException;
import java.util.List;

/**
 * Interface for the game.
 */
public interface GameService {

  /**
   * Let the user move the room to the south(if possible).
   */
  String moveSouth() throws RoomFailedException;

  /**
   * Let the user move the room to the north(if possible).
   */
  String moveNorth() throws RoomFailedException;

  /**
   * Let the user move the room to the west(if possible).
   */
  String moveWest() throws RoomFailedException;

  /**
   * Let the user move the room to the east(if possible).
   */
  String moveEast() throws RoomFailedException;


  /**
   * Gets Item for player.
   *
   * @param item gets an item
   * @return gives item to player
   * @throws NoSuchItemFoundException if item not found.
   * @throws NullPointerException if item cant be null.
   */
  Item pickUpItem(Item item) throws NoSuchItemFoundException;

  /**
   * Method to remove an item from the player inventory.
   *
   * @param itemName the name of the item to be removed.
   * @return the message, which is about the success or failure of the operation.
   */
  String dropItem(String itemName);


  /**
   * Inspect a door/pathway.
   */
  String inspect(Direction direction);

  /**
   * Let the user interact with the environment.
   * (Solve puzzles)
   */
  String interaction(Direction direction, Item item);

  /**
   * Let the user search through the room.
   */
  List<Item> searchRoom() throws RoomFailedException;


  /**
   * Text to be given to the player at the start of the game.
   */
  String startText();

  /**
   * Message to be given to the player after (probably) every action
   * and every time the player moves between rooms.
   */
  String check();
}
