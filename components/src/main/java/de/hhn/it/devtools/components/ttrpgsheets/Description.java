package de.hhn.it.devtools.components.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;

/**
 * A simple description for the character.
 */
public class Description {
  private String description;

  /**
   * Constructor for an empty description.
   */
  public Description() {
    description = "";
  }

  /**
   * Constructor for creating a description based upon a descriptor.
   *
   * @param descriptor the descriptor
   */
  public Description(DescriptionDescriptor descriptor) {
    description = descriptor.getText();
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
  }
}
