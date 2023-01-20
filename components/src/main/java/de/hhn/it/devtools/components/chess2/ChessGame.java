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
 * @version 1.5
 */

public class ChessGame implements Chess2Service {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ChessGame.class);

  private Player redPlayer;
  private Player blackPlayer;
  private Player currentPlayer;
  private Coordinate currentlySelected; //Could be used instead of selectedCoordinate
  private boolean monkeyChaos = false;
  private Bear bear;

  protected Board gameBoard;
  protected WinningPlayerState winState;
  protected GameState gameState;

  public Coordinate bearCoordinate;
  public Coordinate otherKingCoordinate = new Coordinate(3, 7);

  /**
   * The Constructor that initializes every needed object.
   * TODO: REFACTORING EVERY WHITE PLAYER THING TO REDPLAYER ;(((
   */
  public ChessGame() {
    logger.info("ChessGame Constructor");

    gameBoard = new Board();
    redPlayer = new Player('r', gameBoard);
    blackPlayer = new Player('b', gameBoard);
    currentPlayer = redPlayer;
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
    redPlayer.initializeMyPieces();
    blackPlayer.initializeMyPieces();

    Piece redPiece;
    Piece blackPiece;
    for (int i = 0; i < redPlayer.myPieces.length; i++) {
      redPiece = redPlayer.myPieces[i];
      blackPiece = blackPlayer.myPieces[i];

      /* Set Optional Piece of Field */
      gameBoard.getSpecificField(redPiece.getCoordinate())
          .setPiece(Optional.of(redPiece));
      gameBoard.getSpecificField(blackPiece.getCoordinate())
          .setPiece(Optional.of(blackPiece));

      /* Set FieldState of Field */
      gameBoard.getSpecificField(redPiece.getCoordinate())
          .setFieldState(FieldState.HAS_CURRENT_PIECE);
      gameBoard.getSpecificField(blackPiece.getCoordinate())
          .setFieldState(FieldState.HAS_OTHER_PIECE);
    }
    gameBoard.getSpecificField(otherKingCoordinate)
        .setFieldState(FieldState.OTHER_KING);


    /* Set Optional Piece and FieldState of the Bear */
    gameBoard.getSpecificField(bearCoordinate)
        .setPiece(Optional.of(bear));
    gameBoard.getSpecificField(bearCoordinate)
        .setFieldState(FieldState.HAS_BEAR);

    /* Calculate all Pieces movements */
    for (Piece piece : redPlayer.myPieces) {
      piece.calculate(gameBoard);
    }
    for (Piece piece : blackPlayer.myPieces) {
      piece.calculate(gameBoard);
    }
    gameBoard.getSpecificField(bearCoordinate).getPiece().calculate(gameBoard);
  }

  /**
   * We check through all Jail fields if there is any Queen/King and raise the black/red counter.
   * Should the counter hit 2 one player wins
   *
   * @return if a player won or not! (true = won / false = not)
   */
  private boolean handleWin() {
    logger.info("handleWin");

    int blackLose = 0;
    int redLose = 0;

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
          && fields[fields.length - i].getPiece().getColor() == 'r')
          || (fields[fields.length - i].getFieldState() == FieldState.JAIL_QUEEN
          && fields[fields.length - i].getPiece().getColor() == 'r')) {
        redLose++;
      }
    }

    /* Should a player have both his King and Queen in Jail, return true and "end the game"*/
    if (redLose == 2) {
      gameState = GameState.CHECKMATE;
      winState = WinningPlayerState.BLACK_WIN;
      return true;
    }
    if (blackLose == 2) {
      gameState = GameState.CHECKMATE;
      winState = WinningPlayerState.RED_WIN;
      return true;
    }
    return false;
  }

  /**
   * Before every new Round this method gets called. It initializes the new Round sets all
   * fieldStates and currentPlayer to the correct value. MARK tests if there is Mate/Check we won't
   * be getting this done it is too hard
   */
  private void setUpNewRound() {
    logger.info("setUpNewRound");

    /* Call handleWin() if it is true yeah we do nothing */
    if (handleWin()) {
      return;
    }

    /* Switch currentPlayer to the otherPlayer */
    if (redPlayer == currentPlayer) {
      currentPlayer = blackPlayer;
    } else {
      currentPlayer = redPlayer;
    }

    if (gameState == GameState.CHECK) {
      gameState = GameState.RUNNING;
    }

    //Could do a switch case, but I don't think it differs much
    /* Switch FieldStates from HAS_CURRENT_PIECE to HAS_OTHER_PIECE and the other way round */
    for (Field field : gameBoard.getFields()) {
      if (field.getFieldState() == FieldState.FREE_FIELD
          || field.getCoordinate().compareCoordinates(new Coordinate(8, 3))
          || field.getCoordinate().compareCoordinates(new Coordinate(8, 4))
          || field.getCoordinate().compareCoordinates(new Coordinate(9, 3))
          || field.getCoordinate().compareCoordinates(new Coordinate(9, 4))) {
        continue;
      }

      if (gameState != GameState.CHECK && field.getPiece().getCanDefeatKing()) {
        gameState = GameState.CHECK;
      }

      if (field.getPiece().getClass().equals(King.class)
          && field.getFieldState() == FieldState.OTHER_KING) {
        gameBoard.getSpecificField(field.getCoordinate())
            .setFieldState(FieldState.HAS_CURRENT_PIECE);
        continue;
      }
      if (field.getFieldState() == FieldState.HAS_OTHER_PIECE) {
        gameBoard.getSpecificField(field.getCoordinate())
            .setFieldState(FieldState.HAS_CURRENT_PIECE);
        continue;
      }

      if (field.getPiece().getClass().equals(King.class)
          && field.getFieldState() == FieldState.HAS_CURRENT_PIECE) {
        gameBoard.getSpecificField(field.getCoordinate())
            .setFieldState(FieldState.OTHER_KING);
        otherKingCoordinate = field.getCoordinate();
        continue;
      }
      if (field.getFieldState() == FieldState.HAS_CURRENT_PIECE) {
        gameBoard.getSpecificField(field.getCoordinate())
            .setFieldState(FieldState.HAS_OTHER_PIECE);
      }
    }

    /* Calculate all pieces movements */
    for (Piece piece : redPlayer.myPieces) {
      if (piece.getCoordinate().getX() == -1) {
        continue;
      }
      piece.calculate(gameBoard);
    }
    for (Piece piece : blackPlayer.myPieces) {
      if (piece.getCoordinate().getX() == -1) {
        continue;
      }
      piece.calculate(gameBoard);
    }
    if (bearCoordinate.getX() != -1) {
      bear.calculate(gameBoard);
    }
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

    gameState = GameState.CHECKMATE;
    bearCoordinate = null;
    bear = null;
    gameBoard = new Board();
    redPlayer = new Player('r', gameBoard);
    blackPlayer = new Player('b', gameBoard);
    currentPlayer = redPlayer;
    winState = WinningPlayerState.NO_WINNER;
  }

  @Override
  public void giveUp() {
    logger.info("giveUp");

    if (gameState == GameState.CHECKMATE || winState != WinningPlayerState.STILL_RUNNING) {
      return;
    }

    gameState = GameState.CHECKMATE;
    if (currentPlayer == redPlayer) {
      winState = WinningPlayerState.BLACK_WIN;
    } else if (currentPlayer == blackPlayer) {
      winState = WinningPlayerState.RED_WIN;
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

    /* When monkeyChaos, the monkey can only jump no other moves */
    if (monkeyChaos) {
      Monkey monkey = (Monkey) field.getPiece();
      return monkey.getPossibleJump();
    }

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

    FieldState oldPieceFieldState = gameBoard.getSpecificField(newCoordinate).getFieldState();

    /* Selected Piece itself is pressed */
    if (oldPieceFieldState == FieldState.SELECTED) {
      /* Should monkey be pressed and monkeyChaos is true then the round ends */
      if (gameBoard.getSpecificField(selectedCoordinate).getPiece()
          .getClass().equals(Monkey.class) && monkeyChaos) {
        monkeyChaos = false;
        currentlySelected = null;
        gameBoard.getSpecificField(newCoordinate).setFieldState(FieldState.HAS_CURRENT_PIECE);
        setUpNewRound();
        return gameBoard;
      }
      return gameBoard;
    }

    /* Selected Piece gets moved to the new Coordinate also FieldState */
    if (oldPieceFieldState == FieldState.FREE_FIELD) {
      Field selectedField = gameBoard.getSpecificField(selectedCoordinate);

      //Piece gets new Coordinate
      gameBoard.getSpecificField(selectedCoordinate).getPiece()
          .setCoordinate(newCoordinate);

      //New Field gets updated & lostPiece set to false
      gameBoard.getSpecificField(newCoordinate)
          .setPiece(Optional.of(selectedField.getPiece()));
      gameBoard.getSpecificField(newCoordinate)
          .setFieldState(FieldState.HAS_CURRENT_PIECE);
      gameBoard.lostPiece = false;
      monkeyChaos = false;

      selectedField = gameBoard.getSpecificField(selectedCoordinate);

      /* The SELECTED piece is Bear: FieldState -> HAS_BEAR and Coordinates get updated*/
      if (selectedField.getPiece() == bear) {
        bearCoordinate = newCoordinate;
        bear.setCoordinate(bearCoordinate);
        gameBoard.getSpecificField(bearCoordinate).setPiece(Optional.of(bear));
        gameBoard.getSpecificField(bearCoordinate).setFieldState(FieldState.HAS_BEAR);
        monkeyChaos = false;

        /* The SELECTED piece be a Monkey and is able to jump,
         * the current Player can move with the Monkey till he is not able to anymore! */
      } else if (selectedField.getPiece().getClass().equals(Monkey.class)
          && ((selectedCoordinate.getY() > newCoordinate.getX() + 1
          || selectedCoordinate.getY() > newCoordinate.getY() + 1)
          || (selectedCoordinate.getY() < newCoordinate.getX() - 1
          || selectedCoordinate.getY() < newCoordinate.getY() - 1))) {
        gameBoard.getSpecificField(selectedCoordinate).setPiece(Optional.empty());
        gameBoard.getSpecificField(selectedCoordinate).setFieldState(FieldState.FREE_FIELD);
        gameBoard.getSpecificField(newCoordinate).setFieldState(FieldState.SELECTED);

        Monkey monkey = (Monkey) gameBoard.getSpecificField(newCoordinate).getPiece();
        monkey.calculateJump(gameBoard);
        monkeyChaos = true;
        return gameBoard;
      }

      /* Selected Piece gets moved to the new Coordinate also FieldState.
       * Checks if the hit Piece is a King or Queen they get send to Jail accordingly.
       * Lastly it sets the Coordinate of the otherPiece to -1/-1 */
    } else if (oldPieceFieldState == FieldState.HAS_OTHER_PIECE
        || oldPieceFieldState == FieldState.OTHER_KING) {

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
      monkeyChaos = false;
      /* The new Field has the Bear on it. Both Pieces get destroyed and yes King/Queen get
       * send to Jail if they slay the bear.*/
    } else if (oldPieceFieldState == FieldState.HAS_BEAR) {

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
        gameBoard.getSpecificField(selectedCoordinate).getPiece()
            .setCoordinate(new Coordinate(-1, -1));
      }

      //New Field gets updated & lostPiece set to true
      gameBoard.getSpecificField(newCoordinate).setPiece(Optional.empty());
      gameBoard.getSpecificField(newCoordinate).setFieldState(FieldState.FREE_FIELD);
      gameBoard.lostPiece = true;
      monkeyChaos = false;
    }

    //The old SELECTED Field gets updated
    gameBoard.getSpecificField(selectedCoordinate).setPiece(Optional.empty());
    gameBoard.getSpecificField(selectedCoordinate).setFieldState(FieldState.FREE_FIELD);
    currentlySelected = null;

    setUpNewRound();
    return gameBoard;
  }
}
