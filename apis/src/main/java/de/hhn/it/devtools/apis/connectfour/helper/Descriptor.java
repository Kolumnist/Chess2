package de.hhn.it.devtools.apis.connectfour.helper;

import de.hhn.it.devtools.apis.connectfour.enums.MatchState;

/**
 * This class models the descriptor which describes the current state of the match.
 */
public class Descriptor {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Descriptor.class);

  /**
   * Describes the current match state for singleplayer mode.
   *
   * @param matchState The current match state.
   * @param player1    The selected player profile.
   * @return The description of the current match state.
   */
  public String describeSingleplayer(MatchState matchState, Profile player1) {
    logger.info("describeSingleplayer: {}, {}", matchState, player1);
    switch (matchState) {
      case PLAYER_1_IS_PLAYING -> {
        return player1 + " is playing!";
      }
      case PLAYER_2_IS_PLAYING -> {
        return "Computer is playing!";
      }
      case PLAYER_1_WON -> {
        return player1 + " won!";
      }
      case PLAYER_2_WON -> {
        return "Computer won!";
      }
      case DRAW -> {
        return "It's a draw!";
      }
      default -> {
        return "";
      }
    }
  }

  /**
   * Describe the match state for multiplayer mode.
   *
   * @param matchState The current match state.
   * @param player1    The selected profile for player 1.
   * @param player2    The selected profile for player 2.
   * @return The description of the current match state.
   */
  public String describeMultiplayer(MatchState matchState, Profile player1, Profile player2) {
    logger.info("describeMultiplayer: {}, {}, {}", matchState, player1, player2);
    switch (matchState) {
      case PLAYER_1_IS_PLAYING -> {
        return player1 + " is playing!";
      }
      case PLAYER_2_IS_PLAYING -> {
        return player2 + " is playing!";
      }
      case PLAYER_1_WON -> {
        return player1 + " won!";
      }
      case PLAYER_2_WON -> {
        return player2 + " won!";
      }
      case DRAW -> {
        return "It's a draw!";
      }
      default -> {
        return "";
      }
    }
  }
}
