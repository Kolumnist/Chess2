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
        this.layout = new Layout();
        this.player = new Player("Steve");
        this.currentRoom = layout.startRoom;
    }

    public void moveSouth(){
        currentRoom = currentRoom.toTheSouth;
        currentRoom.setPlayerInside(true);
    }

    public void moveNorth(){
        currentRoom = currentRoom.toTheNorth;
        currentRoom.setPlayerInside(true);
    }

    public void moveWest(){
        currentRoom = currentRoom.toTheWest;
        currentRoom.setPlayerInside(true);
    }

    public void moveEast(){
        currentRoom = currentRoom.toTheEast;
        currentRoom.setPlayerInside(true);
    }

    public void interaction(){

    }

    public void searchRoom(){

    }

    public void startGame(){

    }


}
