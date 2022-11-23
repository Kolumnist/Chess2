package de.hhn.it.devtools.apis.chess2;

import java.security.InvalidParameterException;

/**
 * Defines the board and lets you get all fields or a specific field.
 * An Object of Board updates while the game is running.
 *
 * @author Collin Hoss, Michel Jouaux, Lara Mangi
 * @version 1.0
 */

public class Board {

  private final Field[] fields = new Field[68];

  /**
   * Constructor of board. Initializes the fields. (It starts left bottom with 0/0 and goes to the
   * right)
   */
  public Board() {

    /* 8*8 board gets initialized */
    int diff = -1;
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        fields[++diff] = new Field(new Coordinate(x, y));
      }
    }

    /* Jail fields get initialized */
    fields[fields.length - 4] = new Field(new Coordinate(9, 2));
    fields[fields.length - 2] = new Field(new Coordinate(9, 3));
    fields[fields.length - 3] = new Field(new Coordinate(8, 3));
    fields[fields.length - 1] = new Field(new Coordinate(8, 4));
  }

  /**
   * A getter for a specific Field.
   *
   * @param index to get the correct field.
   * @return a specific Field.
   */
  public Field getSpecificField(int index) throws InvalidParameterException {
    return fields[index];
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
