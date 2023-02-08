package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.ArrayList;
import java.util.List;

/**
 * Layout defines the dungeon layout of the game and is determined by the selected map,
 * which changes with the different possible maps available.
 */
public class Layout {

  private List<Room> allRooms;
  private Room startRoom;
  private Player player;

  /**
   * Constructor of Layout Class.
   *
   * @param player the current player of the game
   */
  public Layout(Player player) {
    this.player = player;
    this.allRooms = new ArrayList<>();
  }

  /**
   * set the startroom up and place player inside.
   */
  public void setStartRoom() {
    this.startRoom = allRooms.get(0);
    player.setCurrentRoomOfPlayer(startRoom);
  }

  public void setAllRooms(List<Room> allRooms) {
    this.allRooms = allRooms;
  }

  public List<Room> getAllRooms() {
    return allRooms;
  }

  public Room getStartRoom() {
    return startRoom;
  }

  public Player getPlayer() {
    return player;
  }
}

