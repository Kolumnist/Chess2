package de.hhn.it.devtools.components.connectfour.junit;

import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.components.connectfour.provider.helper.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test board: good cases.")
public class TestBoardGoodCases {
  Board board;

  @BeforeEach
  void setup() {
    board = new Board();
  }

  @Test
  @DisplayName("Test place disc in column.")
  void testPlaceDiscInColumn() {
    int column = 5;
    int row;
    try {
      row = board.placeDiscInColumn(column);
      if (row != 0) {
        Assertions.fail();
      }
      row = board.placeDiscInColumn(column);
      if (row != 1) {
        Assertions.fail();
      }
    } catch (IllegalOperationException e) {
      Assertions.fail();
    }
  }

  @Test
  @DisplayName("Test won.")
  void testWon() {
    if (board.isWon()) {
      // New board, no winner.
      Assertions.fail();
    }
    try {
      board.placeDiscInColumn(0); // RED
      board.placeDiscInColumn(1); // GREEN
      board.placeDiscInColumn(0); // RED
      board.placeDiscInColumn(1); // GREEN
      board.placeDiscInColumn(0); // RED
      board.placeDiscInColumn(1); // GREEN
      board.placeDiscInColumn(0); // RED
    } catch (IllegalOperationException e) {
      Assertions.fail();
    }
    if (!board.isWon()) {
      // RED should have won.
      Assertions.fail();
    }
  }

  @Test
  @DisplayName("Test draw.")
  void testDraw() {
    if (board.isDraw()) {
      // New board, no draw.
      Assertions.fail();
    }
    try {
      for (int row = 0; row < 6; row++) {
        for (int column = 0; column < 3; column++) {
          board.placeDiscInColumn(column);
        }
      }
      /*
      o x o
      x o x
      o x o
      x o x
      o x o
      x o x
       */
      for (int row = 0; row < 6; row++) {
        for (int column = 3; column < 6; column++) {
          board.placeDiscInColumn(column);
        }
      }
      /*
      o x o o x o
      x o x x o x
      o x o o x o
      x o x x o x
      o x o o x o
      x o x x o x
       */
      for (int row = 0; row < 6; row++) {
        board.placeDiscInColumn(6);
      }
      /*
      o x o o x o o
      x o x x o x x
      o x o o x o o
      x o x x o x x
      o x o o x o o
      x o x x o x x
       */
    } catch (IllegalOperationException e) {
      Assertions.fail();
    }
    if (!board.isDraw()) {
      // It should be a draw.
      Assertions.fail();
    }
  }
}
