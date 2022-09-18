package de.hhn.it.devtools.components.example.coffeemakerservice.provider.makerstates;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.State;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMaker;

/**
 * State the coffee maker is in, when an error occured which could not be resolved.
 */
public class ErrorState extends MakerState {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ErrorState.class);

  public ErrorState(final WnckCoffeeMaker maker) {
    super(maker);
    state = State.ERROR;
  }

  @Override
  public void onSwitchOn() throws IllegalStateException {
    throw MessageHelper.errorState;
  }

  @Override
  public void onSwitchOff() throws IllegalStateException {
    maker.setMakerState(new SwitchOffState(maker));
  }

  @Override
  public void onBrew() throws IllegalStateException {
    throw MessageHelper.errorState;
  }

  @Override
  public void onCleanIt() throws IllegalStateException {
    throw MessageHelper.errorState;
  }
}
