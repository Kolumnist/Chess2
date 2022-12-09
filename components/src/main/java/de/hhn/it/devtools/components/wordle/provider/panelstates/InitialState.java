package de.hhn.it.devtools.components.wordle.provider.panelstates;

import de.hhn.it.devtools.apis.wordle.State;
import de.hhn.it.devtools.components.wordle.provider.WordlePanel;

public class InitialState extends PanelState{
  public InitialState(WordlePanel panel) {
    super(panel);
    state = State.INITIAL;
  }
}
