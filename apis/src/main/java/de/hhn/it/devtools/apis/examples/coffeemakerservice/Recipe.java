package de.hhn.it.devtools.apis.examples.coffeemakerservice;

import java.util.Objects;

/**
 * Combination of coffee powder, milk, sugar and milkFroth to prepare a personal version of a
 * coffee.
 */
public record Recipe (Quantity coffee, Quantity milk, Quantity sugar, Quantity milkFroth) {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Recipe.class);


  /**
   * Constructor for a coffee recipe.
   *
   * @param coffee Quantity of coffee
   * @param milk Quantity of milk
   * @param sugar Quantity of sugar
   * @param milkFroth Quantity of milkFroth
   */
  public Recipe {
    logger.debug("creating Recipe with - coffee {} - milk {} - sugar {} - milkfroth {}",
            coffee, milk, sugar, milkFroth);
    Objects.requireNonNull(coffee);
    Objects.requireNonNull(milk);
    Objects.requireNonNull(sugar);
    Objects.requireNonNull(milkFroth);
  }

}
