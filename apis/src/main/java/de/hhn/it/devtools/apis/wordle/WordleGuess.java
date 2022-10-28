package de.hhn.it.devtools.apis.wordle;

/**
 * A class that models an entire Wordle guess, consisting of five WordlePanels.
 */
public class WordleGuess {
  private char[] wordleWord = new char[5];

  /**
   * Constructor of WordleGuess which will be entered into the WordlePanel Array.
   *
   * @param word The word which will be embedded in the WordlePanel Array.
   */
  public WordleGuess(String word) {
    for (int i = 0; i < wordleWord.length; i++) {
      wordleWord[i] = word.charAt(i);
    }
  }

  public char[] getWordleWord() {
    return wordleWord;
  }

  public void setWordleWord(char[] wordleWord) {
    this.wordleWord = wordleWord;
  }

  /**
   * Sets the letter at the given index to the given char.
   *
   * @param index The index at which the letter should be set
   * @param letter The letter which will be set
   */
  public void setLetterAtIndex(int index, char letter) {
    wordleWord[index] = letter;
  }

  /**
   * Deletes the letter at the given index.
   *
   * @param index The index at which the letter should be deleted
   */
  public void deleteLetterAtIndex(int index) {
    wordleWord[index] = ' ';
  }
}
