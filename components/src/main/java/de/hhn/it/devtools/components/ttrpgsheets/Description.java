package de.hhn.it.devtools.components.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionType;

/**
 * A simple description for the character.
 */
public class Description {
  private DescriptionType type;
  private String description;

  /**
   * Constructor for creating a description based upon a descriptor.
   *
   * @param descriptor the descriptor
   */
  public Description(DescriptionDescriptor descriptor) {
    type = descriptor.getDescriptionType();
    description = descriptor.getText();
  }

  /**
   * Getter for the type of the description.
   *
   * @return the type of the description
   */
  public DescriptionType getType() {
    return type;
  }

  /**
   * Getter for the description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Setter for the type of the description.
   *
   * @param type the new type
   */
  public void setType(DescriptionType type) {
    this.type = type;
  }

  /**
   * Setter for the description.
   *
   * @param description the new description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return getDescription();
  } //TODO - ToString Formatierung überlegen
}
