package de.hhn.it.devtools.apis.wordle;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

public interface WordlePanelService {
  public void deleteLetter();

  public char getLetter();

  public void setLetter(char letter);

  public State getState();

  public void setState(State state);

  public int getId();

  public void setId(int id);

  public void addCallback(final WordlePanelListener listener) throws IllegalParameterException;

  public void removeCallback(final WordlePanelListener listener) throws IllegalParameterException;

  public void notifyListeners(State panelState);
}
