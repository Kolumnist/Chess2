package de.hhn.it.devtools.javafx.controllers.connectfour.helper;


import de.hhn.it.devtools.apis.connectfour.GameState;
import de.hhn.it.devtools.apis.connectfour.Mode;
import de.hhn.it.devtools.apis.connectfour.Profile;
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
  public static ConnectFour getInstance() {
    return connectFour;
  }


  /**
   * Reset the ConnectFour instance to a new instance.
   */
  public static void reset() {
    connectFour = new ConnectFour();
  }

  /**
   * Reset the ConnectFour and use previous values.
   */
  public static void restart() {
    Mode m = connectFour.getMode();
    Profile p1 = connectFour.getProfileA();
    Profile p2 = connectFour.getProfileB();
    GameState s = connectFour.getGameState();
    reset();

    connectFour.setMode(m);
    // change starting player
    if (s == GameState.FINISHED) {
      connectFour.chooseProfileA(p2);
      connectFour.chooseProfileB(p1);
    } else {
      connectFour.chooseProfileA(p1);
      connectFour.chooseProfileB(p2);
    }
  }
}
