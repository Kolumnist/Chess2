package de.hhn.it.devtools.apis.connectfour;

/**
 * This enum contains the different match states.
 */
public enum MatchState {
  /**
   * Player A can place the disk.
   */
  PLAYER_A_IS_PLAYING("Player 1's turn."),
  /**
   * Player A places the disk.
   */
  PLAYER_B_IS_PLAYING("Player 2's turn."),
  /**
   * Player A won the game.
   */
  PLAYER_A_WON("Player 1 won!"),
  /**
   * Player B won the game.
   */
  PLAYER_B_WON("Player 2 won!"),
  /**
   * Neither Player A nor Player B won the game.
   */
  DRAW("Draw.");
  String description;

  MatchState(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}
