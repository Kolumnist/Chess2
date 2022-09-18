package de.hhn.it.devtools.components.example.coffeemakerservice.provider.makerstates;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.State;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMaker;

/**
 * State when the CoffeeMaker is cleaning the brewing system.
 */
public class CleaningState extends MakerState {
  public static final int CLEANING_TIME_MILLIS = 1000;
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(CleaningState.class);

  /**
   * Standard constructor for States.
   *
   * @param maker related maker
   */
  public CleaningState(final WnckCoffeeMaker maker) {
    super(maker);
    state = State.CLEANING;
    spawnCleaningThread();
  }

  /**
   * Simulates the cleaning action of a coffee maker.
   */
  private void spawnCleaningThread() {

    // start the new thread
    Thread cleaningThread = new Thread(new RunCleaning());
    cleaningThread.start();
  }

  @Override
  public void onSwitchOn() throws IllegalStateException {
    throw MessageHelper.alreadySwitchedOn;
  }

  @Override
  public void onSwitchOff() throws IllegalStateException {
    throw MessageHelper.stillCleaning;
  }

  @Override
  public void onBrew() throws IllegalStateException {
    throw MessageHelper.stillCleaning;
  }

  @Override
  public void onCleanIt() throws IllegalStateException {
    throw MessageHelper.alreadyCleaning;
  }

  class RunCleaning implements Runnable {

    @Override
    public void run() {
      logger.debug("Cleaning Thread " + Thread.currentThread());
      try {
        Thread.sleep(CLEANING_TIME_MILLIS);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      maker.setMakerState(new ReadyState(maker));
      logger.debug("Cleaning ended. new state = {}", maker.getMakerState());

    }
  }
}
