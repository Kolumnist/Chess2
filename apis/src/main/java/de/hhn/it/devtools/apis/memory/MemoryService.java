package de.hhn.it.devtools.apis.memory;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * This interface MemoryService contains the functionality of a memory game.
 */
public interface MemoryService {

    /**
     * Opens the menu.
     */
    void openMenu();

    /**
     * Opens the sub-menu to choose a difficulty.
     */
    void openSettings();

    /**
     * Starts the memory game.
     */
    void startGame();

    /**
     * Deletes the current memory game and creates a new memory game.
     */
    void newGame();

    /**
     * Closes the current memory game.
     */
    void closeGame();

    /**
     * Changes the game mode of the memory game to the chosen difficulty.
     * @param difficulty chosen difficulty
     * @throws IllegalParameterException if the difficulty does not exist
     */
    void changeDifficulty(Difficulty difficulty) throws IllegalParameterException;

    /**
     * Adds a listener to get information on the state of the card.
     *
     * @param name name of the card
     * @param listener object implementing the listener interface
     * @throws IllegalParameterException if the name of the Card does not exist or the listener is a null reference
     */
    void addCallback(String name, PictureCardListener listener) throws IllegalParameterException;

    /**
     * Removes a listener.
     *
     * @param name       name of the card
     * @param listener listener to be removed
     * @throws IllegalParameterException if the name of the card does not exist or the listener is a null reference
     */
    void removeCallback(String name, PictureCardListener listener) throws IllegalParameterException;

    /**
     * Returns the descriptor of the picture card with the corresponding name.
     *
     * @param name name of the Picture Card
     * @return descriptor of the picture card
     * @throws IllegalParameterException if the name of the card does not exist
     */
    PictureCardDescriptor getPictureCard(String name) throws IllegalParameterException;



}
