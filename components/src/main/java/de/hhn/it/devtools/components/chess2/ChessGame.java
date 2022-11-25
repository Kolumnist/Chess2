package de.hhn.it.devtools.components.chess2;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.GameState;
import de.hhn.it.devtools.apis.chess2.WinningPlayerState;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * The main Controller class.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.1
 */

public class ChessGame implements Chess2Service {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ChessGame.class);

  private final Player whitePlayer;
  private final Player blackPlayer;
  private Player currentPlayer;

  private Board gameBoard;
  public WinningPlayerState winState;
  public GameState gameState;

  /* TODO: Write Javadocs*/
  public ChessGame() {
    gameBoard = new Board();
    whitePlayer = new Player('w');
    blackPlayer = new Player('b');
    currentPlayer = whitePlayer;
  }

  @Override
  public Board startNewGame() {
    logger.info("startNewGame");

    gameState = GameState.RUNNING;
    winState = WinningPlayerState.STILL_RUNNING;

    return gameBoard;
  }

  /* TODO: think really hard about what this method should do */
  @Override
  public void endGame() {
    logger.info("endGame");

    gameBoard = null;
    currentPlayer = null;
    gameState = null;
    winState = null;
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

    /* TODO: Ask team -> can the Player have less pieces when some of em are defeated? */
    for (int i = 0; i < currentPlayer.myPieces.length; i++) {
      currentPlayer.myPieces[i].getCoordinate();
    }

    return currentCoordinates;
  }

  @Override
  public Coordinate[] getPossibleMoves(Coordinate selectedPieceCoordinate)
      throws IllegalParameterException {
    logger.info("getPossibleMoves", selectedPieceCoordinate);

    return new Coordinate[0];
  }

  @Override
  public void moveSelectedPiece(Coordinate selectedCoordinate, Coordinate newCoordinate)
      throws IllegalParameterException {
    logger.info("moveSelectedPiece", selectedCoordinate, newCoordinate);

  }

  @Override
  public FieldState getFieldState(Coordinate selectedCoordinate) throws IllegalParameterException {
    logger.info("getFieldState", selectedCoordinate);

    return gameBoard.getSpecificField(selectedCoordinate).getFieldState();
  }
}
