package de.hhn.it.devtools.components.wordle.provider;

import de.hhn.it.devtools.apis.wordle.WordlePanelService;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that models a WordleGame by keeping an Array of WordleGuesses and an index indicating
 * how many guesses the player already made.
 */
public class WordleGame {

  private final WordleGuess[] playerGuesses;
  private int wordleGuessIndex;

  private Map<Integer, WordlePanel> panels;

  /**
   * Constructor for WordleGame that initiates instance variables and also puts every panel into the
   * panel HashMap.
   */
  public WordleGame() {
    panels = new HashMap<>();
    this.playerGuesses = new WordleGuess[6];
    this.wordleGuessIndex = 0;
    for (int i = 0; i <= 5; i++) {
      playerGuesses[i] = new WordleGuess();
    }
    for (WordleGuess guess : playerGuesses) {
      WordlePanelService[] panelArray = guess.getWordleWord();
      for (WordlePanelService panel : panelArray) {
        panels.put(panel.getId(), (WordlePanel) panel);
      }
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

  public Map<Integer, WordlePanel> getPanels() {
    return panels;
  }
}
