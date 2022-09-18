package de.hhn.it.devtools.components.example.coffeemakerservice.provider.makerstates;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.State;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMaker;

/**
 * State when the CoffeeMaker is brewing a coffee.
 */
public class BrewingState extends MakerState {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(BrewingState.class);
  private static final long BREWING_TIME_MILLIS = 1000;

  /**
   * Standard constructor for States.
   *
   * @param maker related maker
   */
  public BrewingState(final WnckCoffeeMaker maker) {
    super(maker);
    state = State.BREWING;
    spawnBrewingThread();
  }

  class RunBrewing implements Runnable {

    @Override
    public void run() {
      logger.debug("Brewing Thread " + Thread.currentThread());
      try {
        Thread.sleep(BREWING_TIME_MILLIS);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      maker.setMakerState(new ReadyState(maker));
      logger.debug("Brewing ended. new state = {}", maker.getMakerState());

    }
  }

  private void spawnBrewingThread() {
    Thread brewingThread = new Thread(new RunBrewing());
    brewingThread.start();
  }

  @Override
  public void onSwitchOn() throws IllegalStateException {
    throw MessageHelper.alreadySwitchedOn;
  }

  @Override
  public void onSwitchOff() throws IllegalStateException {
    throw MessageHelper.stillBrewing;
  }

  @Override
  public void onBrew() throws IllegalStateException {
    throw MessageHelper.alreadyBrewing;
  }

  @Override
  public void onCleanIt() throws IllegalStateException {
    throw MessageHelper.stillBrewing;
  }
}
