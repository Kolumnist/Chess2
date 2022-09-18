package de.hhn.it.devtools.components.example.coffeemakerservice.provider.makerstates;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.State;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMaker;

/**
 * Base class for the different states a coffee maker can be in.
 */
public abstract class MakerState {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MakerState.class);

  protected State state;
  protected WnckCoffeeMaker maker;

  public MakerState(WnckCoffeeMaker maker) {
    logger.debug("MakerState - Constructor - " + this.getClass().getSimpleName());
    this.maker = maker;
  }

  public State getState() {
    return state;
  }

  public void setState(final State state) {
    this.state = state;
  }

  public abstract void onSwitchOn() throws IllegalStateException;

  public abstract void onSwitchOff() throws IllegalStateException;

  public abstract void onBrew() throws IllegalStateException;

  public abstract void onCleanIt() throws IllegalStateException;

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
