package de.hhn.it.devtools.components.example.coffeemakerservice.provider.makerstates;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.State;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMaker;

/**
 * State the coffee maker is in it is switched off. The machine is not really switched off, but
 * in a kind of suspend state. There is still some logic available to receive a "switch on" signal.
 */
public class SwitchOffState extends MakerState {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SwitchOffState.class);

  public SwitchOffState(final WnckCoffeeMaker maker) {
    super(maker);
    state = State.OFF;
  }


  @Override
  public void onSwitchOn() throws IllegalStateException {
    maker.setMakerState(new HeatingState(maker));
  }

  @Override
  public void onSwitchOff() throws IllegalStateException {
    throw MessageHelper.alreadySwitchedOff;
  }

  @Override
  public void onBrew() throws IllegalStateException {
    throw  MessageHelper.switchOnFirst;
  }

  @Override
  public void onCleanIt() throws IllegalStateException {
    throw  MessageHelper.switchOnFirst;
  }
}
