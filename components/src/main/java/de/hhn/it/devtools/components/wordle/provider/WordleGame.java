package de.hhn.it.devtools.components.wordle.provider;

public class WordleGame {

  private String solution;
  private WordleGuess[] playerGuesses;
  private int wordleGuessIndex;
  private int wordlePanelIndex;
  private WordleGameLogic backend;

  public WordleGame(String solution, WordleGameLogic backend) {
    this.solution = solution;
    this.playerGuesses = new WordleGuess[6];
    this.wordleGuessIndex = 0;
    this.wordlePanelIndex = 0;
    this.backend = backend;
    for (int i = 0; i <= 5; i++){
      playerGuesses[i] = new WordleGuess("     ");
    }
  }


}
