package de.hhn.it.devtools.javafx.chess2;

public class Rules {

  String figur;
  String description;

  public Rules(String figur, String description) {
    this.figur = figur;
    this.description = description;
  }

  public String getFigur() {
    return figur;
  }

  public void setFigur(String figur) {
    this.figur = figur;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
