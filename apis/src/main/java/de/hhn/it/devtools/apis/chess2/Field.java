package de.hhn.it.devtools.apis.chess2;

import java.util.Optional;

/**
 * Defines the Field which has a state, a coordinate and optionally a Piece.
 *
 * @author Collin Hoss, Michel Jouaux, Lara Mangi
 * @version 1.1
 */

public class Field {

  private final Coordinate coordinate;
  private Optional<Piece> piece = Optional.empty();
  private FieldState fieldState = FieldState.FREE_FIELD;

  /**
   * Constructor of Field, it initializes the coordinate.
   *
   * @param coordinate where the Field is placed.
   */
  public Field(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  /**
   * The getter for the Coordinate.
   *
   * @return this coordinate.
   */
  public Coordinate getCoordinate() {
    return coordinate;
  }

  /**
   * Sets the Optional piece to not be empty.
   *
   * @param piece that is on this field now.
   */
  public void setPiece(Optional<Piece> piece) {
    this.piece = piece;
  }

  /**
   * Get the Optional piece or a default not null value.
   *
   * @return piece that is on this field or is empty.
   */
  public Piece getPiece() {
    return piece.orElse(null);
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