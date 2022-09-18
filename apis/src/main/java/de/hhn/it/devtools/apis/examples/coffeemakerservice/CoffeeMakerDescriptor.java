package de.hhn.it.devtools.apis.examples.coffeemakerservice;

/**
 * Describes a coffee maker, its type, model, location, id and actual state.
 */
public class CoffeeMakerDescriptor {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(CoffeeMakerDescriptor.class);

  private String location;
  private String model;
  private State state;
  private int id;

  /**
   * Constructor stating location and model of the CoffeeMaker.
   *
   * @param location location where the CoffeeMaker is accessible
   * @param model model of the CoffeeMaker
   */
  public CoffeeMakerDescriptor(final String location, final String model) {
    this.location = location;
    this.model = model;
    id = 0;
  }

  /**
   * Returns the location of the CoffeeMaker.
   *
   * @return location of the CoffeeMaker
   */
  public String getLocation() {
    return location;
  }


  /**
   * Returns the model.
   *
   * @return model of the CoffeeMaker, e.g. Nespresso, Senseo, ...
   */
  public String getModel() {
    return model;
  }

  /**
   * returns the actual state of the CoffeeMaker.
   *
   * @return actual state
   */
  public State getState() {
    return state;
  }

  /**
   * Sets the actual state of the CoffeeMaker.
   *
   * @param state actual state
   */
  public void setState(final State state) {
    this.state = state;
  }

  /**
   * returns the id of the CoffeeMaker.
   *
   * @return unique id of the CoffeeMaker
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the id of the CoffeeMaker.
   *
   * @param id id of the CoffeeMaker
   */
  public void setId(final int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "CoffeeMakerDescriptor{"
            + "location='" + location + '\''
            + ", model='" + model + '\''
            + ", state=" + state
            + ", id=" + id
            + '}';
  }
}
