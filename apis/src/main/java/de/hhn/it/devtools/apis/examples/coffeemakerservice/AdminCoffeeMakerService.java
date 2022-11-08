package de.hhn.it.devtools.apis.examples.coffeemakerservice;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * Admin interface to the WnckCoffeMakerService to add / remove CoffeeMakers to / from the service.
 */
public interface AdminCoffeeMakerService {

  /**
   * Adds a new CoffeeMaker to the service.
   *
   * @param descriptor descriptor of the new coffee maker
   * @throws IllegalParameterException if the descriptor is a null reference or incomplete.
   */
  void addCoffeeMaker(CoffeeMakerDescriptor descriptor) throws IllegalParameterException;

  /**
   * Removes a CoffeeMaker from the service.
   *
   * @param coffeeMakerId id of the coffee maker to be removed
   * @throws IllegalParameterException if the id of the coffee maker does not exist.
   */
  void removeCoffeeMaker(int coffeeMakerId) throws IllegalParameterException;

}
