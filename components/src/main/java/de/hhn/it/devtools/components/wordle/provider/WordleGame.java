package de.hhn.it.devtools.components.wordle.provider;

import de.hhn.it.devtools.apis.wordle.WordleGuess;

public class WordleGame {

  private String solution;
  private WordleGuess[] playerGuesses;
  private int playerGuessesIndex;
  private int currentGuessIndex;

  public WordleGame(String solution) {
    this.solution = solution;
    this.playerGuesses = new WordleGuess[6];
    this.playerGuessesIndex = 0;
    this.currentGuessIndex = 0;
  }
}
