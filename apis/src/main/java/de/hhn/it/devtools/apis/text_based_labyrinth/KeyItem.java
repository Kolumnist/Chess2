package de.hhn.it.devtools.apis.text_based_labyrinth;

/**
 * Javadoc.
 */
public class KeyItem extends Item {

  private final String name;

  /**
   * Javadoc.
   *
   * @param id id
   * @param name name
   */
  public KeyItem(int id, String name) {
    super(id);
    this.name = name;

  }

  /**
   * Javadoc.
   *
   * @return return
   */
  public String getName() {
    return name;
  }
}

