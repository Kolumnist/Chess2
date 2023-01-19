package de.hhn.it.devtools.apis.connectfour.enums;

/**
 * This enum contains the different difficulty levels.
 */
public enum Difficulty {
  /**
   * Not difficult at all.
   */
  EASY("Easy"),
  /**
   * Not too difficult.
   */
  MEDIUM("Medium"),
  /**
   * Very difficult.
   */
  HARD("Hard");
  String description;

  Difficulty(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}
