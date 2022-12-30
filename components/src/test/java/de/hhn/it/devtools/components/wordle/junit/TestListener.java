package de.hhn.it.devtools.components.wordle.junit;

import de.hhn.it.devtools.apis.wordle.State;
import de.hhn.it.devtools.apis.wordle.WordlePanelListener;

public class TestListener implements WordlePanelListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestListener.class);

  @Override
  public void newState(State state) {
    logger.info("Callback state is: " + state);
  }
}
