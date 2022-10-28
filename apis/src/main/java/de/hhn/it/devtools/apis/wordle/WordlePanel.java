package de.hhn.it.devtools.apis.wordle;

/**
 * A class that models a singular panel within a Wordle guess.
 */
public class WordlePanel {

  private char letter;

  /**
   * Constructor for WordlePanel which assigns a char to the field letter.
   *
   * @param letter The letter that will be entered into the field letter.
   */
  public WordlePanel(char letter) {
    this.letter = letter;
  }

  public char getLetter() {
    return letter;
  }

  public void setLetter(char letter) {
    this.letter = letter;
  }

  /**
   * Deletes the content of the field letter.
   */
  public void deleteLetter() {
    this.letter = ' ';
  }

}
