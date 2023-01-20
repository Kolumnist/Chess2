package de.hhn.it.devtools.components.wordle.provider;

import de.hhn.it.devtools.apis.wordle.State;
import de.hhn.it.devtools.apis.wordle.WordleGuessService;
import de.hhn.it.devtools.apis.wordle.WordlePanelService;

/**
 * A class that models an entire Wordle guess, consisting of five WordlePanels.
 */
public class WordleGuess implements WordleGuessService {
  private WordlePanelService[] wordleWord = new WordlePanel[5];

  /**
   * Constructor of WordleGuess which will be entered into the WordlePanel Array.
   */

  public WordleGuess() {
    for (int i = 0; i < wordleWord.length; i++) {
      // i als ID entfernt, da ID inhärent über static counter vergeben wird
      wordleWord[i] = new WordlePanel(' ');
      wordleWord[i].setState(State.INITIAL);
    }
  }
  public WordleGuess(String word) {
    for (int i = 0; i < wordleWord.length; i++) {
      // i als ID entfernt, da ID inhärent über static counter vergeben wird
      wordleWord[i] = new WordlePanel(word.toLowerCase().charAt(i));
      wordleWord[i].setState(State.INITIAL);
    }
  }

  public WordlePanelService[] getWordleWord() {
    return wordleWord;
  }

  public void changeContentsOfWordlePanels(String newContent){
    for(int i = 0; i<=4;i++){
      setLetterAtIndex(i, newContent.toLowerCase().charAt(i));
    }
  }

  /**
   * Sets the letter at the given index to the given char.
   *
   * @param index The index at which the letter should be set
   * @param letter The letter which will be set
   */
  public void setLetterAtIndex(int index, char letter) {
    wordleWord[index].setLetter(letter);
  }

  public char getLetterAtIndex(int index) {
    return wordleWord[index].getLetter();
  }

  /**
   * Deletes the letter at the given index.
   *
   * @param index The index at which the letter should be deleted
   */
  public void deleteLetterAtIndex(int index) {
    wordleWord[index].deleteLetter();
  }

  /**
   * Method that returns the contents of the WordlePanel Array as a String
   * @return String that contains the guess as a String
   */
  public String getWordleGuessAsString() {
    StringBuilder wordleGuessAsString = new StringBuilder();
    for (WordlePanelService wordlePanel : wordleWord) {
      wordleGuessAsString.append(wordlePanel.getLetter());
    }
    return String.valueOf(wordleGuessAsString).toLowerCase();
  }
}
