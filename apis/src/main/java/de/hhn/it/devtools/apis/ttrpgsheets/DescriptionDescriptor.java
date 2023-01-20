package de.hhn.it.devtools.apis.ttrpgsheets;

import java.io.Serializable;

/**
 * The Descriptor for the Description class.
 */
public class DescriptionDescriptor implements Serializable {
  private DescriptionType descriptionType;
  private String text;

  /**
   * Constructor stating {@link DescriptionType} and text of the Description.
   *
   * @param descriptionType the type of the Description
   * @param text the text within the Description
   */
  public DescriptionDescriptor(DescriptionType descriptionType, String text) {
    this.descriptionType = descriptionType;
    this.text = text;
  }

  /**
   * Returns the {@link DescriptionType} of the Description.
   *
   * @return {@link DescriptionType} of the Description
   */
  public DescriptionType getDescriptionType() {
    return descriptionType;
  }

  /**
   * Sets the {@link DescriptionType} of the Description.
   *
   * @param descriptionType type of Description
   */
  public void setDescriptionType(DescriptionType descriptionType) {
    this.descriptionType = descriptionType;
  }

  /**
   * Returns the text of the Description.
   *
   * @return text of the Description
   */
  public String getText() {
    return text;
  }

  /**
   * Sets the text of the Description.
   *
   * @param text of the Description
   */
  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof DescriptionDescriptor other) {
      return descriptionType.equals(other.getDescriptionType()) && text.equals(other.text);
    }
    return false;
  }

  @Override
  public String toString() {
    return "DescriptionDescriptor: [Type: " + descriptionType
            + ", Text: " + text + "]";
  }
}
