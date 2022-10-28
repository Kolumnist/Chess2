package de.hhn.it.devtools.apis.wordle;

/**
 * A class that models a singular panel within a Wordle guess.
 */
public class WordlePanel {

  private char letter;

  public WordlePanel(char letter) {
    this.letter = letter;
  }

  public char getLetter() {
    return letter;
  }

  public void setLetter(char letter) {
    this.letter = letter;
  }

}
