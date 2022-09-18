package de.hhn.it.devtools.components.example.coffeemakerservice.provider;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.AdminCoffeeMakerService;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerDescriptor;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerListener;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.Recipe;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.helper.CheckingHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple implementation of the CoffeeMakerService and AdminCoffeeMakerService interfaces.
 */
public class WnckCoffeeMakerService implements CoffeeMakerService, AdminCoffeeMakerService {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(WnckCoffeeMakerService.class);

  private Map<Integer, CoffeeMaker> makers;

  public WnckCoffeeMakerService() {
    makers = new HashMap<>();
  }

  private CoffeeMaker getMakerById(int id) throws IllegalParameterException {
    logger.info("getMakerById: id = {}", id);
    if (!makers.containsKey(id)) {
      throw new IllegalParameterException("Maker with id " + id + " does not exist.");
    }
    return makers.get(id);

  }

  /**
   * Returns a list of registered coffee makers.
   *
   * @return List of registered coffee makers
   */
  @Override
  public List<CoffeeMakerDescriptor> getCoffeeMakers() {
    logger.info("getCoffeeMakers: no params");
    List<CoffeeMakerDescriptor> results = new ArrayList<>();
    makers.values().forEach((maker) -> results.add(maker.getDescriptor()));

    return results;
  }

  /**
   * Returns the descriptor of the coffee maker with the given id.
   *
   * @param id id of the coffee maker
   * @return descriptor of the coffee maker
   * @throws IllegalParameterException if the id does not exist
   */
  @Override
  public CoffeeMakerDescriptor getCoffeeMaker(final int id) throws IllegalParameterException {
    logger.info("getCoffeeMaker: id = {}", id);
    CoffeeMaker maker = getMakerById(id);
    return maker.getDescriptor();
  }

  /**
   * Adds a listener to get updates on the state of the coffee machine.
   *
   * @param id       id of the coffee maker
   * @param listener object implementing the listener interface
   */
  @Override
  public void addCallback(final int id, final CoffeeMakerListener listener) throws
          IllegalParameterException {
    logger.info("addCallback: id = {}, listener = {}", id, listener);
    CoffeeMaker maker = getMakerById(id);
    maker.addCallback(listener);
  }

  /**
   * Removes a listener.
   *
   * @param id       id of the coffee maker
   * @param listener listener to be removed
   */
  @Override
  public void removeCallback(final int id, final CoffeeMakerListener listener) throws
          IllegalParameterException {
    logger.info("removeCallback: id = {}, listener = {}");
    CoffeeMaker maker = getMakerById(id);
    maker.removeCallback(listener);
  }

  /**
   * Switches the machine on.
   *
   * @param id id of the coffee maker
   * @throws IllegalParameterException if the machine does not exist
   * @throws IllegalStateException     if the machine is already switched on
   */
  @Override
  public void switchOn(final int id) throws IllegalParameterException, IllegalStateException {
    logger.info("switchOn: id = {}", id);
    CoffeeMaker maker = getMakerById(id);
    maker.switchOn();
  }

  /**
   * Switches the machine off.
   *
   * @param id id of the coffee maker
   * @throws IllegalParameterException if the given id does not exist
   * @throws IllegalStateException     if the machine is OFF
   */
  @Override
  public void switchOff(final int id) throws IllegalParameterException, IllegalStateException {
    logger.info("switchOff: id = {}", id);
    CoffeeMaker maker = getMakerById(id);
    maker.switchOff();
  }

  /**
   * Brews a coffee.
   *
   * @param id      id of the coffee maker
   * @param recipe Recipe with quantities of coffee, milk, sugar, ...
   * @throws IllegalParameterException if the machine does not exist or the recipe is a null
   *                                   reference
   * @throws IllegalStateException     if the machine cannot brew now
   */
  @Override
  public void brewCoffee(final int id, final Recipe recipe) throws IllegalParameterException,
          IllegalStateException {
    logger.info("brewCoffee: id = {}, recipe = {}", id, recipe);
    CoffeeMaker maker = getMakerById(id);
    if (recipe == null) {
      throw new IllegalParameterException("Recipe is a null reference.");
    }

    maker.brew(recipe);
  }

  /**
   * Starts the cleaning process.
   *
   * @param id id of the coffee maker
   * @throws IllegalParameterException if the machine does not exist
   * @throws IllegalStateException     if the machine cannot start cleaning now
   */
  @Override
  public void cleanIt(final int id) throws IllegalParameterException, IllegalStateException {
    logger.info("cleanIt: id = {}", id);
    CoffeeMaker maker = getMakerById(id);
    maker.cleanIt();

  }

  @Override
  public void addCoffeeMaker(final CoffeeMakerDescriptor descriptor) throws
          IllegalParameterException {
    logger.info("addCoffeeMaker: descriptor = {}", descriptor);
    CheckingHelper.assertThatIsNotNull(descriptor, "descriptor");
    CheckingHelper.assertThatIsReadableString(descriptor.getLocation(), "location");
    CheckingHelper.assertThatIsReadableString(descriptor.getModel(), "model");


    CoffeeMaker maker = new WnckCoffeeMaker(descriptor);
    makers.put(maker.getDescriptor().getId(), maker);

  }

  @Override
  public void removeCoffeeMaker(final int id) throws
          IllegalParameterException {
    logger.info("removeCoffeeMaker, id = {}", id);
    if (!makers.containsKey(id)) {
      throw new IllegalParameterException("maker with id " + id + " not registered.");
    }

    makers.remove(id);

  }
}
