package de.hhn.it.devtools.apis.chess2;

/**
 * Defines the board and lets you get all fields or a specific field.
 *
 * @author Collin Hoss, Michel Jouaux, Lara Mangi
 * @version 1.0
 */

public class Board {

  private final Field[] fields;

  /**
   * Constructor of board.
   *
   * @param fields all needed fields that make the board.
   */
  public Board(Field[] fields) {
    this.fields = fields;
  }

  /**
   * A getter for a specific Field.
   *
   * @param index to get the correct field.
   * @return a specific Field.
   */
  public Field getSpecificField(int index) {
    try {
      return fields[index];
    } catch (ArrayIndexOutOfBoundsException arrayException) {
      return null;
    }
  }

  /**
   * A getter for all fields.
   *
   * @return all fields.
   */
  public Field[] getFields() {
    return fields;
  }

}
