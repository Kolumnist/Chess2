package de.hhn.it.devtools.apis.textbasedlabyrinth;

import de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions.RoomFailedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Game class.
 */
public class Game implements GameService {

    public Room currentRoom;
    public Player player;
    public Layout layout;

    /**
     * Constructor for Game.
     */
    public Game() {
        this.player = new Player("Placeholder");
        this.layout = new Layout(player);

        this.currentRoom = layout.startRoom;
    }

    /**
     * Command the Player to move to the Room to the South
     */
    public void moveSouth() throws RoomFailedException {
        if(currentRoom.isSouthAssigned.equals(true)){
            currentRoom = currentRoom.toTheSouth;
            player.setCurrentRoomOfPlayer(currentRoom);
        }
        else {
            throw new RoomFailedException("No room found in the southern direction.");
        }
    }

    /**
     * Command the Player to move to the Room to the North
     */
    public void moveNorth() throws RoomFailedException {
        if(currentRoom.isNorthAssigned.equals(true)){
            currentRoom = currentRoom.toTheNorth;
            player.setCurrentRoomOfPlayer(currentRoom);
        }
        else {
            throw new RoomFailedException("No room found in the northern direction.");
        }
    }

    /**
     * Command the Player to move to the Room to the West
     */
    public void moveWest() throws RoomFailedException {
        if(currentRoom.isWestAssigned.equals(true)){
            currentRoom = currentRoom.toTheWest;
            player.setCurrentRoomOfPlayer(currentRoom);
        }
        else {
            throw new RoomFailedException("No room found in the western direction.");
        }
    }

    /**
     * Command the Player to move to the Room to the East
     */
    public void moveEast() throws RoomFailedException {
        if(currentRoom.isEastAssigned.equals(true)){
            currentRoom = currentRoom.toTheEast;
            player.setCurrentRoomOfPlayer(currentRoom);
        }
        else {
            throw new RoomFailedException("No room found in the eastern direction.");
        }
    }

    public void interaction() {

    }

    public void searchRoom() throws RoomFailedException {
        List<Item> items = new ArrayList<>();

        try {
            items = currentRoom.search();
        } catch (NullPointerException n) {
            throw new RoomFailedException(n.getMessage());
        }

        if (items.size() == 1) {
            if (items.get(0).getItemId() == 10077001) {
                System.out.println("There are no items in this room.");
            } else {
                System.out.println("In this room, you find: ");
                for (Item item : items) {
                    System.out.println(item.getName());
                }

            }
        }


    }

    /**
     * Checks the game for its current status.
     * Prints out what is currently going on.
     */
    public void check() {
        System.out.println("You are " + player.getName());
        System.out.println("You are in " + currentRoom.getDescription());
        System.out.println("You are alone.");
        System.out.println("You can search the room or move on.");
    }
}
