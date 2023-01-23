package de.hhn.it.devtools.components.game2048.junit;

import de.hhn.it.devtools.apis.game2048.Game2048Listener;
import de.hhn.it.devtools.apis.game2048.State;

public class DummyListener implements Game2048Listener {

  private int countCalls;

  public DummyListener() {
    countCalls = 0;
  }

  @Override
  public void newState(State state) {
    countCalls++;
  }

  public int getCountCalls() {
    return countCalls;
  }
}
