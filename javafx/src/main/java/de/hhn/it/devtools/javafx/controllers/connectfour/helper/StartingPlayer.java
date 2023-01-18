package de.hhn.it.devtools.javafx.controllers.connectfour.helper;

/**
 * This enum contains the values for choosing the starting player.
 */
public enum StartingPlayer {
  HUMAN("Human"),
  COMPUTER("Computer"),
  PLAYER1("Player 1"),
  PLAYER2("Player 2"),
  RANDOM("Random");
  private final String description;

  StartingPlayer(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}
