package de.hhn.it.devtools.components.ttrpgsheets.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionType;
import de.hhn.it.devtools.components.ttrpgsheets.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Description}.
 */
@DisplayName("Test the description")
public class DescriptionTest {

  static Description ageDescription;

  @BeforeEach
  void setup() {
    ageDescription = new Description(new DescriptionDescriptor(DescriptionType.AGE, "35"));
  }

  @Test
  @DisplayName("Create a new description with a description descriptor")
  void createNewDescriptionWithDescriptor() {
    Description description = new Description(
            new DescriptionDescriptor(DescriptionType.CHARACTER_NAME, "Sylas"));
    assertNotNull(description);
    assertEquals("Sylas", description.getDescription());
    assertEquals(DescriptionType.CHARACTER_NAME, description.getType());
  }

  @Test
  @DisplayName("Description to DescriptionDescriptor")
  void toDescriptionDescriptor() {
    DescriptionDescriptor descriptor = ageDescription.toDescriptionDescriptor();
    assertEquals(ageDescription.getType(), descriptor.getDescriptionType());
    assertEquals(ageDescription.getDescription(), descriptor.getText());
  }

  @Test
  @DisplayName("Get the type of the description")
  void getType() {
    assertEquals(DescriptionType.AGE, ageDescription.getType());
  }

  @Test
  @DisplayName("Get the description")
  void getDescription() {
    assertEquals("35", ageDescription.getDescription());
  }

  @Test
  @DisplayName("Set the description")
  void setDescription() {
    ageDescription.setDescription("36");
    assertEquals("36", ageDescription.getDescription());
  }

  @Test
  @DisplayName("To String")
  void toStringTest() {
    assertEquals("Description: [Type: AGE, Description: 35]", ageDescription.toString());
  }
}