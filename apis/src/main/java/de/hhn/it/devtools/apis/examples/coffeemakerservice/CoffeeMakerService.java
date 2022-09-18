package de.hhn.it.devtools.apis.examples.coffeemakerservice;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.List;

/**
 * This CoffeeMakerService is an interface to a bunch of coffee makers, e.g. in an office.
 * For simplicity all coffee makers have the same functionality.
 */
public interface CoffeeMakerService {

  /**
   * Returns a list of registered coffee makers.
   *
   * @return List of registered coffee makers
   */
  List<CoffeeMakerDescriptor> getCoffeeMakers();

  /**
   * Returns the descriptor of the coffee maker with the given id.
   *
   * @param id id of the coffee maker
   * @return descriptor of the coffee maker
   * @throws IllegalParameterException if the id does not exist
   */
  CoffeeMakerDescriptor getCoffeeMaker(int id) throws IllegalParameterException;

  /**
   * Adds a listener to get updates on the state of the coffee maker.
   *
   * @param id       id of the coffee maker
   * @param listener object implementing the listener interface
   * @throws IllegalParameterException if either the id does not exist or the listener is a null
   *                                   reference.
   */
  void addCallback(int id, CoffeeMakerListener listener) throws IllegalParameterException;

  /**
   * Removes a listener.
   *
   * @param id       id of the coffee maker
   * @param listener listener to be removed
   * @throws IllegalParameterException if the id is invalid or the listener is null
   */
  void removeCallback(int id, CoffeeMakerListener listener) throws IllegalParameterException;

  /**
   * Switches the machine on.
   *
   * @param id id of the maker
   * @throws IllegalParameterException if the maker does not exist
   * @throws IllegalStateException     if the maker is already switched on
   */
  void switchOn(int id) throws IllegalParameterException, IllegalStateException;

  /**
   * Switches the machine off.
   *
   * @param id id of the maker
   * @throws IllegalParameterException if the given id does not exist
   * @throws IllegalStateException     if the maker is OFF
   */
  void switchOff(int id) throws IllegalParameterException, IllegalStateException;

  /**
   * Brews a coffee.
   *
   * @param id     id of the coffee maker
   * @param recipe Recipe with quantities of coffee, milk, sugar, ...
   * @throws IllegalParameterException if the maker does not exist or the recipe is a null
   *                                   reference
   * @throws IllegalStateException     if the maker cannot brew now
   */
  void brewCoffee(int id, Recipe recipe) throws IllegalParameterException, IllegalStateException;

  /**
   * Starts the cleaning process.
   *
   * @param id id of the machine
   * @throws IllegalParameterException if the maker does not exist
   * @throws IllegalStateException     if the maker cannot start cleaning now
   */
  void cleanIt(int id) throws IllegalParameterException, IllegalStateException;
}
