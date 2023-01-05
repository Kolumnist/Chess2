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
  Board board;

  @BeforeEach
  void setup() {
    chessGame = new ChessGame();
    chess2Service = chessGame;
    board = chess2Service.startNewGame();
  }

  @Test
  @DisplayName("Start a completely new Game and test all important variables")
  void TestStartGame() {
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
    chess2Service.giveUp();
    assertEquals(WinningPlayerState.BLACK_WIN, chess2Service.getWinningPlayer());
  }

  @Test
  @DisplayName("Player Black gives Up and White wins")
  void TestBlackGiveUp()
      throws IllegalStateException, IllegalParameterException, InvalidMoveException {

    Coordinate[] currentCoords = chess2Service.getCurrentFields();
    Coordinate[] possibleMoves = chess2Service.getPossibleMoves(currentCoords[0]);
    chess2Service.moveSelectedPiece(currentCoords[0], new Coordinate(4, 1));

    chess2Service.giveUp();
    assertEquals(WinningPlayerState.WHITE_WIN, chess2Service.getWinningPlayer());
  }

  @Test
  @DisplayName("getCurrentFields for a freshly started Game has currently the white pieces")
  void TestStartGetCurrentFields() throws IllegalStateException, IllegalParameterException {
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
  @DisplayName("GetPossibleMoves of a crow can move to any free field")
  void TestGetPossibleMovesOfCrowAtBeginning()
      throws IllegalStateException, IllegalParameterException {
    Coordinate[] coordinates = chess2Service.getPossibleMoves(new Coordinate(0, 0));
    assertEquals(31, coordinates.length);
  }

  @Test
  @DisplayName("GetPossibleMoves of a fish in the backline to no fields")
  void TestGetPossibleMovesOfFishAtBeginning()
      throws IllegalStateException, IllegalParameterException {
    Coordinate[] coordinates = chess2Service.getPossibleMoves(new Coordinate(2, 0));
    assertEquals(0, coordinates.length);
  }

  @Test
  @DisplayName("GetPossibleMoves of a monkey that can only jump two fields")
  void TestGetPossibleMovesOfMonkeyAtBeginning()
      throws IllegalStateException, IllegalParameterException {
    Coordinate[] coordinates = chess2Service.getPossibleMoves(new Coordinate(1, 0));
    assertEquals(2, coordinates.length);
  }

  @Test
  @DisplayName("Selected Bear gets moved")
  void TestMoveBearOneField()
      throws IllegalParameterException, IllegalStateException, InvalidMoveException {

    Coordinate selectCoord = chessGame.bearCoordinate;
    Coordinate[] possibleMoves = chess2Service.getPossibleMoves(selectCoord);
    board = chess2Service.moveSelectedPiece(selectCoord, possibleMoves[0]);

    assertEquals(FieldState.FREE_FIELD, board.getSpecificField(selectCoord).getFieldState());
    assertEquals(FieldState.HAS_BEAR, board.getSpecificField(possibleMoves[0]).getFieldState());
  }

  @Test
  @DisplayName("Selected Monkey gets moved and the turn gets ended")
  void TestMoveJumpMonkeyOnce()
      throws IllegalParameterException, IllegalStateException, InvalidMoveException {

    Coordinate[] possibleMoves = chess2Service.getPossibleMoves(new Coordinate(1, 0));
    board = chess2Service.moveSelectedPiece(new Coordinate(1, 0), possibleMoves[0]);
    board = chess2Service.moveSelectedPiece(possibleMoves[0], possibleMoves[0]);

    assertEquals(FieldState.FREE_FIELD, board.getSpecificField(
        new Coordinate(1, 0)).getFieldState());
    assertEquals(FieldState.HAS_OTHER_PIECE, board.getSpecificField(
        possibleMoves[0]).getFieldState());
  }

  @Test
  @DisplayName("Selected Piece gets moved on bear both are defeated")
  void TestMoveSelectedPieceOnBear()
      throws IllegalParameterException, IllegalStateException, InvalidMoveException {
    //TODO minimum 12-32
    Coordinate[] currentCoords = chess2Service.getCurrentFields();
    Coordinate[] possibleMoves = chess2Service.getPossibleMoves(currentCoords[0]);
    Coordinate bear = new Coordinate();
    for (Field field : board.getFields()) {
      bear = field.getFieldState() == FieldState.HAS_BEAR ? field.getCoordinate() : bear;
    }
    board = chess2Service.moveSelectedPiece(currentCoords[0], bear);

    assertEquals(FieldState.FREE_FIELD, board.getSpecificField(bear).getFieldState());
    assertNull(board.getSpecificField(bear).getPiece());
  }

  @Test
  @DisplayName("Selected King gets moved on bear both are defeated, King sent to Jail")
  void TestMoveSelectedKingOnBear()
      throws IllegalParameterException, IllegalStateException, InvalidMoveException {
    Coordinate[] possibleMoves = chess2Service.getPossibleMoves(new Coordinate(4, 0));
    Coordinate bear = new Coordinate();
    for (Field field : board.getFields()) {
      bear = field.getFieldState() == FieldState.HAS_BEAR ? field.getCoordinate() : bear;
    }
    board = chess2Service.moveSelectedPiece(new Coordinate(4, 0), bear);

    assertEquals(FieldState.JAIL_KING, board.getSpecificField(
        new Coordinate(8, 3)).getFieldState());
    assertNull(board.getSpecificField(bear).getPiece());
  }
  @Test
  @DisplayName("Selected Queen gets moved on bear both are defeated, Queen sent to Jail")
  void TestMoveSelectedQueenOnBear()
      throws IllegalParameterException, IllegalStateException, InvalidMoveException {
    Coordinate[] possibleMoves = chess2Service.getPossibleMoves(new Coordinate(3, 0));
    Coordinate bear = new Coordinate();
    for (Field field : board.getFields()) {
      bear = field.getFieldState() == FieldState.HAS_BEAR ? field.getCoordinate() : bear;
    }
    board = chess2Service.moveSelectedPiece(new Coordinate(3, 0), bear);

    assertEquals(FieldState.JAIL_QUEEN, board.getSpecificField(
        new Coordinate(9, 3)).getFieldState());
    assertNull(board.getSpecificField(bear).getPiece());
  }

  @Test
  @DisplayName("White wins because both the King and Queen of black are in the jail")
  void TestWhiteWin()
      throws IllegalParameterException, IllegalStateException, InvalidMoveException {
    Coordinate[] possibleMoves = chess2Service.getPossibleMoves(new Coordinate(3, 0));
    board = chess2Service.moveSelectedPiece(
        new Coordinate(3, 0), new Coordinate(4, 7));

    possibleMoves = chess2Service.getPossibleMoves(new Coordinate(3, 7));
    Coordinate bear = new Coordinate();
    for (Field field : board.getFields()) {
      bear = field.getFieldState() == FieldState.HAS_BEAR ? field.getCoordinate() : bear;
    }
    chess2Service.moveSelectedPiece(new Coordinate(3, 7), bear);

    assertEquals(GameState.CHECKMATE, chess2Service.getGameState());
    assertEquals(WinningPlayerState.WHITE_WIN, chess2Service.getWinningPlayer());
  }
  @Test
  @DisplayName("Black wins because both the King and Queen of white are in the jail")
  void TestBlackWin()
      throws IllegalParameterException, IllegalStateException, InvalidMoveException {
    Coordinate[] possibleMoves = chess2Service.getPossibleMoves(new Coordinate(3, 0));
    Coordinate bear = new Coordinate();
    for (Field field : board.getFields()) {
      bear = field.getFieldState() == FieldState.HAS_BEAR ? field.getCoordinate() : bear;
    }
    chess2Service.moveSelectedPiece(new Coordinate(3, 0), bear);
    possibleMoves = chess2Service.getPossibleMoves(new Coordinate(3, 7));
    board = chess2Service.moveSelectedPiece(
        new Coordinate(3, 7), new Coordinate(4, 0));

    assertEquals(GameState.CHECKMATE, chess2Service.getGameState());
    assertEquals(WinningPlayerState.BLACK_WIN, chess2Service.getWinningPlayer());
  }

  @Test
  @DisplayName("FieldState gives correct FieldState any Time")
  void TestGetFieldStateAtomic() throws IllegalStateException, IllegalParameterException {
    FieldState fieldState = chess2Service.getFieldState(new Coordinate(0, 0));
    FieldState fieldState2 = chess2Service.getFieldState(new Coordinate(0, 0));

    assertEquals(fieldState, fieldState2);
  }

  @Test
  @DisplayName("WinninPlayerState gives correct State any Time")
  void TestGetWinningPlayerStateAtomic() {
    WinningPlayerState winState = chess2Service.getWinningPlayer();
    WinningPlayerState winState2 = chess2Service.getWinningPlayer();

    assertEquals(winState, winState2);
  }

  @Test
  @DisplayName("GameState gives correct State any Time")
  void TestGetGameStateAtomic() {
    GameState gameState = chess2Service.getGameState();
    GameState gameState2 = chess2Service.getGameState();
    assertEquals(gameState2, gameState);
  }

  @Test
  @DisplayName("GameState of a freshly started Game should be RUNNING")
  void TestGetGameState_OfStartedGame() {
    GameState gameState = chess2Service.getGameState();
    assertEquals(GameState.RUNNING, gameState);
  }

  @Test
  @DisplayName("GameState after a Player gave up, should be CHECKMATE")
  void TestGetGameState_WhenGivenUp() {
    chess2Service.giveUp();
    GameState gameState = chess2Service.getGameState();
    assertEquals(GameState.CHECKMATE, gameState);
  }

  @Test
  @DisplayName("After 3 boring turns there should be no Error")
  void Test3TurnsNoError()
      throws IllegalParameterException, IllegalStateException, InvalidMoveException {

    Coordinate[] currentPieces = chess2Service.getCurrentFields();
    Coordinate[] possibleMoves = chess2Service.getPossibleMoves(currentPieces[9]);
    board = chess2Service.moveSelectedPiece(currentPieces[9], possibleMoves[0]);

    currentPieces = chess2Service.getCurrentFields();
    possibleMoves = chess2Service.getPossibleMoves(currentPieces[9]);
    board = chess2Service.moveSelectedPiece(currentPieces[9], possibleMoves[0]);

    currentPieces = chess2Service.getCurrentFields();
    possibleMoves = chess2Service.getPossibleMoves(currentPieces[9]);
    board = chess2Service.moveSelectedPiece(currentPieces[9], possibleMoves[0]);
  }
}
