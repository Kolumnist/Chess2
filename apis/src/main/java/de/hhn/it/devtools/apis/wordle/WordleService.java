package de.hhn.it.devtools.apis.wordle;

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
   * @return true if the guess is 5 letters long
   */
  boolean validateWordleGuess(WordleGuess guess) throws IllegalGuessException;

  /**
   * Quits the game and closes all Wordle windows.
   */
  void quitGame();

}
