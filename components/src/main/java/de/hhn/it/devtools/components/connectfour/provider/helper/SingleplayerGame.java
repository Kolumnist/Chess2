package de.hhn.it.devtools.components.connectfour.provider.helper;

import de.hhn.it.devtools.apis.connectfour.enums.GameState;
import de.hhn.it.devtools.apis.connectfour.enums.SingleplayerState;
import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class modells a singleplayer match.
 */
public class SingleplayerGame extends Game {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SingleplayerGame.class);

  private String humanColor;
  private String computerColor;
  private final Profile player;
  private boolean playerIsFirst;
  private SingleplayerState singleplayerState;
  private Timer timer = new Timer(true);

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
    listener.updateTile(column, row,
        singleplayerState == SingleplayerState.HUMAN_IS_PLAYING ? humanColor : computerColor);
    // Game won?
    if (board.isWon()) {
      if (singleplayerState == SingleplayerState.HUMAN_IS_PLAYING) {
        singleplayerState = SingleplayerState.HUMAN_WON;
        playerIsFirst = false;
      } else {
        singleplayerState = SingleplayerState.COMPUTER_WON;
        playerIsFirst = true;
      }
      gameState = GameState.FINISHED;
      updatePlayerStatistics();
      // Draw?
    } else if (board.isDraw()) {
      singleplayerState = SingleplayerState.DRAW;
      gameState = GameState.FINISHED;
      updatePlayerStatistics();
      playerIsFirst = !playerIsFirst;
    } else {
      // Switch players.
      if (singleplayerState == SingleplayerState.HUMAN_IS_PLAYING) {
        singleplayerState = SingleplayerState.COMPUTER_IS_PLAYING;
        play();
      } else {
        singleplayerState = SingleplayerState.HUMAN_IS_PLAYING;
        listener.unlock();
      }
    }
    // Update.
    listener.updateDescription(
        descriptor.describeSingleplayer(singleplayerState, player));
  }

  @Override
  public void start() {
    logger.info("start: no params");
    timer.cancel();
    timer = new Timer(true);
    board = new Board();
    if (playerIsFirst) {
      singleplayerState = SingleplayerState.HUMAN_IS_PLAYING;
      humanColor = "RED";
      computerColor = "GREEN";
    } else {
      singleplayerState = SingleplayerState.COMPUTER_IS_PLAYING;
      computerColor = "RED";
      humanColor = "GREEN";
    }
    gameState = GameState.RUNNING;
    listener.updateDescription(descriptor.describeSingleplayer(singleplayerState, player));
    if (singleplayerState == SingleplayerState.COMPUTER_IS_PLAYING) {
      play();
    } else {
      listener.unlock();
    }
  }

  /**
   * Make the next move for the computer.
   */
  private void play() {
    logger.info("play: no params");
    timer.schedule(new TimerTask() {
      int preset = (int) (Math.random() * 6);

      @Override
      public void run() {
        try {
          placeDiscInColumn((preset += 1) % 6);
        } catch (IllegalOperationException ignore) {
          run();
        }
      }
    }, 700);
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
    listener.save();
  }
}
