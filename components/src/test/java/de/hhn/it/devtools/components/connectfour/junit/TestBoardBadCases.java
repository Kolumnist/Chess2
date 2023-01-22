package de.hhn.it.devtools.components.connectfour.junit;

import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.components.connectfour.provider.helper.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test board: bad cases.")
public class TestBoardBadCases {
  Board board;

  @BeforeEach
  void setup() {
    board = new Board();
  }

  @Test
  @DisplayName("Test place disc in full column.")
  void testPlaceDiscInFullColumn() {
    try {
      for (int i = 0; i < 8; i++) {
        board.placeDiscInColumn(2);
      }
      // Exception should be thrown.
      Assertions.fail();
    } catch (IllegalOperationException ignore) {
      // Success
    }
  }
}
