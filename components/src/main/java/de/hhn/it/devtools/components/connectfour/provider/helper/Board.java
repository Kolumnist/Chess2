package de.hhn.it.devtools.components.connectfour.provider.helper;

import de.hhn.it.devtools.apis.connectfour.enums.GameState;
import de.hhn.it.devtools.apis.connectfour.enums.MatchState;
import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class models the game board.
 */
public class Board {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Board.class);

  private final int NUMBER_OF_TILES = 42;         // Number of tiles.
  private final char[][] board = new char[7][6];  // Board: [column][row].
  private final int[] topRowIndex = new int[7];   // Index of first free row in each column.
  private int discsPlaced;                        // Number of discs placed.

  private int affectedRow;
  private int affectedColumn;
  private String affectedColor;
  private GameState gameState;
  private MatchState matchState;

  private final LinkedList<Character> players = new LinkedList<>(); // Players.

  public Board(MatchState matchState) {
    logger.info("Constructor - no params");
    this.matchState = matchState;
    // Initialize board.
    for (int i = 0; i < 7; i++) {
      Arrays.fill(board[i], ' ');
    }
    // Initialize top row indices.
    Arrays.fill(topRowIndex, 0);
    // Initialize players.
    players.add('X'); // First player.
    players.add('O'); // Second player.
  }

  /**
   * Place a disc in this column.
   *
   * @param column Column in which the disc should be placed in.
   */
  public void placeDiscInColumn(int column) throws IllegalOperationException {
    logger.info("placeDiscInColumn: column = {}", column);
    // In case there is a row left:
    if (topRowIndex[column] < 6) {
      board[column][topRowIndex[column]] = players.getFirst();
      affectedColumn = column;
      affectedRow = topRowIndex[column];
      affectedColor = players.getFirst() == 'X' ? "RED" : "GREEN";
      topRowIndex[column] = topRowIndex[column] + 1;
      discsPlaced++;
      nextPlayer();
      nextState();
    } else {
      throw new IllegalOperationException("Column is full.");
    }
  }

  /**
   * Get next match state.
   */

  // make boolean
  private void nextState() {
    logger.info("nextState: no params");
    final int maxX = 7;
    final int maxY = 6;

    int[][] directions = {{1, 0}, {1, -1}, {1, 1}, {0, 1}};
    for (int[] d : directions) {
      int dx = d[0];
      int dy = d[1];
      for (int x = 0; x < maxX; x++) {
        for (int y = 0; y < maxY; y++) {
          int lastX = x + 3 * dx;
          int lastY = y + 3 * dy;
          if (0 <= lastX && lastX < maxX && 0 <= lastY && lastY < maxY) {
            char w = board[x][y];
            if (w != ' ' && w == board[x + dx][y + dy]
                && w == board[x + 2 * dx][y + 2 * dy]
                && w == board[lastX][lastY]) {
              // Game won.
              if (matchState == MatchState.PLAYER_1_IS_PLAYING) {
                matchState = MatchState.PLAYER_1_WON;
              } else {
                matchState = MatchState.PLAYER_2_WON;
              }
              gameState = GameState.FINISHED;
              return;
            }
          }
        }
      }
    }
    // No winner.
    if (isFull()) {
      matchState = MatchState.DRAW; // Draw.
      gameState = GameState.FINISHED;
    } else if (matchState == MatchState.PLAYER_1_IS_PLAYING) {
      matchState = MatchState.PLAYER_2_IS_PLAYING; // Player 2 is next.
    } else {
      matchState = MatchState.PLAYER_1_IS_PLAYING; // Player 1 is next.
    }
  }

  /**
   * Check, if all discs have been placed.
   *
   * @return True, if board is full, false if not.
   */
  private boolean isFull() {
    logger.info("isFull: no params");
    return discsPlaced == NUMBER_OF_TILES;
  }

  /**
   * Get the next player.
   */
  private void nextPlayer() {
    logger.info("nextPlayer: no params");
    players.add(players.pop());
  }

  /**
   * Get the current game state.
   */
  public GameState getGameState() {
    logger.info("getGameState: no params");
    return gameState;
  }

  /**
   * Get the current match state.
   */
  public MatchState getMatchState() {
    logger.info("getMatchState: no params");
    return matchState;
  }

  /**
   * Get affected row.
   */
  public int getAffectedRow() {
    logger.info("getAffectedRow: no params");
    return affectedRow;
  }

  /**
   * Get affected column.
   */
  public int getAffectedColumn() {
    logger.info("getAffectedColumn: no params");
    return affectedColumn;
  }

  /**
   * Get affected color.
   */
  public String getAffectedColor() {
    logger.info("getAffectedCcolor: no params");
    return affectedColor;
  }
}
