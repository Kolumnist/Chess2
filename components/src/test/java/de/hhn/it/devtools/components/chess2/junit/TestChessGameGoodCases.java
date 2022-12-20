package de.hhn.it.devtools.components.chess2.junit;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.GameState;
import de.hhn.it.devtools.apis.chess2.IllegalStateException;
import de.hhn.it.devtools.apis.chess2.WinningPlayerState;
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

@DisplayName("Test ChessGame and Chess2Service interface with good cases.")
public class TestChessGameGoodCases {

  Chess2Service chess2Service;
  ChessGame chessGame;

  @BeforeEach
  void setup() {
    chessGame = new ChessGame();
    chess2Service = chessGame;
  }

  @Test
  @DisplayName("")
  void TestStartGame() {
    Board board = chess2Service.startNewGame();
    assertNotNull(board);
    Board board2 = chess2Service.startNewGame();
    assertEquals(board, board2);
  }

  @Test
  @DisplayName("")
  void TestEndGame() {
    //TODO minimum 1
    //chess2Service.endGame();
  }

  @Test
  @DisplayName("")
  void TestGiveUp() {
    //TODO minimum 2
    chess2Service.giveUp();
    chess2Service.getWinningPlayer();
  }

  @Test
  @DisplayName("")
  void TestGetCurrentFields() {
    //TODO minimum 3
    Coordinate[] coordinates = chess2Service.getCurrentFields();
  }

  @Test
  @DisplayName("")
  void TestGetPossibleMoves() {
    //TODO minimum 10
    //Coordinate[] coordinates = chess2Service.getPossibleMoves();
  }

  @Test
  @DisplayName("")
  void TestMoveSelectedPiece() {
    //TODO minimum 12-32
    //chess2Service.moveSelectedPiece();
  }

  @Test
  @DisplayName("")
  void TestGetFieldState() {
    //TODO minimum 8
    //FieldState fieldState = chess2Service.getFieldState();
  }

  @Test
  @DisplayName("")
  void TestGetWinningPlayerState() {
    //TODO minimum 5
    WinningPlayerState winState = chess2Service.getWinningPlayer();
  }

  @Test
  @DisplayName("GameState of a not started Game has to be null or a defined value")
  void TestGetGameState_OfNotStartedGame() {
    //TODO minimum 3
    GameState gameState = chess2Service.getGameState();
    assertNull(gameState);
  }

  @Test
  @DisplayName("GameState of a freshly started Game should be RUNNING")
  void TestGetGameState_OfStartedGame() {
    //TODO minimum 3
    chess2Service.startNewGame();
    GameState gameState = chess2Service.getGameState();
    assertEquals(GameState.RUNNING, gameState);
  }

  @Test
  @DisplayName("GameState after a Player gave up, should be CHECKMATE")
  void TestGetGameState() {
    //TODO minimum 3
    chess2Service.startNewGame();
    chess2Service.giveUp();
    GameState gameState = chess2Service.getGameState();
    assertEquals(GameState.CHECKMATE, gameState);
  }

}
