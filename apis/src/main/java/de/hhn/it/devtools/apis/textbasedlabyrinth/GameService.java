package de.hhn.it.devtools.apis.textbasedlabyrinth;

/**
 * Interface for the Game
 */
public interface GameService {

    /**
     * Let the User move the The room to the South(if possible)
     */
    void moveSouth();

    /**
     * Let the User move the The room to the North(if possible)
     */
    void moveNorth();

    /**
     * Let the User move the The room to the West(if possible)
     */
    void moveWest();

    /**
     * Let the User move the The room to the East(if possible)
     */
    void moveEast();

    /**
     * Let the User interact with the environment
     */
    void interaction();

    /**
     * Let the User search through the room
     */
    void searchRoom();
}
