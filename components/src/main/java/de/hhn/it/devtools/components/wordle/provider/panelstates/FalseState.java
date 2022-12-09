package de.hhn.it.devtools.components.wordle.provider.panelstates;

import de.hhn.it.devtools.apis.wordle.State;
import de.hhn.it.devtools.components.wordle.provider.WordlePanel;

public class FalseState extends PanelState {
  public FalseState(WordlePanel panel) {
    super(panel);
    state = State.FALSE;
  }
}
