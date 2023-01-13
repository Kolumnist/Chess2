package de.hhn.it.devtools.components.memory.junit;

import de.hhn.it.devtools.apis.memory.PictureCardListener;
import de.hhn.it.devtools.apis.memory.State;

public class DummyCallbackPictureCard implements PictureCardListener {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(de.hhn.it.devtools.components.memory.junit.DummyCallbackPictureCard.class);

  @Override
  public void currentState(State state) {
    logger.info("Callback called with state " + state);
  }
}
