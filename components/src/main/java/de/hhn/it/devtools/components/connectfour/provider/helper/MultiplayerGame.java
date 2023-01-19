package de.hhn.it.devtools.components.connectfour.provider.helper;

import de.hhn.it.devtools.apis.connectfour.enums.MatchState;
import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;

/**
 * This class modells a multiplayer match.
 */
public class MultiplayerGame extends Game {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MultiplayerGame.class);

  private final Profile player1;
  private final Profile player2;

  /**
   * Creates a new multiplayer game.
   *
   * @param player1 Player 1.
   * @param player2 Player 2.
   */
  public MultiplayerGame(Profile player1, Profile player2, boolean player1IsFirst) {
    logger.info("Constructor - player1 = {}, player2 = {}", player1, player2);
    this.player1 = player1;
    this.player2 = player2;
    this.player1IsFirst = player1IsFirst;
    if (player1IsFirst) {
      matchState = MatchState.PLAYER_1_IS_PLAYING;
    } else {
      matchState = MatchState.PLAYER_2_IS_PLAYING;
    }
  }

  /**
   * Initialize the board.
   */
  @Override
  public void initializeBoard() {
    logger.info("initializeBoard: no params");
    board = new Board(matchState);
  }

  /**
   * Place the disc in the specified column.
   *
   * @param column The column in which the disc is to be placed in.
   * @throws IllegalOperationException If column is full.
   */
  @Override
  public void placeDiscInColumn(int column) throws IllegalOperationException {
    logger.info("placeDiscInColumn: column = {}", column);
    board.placeDiscInColumn(column);
    gameState = board.getGameState();
    matchState = board.getMatchState();
  }

  /**
   * Describes the current match state.
   *
   * @return Description of the current match state.
   */
  @Override
  public String getDescription() {
    logger.info("getDescription: no params");
    return descriptor.describeMultiplayer(matchState, player1, player2);
  }
}
