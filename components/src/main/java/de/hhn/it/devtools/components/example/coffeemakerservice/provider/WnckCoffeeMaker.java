package de.hhn.it.devtools.components.example.coffeemakerservice.provider;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerDescriptor;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerListener;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.Recipe;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.makerstates.MakerState;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.makerstates.SwitchOffState;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple implementation of the CoffeeMaker interface.
 */
public class WnckCoffeeMaker implements CoffeeMaker {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(WnckCoffeeMaker.class);
  private static AtomicInteger idCounter = new AtomicInteger();
  private MakerState makerState;
  private List<CoffeeMakerListener> listeners;
  private CoffeeMakerDescriptor descriptor;

  /**
   * Constructor to create a WnckCoffeeMaker based on the information in the given
   * CoffeeMakerDescriptor.
   *
   * @param descriptor Descriptor with basic facts about the CoffeeMaker to be created
   */
  public WnckCoffeeMaker(CoffeeMakerDescriptor descriptor) {
    logger.debug("Constructor - {}", descriptor);
    listeners = new ArrayList<>();
    this.descriptor = descriptor;
    descriptor.setId(idCounter.getAndIncrement());
    makerState = new SwitchOffState(this);
    updateDescriptor();
  }

  public static int getIdCounter() {
    return idCounter.get();
  }

  public static void resetIdCounter() {
    WnckCoffeeMaker.idCounter.set(0);
  }

  private void updateDescriptor() {
    descriptor.setState(makerState.getState());
  }

  @Override
  public void switchOn() throws IllegalStateException {
    makerState.onSwitchOn();
  }

  @Override
  public void switchOff() throws IllegalStateException {
    makerState.onSwitchOff();
  }

  @Override
  public void brew(final Recipe recipe) throws IllegalParameterException, IllegalStateException {
    makerState.onBrew();
  }

  @Override
  public void cleanIt() throws IllegalStateException {
    makerState.onCleanIt();
  }

  @Override
  public void addCallback(final CoffeeMakerListener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (listeners.contains(listener)) {
      throw new IllegalParameterException("Listener already registered.");
    }

    listeners.add(listener);
  }

  @Override
  public void removeCallback(final CoffeeMakerListener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (!listeners.contains(listener)) {
      throw new IllegalParameterException("Listener is not registered:" + listener);
    }

    listeners.remove(listener);
  }

  @Override
  public CoffeeMakerDescriptor getDescriptor() {
    return descriptor;
  }

  public MakerState getMakerState() {
    return makerState;
  }

  /**
   * sets the state of the CoffeeMaker and notifies all listeners.
   *
   * @param makerState new maker state
   */
  public void setMakerState(final MakerState makerState) {
    logger.debug("setMakerState - {}", makerState);
    this.makerState = makerState;
    updateDescriptor();
    notifyListeners(makerState);
  }

  private void notifyListeners(MakerState makerState) {
    listeners.forEach((listener) -> listener.newState(makerState.getState()));
  }
}
