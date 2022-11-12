package de.hhn.it.devtools.components.wordle.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import de.hhn.it.devtools.apis.wordle.WordleGuess;
import de.hhn.it.devtools.apis.wordle.WordlePanelListener;
import de.hhn.it.devtools.apis.wordle.WordleService;
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
  public boolean validateWordleGuess(WordleGuess guess) throws IllegalGuessException {
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
