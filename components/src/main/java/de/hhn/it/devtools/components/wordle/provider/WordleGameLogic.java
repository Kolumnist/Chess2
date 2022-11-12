package de.hhn.it.devtools.components.wordle.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.wordle.*;

import java.security.SecureRandom;

public class WordleGameLogic implements WordleService{

  private String currentWordleSolution;
  private String previousWordleSolution;
  private SecureRandom rng = new SecureRandom();
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
    int randomInt = rng.nextInt(WordleSolutionSelector.getSolutionListLength());
    currentWordleSolution = WordleSolutionSelector.accessListAtIndex(randomInt);
    return currentWordleSolution;
  }

  @Override
  public boolean validateWordleGuess(WordleGuess guess) {
    String enteredWordleGuess = guess.getWordleGuessAsString().toLowerCase();
    WordlePanel[] wordlePanels = guess.getWordleWord();
    if(enteredWordleGuess.equals(currentWordleSolution.toLowerCase())) { // checks if entered Guess is equal solution
      for (WordlePanel panel : guess.getWordleWord()) {
        panel.setState(State.CORRECT);
      }
      return true;
    }
    for (WordlePanel panel : guess.getWordleWord()) { // checks if entered Guess is five characters long
      try {
        if (panel.getLetter() == ' ')
          throw new IllegalGuessException("Wordle guess does not contain five valid characters!");
      } catch (IllegalGuessException exception) {
        System.out.println(exception + " Please enter a valid Wordle Guess!");
        return false;
      }
    }
    for (int i = 0; i < guess.getWordleWord().length; i++) {
      if (enteredWordleGuess.charAt(i) == currentWordleSolution.charAt(i)) {
        wordlePanels[i].setState(State.CORRECT);
      }
      else if (currentWordleSolution.contains(Character.toString(enteredWordleGuess.charAt(i)))) {
        wordlePanels[i].setState(State.PARTIALLY_CORRECT);
      }
      else {
        wordlePanels[i].setState(State.FALSE);
      }
    }
    return true;
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
