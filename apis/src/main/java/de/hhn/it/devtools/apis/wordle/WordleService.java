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

  boolean receiveAndComputeGuess(String stringGuess) throws IllegalGuessException;


  /**
   * Quits the game and closes all Wordle windows.
   */
  void quitGame();

  /**
   * Adds a listener in order to get information on the state of a WordlePanel.
   *
   * @param listener WordlePanelListener which will be added to the panel
   * @param panel Panel to which Listener will be added
   * @throws IllegalParameterException if the given ID or listener do not exist
   */
  void addCallback(WordlePanelListener listener, WordlePanelService panel) throws IllegalParameterException;

  /**
   * Removes a listener from a WordlePanel.
   *
   * @param listener Listener which is to be removed
   * @param panel Panel from which Listener is removed
   * @throws IllegalParameterException if the given ID or listener do not exist
   */
  void removeCallback(WordlePanelListener listener, WordlePanelService panel) throws IllegalParameterException;

  WordlePanelService getPanelById(int id) throws IllegalParameterException;

}
