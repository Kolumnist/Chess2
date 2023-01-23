package de.hhn.it.devtools.javafx.controllers.connectfour.helper;

import de.hhn.it.devtools.apis.connectfour.interfaces.ConnectFourInterface;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;

/**
 * This class stores an instance of the "Connect Four" game.
 */
public class Instance {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Instance.class);

  private static final ConnectFourInterface connectFour = new ConnectFour();

  /**
   * Get the ConnectFour instance.
   *
   * @return instance
   */
  public static ConnectFourInterface getInstance() {
    logger.info("getInstance: no params");
    return connectFour;
  }
}
