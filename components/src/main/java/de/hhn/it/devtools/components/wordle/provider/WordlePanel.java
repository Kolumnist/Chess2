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
  private static int panelCount = 0;
  private int panelId;
  private List<WordlePanelListener> listeners = new ArrayList<>();

  /**
   * Constructor for WordlePanel which assigns a char to the field letter and an id.
   *
   * @param letter The letter that will be entered into the field letter.
   */
  public WordlePanel(char letter) {
    this.letter = letter;
    this.state = State.INITIAL;
    panelId = panelCount;
    panelCount++;
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
    return panelId;
  }

  public void setId(int id) {
    panelId = id;
  }

  /**
   * Method that adds a given Listener to this Panel.
   *
   * @param listener Listener which is to be added to this Panel
   * @throws IllegalParameterException Thrown when the listener is either already registered
   *      or the given Listener was a null reference
   */
  public void addCallback(final WordlePanelListener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (listeners.contains(listener)) {
      throw new IllegalParameterException("Listener already registered.");
    }

    listeners.add(listener);
  }

  /**
   * Method that removes a Listener from this panel.
   *
   * @param listener Listener which is to be removed
   * @throws IllegalParameterException Thrown when the Listener which was to be removed is either
   *      a null reference of the given Listener was not registered in the first place
   */
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

  public static void setPanelCount(int panelCount) {
    WordlePanel.panelCount = panelCount;
  }
}

