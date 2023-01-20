package de.hhn.it.devtools.javafx.controllers.connectfour.helper;

import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;

/**
 * This class stores an instance of the "Connect Four" game.
 */
public class Instance {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Instance.class);

  private static final ConnectFour connectFour = new ConnectFour();

  /**
   * Get the ConnectFour instance.
   *
   * @return instance
   */
  public static ConnectFour getInstance() {
    logger.info("getInstance: no params");
    return connectFour;
  }
}
