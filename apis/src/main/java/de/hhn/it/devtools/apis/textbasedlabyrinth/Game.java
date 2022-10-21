package de.hhn.it.devtools.apis.textbasedlabyrinth;

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
    public void moveSouth(){
        if(currentRoom.isSouthAssigned.equals(true)){
            currentRoom = currentRoom.toTheSouth;
            player.setCurrentRoomOfPlayer(currentRoom);
        }
        else {
            System.out.println("There is no Path to the South");
        }
    }

    /**
     * Command the Player to move to the Room to the North
     */
    public void moveNorth(){
        if(currentRoom.isNorthAssigned.equals(true)){
            currentRoom = currentRoom.toTheNorth;
            player.setCurrentRoomOfPlayer(currentRoom);
        }
        else {
            System.out.println("There is no Path to the North");
        }
    }

    /**
     * Command the Player to move to the Room to the West
     */
    public void moveWest(){
        if(currentRoom.isWestAssigned.equals(true)){
            currentRoom = currentRoom.toTheWest;
            player.setCurrentRoomOfPlayer(currentRoom);
        }
        else {
            System.out.println("There is no Path to the West");
        }
    }

    /**
     * Command the Player to move to the Room to the East
     */
    public void moveEast(){
        if(currentRoom.isEastAssigned.equals(true)){
            currentRoom = currentRoom.toTheEast;
            player.setCurrentRoomOfPlayer(currentRoom);
        }
        else {
            System.out.println("There is no Path to the East");
        }
    }

    public void interaction(){

    }

    public void searchRoom(){

    }
}
