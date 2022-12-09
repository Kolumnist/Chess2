package de.hhn.it.devtools.components.wordle.provider;

public class WordleGame {

  private String solution;
  private WordleGuess[] playerGuesses;
  private int wordleGuessIndex;
  private int wordlePanelIndex;

  public WordleGame(String solution) {
    this.solution = solution;
    this.playerGuesses = new WordleGuess[6];
    this.wordleGuessIndex = 0;
    this.wordlePanelIndex = 0;
  }
}
