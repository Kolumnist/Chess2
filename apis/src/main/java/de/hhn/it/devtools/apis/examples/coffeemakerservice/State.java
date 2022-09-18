package de.hhn.it.devtools.apis.examples.coffeemakerservice;

/**
 * Enum to represent the different states a coffee maker can be in.
 */
public enum State {
/** The CoffeeMaker is switched off. */
  OFF,
  /** The CoffeeMaker is heating. */
  HEATING,
  /** The CoffeeMaker is ready to brew coffee. */
  READY,
  /** The CoffeeMaker is in an error state, e.g. no water, no heating, ....*/
  ERROR,
  /** The CoffeeMaker is cleaning its brewing system. */
  CLEANING,
  /** The CoffeeMaker is brewing coffee. */
  BREWING
}
