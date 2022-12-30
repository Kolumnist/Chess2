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
import de.hhn.it.devtools.components.chess2.pieces.Monkey;
import de.hhn.it.devtools.components.chess2.pieces.Queen;
import java.util.Optional;
import java.util.Random;

/**
 * The main Controller class.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.4
 */

public class ChessGame implements Chess2Service {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ChessGame.class);

  private final Player whitePlayer;
  private final Player blackPlayer;
  private Player currentPlayer;
  private Coordinate currentlySelected; //Could be used instead of selectedCoordinate
  private boolean monkeyChaos = false;
  private Bear bear;

  protected final Board gameBoard;
  protected WinningPlayerState winState;
  protected GameState gameState;

  protected Coordinate bearCoordinate;

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
   * All Player pieces get "set" on the Board and the fieldStates get set. The bear piece get "set"
   * on the Board.
   */
  private void initializeBoard() {
    logger.info("initializeBoard");

    /* Set all FieldState beside the Jail to Free_Field */
    int diff = -1;
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        gameBoard.getFields()[++diff].setFieldState(FieldState.FREE_FIELD);
      }
    }

    /* Set the FieldState of the Jails to Jail */
    int fieldsMax = gameBoard.getFields().length;
    gameBoard.getFields()[fieldsMax - 4].setFieldState(FieldState.JAIL);
    gameBoard.getFields()[fieldsMax - 2].setFieldState(FieldState.JAIL);
    gameBoard.getFields()[fieldsMax - 3].setFieldState(FieldState.JAIL);
    gameBoard.getFields()[fieldsMax - 1].setFieldState(FieldState.JAIL);

    /* Set the Pieces back to the old position */
    whitePlayer.initializeMyPieces();
    blackPlayer.initializeMyPieces();

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
        .setPiece(Optional.of(bear));
    gameBoard.getSpecificField(bearCoordinate)
        .setFieldState(FieldState.HAS_BEAR);

    for (Piece piece : whitePlayer.myPieces) {
      piece.calculate();
    }
    for (Piece piece : blackPlayer.myPieces) {
      piece.calculate();
    }
    gameBoard.getSpecificField(bearCoordinate).getPiece().calculate();
  }

  /**
   * We check through all Jail fields if there is any Queen/King and raise the black/white counter.
   * Should the counter hit 2 one player wins
   *
   * @return if a player won or not! (true = won / false = not)
   */
  private boolean handleWin() {
    logger.info("handleWin");

    int blackLose = 0;
    int whiteLose = 0;

    Field[] fields = gameBoard.getFields();

    /* Go through all 4 Jail fields and check if there is a King or Queen on it */
    for (int i = 1; i < 5; i++) {
      if ((fields[fields.length - i].getFieldState() == FieldState.JAIL_KING
          && fields[fields.length - i].getPiece().getColor() == 'b')
          || (fields[fields.length - i].getFieldState() == FieldState.JAIL_QUEEN
          && fields[fields.length - i].getPiece().getColor() == 'b')) {
        blackLose++;
        continue;
      }
      if ((fields[fields.length - i].getFieldState() == FieldState.JAIL_KING
          && fields[fields.length - i].getPiece().getColor() == 'w')
          || (fields[fields.length - i].getFieldState() == FieldState.JAIL_QUEEN
          && fields[fields.length - i].getPiece().getColor() == 'w')) {
        whiteLose++;
      }
    }

    /* Should a player have both his King and Queen in Jail, return true and "end the game"*/
    if (whiteLose == 2) {
      gameState = GameState.CHECKMATE;
      winState = WinningPlayerState.BLACK_WIN;
      return true;
    }
    if (blackLose == 2) {
      gameState = GameState.CHECKMATE;
      winState = WinningPlayerState.WHITE_WIN;
      return true;
    }
    return false;
  }

  /**
   * Before every new Round this method gets called. It initializes the new Round sets all
   * fieldStates and currentPlayer to the correct value.
   * It also TODO: tests if there is Mate/Check
   */
  private void setUpNewRound() {
    logger.info("setUpNewRound");

    /* Call handleWin() if it is true yeah we do nothing */
    if (handleWin()) {
      return;
    }

    /* Switch currentPlayer to the otherPlayer */
    if (whitePlayer == currentPlayer) {
      currentPlayer = blackPlayer;
    } else {
      currentPlayer = whitePlayer;
    }

    //Could do a switch case, but I don't think it differs much
    /* Switch FieldStates from HAS_CURRENT_PIECE to HAS_OTHER_PIECE and the other way round */
    for (Field field : gameBoard.getFields()) {
      if (field.getFieldState() == FieldState.HAS_CURRENT_PIECE) {
        gameBoard.getSpecificField(field.getCoordinate())
            .setFieldState(FieldState.HAS_OTHER_PIECE);
        continue;
      }
      if (field.getFieldState() == FieldState.HAS_OTHER_PIECE) {
        gameBoard.getSpecificField(field.getCoordinate())
            .setFieldState(FieldState.HAS_CURRENT_PIECE);
      }
    }

    /* Calculate all pieces movements */
    for (Piece piece : whitePlayer.myPieces) {
      if (piece.getCoordinate().getX() == -1) {
        continue;
      }
      piece.calculate();
    }
    for (Piece piece : blackPlayer.myPieces) {
      if (piece.getCoordinate().getY() == -1) {
        continue;
      }
      piece.calculate();
    }
    gameBoard.getSpecificField(bearCoordinate).getPiece().calculate();
  }

  @Override
  public Board startNewGame() {
    logger.info("startNewGame");

    /* "Random" coordinate for the bear */
    int x = new Random().nextInt(1, 7);
    int y = new Random().nextInt(3, 5);
    bearCoordinate = new Coordinate(x, y);
    bear = new Bear('g', bearCoordinate);

    initializeBoard();
    gameBoard.lostPiece = false;

    gameState = GameState.RUNNING;
    winState = WinningPlayerState.STILL_RUNNING;

    return gameBoard;
  }

  @Override
  public void endGame() {
    logger.info("endGame");

    //currentPlayer = whitePlayer;
    gameState = null;
    winState = WinningPlayerState.NO_WINNER;
  }

  @Override
  public void giveUp() {
    logger.info("giveUp");

    if (gameState == GameState.CHECKMATE || winState != WinningPlayerState.STILL_RUNNING) {
      return;
    }

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
  public FieldState getFieldState(Coordinate selectedCoordinate)
      throws IllegalParameterException {
    logger.info("getFieldState", selectedCoordinate);

    return gameBoard.getSpecificField(selectedCoordinate).getFieldState();
  }

  @Override
  public Coordinate[] getCurrentFields() {
    logger.info("getCurrentFields");

    if (monkeyChaos) {
      return new Coordinate[]{currentlySelected};
    }

    Coordinate[] currentCoordinates = new Coordinate[currentPlayer.myPieces.length];
    for (int i = 0; i < currentPlayer.myPieces.length; i++) {
      currentCoordinates[i] = currentPlayer.myPieces[i].getCoordinate();
    }
    //This returns all pieces also defeated once that have no coordinate on the field, but it can
    //still get checked by the UI if it is even a possible Button!
    return currentCoordinates;
  }

  @Override
  public Coordinate[] getPossibleMoves(Coordinate selectedPieceCoordinate)
      throws IllegalParameterException {
    logger.info("getPossibleMoves", selectedPieceCoordinate);

    Field field = gameBoard.getSpecificField(selectedPieceCoordinate);

    /* Should a Piece be SELECTED already it gets the new FieldState HAS_CURRENT_PIECE */
    if (currentlySelected != null) {
      gameBoard.getSpecificField(currentlySelected).setFieldState(FieldState.HAS_CURRENT_PIECE);
    }

    /* When a "correct" Field gets selected the pieces possible Moves get returned */
    if (field.getFieldState() == FieldState.HAS_CURRENT_PIECE
        || field.getFieldState() == FieldState.HAS_BEAR) {

      gameBoard.getSpecificField(selectedPieceCoordinate).setFieldState(FieldState.SELECTED);
      currentlySelected = field.getCoordinate();
      return field.getPiece().getPossibleMove();
    }

    return new Coordinate[64];
  }

  @Override
  public Board moveSelectedPiece(Coordinate selectedCoordinate, Coordinate newCoordinate)
      throws IllegalParameterException {
    logger.info("moveSelectedPiece", selectedCoordinate, newCoordinate);

    if (gameBoard.getSpecificField(selectedCoordinate).getFieldState() != FieldState.SELECTED) {
      return gameBoard;
    }

    FieldState oldFieldState = gameBoard.getSpecificField(newCoordinate).getFieldState();

    /* Selected Piece gets moved to the new Coordinate also FieldState */
    if (oldFieldState == FieldState.FREE_FIELD) {

      //Piece gets new Coordinate
      gameBoard.getSpecificField(selectedCoordinate).getPiece()
          .setCoordinate(newCoordinate);

      //New Field gets updated & lostPiece set to false
      gameBoard.getSpecificField(newCoordinate)
          .setPiece(Optional.of(gameBoard.getSpecificField(selectedCoordinate).getPiece()));
      gameBoard.getSpecificField(newCoordinate)
          .setFieldState(FieldState.HAS_CURRENT_PIECE);
      gameBoard.lostPiece = false;

      /* The SELECTED piece is Bear: FieldState -> HAS_BEAR and Coordinates get updated*/
      if (gameBoard.getSpecificField(selectedCoordinate).getPiece() == bear) {
        gameBoard.getSpecificField(newCoordinate).setFieldState(FieldState.HAS_BEAR);
        bearCoordinate = newCoordinate;
        bear.setCoordinate(bearCoordinate);

        /* The SELECTED piece be a Monkey and is able to jump,
         * the current Player can move with the Monkey till he is not able to anymore! */
      } else if (gameBoard.getSpecificField(selectedCoordinate).getPiece()
          .getClass().equals(Monkey.class)) {
        gameBoard.getSpecificField(selectedCoordinate).setPiece(Optional.empty());
        gameBoard.getSpecificField(selectedCoordinate).setFieldState(FieldState.FREE_FIELD);
        Monkey monkey = (Monkey) gameBoard.getSpecificField(selectedCoordinate).getPiece();
        //monkey.calculateJump();
        monkeyChaos = true;
        return gameBoard;
      }

      /* Selected Piece gets moved to the new Coordinate also FieldState.
       * Checks if the hit Piece is a King or Queen they get send to Jail accordingly.
       * Lastly it sets the Coordinate of the otherPiece to -1/-1 */
    } else if (oldFieldState == FieldState.HAS_OTHER_PIECE) {

      //Piece gets new Coordinate
      gameBoard.getSpecificField(selectedCoordinate).getPiece()
          .setCoordinate(newCoordinate);

      /* Test for Queen/King and put them in Jail */
      if (gameBoard.getSpecificField(newCoordinate).getPiece()
          .getClass().equals(King.class)) {
        currentPlayer.setKingOnJail(gameBoard.getSpecificField(newCoordinate).getPiece());
      } else if (gameBoard.getSpecificField(newCoordinate).getPiece()
          .getClass().equals(Queen.class)) {
        currentPlayer.setQueenOnJail(gameBoard.getSpecificField(newCoordinate).getPiece());
        /* Or update the destroyed Piece */
      } else {
        gameBoard.getSpecificField(newCoordinate).getPiece()
            .setCoordinate(new Coordinate(-1, -1));
      }
      //New Field gets updated & lostPiece set to true
      gameBoard.getSpecificField(newCoordinate)
          .setPiece(Optional.of(gameBoard.getSpecificField(selectedCoordinate).getPiece()));
      gameBoard.getSpecificField(newCoordinate)
          .setFieldState(FieldState.HAS_CURRENT_PIECE);
      gameBoard.lostPiece = true;

      /* The new Field has the Bear on it. Both Pieces get destroyed and yes King/Queen get
       * send to Jail if they slay the bear.*/
    } else if (oldFieldState == FieldState.HAS_BEAR) {

      //Update bearCoordinate
      bearCoordinate = new Coordinate(-1, -1);
      bear.setCoordinate(bearCoordinate);

      /* Test for Queen/King and put them in Jail */
      if (gameBoard.getSpecificField(selectedCoordinate).getPiece() //Kinda duplicate but no
          .getClass().equals(King.class)) {
        currentPlayer.setKingOnJail(gameBoard.getSpecificField(selectedCoordinate).getPiece());
      } else if (gameBoard.getSpecificField(selectedCoordinate).getPiece()
          .getClass().equals(Queen.class)) {
        currentPlayer.setQueenOnJail(gameBoard.getSpecificField(selectedCoordinate).getPiece());
        /* Or update the destroyed Piece */
      } else {
        gameBoard.getSpecificField(newCoordinate).getPiece()
            .setCoordinate(new Coordinate(-1, -1));
      }

      //New Field gets updated & lostPiece set to true
      gameBoard.getSpecificField(newCoordinate).setPiece(Optional.empty());
      gameBoard.getSpecificField(newCoordinate).setFieldState(FieldState.FREE_FIELD);
      gameBoard.lostPiece = true;
    }

    //The old SELECTED Field gets updated
    gameBoard.getSpecificField(selectedCoordinate).setPiece(Optional.empty());
    gameBoard.getSpecificField(selectedCoordinate).setFieldState(FieldState.FREE_FIELD);
    currentlySelected = null;

    setUpNewRound();
    return gameBoard;
  }
}
