package de.hhn.it.devtools.apis.chess2;

import java.util.Optional;

/**
 * Defines the Field which can have a Piece on it and always has a state.
 *
 * @author Collin Hoss, Michel Jouaux, Lara Mangi
 * @version 1.0
 */

public class Field {

  private final Coordinate coordinate;
  private Optional<Piece> piece = Optional.empty();
  private FieldState fieldState = FieldState.FREE_FIELD;

  /**
   * Constructor of Field.
   *
   * @param coordinate where the Field is placed.
   */
  public Field(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  /**
   * The getter for the Coordinate.
   *
   * @return the fields Coordinate.
   */
  public Coordinate getCoordinate() {
    return coordinate;
  }

  /**
   * The getter for the fieldState.
   *
   * @return the fieldState of this.
   */
  public FieldState getFieldState() {
    return fieldState;
  }

  /**
   * The setter for the fieldState.
   *
   * @param fieldState is the new fieldState
   */
  public void setFieldState(FieldState fieldState) {
    this.fieldState = fieldState;
  }

}
