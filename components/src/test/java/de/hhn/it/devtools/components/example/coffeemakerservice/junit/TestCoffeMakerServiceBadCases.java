package de.hhn.it.devtools.components.example.coffeemakerservice.junit;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.AdminCoffeeMakerService;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerDescriptor;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test the CoffeeMakerService with bad cases.")
@ExtendWith(CoffeeMakerParameterResolver.class)
public class TestCoffeMakerServiceBadCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestCoffeMakerServiceBadCases.class);

  CoffeeMakerService coffeeMakerService;
  AdminCoffeeMakerService adminCoffeeMakerService;

  @BeforeEach
  void setup(List<CoffeeMakerDescriptor> descriptors) throws IllegalParameterException {
    WnckCoffeeMakerService wnckCoffeeMakerService = new WnckCoffeeMakerService();
    coffeeMakerService = wnckCoffeeMakerService;
    adminCoffeeMakerService = wnckCoffeeMakerService;

    for (CoffeeMakerDescriptor descriptor : descriptors) {
      adminCoffeeMakerService.addCoffeeMaker(descriptor);
    }
  }

  @Test
  @DisplayName("ask for a non existing coffeeMaker")
  void testExceptionWhenRequestingNonExistentCoffeeMaker() {
    IllegalParameterException illegalParameterException = assertThrows(
            IllegalParameterException.class,
            () -> coffeeMakerService.getCoffeeMaker(123456));
  }

  @Test
  @DisplayName("add callback for a non existing coffeeMaker")
  void testExceptionWhenAddingCallbackToNonExistentCoffeeMaker() {
    IllegalParameterException illegalParameterException = assertThrows(
            IllegalParameterException.class,
            () -> coffeeMakerService.addCallback(123456, new DummyCallback()));
  }

  @Test
  @DisplayName("remove callback for a non existing coffeeMaker")
  void testExceptionWhenRemovingCallbackToNonExistentCoffeeMaker() {
    IllegalParameterException illegalParameterException = assertThrows(
            IllegalParameterException.class,
            () -> coffeeMakerService.removeCallback(123456, new DummyCallback()));
  }


  @Test
  @DisplayName("brew coffee with a null receipt")
  void testBrewingWithNullReceipt() throws IllegalParameterException {
    IllegalParameterException illegalParameterException = assertThrows(IllegalParameterException
            .class, () -> coffeeMakerService.brewCoffee(0, null));
  }
}
