package de.hhn.it.devtools.components.wordle.provider;

import de.hhn.it.devtools.apis.wordle.IllegalGuessException;

public class WordleGame {

  private final WordleGuess[] playerGuesses;
  private int wordleGuessIndex;

  public WordleGame() {
    this.playerGuesses = new WordleGuess[6];
    this.wordleGuessIndex = 0;
    for (int i = 0; i <= 5; i++){
      playerGuesses[i] = new WordleGuess();
    }
  }

  public WordleGuess[] getPlayerGuesses() {
    return playerGuesses;
  }

  public int getWordleGuessIndex() {
    return wordleGuessIndex;
  }

  public void incrementWordleGuessIndex() {
    wordleGuessIndex++;
  }

}
