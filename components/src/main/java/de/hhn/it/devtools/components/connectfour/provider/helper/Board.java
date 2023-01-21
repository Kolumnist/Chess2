package de.hhn.it.devtools.components.connectfour.provider.helper;

import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class models the game board.
 */
public class Board {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Board.class);

  private static final int NUMBER_OF_TILES = 42;  // Number of tiles.
  private final char[][] board = new char[7][6];  // Board: [column][row].
  private final int[] topRowIndex = new int[7];   // Index of first free row in each column.
  private int discsPlaced;                        // Number of discs placed.

  private final LinkedList<Character> players = new LinkedList<>(); // Players.

  /**
   * Create a new board.
   */
  public Board() {
    logger.info("Constructor - no params");
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
   * @return The row in which the disc was placed in.
   */
  public int placeDiscInColumn(int column) throws IllegalOperationException {
    logger.info("placeDiscInColumn: column = {}", column);
    // In case there is a row left:
    if (topRowIndex[column] < 6) {
      int row = topRowIndex[column];
      board[column][row] = players.getFirst();
      topRowIndex[column] = row + 1;
      discsPlaced++;
      nextPlayer();
      return row;
    } else {
      throw new IllegalOperationException("Column is full.");
    }
  }

  /**
   * Check whether the game is won or not.
   *
   * @return True, if game is won. Return false, if otherwise.
   */
  public boolean isWon() {
    logger.info("isWon: no params");
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
              return true;
            }
          }
        }
      }
    }
    // No winner.
    return false;
  }

  /**
   * Check, if all discs have been placed.
   *
   * @return True, if board is full, false if not.
   */
  public boolean isDraw() {
    logger.info("isDraw: no params");
    return discsPlaced == NUMBER_OF_TILES;
  }

  /**
   * Get the next player.
   */
  private void nextPlayer() {
    logger.info("nextPlayer: no params");
    players.add(players.pop());
  }
}
