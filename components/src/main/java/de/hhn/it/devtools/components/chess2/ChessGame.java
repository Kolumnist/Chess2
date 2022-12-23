package de.hhn.it.devtools.components.chess2;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.Field;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.GameState;
import de.hhn.it.devtools.apis.chess2.Piece;
import de.hhn.it.devtools.apis.chess2.WinningPlayerState;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.chess2.pieces.Bear;
import de.hhn.it.devtools.components.chess2.pieces.King;
import de.hhn.it.devtools.components.chess2.pieces.Queen;
import java.util.Optional;
import java.util.Random;

/**
 * The main Controller class.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.2
 */

public class ChessGame implements Chess2Service {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ChessGame.class);

  private final Player whitePlayer;
  private final Player blackPlayer;
  private Player currentPlayer;
  private Coordinate bearCoordinate;

  protected Board gameBoard;
  protected WinningPlayerState winState;
  protected GameState gameState;

  protected boolean currentKingInJail;

  /**
   * The Constructor that initializes every needed object.
   */
  public ChessGame() {
    logger.info("ChessGame Constructor");

    gameBoard = new Board();
    whitePlayer = new Player('w', gameBoard);
    blackPlayer = new Player('b', gameBoard);
    currentPlayer = whitePlayer;
  }

  /**
   * All Player pieces get "set" on the Board
   * and the fieldStates get set.
   * The bear piece get "set" on the Board.
   */
  private void initializeBoard() {
    logger.info("initializeBoard");

    Piece whitePiece;
    Piece blackPiece;

    for (int i = 0; i < whitePlayer.myPieces.length; i++) {
      whitePiece = whitePlayer.myPieces[i];
      blackPiece = blackPlayer.myPieces[i];

      /* Set Optional Piece of Field */
      gameBoard.getSpecificField(whitePiece.getCoordinate())
          .setPiece(Optional.of(whitePiece));
      gameBoard.getSpecificField(blackPiece.getCoordinate())
          .setPiece(Optional.of(blackPiece));

      /* Set FieldState of Field */
      gameBoard.getSpecificField(whitePiece.getCoordinate())
          .setFieldState(FieldState.HAS_CURRENT_PIECE);
      gameBoard.getSpecificField(blackPiece.getCoordinate())
          .setFieldState(FieldState.HAS_OTHER_PIECE);
    }
    /* Set Optional Piece and FieldState of the Bear */
    gameBoard.getSpecificField(bearCoordinate)
        .setPiece(Optional.of(new Bear('g', bearCoordinate)));
    gameBoard.getSpecificField(bearCoordinate)
        .setFieldState(FieldState.HAS_BEAR);
  }

  private void setUpNewRound() {
    for (Piece piece : whitePlayer.myPieces) {

    }
    for (Piece piece : blackPlayer.myPieces) {

    }

  }

  @Override
  public Board startNewGame() {
    logger.info("startNewGame");

    /* "Random" coordinate for the bear */
    int x = new Random().nextInt(1, 7);
    int y = new Random().nextInt(3, 5);
    bearCoordinate = new Coordinate(x, y);

    initializeBoard();

    gameState = GameState.RUNNING;
    winState = WinningPlayerState.STILL_RUNNING;
    currentKingInJail = false;

    return gameBoard;
  }

  /* TODO: think really hard about what this method should do */
  @Override
  public void endGame() {
    logger.info("endGame");

    currentPlayer = whitePlayer;
    gameState = null;
    winState = WinningPlayerState.NO_WINNER;
  }

  @Override
  public void giveUp() {
    logger.info("giveUp");

    gameState = GameState.CHECKMATE;
    if (currentPlayer == whitePlayer) {
      winState = WinningPlayerState.BLACK_WIN;
    } else if (currentPlayer == blackPlayer) {
      winState = WinningPlayerState.WHITE_WIN;
    }
  }

  @Override
  public WinningPlayerState getWinningPlayer() {
    logger.info("getWinningPlayer");

    return winState;
  }

  @Override
  public GameState getGameState() {
    logger.info("getGameState");

    return gameState;
  }

  @Override
  public Coordinate[] getCurrentFields() {
    logger.info("getCurrentFields");

    Coordinate[] currentCoordinates = new Coordinate[currentPlayer.myPieces.length];
    for (int i = 0; i < currentPlayer.myPieces.length; i++) {
      currentCoordinates[i] = currentPlayer.myPieces[i].getCoordinate();
    }
    //This returns all pieces also defeated once that have no coordinate on the field but it can
    //still get checked by the UI if it is even a possible Button!
    return currentCoordinates;
  }

  @Override
  public Coordinate[] getPossibleMoves(Coordinate selectedPieceCoordinate)
      throws IllegalParameterException {
    logger.info("getPossibleMoves", selectedPieceCoordinate);

    Field field = gameBoard.getSpecificField(selectedPieceCoordinate);
    gameBoard.getSpecificField(selectedPieceCoordinate).setFieldState(FieldState.SELECTED);

    if (field.getFieldState() == FieldState.HAS_CURRENT_PIECE
        || field.getFieldState() == FieldState.HAS_BEAR) {
      return field.getPiece().getPossibleMove();
    }

    return new Coordinate[0];
  }

  /* TODO: Next method with Michel */
  @Override
  public void moveSelectedPiece(Coordinate selectedCoordinate, Coordinate newCoordinate)
      throws IllegalParameterException {
    logger.info("moveSelectedPiece", selectedCoordinate, newCoordinate);

    /* TODO: Check if there is a Piece on the field two fields */

    Piece currentPiece = gameBoard.getSpecificField(selectedCoordinate).getPiece();
    Piece otherPiece = gameBoard.getSpecificField(newCoordinate).getPiece();

    if (otherPiece.getClass().equals(King.class) || otherPiece.getClass().equals(Queen.class)) {

      /*TODO: Doc some stuff here it is complicated*/
      int jailOffset = new Random().nextInt(0, 2);
      if (currentPlayer == whitePlayer) {
        Coordinate jailCoordinate = new Coordinate(3 + jailOffset, 8 + jailOffset);
        whitePlayer.setPieceOnJail(otherPiece, jailCoordinate);
      } else if (currentPlayer == blackPlayer) {
        Coordinate jailCoordinate = new Coordinate(4 - jailOffset, 8 + jailOffset);
        blackPlayer.setPieceOnJail(otherPiece, jailCoordinate);
      }
    }

    gameBoard.getSpecificField(selectedCoordinate).setPiece(Optional.empty());
    gameBoard.getSpecificField(newCoordinate).setPiece(Optional.ofNullable(currentPiece));
    gameBoard.getSpecificField(selectedCoordinate).setFieldState(FieldState.FREE_FIELD);
    gameBoard.getSpecificField(newCoordinate).setFieldState(FieldState.HAS_CURRENT_PIECE);

    setUpNewRound();

  }

  @Override
  public FieldState getFieldState(Coordinate selectedCoordinate) throws IllegalParameterException {
    logger.info("getFieldState", selectedCoordinate);

    return gameBoard.getSpecificField(selectedCoordinate).getFieldState();
  }
}
