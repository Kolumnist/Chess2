package de.hhn.it.devtools.components.connectfour.provider;

import de.hhn.it.devtools.apis.connectfour.IllegalOperationException;
import java.util.Arrays;

/**
 * This class models the game board.
 */
public class Board {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Board.class);
  private final Disc[][] board = new Disc[7][6];  // [column][row]
  private final int[] topRowIndex = new int[7];   // Index of first free row in each column
  private int discsPlaced = 0;

  public Board() {
    logger.info("Creating new board...");
    Arrays.fill(topRowIndex, 0);
  }

  /**
   * Place a disc in this column.
   *
   * @param column column in which the disc should be placed in
   * @param disc   the disc of the current player
   */
  public void placeDiscInColumn(int column, Disc disc) throws IllegalOperationException {
    // In case there is a row left...
    if (topRowIndex[column] < 6) {
      board[column][topRowIndex[column]] = disc;
      topRowIndex[column] = topRowIndex[column] + 1;
      discsPlaced++;
    } else {
      throw new IllegalOperationException();
    }
  }

  /**
   * Check whether the game is finished or not.
   *
   * @return true, if game is finished.
   */
  public boolean check() {
    final int maxx = 7;
    final int maxy = 6;

    int[][] directions = {{1, 0}, {1, -1}, {1, 1}, {0, 1}};
    for (int[] d : directions) {
      int dx = d[0];
      int dy = d[1];
      for (int x = 0; x < maxx; x++) {
        for (int y = 0; y < maxy; y++) {
          int lastx = x + 3 * dx;
          int lasty = y + 3 * dy;
          if (0 <= lastx && lastx < maxx && 0 <= lasty && lasty < maxy) {
            Disc w = board[x][y];
            if (w != null && w == board[x + dx][y + dy]
                && w == board[x + 2 * dx][y + 2 * dy]
                && w == board[lastx][lasty]) {
              logger.info("Game won.");
              return true;
            }
          }
        }
      }
    }
    logger.info("Continue.");
    return false; // no winner
  }

  /**
   * Check, if all discs have been placed.
   *
   * @return true, if board is full, false if not
   */
  public boolean isFull() {
    return discsPlaced == 42;
  }

  /**
   * Get the discs' arrangement of the board.
   *
   * @return discs
   */
  public Disc[][] getDiscs() {
    return board;
  }
}
