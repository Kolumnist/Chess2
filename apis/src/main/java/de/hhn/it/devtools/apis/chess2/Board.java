package de.hhn.it.devtools.apis.chess2;

/**
 * Defines the board and lets you get all fields or a specific field.
 * An Object of Board updates while the game is running.
 *
 * @author Collin Hoss, Michel Jouaux, Lara Mangi
 * @version 1.2
 */

public class Board {

  private final Field[] fields = new Field[68];
  public boolean lostPiece = false;

  /**
   * Constructor of board.
   * Initializes the fields. (It starts left bottom with 0/0 and goes to the right)
   */
  public Board() {

    /* 8*8 board gets initialized */
    int diff = -1;
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        fields[++diff] = new Field(new Coordinate(x, y));
        fields[diff].setFieldState(FieldState.FREE_FIELD);
      }
    }

    /* Jail fields get initialized */
    fields[fields.length - 4] = new Field(new Coordinate(9, 4));
    fields[fields.length - 2] = new Field(new Coordinate(9, 3));
    fields[fields.length - 3] = new Field(new Coordinate(8, 3));
    fields[fields.length - 1] = new Field(new Coordinate(8, 4));
    fields[fields.length - 4].setFieldState(FieldState.JAIL);
    fields[fields.length - 2].setFieldState(FieldState.JAIL);
    fields[fields.length - 3].setFieldState(FieldState.JAIL);
    fields[fields.length - 1].setFieldState(FieldState.JAIL);
  }

  /**
   * A getter for a specific Field. The index gets calculated with the x and y value of coordinate.
   *
   * @param coordinate to get the correct field.
   * @return the Field on index.
   * @throws IllegalArgumentException so that no ArrayIndexOutOfBoundsException will occur.
   */
  public Field getSpecificField(Coordinate coordinate) throws IllegalArgumentException {

    int fieldIndex = coordinate.getX() + 8 * coordinate.getY();

    if (fieldIndex < 64 && fieldIndex >= 0 && coordinate.getX() < 8) {
      return fields[fieldIndex];
    }

    for (int i = 1; i < 5; i++) {
      if (fields[fields.length - i].getCoordinate().getX() == coordinate.getX()
          && fields[fields.length - i].getCoordinate().getY() == coordinate.getY()) {
        return fields[fields.length - i];
      }
    }

    return fields[fields.length - 1];
  }

  /**
   * A getter for all fields as array.
   *
   * @return all fields.
   */
  public Field[] getFields() {
    return fields;
  }

}
