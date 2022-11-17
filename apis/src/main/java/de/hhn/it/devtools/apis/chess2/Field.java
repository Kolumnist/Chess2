package de.hhn.it.devtools.apis.chess2;

/**
 * Defines the Field which can have a Piece on it and always has a state.
 *
 * @author Collin Hoss, Michel Jouaux, Lara Mangi
 * @version 1.0
 */

public class Field {

  private final Coordinate coordinate;
  private Piece piece = null;
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
   * The getter for the piece if there is one, if not return null or throw exception.
   *
   * @return a piece that is standing on this, can return null.
   */
  public Piece getPiece() {
    return piece;
  }

  /**
   * The setter for the piece.
   *
   * @param piece the piece that is now standing on this.
   */
  public void setPiece(Piece piece) {
    this.piece = piece;
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
