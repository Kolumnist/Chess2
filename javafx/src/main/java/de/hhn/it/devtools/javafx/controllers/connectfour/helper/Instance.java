package de.hhn.it.devtools.javafx.controllers.connectfour.helper;


import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;

/**
 * This class stores an instance of the "Connect Four" game.
 */
public class Instance {
  private static ConnectFour connectFour = new ConnectFour();

  /**
   * Get the ConnectFour instance.
   *
   * @return instance
   */
  public static ConnectFour getConnectFour() {
    return connectFour;
  }
}
