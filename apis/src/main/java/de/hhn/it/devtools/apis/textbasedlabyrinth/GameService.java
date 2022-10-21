package de.hhn.it.devtools.apis.textbasedlabyrinth;

import de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions.RoomFailedException;

/**
 * Interface for the game.
 */
public interface GameService {

    /**
     * Let the user move the room to the South(if possible)
     */
    void moveSouth() throws RoomFailedException;

    /**
     * Let the user move the room to the North(if possible)
     */
    void moveNorth() throws RoomFailedException;

    /**
     * Let the user move the room to the West(if possible)
     */
    void moveWest() throws RoomFailedException;

    /**
     * Let the user move the room to the East(if possible)
     */
    void moveEast() throws RoomFailedException;

    /**
     * Let the user interact with the environment
     */
    void interaction();

    /**
     * Let the user search through the room
     */
    void searchRoom() throws RoomFailedException;
}
