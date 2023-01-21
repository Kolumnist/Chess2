package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;

import de.hhn.it.devtools.apis.textbasedlabyrinth.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Game class.
 */
public class Game implements GameService {


  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Game.class);


  private Room currentRoom;
  private Player player;
  public Layout currentLayout;
  public ArrayList<Layout> layouts;
  private ArrayList<OutputListener> listeners;
  private ArrayList<Map> allMaps;
  public Map map;
  private int score;



  public Game() {

  }


  /**
   * This method exists to initialize the player and the lists,
   * and other things.
   * This method should only be done once per launch.
   */
  public void startup() {
    this.player = new Player("Jones");
    this.currentLayout = new Layout(player);
    layouts = new ArrayList<>();
    listeners = new ArrayList<>();
    allMaps = new ArrayList<>();

    allMaps.add(Map.Ancient_Dungeon);
    allMaps.add(Map.Unknown_Sewers);
    allMaps.add(Map.Grave_of_the_Mad_King);

    logger.info("Game initialized.");
    score = 0;
  }


  public void start() {
    for (OutputListener outputListener : listeners) {
      outputListener.listenerStart();
    }
    logger.info("Game started.");
  }

  public void end() {
    for (OutputListener outputListener : listeners) {
      outputListener.listenerEnd();
    }
  }

  public void reset() {
    player.reset();
    score = 0;
    for (OutputListener outputListener : listeners) {
      outputListener.listenerReset();
    }

    logger.info("Game reset.");

  }


  /**
   * This method deals with the player moving from room to room.
   * @param direction the direction in which the player is moving.
   */
  public void move(Direction direction) throws IllegalArgumentException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }
    boolean stop = false;

    Door checkDoor = null;
    try {
      checkDoor = currentRoom.getDoor(direction);
    } catch (RoomFailedException e) {
      stop = true;
      for (OutputListener outputListener : listeners) {
        outputListener.sendOutputNavigation("There is no way in that direction.");
      }
    }

    if (!stop) {
      String message = checkDoor.open();
      for (OutputListener outputListener : listeners) {
        outputListener.listenerMove();
        outputListener.sendOutputPlayer(message);
      }
      if (!checkDoor.checkIfLocked()) {
        if (checkDoor.checkIfFake()) {
          String fake = "The door reveals no path, but a wall. You cannot move in this direction.";
          for (OutputListener outputListener : listeners) {
            outputListener.sendOutputNavigation(fake);
          }
        } else {
          try {
            currentRoom = currentRoom.getRoom(direction);
          } catch (RoomFailedException e) {
            stop = true;
            for (OutputListener outputListener : listeners) {
              outputListener.sendOutputNavigation("There is no room in that direction.");
            }
          }
        }
      }
    }
    if (!stop) {
      player.setCurrentRoomOfPlayer(currentRoom);
      check();
    }

    if(player.getCurrentRoomOfPlayer().isExit()){
      end();
      logger.info("Exit was reached. The player is victorious, with " + score + " as his final score.");
      logger.info("Thank you for playing.");
    }
  }

  /**
   * Inspects the room for doors.
   *
   * @param direction gets doors in all directions.
   * @throws IllegalArgumentException direction should not be null.
   */
  public String inspect(Direction direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }

    String message = null;
    try {
      message = currentRoom.getDoor(direction).getInspectMessage();
    } catch (RoomFailedException e) {
      for (OutputListener outputListener : listeners) {
        outputListener.sendOutputNavigation("There is no way in that direction.");
      }
    }
    for (OutputListener outputListener : listeners) {
      outputListener.sendOutputNavigation(message);
    }
    return message;
  }

  /**
   * Player tries to interact with a door/direction.
   *
   * @param direction gets the direction to the next door.
   * @param item item player uses.
   * @throws IllegalArgumentException direction not null.
   */
  public boolean interaction(Direction direction, Item item) throws IllegalArgumentException, RoomFailedException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }
    if (item == null) {
      throw new IllegalArgumentException("Item should not be null.");
    }

    String successMessage;
    boolean unlocked = currentRoom.getDoor(direction).unlock(item);
    Door door = currentRoom.getDoor(direction);
    if (unlocked) {
      successMessage = door.getPuzzle().getUnlockMessage();
    } else {
      successMessage = door.getPuzzle().getLockedMessage();
    }
    for (OutputListener outputListener : listeners) {
      outputListener.sendOutputPlayerInteract(successMessage);
    }
    return unlocked;
  }



  public List<Item> searchRoom() {
    List<Item> items = new ArrayList<>();
    items = itemSearcher();
    return items;
  }

  /**
   *
   * @param layout the layout to be assigned.
   */
  @Override
  public void setLayout(Layout layout) {
    this.currentLayout = layout;
  }

  /**
   * Gets item for player.
   *
   * @param itemId gets an item.
   * @return the item that was picked up.
   * @throws NoSuchItemFoundException if item not found.
   * */
  public Item pickUpItem(int itemId) throws NoSuchItemFoundException {

    List<Item> items = new ArrayList<>();

    items = itemSearcher();
    Item searchedItem = null;
    for (Item item : items) {
      if (item.getItemId() == itemId) {
        searchedItem = item;
      }
    }

    if (searchedItem == null) {
      throw new NoSuchItemFoundException("The item was not found.");
    } else {
      player.addItem(searchedItem);
      player.getCurrentRoomOfPlayer().removeItem(searchedItem.getItemId());
      if (searchedItem.getIsTreasure()) {
        score = score + ((Treasure) searchedItem).getScorePoint();
      }

      for (OutputListener outputListener : listeners) {
        outputListener.sendOutputPickUpItem(searchedItem);
        outputListener.updateScore(score);
      }
      return searchedItem;
    }
  }

  /**
   * Method to remove an item from the player inventory.
   *
   * @param itemId the id of the item to be removed.
   * @return the message, which is about the success or failure of the operation.
   */
  public Item dropItem(int itemId) throws NoSuchItemFoundException {
    Item droppedItem = null;
    try {
      droppedItem = player.removeItem(itemId);
    } catch (NoSuchItemFoundException e) {
      throw new NoSuchItemFoundException(e.getMessage());
    }
    currentRoom.addItem(droppedItem);

    if (droppedItem.getIsTreasure()) {
      score = score - ((Treasure) droppedItem).getScorePoint();
    }
    if (score < 0) {
      score = 0;
    }
    for (OutputListener outputListener : listeners) {
      outputListener.sendOutputDropItem(droppedItem);
      outputListener.updateScore(score);
    }
    return droppedItem;
  }


  @Override
  public void inspectItem(Item item, CurrentScreenRequesting requester) {
    if (item == null) {
      throw new IllegalArgumentException("Item was null.");
    }
    if (requester == null) {
      throw new IllegalArgumentException("Item was null");
    }
    String message = item.getInfo();
    for (OutputListener outputListener : listeners) {
      switch (requester) {
        case INTERACTION:
          outputListener.sendOutputPlayerInteract(message);
          outputListener.sendOutputInteractItemName(item.getName());
          break;
        case ROOMINVENTORY:
          outputListener.outputRoomItemInspect(message);
          outputListener.outputRoomItemName(item.getName());
          break;
        case PLAYERINVENTORY:
          outputListener.outputInventoryItemInspect(message);
          outputListener.outputInventoryItemName(item.getName());
          break;
        default:
          throw new IllegalArgumentException("Requester could not be determined.");
      }
    }

  }

  @Override
  public void inspectItemInInventoryOfPlayer(int itemId) throws NoSuchItemFoundException {
    String message = player.getItem(itemId).getInfo();
    for (OutputListener outputListener : listeners) {
      outputListener.sendOutputInventory(message);
    }
  }

  /**
   * Setter for Player name
   * @param name the new name.
   * @return if the new name was accepted.
   */
  @Override
  public boolean setPlayerName(String name) {
    boolean success = !name.isEmpty() && !name.isBlank();

    if(success) {
      player.setName(name);
    }
    return success;
  }

  /**
   * Message to be given to the player after (probably) every action
   * and every time the player moves between rooms.
   */
  public String check() {
    String message = "You find yourself in " + currentRoom.getDescription();
    message = message + ("You can search the room or move on.");
    for (OutputListener outputListener : listeners) {
      outputListener.sendOutputRoom(message);
    }
    return message;
  }



  @Override
  public void addListener(OutputListener listener) {
    listeners.add(listener);
  }



  @Override
  public void removeListener(OutputListener listener) {
    listeners.remove(listener);
  }

  /**
   * Text to be given to the player at the start of the game.
   */
  public String startText() {
    String message = "You are " + player.getName() + " and you are in the depths of a labyrinth.";
    for (OutputListener outputListener : listeners) {
      outputListener.sendOutputRoom(message);
    }
    return message;
  }



  private List<Item> itemSearcher() {
    List<Item> items = new ArrayList<>();
    items = currentRoom.search();
    return items;
  }

  /**
   * Setter for current layout
   * @param newMap map to be selected
   * @param newSeed seed for the map
   */
  public void setCurrentLayout(Map newMap, Seed newSeed) throws RoomFailedException, InvalidSeedException {
    if(newSeed == null){
      throw new InvalidSeedException();
    }
    LayoutGenerator generator = new LayoutGenerator(newMap, newSeed);
    try {
      generator.generateLayout();
    } catch (RoomFailedException e) {
      throw new RoomFailedException();
    }

    generator.setLayout(currentLayout);
    currentRoom = currentLayout.getStartRoom();
  }



  public String getPlayerName() {
    return player.getName();
  }

  @Override
  public Map getMap() {
    return map;
  }

  @Override
  public Player getPlayer() {
    return player;
  }

  public Layout getCurrentLayout() {
    return currentLayout;
  }

  @Override
  public Room getCurrentRoom() {
    return currentRoom;
  }

  @Override
  public ArrayList<Map> getMaps() {
    return allMaps;
  }

  @Override
  public int getScore() {
    return score;
  }
}


