package de.hhn.it.devtools.apis.memory;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * This interface MemoryService contains the functionality of a memory game.
 */
public interface MemoryService {

    /**
     * Changes the game mode of the memory game to the chosen difficulty.
     * @param difficulty chosen difficulty
     * @throws IllegalParameterException if the difficulty does not exist
     */
    void changeDifficulty(Difficulty difficulty) throws IllegalParameterException;

    /**
     * Starts the memory game.
     */
    void startGame();

    /**
     * Deletes the current memory game and creates a new memory game.
     */
    void newGame();

    /**
     * Adds a listener to get information on the state of the Card.
     * @param name name of the Card
     * @param listener object implementing the listener interface
     * @throws IllegalParameterException if the name of the Card does not exist oder the listener is a null reference
     */
    void addCallback(String name, PictureCardListener listener) throws IllegalParameterException;





}
