package de.hhn.it.devtools.javafx.controllers.connectfour.helper;

import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;

/**
 * This class stores an instance of the "Connect Four" game.
 */
public class Instance {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Instance.class);

  private static ConnectFour connectFour = new ConnectFour();

  /**
   * Reset the ConnectFour instance.
   */
  public static void reset() {
    logger.info("reset: no params");
    connectFour = new ConnectFour();
  }

  /**
   * Get the ConnectFour instance.
   *
   * @return instance
   */
  public static ConnectFour getConnectFour() {
    logger.info("getConnectFour: no params");
    return connectFour;
  }
}
