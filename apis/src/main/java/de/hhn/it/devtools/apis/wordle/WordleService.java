package de.hhn.it.devtools.apis.wordle;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * An interface that provides methods for our Wordle micro-project.
 */

public interface WordleService {

  /**
   * Method to start the game, i.e. run constructors and show UI.
   */
  void startGame();

  /**
   * Method to start another game while the program is still running.
   */
  void startAnotherGame();

  /**
   * Method that randomly selects a Wordle solution that the user must attempt to guess.
   *
   * @return The randomly selected Wordle solution
   */
  String selectWordle();

  /**
   * A method to ensure that the user's guess is 5 letters long. An exception is thrown if it isn't.
   *
   * @param guess the guess which the method will validate
   * @throws IllegalGuessException if the given guess is too short
   */
  void validateWordleGuess(WordleGuess guess) throws IllegalGuessException;

  /**
   * Quits the game and closes all Wordle windows.
   */
  void quitGame();

  /**
   * Adds a listener in order to get information on the state of a WordlePanel.
   *
   * @param id ID of the WordlePanel which will have a callback added to it
   * @param listener WordlePanelListener which will be added to the panel
   * @throws IllegalParameterException if the given ID or listener do not exist
   */
  void addCallback(int id, WordlePanelListener listener) throws IllegalParameterException;

  /**
   * Removes a listener from a WordlePanel.
   *
   * @param id ID of the WordlePanel which will have its Listener removed
   * @param listener Listener which is to be removed
   * @throws IllegalParameterException if the given ID or listener do not exist
   */
  void removeCallback(int id, WordlePanelListener listener) throws IllegalParameterException;

}
