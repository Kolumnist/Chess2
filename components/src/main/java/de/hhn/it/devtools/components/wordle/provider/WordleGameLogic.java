package de.hhn.it.devtools.components.wordle.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.wordle.*;

import java.security.SecureRandom;

public class WordleGameLogic implements WordleService{

  private String currentWordleSolution;
  private String previousWordleSolution;
  private SecureRandom csprng = new SecureRandom();
  @Override
  public void startGame() {
    String newSolution = selectWordle();
    setPreviousWordleSolution(newSolution);
    new WordleGame(newSolution);
  }

  @Override
  public void startAnotherGame() {
    String newSolution = selectWordle();
    if(!newSolution.equals(getPreviousWordleSolution())) {
      new WordleGame(newSolution);
    }
    else {
      startAnotherGame();
    }
  }
  @Override
  public String selectWordle() {
    int randomInt = csprng.nextInt(WordleSolutionSelector.getSolutionListLength());
    currentWordleSolution = WordleSolutionSelector.accessListAtIndex(randomInt);
    return currentWordleSolution;
  }

  @Override
  public void validateWordleGuess(WordleGuess guess) throws IllegalGuessException {
    checkIfGuessIsLongEnough(guess);
  }

  /**
   * A method that checks whether the guess is entirely correct or only partially.
   *
   * @param guess The WordleGuess entered by the player
   * @return true if guess is equal to solution and false otherwise
   */
  public boolean checkIfGuessIsEqualToSolution(WordleGuess guess){
    String enteredWordleGuess = guess.getWordleGuessAsString().toLowerCase();
    WordlePanel[] wordlePanels = guess.getWordleWord();
    return checkIfGuessIsCorrect(enteredWordleGuess, guess) ||
        checkPanelsIndividually(enteredWordleGuess, wordlePanels, guess);
  }


  /**
   * A method that checks if the WordleGuess is equal to the solution.
   *
   * @param enteredWordleGuess The String value of the entered WordleGuess
   * @param guess The WordleGuess that the player entered
   * @return true if guess is equal to solution and false otherwise
   */
  public boolean checkIfGuessIsCorrect(String enteredWordleGuess, WordleGuess guess) {
    if (enteredWordleGuess.equals(currentWordleSolution.toLowerCase())) {
      for (WordlePanel panel : guess.getWordleWord()) {
        panel.setState(State.CORRECT);
        return true;
      }
    }
    return false;
  }

  /**
   * A method that checks if the given guess is long enough.
   *
   * @param guess The guess entered by the player
   * @throws IllegalGuessException is thrown if guess is not long enough
   */
  public void checkIfGuessIsLongEnough (WordleGuess guess) throws IllegalGuessException {
    for (WordlePanel panel : guess.getWordleWord()) {
      if (panel.getLetter() == ' ')
        throw new IllegalGuessException("Wordle guess does not contain five valid characters!");
    }
  }

  /**
   * A method that checks and updates each WordlePanel within the players guess individually according to game rules.
   *
   * @param enteredWordleGuess The String value of the entered WordleGuess
   * @param wordlePanels The WordlePanels Array of the given guess
   * @param guess The WordleGuess made by the player
   * @return always returns false since given guess is not the solution
   */
  public boolean checkPanelsIndividually(String enteredWordleGuess, WordlePanel[] wordlePanels, WordleGuess guess) {
    for (int i = 0; i < guess.getWordleWord().length; i++) {
      if (enteredWordleGuess.charAt(i) == currentWordleSolution.charAt(i)) {
        wordlePanels[i].setState(State.CORRECT);
      } else if (currentWordleSolution.contains(Character.toString(enteredWordleGuess.charAt(i)))) {
        wordlePanels[i].setState(State.PARTIALLY_CORRECT);
      } else {
        wordlePanels[i].setState(State.FALSE);
      }
    }
    return false;
  }

  @Override
  public void quitGame() {

  }

  @Override
  public void addCallback(int id, WordlePanelListener listener) throws IllegalParameterException {

  }

  @Override
  public void removeCallback(int id, WordlePanelListener listener) throws IllegalParameterException {

  }

  public String getPreviousWordleSolution() {
    return previousWordleSolution;
  }

  public void setPreviousWordleSolution(String previousWordleSolution) {
    this.previousWordleSolution = previousWordleSolution;
  }
}
