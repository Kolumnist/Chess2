package de.hhn.it.devtools.components.ttrpgsheets.junit;

import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionType;
import de.hhn.it.devtools.components.ttrpgsheets.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TestDescriptionBadCases {

  static Description characterClassDescription;

  @BeforeEach
  void setup() {
    characterClassDescription = new Description(
            new DescriptionDescriptor(DescriptionType.CHARACTER_CLASS, "Warrior"));
  }

  @Test
  @DisplayName("Get the wrong type of the description.")
  void getType() {
    assertNotEquals(DescriptionType.AGE, characterClassDescription.getType());
  }

  @Test
  @DisplayName("Get the wrong description.")
  void getDescription() {
    assertNotEquals("Mage", characterClassDescription.getDescription());
  }

  @Test
  @DisplayName("Set description didn't work.")
  void setDescription() {
    String oldDescription = characterClassDescription.getDescription();
    characterClassDescription.setDescription("Bard");
    assertNotEquals(oldDescription, characterClassDescription.getDescription());
  }
}