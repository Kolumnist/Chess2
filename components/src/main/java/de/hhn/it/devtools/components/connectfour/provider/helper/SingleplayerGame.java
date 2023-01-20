package de.hhn.it.devtools.components.connectfour.provider.helper;

import de.hhn.it.devtools.apis.connectfour.enums.GameState;
import de.hhn.it.devtools.apis.connectfour.enums.SingleplayerState;
import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import java.util.Timer;

/**
 * This class modells a singleplayer match.
 */
public class SingleplayerGame extends Game {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SingleplayerGame.class);

  private final Profile player;
  private boolean playerIsFirst;
  private SingleplayerState singleplayerState;
  private final Timer timer = new Timer(true);

  /**
   * Create a new singleplayer game.
   *
   * @param player        The player.
   * @param playerIsFirst True if the human begins. Otherwise, false.
   */
  public SingleplayerGame(Profile player, boolean playerIsFirst) {
    logger.info("Constructor - player = {}", player);
    this.player = player;
    this.playerIsFirst = playerIsFirst;
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
    int row = board.placeDiscInColumn(column);
    if (board.isWon()) {

    }
    listener.updateDescription(descriptor.describeSingleplayer(singleplayerState, player));
  }

  /**
   * Restart the game.
   */
  @Override
  public void restart() {
    logger.info("restart: no params");
    board = new Board();
    // Switch players if game was won by starting player or ended in a draw.
    if (gameState == GameState.FINISHED) {
      if (playerIsFirst && singleplayerState == SingleplayerState.HUMAN_WON         // 1 & 1.
          || !playerIsFirst && singleplayerState == SingleplayerState.COMPUTER_WON  // 2 & 2.
          || singleplayerState == SingleplayerState.DRAW) {                         // Draw.
        if (playerIsFirst) {
          singleplayerState = SingleplayerState.COMPUTER_IS_PLAYING;  // Switch.
          playerIsFirst = false;
        } else {
          singleplayerState = SingleplayerState.HUMAN_IS_PLAYING;     // Same.
          playerIsFirst = true;
        }
      }
    }
    gameState = GameState.RUNNING; // Start game.
    listener.updateDescription(descriptor.describeSingleplayer(singleplayerState, player));
    if (singleplayerState == SingleplayerState.COMPUTER_IS_PLAYING) {
      play();
    } else {
      listener.unlock();
    }
  }

  @Override
  public void start() {
    board = new Board();
    if (playerIsFirst) {
      singleplayerState = SingleplayerState.HUMAN_IS_PLAYING;
    } else {
      singleplayerState = SingleplayerState.COMPUTER_IS_PLAYING;
    }
    gameState = GameState.RUNNING;
    listener.updateDescription(descriptor.describeSingleplayer(singleplayerState, player));
    if (singleplayerState == SingleplayerState.COMPUTER_IS_PLAYING) {
      play();
    }
  }

  /**
   * Make the next move for the computer.
   */
  private void play() {
    logger.info("play: no params");
    int i = (int) (Math.random() * 6);
    while (true) {
      try {
        placeDiscInColumn((i += 1) % 6);
        break;
      } catch (IllegalOperationException ignore) {
      }
    }
    listener.unlock();
  }

  /**
   * Update the player statistics.
   */
  private void updatePlayerStatistics() {
    logger.info("updatePlayerStatistics: no params");
    switch (singleplayerState) {
      case HUMAN_WON -> player.addSingleplayerWin();
      case COMPUTER_WON -> player.addSingleplayerLoose();
      default -> player.addSingleplayerDraw(); // Draw
    }
  }
}
