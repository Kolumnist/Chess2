package de.hhn.it.devtools.components.connectfour.provider.helper;

import de.hhn.it.devtools.apis.connectfour.enums.GameState;
import de.hhn.it.devtools.apis.connectfour.enums.MatchState;
import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Descriptor;

/**
 * This class models the game.
 */
public abstract class Game {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Game.class);
  protected Board board;
  protected GameState gameState = GameState.RUNNING;
  protected MatchState matchState = MatchState.PLAYER_1_IS_PLAYING;
  protected Descriptor descriptor = new Descriptor();
  protected boolean player1IsFirst;

  /**
   * Restarts the game.
   */
  public void restart() {
    logger.info("restart: no params");
    if (gameState == GameState.FINISHED) {
      if (player1IsFirst) {
        matchState = MatchState.PLAYER_2_IS_PLAYING;
        player1IsFirst = false;
      } else {
        matchState = MatchState.PLAYER_1_IS_PLAYING;
        player1IsFirst = true;
      }
    } else {
      if (player1IsFirst) {
        matchState = MatchState.PLAYER_1_IS_PLAYING;
      } else {
        matchState = MatchState.PLAYER_2_IS_PLAYING;
      }
    }
    initializeBoard();
  }

  /**
   * Reset board.
   */
  protected abstract void initializeBoard();

  /**
   * Places the disc of the current player in the specified column.
   *
   * @param column The column in which the disc is to be placed in.
   * @throws IllegalOperationException If column is full.
   */
  public abstract void placeDiscInColumn(int column) throws IllegalOperationException;

  /**
   * Describes the current match state.
   *
   * @return Description of the current match state.
   */
  public abstract String getDescription();

  /**
   * Returns the game board.
   *
   * @return The board.
   */
  public Board getBoard() {
    logger.info("getBoard: no params");
    return board;
  }
}
