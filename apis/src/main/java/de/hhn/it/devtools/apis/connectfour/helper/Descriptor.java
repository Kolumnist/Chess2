package de.hhn.it.devtools.apis.connectfour.helper;

import de.hhn.it.devtools.apis.connectfour.enums.MultiplayerState;
import de.hhn.it.devtools.apis.connectfour.enums.SingleplayerState;

/**
 * This class models the descriptor which describes the current state of the match.
 */
public class Descriptor {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Descriptor.class);

  /**
   * Describes the current match state for singleplayer mode.
   *
   * @param singleplayerState The current match state.
   * @param player            The selected player profile.
   * @return The description of the current match state.
   */
  public String describeSingleplayer(SingleplayerState singleplayerState, Profile player) {
    logger.info("describeSingleplayer: {}, {}", singleplayerState, player);
    switch (singleplayerState) {
      case HUMAN_IS_PLAYING -> {
        return player + " is playing!";
      }
      case COMPUTER_IS_PLAYING -> {
        return "Computer is playing!";
      }
      case HUMAN_WON -> {
        return player + " won!";
      }
      case COMPUTER_WON -> {
        return "Computer won!";
      }
      default -> {
        return "It's a draw!";
      }
    }
  }

  /**
   * Describe the match state for multiplayer mode.
   *
   * @param multiplayerState The current match state.
   * @param player1          The selected profile for player 1.
   * @param player2          The selected profile for player 2.
   * @return The description of the current match state.
   */
  public String describeMultiplayer(MultiplayerState multiplayerState, Profile player1,
                                    Profile player2) {
    logger.info("describeMultiplayer: {}, {}, {}", multiplayerState, player1, player2);
    switch (multiplayerState) {
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
      default -> {
        return "It's a draw!";
      }
    }
  }
}
