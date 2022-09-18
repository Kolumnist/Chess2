package de.hhn.it.devtools.apis.examples.coffeemakerservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRecipe {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestRecipe.class);

  private Recipe recipe;

  @Test
  @DisplayName("Create a recipe on a correct way")
  void testCreateGoodCase() {
    recipe = new Recipe(Quantity.LARGE, Quantity.NONE, Quantity.NONE, Quantity.SMALL);
  }

  @Test
  @DisplayName("Create a recipe using a null reference as one of the parameters")
  void testCreateRecipeWithNullReferences() {
    NullPointerException exception = assertThrows(NullPointerException.class, () ->
    recipe = new Recipe(Quantity.LARGE, Quantity.MEDIUM, null, Quantity.LARGE));
  }
}
