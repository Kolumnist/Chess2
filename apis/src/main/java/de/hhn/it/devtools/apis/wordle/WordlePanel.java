package de.hhn.it.devtools.apis.wordle;

/**
 * A class that models a singular panel within a Wordle guess.
 */
public class WordlePanel {

  private char letter;
  private State state;
  private int id;

  /**
   * Constructor for WordlePanel which assigns a char to the field letter and an id.
   *
   * @param letter The letter that will be entered into the field letter.
   * @param id The id given to a Wordle panel in order to identify and differentiate it.
   */
  public WordlePanel(char letter, int id) {

    this.letter = letter;
    this.state = State.INITIAL;
    this.id = id;

  }

  /**
   * Deletes the content of the field letter.
   */
  public void deleteLetter() {
    this.letter = ' ';
  }

  public char getLetter() {
    return letter;
  }

  public void setLetter(char letter) {
    this.letter = letter;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}

