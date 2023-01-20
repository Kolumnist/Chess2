package de.hhn.it.devtools.javafx.chess2;

/**
 * This class implements the Queen which inherits from Piece.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */
public class Rules {

  String figur;
  String description;

  public Rules(String figur, String description) {
    this.figur = figur;
    this.description = description;
  }

  /**
   * Returns the name of a figur.
   *
   * @return the name of a figur
   */
  public String getFigur() {
    return figur;
  }

  /**
   * Sets the name of a figur.
   *
   * @param figur is the name of a figur
   */
  public void setFigur(String figur) {
    this.figur = figur;
  }

  /**
   * Returns the description of a figur.
   *
   * @return the description of a figur
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of a figur.
   *
   * @param description is the description of a figur
   */
  public void setDescription(String description) {
    this.description = description;
  }
}
