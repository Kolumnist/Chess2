package de.hhn.it.devtools.components.example.coffeemakerservice.junit;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerDescriptor;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerListener;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.State;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMaker;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.makerstates.HeatingState;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestCoffeeMaker {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestCoffeeMaker.class);
  public static final int DELTA = 500;

  private WnckCoffeeMaker maker;


  @BeforeEach
  void setup() {
    maker = new WnckCoffeeMaker(new CoffeeMakerDescriptor("A106", "Senseo muddy brown"));
  }


  @Test
  @DisplayName("Test registration of callback.")
  void CheckSuccessfulRegistrationOfCallback() throws IllegalParameterException,
          InterruptedException {
    SimpleCoffeeMakerListener listener = new SimpleCoffeeMakerListener();
    maker.addCallback(listener);
    maker.switchOn();
    Thread.sleep(HeatingState.HEATING_TIME_MILLIS + DELTA);
    assertEquals(2, listener.states.size());
  }

  @Test
  @DisplayName("register two listeners which get both notified.")
  void CheckRegisteringTwoListeners() throws IllegalParameterException, InterruptedException {
    SimpleCoffeeMakerListener listener1 = new SimpleCoffeeMakerListener();
    SimpleCoffeeMakerListener listener2 = new SimpleCoffeeMakerListener();
    maker.addCallback(listener1);
    maker.addCallback(listener2);
    maker.switchOn();
    Thread.sleep(HeatingState.HEATING_TIME_MILLIS + DELTA);
    assertAll(
            () -> assertEquals(2, listener1.states.size()),
            () -> assertEquals(2, listener2.states.size())
    );
  }

  @Test
  @DisplayName("Register a listener, get states, remove listeners, provoke more states.")
  void registerAndRemoveAListener() throws IllegalParameterException, InterruptedException {
    SimpleCoffeeMakerListener listener = new SimpleCoffeeMakerListener();
    maker.addCallback(listener);
    maker.switchOn();
    maker.removeCallback(listener);
    int noStates1 = listener.states.size();
    Thread.sleep(HeatingState.HEATING_TIME_MILLIS + DELTA);
    int noStates2 = listener.states.size();
    assertAll(
            () -> assertEquals(1, noStates1),
            () -> assertEquals(1, noStates2)
    );
  }


  @Test
  @DisplayName("You cannot register the same listener twice.")
  void tryRegisterSameListenerTwice() throws IllegalParameterException {
    SimpleCoffeeMakerListener listener = new SimpleCoffeeMakerListener();
    maker.addCallback(listener);
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
            () -> maker.addCallback(listener));
  }

  @Test
  @DisplayName("You cannot remove a listener which is not registered.")
  void tryRemoveListenerWhichIsNotRegistered() throws IllegalParameterException {
    SimpleCoffeeMakerListener listener = new SimpleCoffeeMakerListener();
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
            () -> maker.removeCallback(listener));
  }

  @Test
  @DisplayName("You cannot register a null reference as listener")
  void tryRegisterNullReferenceAsListener() {
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
            () -> maker.addCallback(null));
  }

  @Test
  @DisplayName("You cannot remove a null reference as listener")
  void tryRemoveNullReferenceAsListener() {
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
            () -> maker.removeCallback(null));
  }
  /**
   * Inner class as a CoffeeMakerListener.
   */
  class SimpleCoffeeMakerListener implements CoffeeMakerListener {

    public List<State> states;

    public SimpleCoffeeMakerListener() {
      states = new ArrayList<>();
    }

    @Override
    public void newState(final State state) {
      states.add(state);

    }
  }
}
