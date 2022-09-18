package de.hhn.it.devtools.components.example.coffeemakerservice.junit;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.AdminCoffeeMakerService;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerDescriptor;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.Quantity;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.Recipe;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.State;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.makerstates.CleaningState;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.makerstates.HeatingState;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("When a maker ...")
public class TestOneCoffeeMakerWithInteractions {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestOneCoffeeMakerWithInteractions.class);
  public static final int DELTA = 500;

  int makerId;
  private CoffeeMakerService coffeeMakerService;
  private Recipe espresso;

  @BeforeEach
  void setup() throws IllegalParameterException {
    WnckCoffeeMakerService wnckCoffeeMakerService = new WnckCoffeeMakerService();
    AdminCoffeeMakerService adminCoffeeMakerService = wnckCoffeeMakerService;
    coffeeMakerService = wnckCoffeeMakerService;
    CoffeeMakerDescriptor descriptor = new CoffeeMakerDescriptor("A106", "Senseo muddy brown");
    adminCoffeeMakerService.addCoffeeMaker(descriptor);
    List<CoffeeMakerDescriptor> makers = coffeeMakerService.getCoffeeMakers();
    makerId = makers.get(0).getId();
    espresso = new Recipe(Quantity.LARGE, Quantity.NONE, Quantity.NONE, Quantity.NONE);
  }

  @Test
  @DisplayName("is created, it is in state OFF")
  void checkStateOfCreatedMaker() throws IllegalParameterException {
    CoffeeMakerDescriptor descriptor = coffeeMakerService.getCoffeeMaker(makerId);
    assertEquals(State.OFF, descriptor.getState());
  }

  @Test
  @DisplayName("is switched on, it changes in state heating and then in state ready")
  void checkStatesHeadingAndReadyAfterSwitchedOn() throws IllegalParameterException,
          InterruptedException {
    coffeeMakerService.switchOn(makerId);
    CoffeeMakerDescriptor descriptor = coffeeMakerService.getCoffeeMaker(makerId);
    State assumeHeating = descriptor.getState();
    Thread.sleep(HeatingState.HEATING_TIME_MILLIS + DELTA);
    descriptor = coffeeMakerService.getCoffeeMaker(makerId);
    State assumeReady = descriptor.getState();
    assertAll(
            () -> assertEquals(State.HEATING, assumeHeating),
            () -> assertEquals(State.READY, assumeReady)
    );
  }

  @Test@DisplayName("is in state READY and we cleanIt, it changes in state cleaning unad then in " +
          "state ready")
  void checkStatesCleaningAndReadyAfterCleanIt() throws IllegalParameterException,
          InterruptedException {
    coffeeMakerService.switchOn(makerId);
    Thread.sleep(HeatingState.HEATING_TIME_MILLIS + DELTA);
    coffeeMakerService.cleanIt(makerId);
    CoffeeMakerDescriptor descriptor = coffeeMakerService.getCoffeeMaker(makerId);
    State assumeCleaning = descriptor.getState();
    Thread.sleep(CleaningState.CLEANING_TIME_MILLIS + DELTA);
    descriptor = coffeeMakerService.getCoffeeMaker(makerId);
    State assumeReady = descriptor.getState();
    assertAll(
            () -> assertEquals(State.CLEANING, assumeCleaning),
            () -> assertEquals(State.READY, assumeReady)
    );
  }

  @Test
  @DisplayName("is in state READY and we call brewing and immediately cleaning, we get an " +
                      "IllegalStateException")
    void checkRaiseOfExceptionWhenCleaningWhileBrewing() throws IllegalParameterException,
          InterruptedException {
    coffeeMakerService.switchOn(makerId);
    Thread.sleep(HeatingState.HEATING_TIME_MILLIS + DELTA);
    coffeeMakerService.brewCoffee(makerId, espresso);
    IllegalStateException exception = assertThrows(IllegalStateException.class,
            () -> coffeeMakerService.cleanIt(makerId));
    CoffeeMakerDescriptor descriptor = coffeeMakerService.getCoffeeMaker(makerId);
    assertEquals(State.BREWING, descriptor.getState());
    }
}
