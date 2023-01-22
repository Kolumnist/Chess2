package de.hhn.it.devtools.components.connectfour;

import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.apis.connectfour.interfaces.ConnectFourListenerInterface;
import de.hhn.it.devtools.components.connectfour.junit.DummyCallback;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;

/**
 * A usage demo for the ConnectFour component.
 */
public class ConnectFourUsageDemo {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ConnectFourUsageDemo.class);

  /**
   * Demonstrate the component use.
   *
   * @param args Nothing.
   */
  public static void main(String[] args) throws IllegalOperationException {
    // Create a new ConnectFour instance.
    ConnectFour instance = new ConnectFour();
    // Create a new listener.
    ConnectFourListenerInterface listener = new DummyCallback();
    // Create new profiles.
    logger.info(">>> create profile 'Alicia01'");
    Profile alice = instance.createProfile("Alicia01");
    logger.info(">>> create profile 'Bob'");
    Profile bob = instance.createProfile("Bob");
    logger.info(">>> create profile 'Carl'");
    Profile carl = instance.createProfile("Carl");
    // Change profile name.
    logger.info(">>> change profile name of Alicia01 to 'Alice'");
    instance.setProfileName(alice.getId(), "Alice");
    // Delete profile.
    logger.info(">>> delete Carl");
    instance.deleteProfile(carl.getId());
    // Create a new singleplayer game.
    logger.info(">>> create new multiplayer game");
    instance.playMultiplayerGame(alice, bob, true);
    // Add callback.
    logger.info(">>> set the callback");
    instance.setCallback(listener);
    // Start the game.
    logger.info(">>> start the game");
    instance.start();
    // Alice plays.
    logger.info("Alice places disc in column 2.");
    instance.placeDiscInColumn(2);
    // Bob plays.
    logger.info("Bob places disc in column 2.");
    instance.placeDiscInColumn(2);
    // Alice plays.
    logger.info("Alice places disc in column 0.");
    instance.placeDiscInColumn(0);
    // Bob plays.
    logger.info("Bob places disc in column 2.");
    instance.placeDiscInColumn(2);
    // Alice plays.
    logger.info("Alice places disc in column 3.");
    instance.placeDiscInColumn(3);
    // Bob plays.
    logger.info("Bob places disc in column 0.");
    instance.placeDiscInColumn(0);
    // Alice wins.
    logger.info("Alice places disc in column 1.");
    instance.placeDiscInColumn(1);
    logger.info("Alice won.");
  }
}
