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
  @DisplayName("Start a completely new Game and test all important variables")
  void TestStartGame() {
    Board board = chess2Service.startNewGame();
    assertNotNull(board);
    assertEquals(68, board.getFields().length);

    int bearNum = 0;
    int otherNum = 0;
    int currentNum = 0;
    int freeNum = 0;
    for (Field field : board.getFields()) {
      if (field.getFieldState() == FieldState.FREE_FIELD) {
        freeNum++;
        continue;
      }
      if (field.getFieldState() == FieldState.HAS_CURRENT_PIECE) {
        assertEquals('w', field.getPiece().getColor());
        currentNum++;
        continue;
      }
      if (field.getFieldState() == FieldState.HAS_OTHER_PIECE) {
        otherNum++;
        continue;
      }
      if (field.getFieldState() == FieldState.HAS_BEAR) {
        bearNum++;
        assertTrue(field.getCoordinate().getX() > 0 && field.getCoordinate().getX() < 7
            && field.getCoordinate().getY() > 2 && field.getCoordinate().getY() < 5);
      }
    }
    assertEquals(1, bearNum);
    assertEquals(16, otherNum);
    assertEquals(16, currentNum);
    assertEquals(31, freeNum);
  }

  @Test
  @DisplayName("Ending a Game of Chess which sets GameState to null")
  void TestEndGame() throws IllegalStateException {
    Board board = chess2Service.startNewGame();
    chess2Service.endGame();
    assertNull(chess2Service.getGameState());
  }

  @Test
  @DisplayName("startGame after endGame every variable is the same as in startGame")
  void TestStartingGameBackUpAfterEndGame() throws IllegalStateException {
    Board board = chess2Service.startNewGame();
    assertNotNull(board);
    chess2Service.endGame();
    chess2Service.startNewGame();

    assertEquals(GameState.RUNNING, chess2Service.getGameState());
    assertNotNull(board);
    assertEquals(68, board.getFields().length);

    int bearNum = 0;
    int otherNum = 0;
    int currentNum = 0;
    int freeNum = 0;
    for (Field field : board.getFields()) {
      if (field.getFieldState() == FieldState.FREE_FIELD) {
        freeNum++;
        continue;
      }
      if (field.getFieldState() == FieldState.HAS_CURRENT_PIECE) {
        assertEquals('w', field.getPiece().getColor());
        currentNum++;
        continue;
      }
      if (field.getFieldState() == FieldState.HAS_OTHER_PIECE) {
        otherNum++;
        continue;
      }
      if (field.getFieldState() == FieldState.HAS_BEAR) {
        bearNum++;
        assertTrue(field.getCoordinate().getX() > 0 && field.getCoordinate().getX() < 7
            && field.getCoordinate().getY() > 2 && field.getCoordinate().getY() < 5);
      }
    }
    assertEquals(1, bearNum);
    assertEquals(16, otherNum);
    assertEquals(16, currentNum);
    assertEquals(31, freeNum);
  }

  @Test
  @DisplayName("Player White gives Up and Black wins")
  void TestWhiteGiveUp() {
    Board board = chess2Service.startNewGame();
    chess2Service.giveUp();
    assertEquals(WinningPlayerState.BLACK_WIN, chess2Service.getWinningPlayer());
  }

  @Test
  @DisplayName("Player Black gives Up and White wins")
  void TestBlackGiveUp()
      throws IllegalStateException, IllegalParameterException, InvalidMoveException {
    Board board = chess2Service.startNewGame();

    Coordinate[] currentCoords = chess2Service.getCurrentFields();
    Coordinate[] possibleMoves = chess2Service.getPossibleMoves(currentCoords[0]);
    chess2Service.moveSelectedPiece(currentCoords[0], new Coordinate(4, 1));

    chess2Service.giveUp();
    assertEquals(WinningPlayerState.WHITE_WIN, chess2Service.getWinningPlayer());
  }

  @Test
  @DisplayName("getCurrentFields for a freshly started Game has currently the white pieces")
  void TestStartGetCurrentFields() throws IllegalStateException, IllegalParameterException {
    Board board = chess2Service.startNewGame();
    Coordinate[] coordinates = chess2Service.getCurrentFields();
    for (Coordinate coordinate : coordinates) {
      assertEquals(FieldState.HAS_CURRENT_PIECE, chess2Service.getFieldState(coordinate));
      assertTrue(coordinate.getX() > -1 && coordinate.getX() < 8);
      assertTrue(coordinate.getY() > -1 && coordinate.getY() < 2);
    }
  }

  @Test
  @DisplayName("getCurrentFields after one round for the black pieces")
  void TestNextTurnGetCurrentFields()
      throws IllegalStateException, IllegalParameterException, InvalidMoveException {
    Board board = chess2Service.startNewGame();
    Coordinate[] currentCoords = chess2Service.getCurrentFields();
    Coordinate[] possibleMoves = chess2Service.getPossibleMoves(currentCoords[0]);
    chess2Service.moveSelectedPiece(currentCoords[0], new Coordinate(4, 1));

    currentCoords = chess2Service.getCurrentFields();
    for (Coordinate coordinate : currentCoords) {
      assertEquals(FieldState.HAS_CURRENT_PIECE, chess2Service.getFieldState(coordinate));
      assertTrue(coordinate.getX() > -1 && coordinate.getX() < 8);
      assertTrue(coordinate.getY() > 5 && coordinate.getY() < 8);
    }
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
  @DisplayName("FieldState gives correct FieldState any Time")
  void TestGetFieldStateAtomic() throws IllegalStateException, IllegalParameterException {
    Board board = chess2Service.startNewGame();

    FieldState fieldState = chess2Service.getFieldState(new Coordinate(0, 0));
    FieldState fieldState2 = chess2Service.getFieldState(new Coordinate(0, 0));

    assertEquals(fieldState, fieldState2);
  }

  @Test
  @DisplayName("WinninPlayerState gives correct State any Time")
  void TestGetWinningPlayerStateAtomic() {
    Board board = chess2Service.startNewGame();

    WinningPlayerState winState = chess2Service.getWinningPlayer();
    WinningPlayerState winState2 = chess2Service.getWinningPlayer();

    assertEquals(winState, winState2);
  }

  @Test
  @DisplayName("GameState of a not started Game has to be null or a defined value")
  void TestGetGameState_OfNotStartedGame() {
    GameState gameState = chess2Service.getGameState();
    assertNull(gameState);
  }

  @Test
  @DisplayName("GameState gives correct State any Time")
  void TestGetGameStateAtomic() {
    chess2Service.startNewGame();
    GameState gameState = chess2Service.getGameState();
    GameState gameState2 = chess2Service.getGameState();
    assertEquals(gameState2, gameState);
  }

  @Test
  @DisplayName("GameState of a freshly started Game should be RUNNING")
  void TestGetGameState_OfStartedGame() {
    chess2Service.startNewGame();
    GameState gameState = chess2Service.getGameState();
    assertEquals(GameState.RUNNING, gameState);
  }

  @Test
  @DisplayName("GameState after a Player gave up, should be CHECKMATE")
  void TestGetGameState_WhenGivenUp() {
    chess2Service.startNewGame();
    chess2Service.giveUp();
    GameState gameState = chess2Service.getGameState();
    assertEquals(GameState.CHECKMATE, gameState);
  }

}
