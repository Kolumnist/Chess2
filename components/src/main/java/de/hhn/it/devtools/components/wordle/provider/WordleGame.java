package de.hhn.it.devtools.components.wordle.provider;

import de.hhn.it.devtools.apis.wordle.IllegalGuessException;

public class WordleGame {

  private String solution;
  private final WordleGuess[] playerGuesses;
  private int wordleGuessIndex;
  private int wordlePanelIndex;
  private final WordleGameLogic backend;
  private boolean isGameFinished;

  public WordleGame(String solution, WordleGameLogic backend) {
    this.solution = solution;
    this.playerGuesses = new WordleGuess[6];
    this.wordleGuessIndex = 0;
    this.wordlePanelIndex = 0;
    this.backend = backend;
    for (int i = 0; i <= 5; i++){
      playerGuesses[i] = new WordleGuess();
    }
  }

  public String getSolution() {
    return solution;
  }

  public WordleGuess[] getPlayerGuesses() {
    return playerGuesses;
  }

  public WordleGameLogic getBackend() {
    return backend;
  }

  public int getWordleGuessIndex() {
    return wordleGuessIndex;
  }

  public int getWordlePanelIndex() {
    return wordlePanelIndex;
  }
}
