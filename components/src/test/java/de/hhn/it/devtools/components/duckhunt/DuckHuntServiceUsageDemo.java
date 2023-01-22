package de.hhn.it.devtools.components.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.DuckData;
import de.hhn.it.devtools.apis.duckhunt.DuckHuntListener;
import de.hhn.it.devtools.apis.duckhunt.DucksInfo;
import de.hhn.it.devtools.apis.duckhunt.GameInfo;
import de.hhn.it.devtools.apis.duckhunt.IllegalDuckIdException;
import de.hhn.it.devtools.apis.duckhunt.IllegalDuckPositionException;
import de.hhn.it.devtools.apis.duckhunt.IllegalGameInfoException;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * This is a runnable usage demo.
 */
public class DuckHuntServiceUsageDemo implements DuckHuntListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuckHuntServiceUsageDemo.class);
  public DucksInfo ducksInfo;

  /**
   * Shows how the components can be used.
   *
   * @param args start parameters
   */
  public static void main(String[] args) throws IllegalParameterException, InterruptedException {
    // main menu

    // create new game with default settings
    DuckHunt game = new DuckHunt();

    // add listener
    DuckHuntServiceUsageDemo listener = new DuckHuntServiceUsageDemo();
    game.addCallback(listener);

    game.startGame();

    Thread.sleep(5000);
    game.pauseGame();
    Thread.sleep(1000);

    // shoot at duck
    //game.shoot(listener.ducksInfo.duckData()[0].getX(), listener.ducksInfo.duckData()[0].getY());

    Thread.sleep(1000);

    System.out.println("Test");
    game.stopGame();
  }

  @Override
  public void newState(GameInfo gameInfo) throws IllegalGameInfoException {
    //logger.debug(gameInfo.toString());
  }

  @Override
  public void newDuckPosition(DucksInfo duckPosition) throws IllegalDuckPositionException {
    ducksInfo = duckPosition;
    for (DuckData duck : duckPosition.duckData()) {
      //logger.debug(duck.toString());
    }
  }

  @Override
  public void duckHit(int id) throws IllegalDuckIdException {
    logger.debug("Duck " + id + " was hit");
  }
}
