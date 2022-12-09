package de.hhn.it.devtools.components.wordle.provider.panelstates;

import de.hhn.it.devtools.apis.wordle.State;
import de.hhn.it.devtools.components.wordle.provider.WordlePanel;

public class PartiallyCorrectState extends PanelState{
  public PartiallyCorrectState(WordlePanel panel) {
    super(panel);
    state = State.PARTIALLY_CORRECT;
  }
}
