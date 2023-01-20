package de.hhn.it.devtools.components.connectfour.provider.helper;

import de.hhn.it.devtools.apis.connectfour.enums.GameState;
import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.apis.connectfour.interfaces.IConnectFourListener;
import java.util.Timer;

/**
 * This class modells a singleplayer match.
 */
public class SingleplayerGame extends Game {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SingleplayerGame.class);

  private final Profile player;
  private final Timer timer = new Timer(true);

  /**
   * Creates a new singleplayer game.
   *
   * @param player   The player.
   * @param listener The game controller.
   */
  public SingleplayerGame(Profile player, boolean player1IsFirst,
                          IConnectFourListener listener) {
    logger.info("Constructor - player = {}", player);
    this.player = player;
    this.player1IsFirst = player1IsFirst;
    if (player1IsFirst) {
      matchState = MatchState.PLAYER_1_IS_PLAYING;
    } else {
      matchState = MatchState.COMPUTER_IS_PLAYING;
    }
  }

  @Override
  public void restart() {
    logger.info("restart: no params");
    if (gameState == GameState.FINISHED) {
      if (player1IsFirst) {
        matchState = MatchState.COMPUTER_IS_PLAYING;
        player1IsFirst = false;
      } else {
        matchState = MatchState.PLAYER_1_IS_PLAYING;
        player1IsFirst = true;
      }
    } else {
      if (player1IsFirst) {
        matchState = MatchState.PLAYER_1_IS_PLAYING;
      } else {
        matchState = MatchState.COMPUTER_IS_PLAYING;
      }
    }
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
        case COMPUTER_WON -> {
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
