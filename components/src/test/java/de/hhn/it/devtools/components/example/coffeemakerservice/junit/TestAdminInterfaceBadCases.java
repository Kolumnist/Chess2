package de.hhn.it.devtools.components.example.coffeemakerservice.junit;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.AdminCoffeeMakerService;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerDescriptor;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test admin interface with bad cases.")
public class TestAdminInterfaceBadCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestAdminInterfaceBadCases.class);

  CoffeeMakerService coffeeMakerService;
  AdminCoffeeMakerService adminCoffeeMakerService;

  @BeforeEach
  void setup() {
    WnckCoffeeMakerService wnckCoffeeMakerService = new WnckCoffeeMakerService();
    coffeeMakerService = wnckCoffeeMakerService;
    adminCoffeeMakerService = wnckCoffeeMakerService;
  }


  @Test
  @DisplayName("Test bad creation of maker with null reference as descriptor")
  public void createMakerWithNullReference() throws IllegalParameterException {
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
            () -> adminCoffeeMakerService.addCoffeeMaker(null));
  }

  @Test
  @DisplayName("Test bad creation with null reference as location in descriptor")
  public void createMakerWithDescriptorWithNullLocation() {
    CoffeeMakerDescriptor descriptor = new CoffeeMakerDescriptor(null, "Model A");
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
            () -> adminCoffeeMakerService.addCoffeeMaker(descriptor));
  }

  @Test
  @DisplayName("Test bad creation with null reference as model in descriptor")
  public void createMakerWithDescriptorWithNullModel() {
    CoffeeMakerDescriptor descriptor = new CoffeeMakerDescriptor("ABC", null);
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
            () -> adminCoffeeMakerService.addCoffeeMaker(descriptor));
  }

  @Test
  @DisplayName("Test bad creation with empty string as location in descriptor")
  public void createMakerWithDescriptorWithEmptyStringLocation() {
    CoffeeMakerDescriptor descriptor = new CoffeeMakerDescriptor("", "Model A");
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
            () -> adminCoffeeMakerService.addCoffeeMaker(descriptor));
  }

  @Test
  @DisplayName("Test bad creation with empty string as model in descriptor")
  public void createMakerWithDescriptorWithEmptyStringModel() {
    CoffeeMakerDescriptor descriptor = new CoffeeMakerDescriptor("ABC", "");
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
            () -> adminCoffeeMakerService.addCoffeeMaker(descriptor));
  }

  @Test
  @DisplayName("Test bad creation with just white space as location in descriptor")
  public void createMakerWithDescriptorWithWhitespaceLocation() {
    CoffeeMakerDescriptor descriptor = new CoffeeMakerDescriptor("  ", "Model A");
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
            () -> adminCoffeeMakerService.addCoffeeMaker(descriptor));
  }

  @Test
  @DisplayName("Test bad creation with empty string as model in descriptor")
  public void createMakerWithDescriptorWithWhitespaceModel() {
    CoffeeMakerDescriptor descriptor = new CoffeeMakerDescriptor("ABC", "      ");
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
            () -> adminCoffeeMakerService.addCoffeeMaker(descriptor));
  }

  @Test
  @DisplayName("Test removal of a non existing coffeeMaker")
  void testExceptionWhenRemovingNonExistentCoffeeMaker() {
    IllegalParameterException illegalParameterException = assertThrows(
            IllegalParameterException.class,
            () -> adminCoffeeMakerService.removeCoffeeMaker(123456));
  }

}
