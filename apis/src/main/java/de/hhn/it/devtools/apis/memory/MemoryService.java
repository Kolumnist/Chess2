package de.hhn.it.devtools.apis.memory;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.List;
import java.util.Timer;


/**
 * This interface MemoryService contains the functionality of a memory game.
 */
public interface MemoryService {

  /**
   * If there is a current memory game, it deletes the game and creates a new memory game.
   * If there is no current memory game, it creates a game.
   *
   * @param difficulty the difficulty of the game that should be created
   * @throws IllegalParameterException if the difficulty does not exist
   */
  void newGame(Difficulty difficulty) throws IllegalParameterException;

  /**
   * Starts the timer of a game if the card field is set up.
   */
  void startTimer();

  /**
   * Closes the current memory game.
   */
  void closeGame();

  /**
   * Changes the game mode of the memory game to the chosen difficulty.
   *
   * @param difficulty chosen difficulty
   * @throws IllegalParameterException if the difficulty does not exist
   */
  void changeDifficulty(Difficulty difficulty) throws IllegalParameterException;

  /**
   * Adds a listener to get information on the state of the card.
   *
   * @param id       ID of the card
   * @param listener object implementing the listener interface
   * @throws IllegalParameterException if the name of the card does not exist
   *                                   or the listener is a null reference
   */
  void addCallback(int id, PictureCardListener listener) throws IllegalParameterException;

  /**
   * Removes a listener.
   *
   * @param id       ID of the card
   * @param listener listener to be removed
   * @throws IllegalParameterException if the name of the card does not exist
   *                                   or the listener is a null reference
   */
  void removeCallback(int id, PictureCardListener listener) throws IllegalParameterException;

  /**
   * Adds a listener to get information about the time.
   * @param listener listener to be added
   */
  void addCallback(TimerListener listener) throws IllegalParameterException;

  /**
   * Removes a listener.
   * @param listener listener to be removed.
   */
  void removeCallback(TimerListener listener) throws IllegalParameterException;

  /**
   * Returns a list of the cards in the current game.
   *
   * @return list of cards in the current game
   */
  List<PictureCardDescriptor> getPictureCards();

  /**
   * Returns the descriptor of the picture card with the corresponding ID.
   *
   * @param id ID of the picture card
   * @return descriptor of the picture card
   * @throws IllegalParameterException if the ID of the card does not exist
   */
  PictureCardDescriptor getPictureCard(int id) throws IllegalParameterException;

  /**
   * Turns a card around.
   *
   * @param id ID of the picture card that should be turned around
   * @throws IllegalParameterException if the card does not exist
   */
  void turnCard(int id) throws IllegalParameterException;


}
