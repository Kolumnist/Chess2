package de.hhn.it.devtools.components.connectfour.provider.helper;

import de.hhn.it.devtools.apis.connectfour.enums.GameState;
import de.hhn.it.devtools.apis.connectfour.enums.MatchState;
import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;

/**
 * This class modells a singleplayer match.
 */
public class SingleplayerGame extends Game {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SingleplayerGame.class);

  private final Profile player;

  /**
   * Creates a new singleplayer game.
   *
   * @param player The player.
   */
  public SingleplayerGame(Profile player, boolean player1IsFirst) {
    logger.info("Constructor - player = {}", player);
    this.player = player;
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
  protected void initializeBoard() {
    board = new Board(matchState);
  }

  /**
   * Places the disc of the current player in the specified column.
   *
   * @param column The column in which the disc is to be placed in.
   * @throws IllegalOperationException If column is full.
   */
  @Override
  public void placeDiscInColumn(int column) throws IllegalOperationException {
    logger.info("placeDiscInColumn: column = {}", column);
    board.placeDiscInColumn(column);
    matchState = board.getMatchState();
    gameState = board.getGameState();
    if (gameState == GameState.FINISHED) {
      switch (matchState) {
        case PLAYER_1_WON -> {
          player.addSingleplayerWin();
        }
        case PLAYER_2_WON -> {
          player.addSingleplayerLoose();
        }
        default -> {
          player.addSingleplayerDraw();
        }
      }
    }
  }

  /**
   * Describes the current match state.
   *
   * @return Description of the current match state.
   */
  @Override
  public String getDescription() {
    logger.info("getDescription: no params");
    return descriptor.describeSingleplayer(matchState, player);
  }
}
