package de.hhn.it.devtools.components.chess2.junit;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.Field;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.GameState;
import de.hhn.it.devtools.apis.chess2.IllegalStateException;
import de.hhn.it.devtools.apis.chess2.InvalidMoveException;
import de.hhn.it.devtools.apis.chess2.WinningPlayerState;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.chess2.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class TestChessGameBadCases {

  Chess2Service chess2Service;
  ChessGame chessGame;

  @BeforeEach
  void setup() {
    chessGame = new ChessGame();
    chess2Service = chessGame;
  }

  @Test
  @DisplayName("do things after endGame try to move etc.")
  void TestAfterEndGameGiveUpContent() throws IllegalStateException {
    Board board = chess2Service.startNewGame();
    chess2Service.endGame();
    assertNotNull(board);
    assertEquals(WinningPlayerState.NO_WINNER, chess2Service.getWinningPlayer());
    chess2Service.giveUp();
    assertEquals(WinningPlayerState.NO_WINNER, chess2Service.getWinningPlayer());
    assertEquals(GameState.CHECKMATE, chess2Service.getGameState());

    chess2Service.startNewGame();
//Moving with a non piece field and moving to a field where I normally couldn't move
    assertEquals(GameState.RUNNING, chess2Service.getGameState());
  }

}
