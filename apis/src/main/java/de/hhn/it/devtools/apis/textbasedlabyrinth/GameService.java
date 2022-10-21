package de.hhn.it.devtools.apis.textbasedlabyrinth;

import de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions.RoomFailedException;

/**
 * Interface for the Game
 */
public interface GameService {

    /**
     * Let the User move the The room to the South(if possible)
     */
    void moveSouth() throws RoomFailedException;

    /**
     * Let the User move the The room to the North(if possible)
     */
    void moveNorth() throws RoomFailedException;

    /**
     * Let the User move the The room to the West(if possible)
     */
    void moveWest() throws RoomFailedException;

    /**
     * Let the User move the The room to the East(if possible)
     */
    void moveEast() throws RoomFailedException;

    /**
     * Let the User interact with the environment
     */
    void interaction();

    /**
     * Let the User search through the room
     */
    void searchRoom() throws RoomFailedException;
}
