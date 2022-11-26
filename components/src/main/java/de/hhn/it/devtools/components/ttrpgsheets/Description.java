package de.hhn.it.devtools.components.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple description for the character.
 */
public class Description {
  private static final Logger logger = LoggerFactory.getLogger(Description.class);
  private final DescriptionType type;
  private String description;

  /**
   * Constructor for creating a description based upon a descriptor.
   *
   * @param descriptionDescriptor the descriptor
   */
  public Description(DescriptionDescriptor descriptionDescriptor) {
    logger.info("Constructor is called. Parameter: descriptionDescriptor = "
            + descriptionDescriptor);
    type = descriptionDescriptor.getDescriptionType();
    setDescription(descriptionDescriptor.getText());
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
   * Setter for the description.
   *
   * @param description the new description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Type: " + getType()
            + "\nDescription: " + getDescription();
  }
}
