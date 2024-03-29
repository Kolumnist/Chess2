package de.hhn.it.devtools.components.connectfour.provider.helper;

import de.hhn.it.devtools.apis.connectfour.enums.GameState;
import de.hhn.it.devtools.apis.connectfour.enums.MultiplayerState;
import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;

/**
 * This class modells a multiplayer match.
 */
public class MultiplayerGame extends Game {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MultiplayerGame.class);

  private String player1Color;
  private String player2Color;
  private final Profile player1;
  private final Profile player2;
  private boolean player1IsFirst;
  private MultiplayerState multiplayerState;

  /**
   * Create a new multiplayer game.
   *
   * @param player1        Player 1.
   * @param player2        Player 2.
   * @param player1IsFirst True if player 1 begins. Otherwise, false.
   */
  public MultiplayerGame(Profile player1, Profile player2, boolean player1IsFirst) {
    logger.info("Constructor - player1 = {}, player2 = {}, player1IsFirst = {}", player1, player2,
        player1IsFirst);
    this.player1 = player1;
    this.player2 = player2;
    this.player1IsFirst = player1IsFirst;
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
        multiplayerState == MultiplayerState.PLAYER_1_IS_PLAYING ? player1Color : player2Color);
    // Game won?
    if (board.isWon()) {
      if (multiplayerState == MultiplayerState.PLAYER_1_IS_PLAYING) {
        multiplayerState = MultiplayerState.PLAYER_1_WON;
        player1IsFirst = false;
      } else {
        multiplayerState = MultiplayerState.PLAYER_2_WON;
        player1IsFirst = true;
      }
      gameState = GameState.FINISHED;
      updatePlayerStatistics();

      // Draw?
    } else if (board.isDraw()) {
      multiplayerState = MultiplayerState.DRAW;
      player1IsFirst = !player1IsFirst;
      gameState = GameState.FINISHED;
      updatePlayerStatistics();
    } else {
      // Switch players.
      if (multiplayerState == MultiplayerState.PLAYER_1_IS_PLAYING) {
        multiplayerState = MultiplayerState.PLAYER_2_IS_PLAYING;
      } else {
        multiplayerState = MultiplayerState.PLAYER_1_IS_PLAYING;
      }
      listener.unlock();
    }
    // Update.
    listener.updateDescription(
        descriptor.describeMultiplayer(multiplayerState, player1, player2));
  }

  /**
   * Start the game.
   */
  @Override
  public void start() {
    logger.info("start: no params");
    board = new Board();
    if (player1IsFirst) {
      multiplayerState = MultiplayerState.PLAYER_1_IS_PLAYING;
      player1Color = "RED";
      player2Color = "GREEN";
    } else {
      multiplayerState = MultiplayerState.PLAYER_2_IS_PLAYING;
      player2Color = "RED";
      player1Color = "GREEN";
    }
    gameState = GameState.RUNNING;
    listener.updateDescription(descriptor.describeMultiplayer(multiplayerState, player1, player2));
    listener.unlock();

  }

  /**
   * Update the player statistics.
   */
  private void updatePlayerStatistics() {
    logger.info("updatePlayerStatistics: no params");
    switch (multiplayerState) {
      case PLAYER_1_WON -> {
        player1.addMultiplayerWin();
        player2.addMultiplayerLoose();
      }
      case PLAYER_2_WON -> {
        player2.addMultiplayerWin();
        player1.addMultiplayerLoose();
      }
      default -> {
        player1.addMultiplayerDraw();
        player2.addMultiplayerDraw();
      } // Draw
    }
    listener.save();
  }
}
