package de.hhn.it.devtools.components.wordle.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.wordle.State;
import de.hhn.it.devtools.apis.wordle.WordlePanelListener;
import de.hhn.it.devtools.apis.wordle.WordlePanelService;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that models a singular panel within a Wordle guess.
 */
public class WordlePanel implements WordlePanelService {

  private char letter;
  private State state;
  private static int id;

  private List<WordlePanelListener> listeners = new ArrayList<>();

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
    this.id++;
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

  public void addCallback(final WordlePanelListener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (listeners.contains(listener)) {
      throw new IllegalParameterException("Listener already registered.");
    }

    listeners.add(listener);
  }
  public void removeCallback(final WordlePanelListener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (!listeners.contains(listener)) {
      throw new IllegalParameterException("Listener is not registered:" + listener);
    }

    listeners.remove(listener);
  }
  public void notifyListeners(State panelState) {
    listeners.forEach((listener) -> listener.newState(panelState));
  }
}

