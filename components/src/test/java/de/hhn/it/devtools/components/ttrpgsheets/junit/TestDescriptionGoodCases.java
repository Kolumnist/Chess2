package de.hhn.it.devtools.components.ttrpgsheets.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionType;
import de.hhn.it.devtools.components.ttrpgsheets.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Description} with good Cases.
 */
@DisplayName("Test the description with good cases.")
public class TestDescriptionGoodCases {

  static Description ageDescription;

  @BeforeEach
  void setup() {
    ageDescription = new Description(new DescriptionDescriptor(DescriptionType.AGE, "35"));
  }

  @Test
  @DisplayName("A new description is created.")
  void createNewDescriptionWithDescriptor() {
    Description description = new Description(
            new DescriptionDescriptor(DescriptionType.CHARACTER_NAME, "Sylas"));
    assertNotNull(description);
    assertEquals("Sylas", description.getDescription());
    assertEquals(DescriptionType.CHARACTER_NAME, description.getType());
  }

  @Test
  @DisplayName("Get the type of the description.")
  void getType() {
    assertEquals(DescriptionType.AGE, ageDescription.getType());
  }

  @Test
  @DisplayName("Get the description.")
  void getDescription() {
    assertEquals("35", ageDescription.getDescription());
  }

  @Test
  @DisplayName("Set the description.")
  void setDescription() {
    ageDescription.setDescription("36");
    assertEquals("36", ageDescription.getDescription());
  }
}