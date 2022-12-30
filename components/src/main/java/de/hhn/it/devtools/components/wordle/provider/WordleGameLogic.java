package de.hhn.it.devtools.components.wordle.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.wordle.*;

public class WordleGameLogic implements WordleService{

  private String currentWordleSolution;
  private String previousWordleSolution;
  private static boolean wasStartGameCalled = false;


  private WordleGame currentWordleGame;
  @Override
  public void startGame() {
    String currentSolution = WordleSolutionSelector.selectWordle();
    setCurrentWordleSolution(currentSolution);
    currentWordleGame = new WordleGame(currentSolution, this);
    wasStartGameCalled = true;
  }

  @Override
  public void startAnotherGame() {
    if(!wasStartGameCalled) {
      throw new NullPointerException("Use the startGame method first");
    }
    else {
      setPreviousWordleSolution(getCurrentWordleSolution());
      String newSolution = WordleSolutionSelector.selectWordle();
      if (!newSolution.equals(getPreviousWordleSolution())) {
        setCurrentWordleSolution(newSolution);
        currentWordleGame = new WordleGame(newSolution, this);
      } else {
        startAnotherGame();
      }
    }
  }

  @Override
  public void validateWordleGuess(WordleGuessService guess) throws IllegalGuessException {
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
    return checkIfGuessIsCorrect(enteredWordleGuess, guess) ||
        checkPanelsIndividually(guess);
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
      for (WordlePanelService panel : guess.getWordleWord()) {
        panel.setState(State.CORRECT);
        panel.notifyListeners(State.CORRECT);
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
  public void checkIfGuessIsLongEnough (WordleGuessService guess) throws IllegalGuessException { // try catch Block will be implemented in the Controller class
    for (WordlePanelService panel : guess.getWordleWord()) {
      if (panel.getLetter() == ' ')
        throw new IllegalGuessException("Wordle guess does not contain five valid characters!");
    }
  }

  /**
   * A method that checks and updates each WordlePanel within the players guess individually according to game rules.
   *
   * @param guess The WordleGuess made by the player
   * @return always returns false since given guess is not the solution
   */
  public boolean checkPanelsIndividually(WordleGuess guess) {
    WordlePanelService[] wordlePanels = guess.getWordleWord();
    String enteredWordleGuess = guess.getWordleGuessAsString().toLowerCase();
    for (int i = 0; i < guess.getWordleWord().length; i++) {
      if (enteredWordleGuess.charAt(i) == currentWordleSolution.charAt(i)) {
        wordlePanels[i].setState(State.CORRECT);
        wordlePanels[i].notifyListeners(State.CORRECT);
      } else if (currentWordleSolution.contains(Character.toString(enteredWordleGuess.charAt(i)))) {
        wordlePanels[i].setState(State.PARTIALLY_CORRECT);
        wordlePanels[i].notifyListeners(State.PARTIALLY_CORRECT);
      } else {
        wordlePanels[i].setState(State.FALSE);
        wordlePanels[i].notifyListeners(State.FALSE);
      }
    }
    return false;
  }

  @Override
  public void quitGame() {
  }

  @Override
  public void addCallback(WordlePanelListener listener, WordlePanelService panel) throws IllegalParameterException {
    panel.addCallback(listener);
  }

  @Override
  public void removeCallback(WordlePanelListener listener, WordlePanelService panel) throws IllegalParameterException {
    panel.removeCallback(listener);
  }

  public String getPreviousWordleSolution() {
    return previousWordleSolution;
  }

  public void setPreviousWordleSolution(String previousWordleSolution) {
    this.previousWordleSolution = previousWordleSolution;
  }

  public void setCurrentWordleSolution(String currentWordleSolution) {
    this.currentWordleSolution = currentWordleSolution;
  }
  public String getCurrentWordleSolution() {
    return currentWordleSolution;
  }


}
