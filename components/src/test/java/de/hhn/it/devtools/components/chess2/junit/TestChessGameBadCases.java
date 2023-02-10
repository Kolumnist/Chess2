package de.hhn.it.devtools.components.chess2.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.Coordinate;
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

  @Test
  @DisplayName("Try to give up a game that has the wrong GameState")
  public void testIllegalStateExceptionInGiveUp()
      throws IllegalStateException {
    Board board = chess2Service.startNewGame();
    chess2Service.endGame();
    assertThrows(de.hhn.it.devtools.apis.chess2.IllegalStateException.class,
        () -> chess2Service.giveUp());
    board = chess2Service.startNewGame();
    chess2Service.giveUp();
    assertThrows(de.hhn.it.devtools.apis.chess2.IllegalStateException.class,
        () -> chess2Service.giveUp());
  }

  @Test
  @DisplayName("Try to move on the bear with the bear which should not be possible")
  public void testInvalidMoveExceptionByMovingBearOnBear()
      throws IllegalParameterException, InvalidMoveException, IllegalStateException {
    Board board = chess2Service.startNewGame();
    Coordinate[] possibleMoves = chess2Service.getPossibleMoves(new Coordinate(3, 0));
    Coordinate bear = new Coordinate();
    for (Field field : board.getFields()) {
      bear = field.getFieldState() == FieldState.HAS_BEAR ? field.getCoordinate() : bear;
    }
    Coordinate finalBear1 = bear;
    Coordinate finalBear2 = bear;
    assertThrows(de.hhn.it.devtools.apis.chess2.InvalidMoveException.class,
        () -> chess2Service.moveSelectedPiece(finalBear1, finalBear2));
  }

}
