package de.hhn.it.devtools.components.wordle.provider.panelstates;

import de.hhn.it.devtools.apis.wordle.State;
import de.hhn.it.devtools.components.wordle.provider.WordlePanel;

public abstract class PanelState {

  protected State state;
  protected WordlePanel panel;

  public PanelState(WordlePanel panel){
    this.panel = panel;
  }

  public State getState() {
    return state;
  }

  public void setState(final State state) {
    this.state = state;
  }
}
