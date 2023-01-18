package de.hhn.it.devtools.components.connectfour.provider;

/**
 * This enum contains the String values for the disc colors.
 */
public enum Color {
  GREEN("GREEN"),
  RED("RED");
  final String color;

  /**
   * Create a new Color.
   *
   * @param color The String value of the color.
   */
  Color(String color) {
    this.color = color;
  }

  /**
   * Get the String value of the color.
   *
   * @return color as String
   */
  @Override
  public String toString() {
    return color;
  }
}
