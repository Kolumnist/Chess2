package de.hhn.it.devtools.components.example.coffeemakerservice.provider.makerstates;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.State;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMaker;

/**
 * State the coffee maker is in when the machine is heating.
 */
public class HeatingState extends MakerState {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(HeatingState.class);
  public static final int HEATING_TIME_MILLIS = 1000;

  /**
   * Standard constructor for States.
   *
   * @param maker related maker
   */
  public HeatingState(final WnckCoffeeMaker maker) {
    super(maker);
    state = State.HEATING;
    spawnHeatingThread();
  }

  class RunHeating implements Runnable {


    @Override
    public void run() {
      logger.debug("Heating Thread " + Thread.currentThread());
      try {
        Thread.sleep(HEATING_TIME_MILLIS);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      maker.setMakerState(new ReadyState(maker));
      logger.debug("Heating ended. New state = {}", maker.getMakerState());

    }
  }

  private void spawnHeatingThread() {
    Thread heatingThread = new Thread(new RunHeating());
    heatingThread.start();
  }

  @Override
  public void onSwitchOn() throws IllegalStateException {
    throw MessageHelper.alreadySwitchedOn;
  }

  @Override
  public void onSwitchOff() throws IllegalStateException {
    maker.setMakerState(new SwitchOffState(maker));
  }

  @Override
  public void onBrew() throws IllegalStateException {
    throw MessageHelper.stillHeating;
  }

  @Override
  public void onCleanIt() throws IllegalStateException {
    throw MessageHelper.stillHeating;
  }
}
