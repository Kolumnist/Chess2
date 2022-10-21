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
   * For the user to enter a letter for their guess using the WordleKeyListener.
   */
  void enterLetter(WordleKeyListener keyListener);

  void deleteLetter(WordleKeyListener keyListener);

  /**
   * A method to ensure that the user's guess is 5 letters long. An exception is thrown if it isn't.
   *
   * @return true if the guess is 5 letters long
   */
  boolean validateWordleGuess() throws IllegalGuessException;

  /**
   * Compares the user's guess to the solution.
   */
  void compareGuessToWordleSolution();

  /**
   * Updates the UI in accordance with the Wordle rules.
   */
  void updateUiWithColours();

  /**
   * Opens a popup window to notify user of his win.
   */
  void showVictoryScreen();

  /**
   * Opens after 6 guesses to notify the player of his loss.
   */
  void showDefeatScreen();

  /**
   * Opens a popup window that shows the rules to Wordle.
   */
  void showRules();

  /**
   * Quits the game and closes all Wordle windows.
   */
  void quitGame();

}
