package de.hhn.it.devtools.apis.wordle;

/**
 * A class that models an entire Wordle guess, consisting of five WordlePanels.
 */
public class WordleGuess {
  private WordlePanel[] wordleWord = new WordlePanel[5];

  /**
   * Constructor of WordleGuess which will be entered into the WordlePanel Array.
   *
   * @param word The word which will be embedded in the WordlePanel Array.
   */
  public WordleGuess(String word) {
    for (int i = 0; i < wordleWord.length; i++) {
      wordleWord[i] = new WordlePanel(word.charAt(i));
    }
  }

  public WordlePanel[] getWordleWord() {
    return wordleWord;
  }

  public void setWordleWord(WordlePanel[] wordleWord) {
    this.wordleWord = wordleWord;
  }

  public void setLetterAtIndex(int index, char letter) {
    wordleWord[index].setLetter(letter);
  }
}
