package de.hhn.it.devtools.javafx.controllers.connectfour.helper;

/**
 * Options to sort by.
 */
public enum SortBy {
  SINGLEPLAYER_RANKING("Singleplayer Ranking"),
  Multiplayer_RANKING("Multiplayer Ranking"),
  ;
  String description;

  SortBy(String s) {
    description = s;
  }

  @Override
  public String toString() {
    return description;
  }
}
