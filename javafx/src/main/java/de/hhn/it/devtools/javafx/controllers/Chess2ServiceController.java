package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.IllegalStateException;
import de.hhn.it.devtools.apis.chess2.WinningPlayerState;
import de.hhn.it.devtools.components.chess2.ChessGame;
import de.hhn.it.devtools.javafx.chess2.Chess2BoardController;
import de.hhn.it.devtools.javafx.chess2.Chess2PopUp;
import de.hhn.it.devtools.javafx.chess2.Chess2PopUpRulesController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * This class implements the main controller for the UI of Chess2.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */
public class Chess2ServiceController extends Controller implements Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Chess2ServiceController.class);
  private Board board;
  private ChessGame chessGame;
  private Chess2Service chess2Service;

  private GridPane gridPane;

  @FXML
  private BorderPane boardPane;

  @FXML
  private Button giveUpButton;

  @FXML
  private static TextField playerTextField;

  @FXML
  private Button startEndGameButton;

  public Chess2ServiceController() {
    chessGame = new ChessGame();
    chess2Service = chessGame;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    startEndGameButton.setText("Start Game");
    giveUpButton.setVisible(false);
    loadBoard();
  }

  @FXML
  void onGiveUpButtonClick(ActionEvent event) {
    if (chessGame.getWinningPlayer() == WinningPlayerState.STILL_RUNNING) {
      chess2Service.giveUp();
      Chess2PopUp p = new Chess2PopUp();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chess2"
          + "/Chess2PopUp.fxml"));
      try {
        loader.load();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      if (WinningPlayerState.RED_WIN == chessGame.getWinningPlayer()) {
        Label l = new Label("Player Black gave up.\nPlayer Red has won.");
        p.initialize(l);
      }
      if (WinningPlayerState.BLACK_WIN == chessGame.getWinningPlayer()) {
        Label l = new Label("Player Red gave up.\nPlayer Black has won.");
        p.initialize(l);
      }
    }
  }

  @FXML
  void onRulesButtonClick(ActionEvent event) {
    Chess2PopUpRulesController p = new Chess2PopUpRulesController();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chess2"
        + "/Chess2PopUpRules.fxml"));
    try {
      loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    p.initialize();
  }

  @FXML
  void onStartEndGameButtonClick(ActionEvent event) throws IllegalStateException {

    if (startEndGameButton.getText().equals("Start Game")) {
      startEndGameButton.setText("End Game");
      giveUpButton.setVisible(true);

      board = chess2Service.startNewGame();

      placePieces();
      Chess2BoardController.giveChessGameAndBoard(chessGame, board);

    } else {
      startEndGameButton.setText("Start Game");
      giveUpButton.setVisible(false);

      chess2Service.endGame();

      loadBoard();
    }
  }

  /**
   * This method load the Chess2Board.fxml.
   */
  private void loadBoard() {
    try {
      gridPane = FXMLLoader.load(getClass().getResource("/fxml/chess2/Chess2Board.fxml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    boardPane.setCenter(gridPane);
  }

  /**
   * This method goes through every Button and places the image of a Piece on it if a Piece stands
   * on it.
   */
  private void placePieces() {
    String[] piece = {"Crow", "Elephant", "Fish", "King-With-Banana", "Monkey", "Queen"};
    String[] color = {"_Red", "_Black"};

    String bearCoordinate = chessGame.bearCoordinate.coordinateToString();

    //Getting all Buttons from the parent GridPane.
    ObservableList observableList = gridPane.getChildren();

    Button[] buttons = new Button[68];

    int i = 0;

    //Turns the ObservableList into an Array for easier handling.
    for (Object o : observableList) {
      if (o instanceof Button) {
        buttons[i++] = (Button) o;
      }
    }

    //Going through the Button-Array and placing the right image on it.
    for (Button button : buttons) {
      //Setting the Bear image on the right button.
      if (bearCoordinate.equals(button.getId().substring(6, 8))) {
        setPieceImage(button, "Bear", "");
        continue;
      }

      //Setting the King image on the right button.
      if (equals(button.getId().substring(6, 8), new String[]{"40"})) {
        setPieceImage(button, piece[3], color[0]);
        continue;
      } else if (equals(button.getId().substring(6, 8), new String[]{"37"})) {
        setPieceImage(button, piece[3], color[1]);
        continue;
      }

      //Setting the Queen image on the right button.
      if (equals(button.getId().substring(6, 8), new String[]{"30"})) {
        setPieceImage(button, piece[5], color[0]);
        continue;
      } else if (equals(button.getId().substring(6, 8), new String[]{"47"})) {
        setPieceImage(button, piece[5], color[1]);
        continue;
      }

      //Setting the Crow image on the right button.
      if (equals(button.getId().substring(6, 8), new String[]{"00", "70"})) {
        setPieceImage(button, piece[0], color[0]);
        continue;
      } else if (equals(button.getId().substring(6, 8), new String[]{"07", "77"})) {
        setPieceImage(button, piece[0], color[1]);
        continue;
      }

      //Setting the Monkey image on the right button.
      if (equals(button.getId().substring(6, 8), new String[]{"10", "60"})) {
        setPieceImage(button, piece[4], color[0]);
        continue;
      } else if (equals(button.getId().substring(6, 8), new String[]{"17", "67"})) {
        setPieceImage(button, piece[4], color[1]);
        continue;
      }

      //Setting the Elephant image on the right button.
      if (equals(button.getId().substring(6, 8), new String[]{"21", "51"})) {
        setPieceImage(button, piece[1], color[0]);
        continue;
      } else if (equals(button.getId().substring(6, 8), new String[]{"26", "56"})) {
        setPieceImage(button, piece[1], color[1]);
        continue;
      }

      //Setting the Fish image on the right button.
      if (equals(button.getId().substring(6, 8),
          new String[]{"01", "11", "20", "31", "41", "50", "61", "71"})) {
        setPieceImage(button, piece[2], color[0]);
      } else if (equals(button.getId().substring(6, 8),
          new String[]{"06", "16", "27", "36", "46", "57", "66", "76"})) {
        setPieceImage(button, piece[2], color[1]);
      }
    }
  }

  /**
   * Retruns true if the string is an element in the arrayString.
   *
   * @param string      the string you got from a method
   * @param arrayString all Strings that you search for
   * @return if the string is one of the elements from the arrayString
   */
  private boolean equals(String string, String[] arrayString) {
    for (String s : arrayString) {
      if (string.equals(s)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Sets the image of the right Piece on a specific Button.
   *
   * @param button the Button on which the Piece will stand
   * @param piece  name of the Piece which should stand on the Field/Button
   * @param color  the color of the Piece
   */
  private void setPieceImage(Button button, String piece, String color) {
    ImageView imageView;

    imageView = new ImageView(
        getClass().getResource("/fxml/chess2/pieces/" + piece + color + ".png").toExternalForm());
    imageView.setPreserveRatio(true);
    imageView.fitWidthProperty().bind(button.widthProperty());
    imageView.fitHeightProperty().bind(button.heightProperty());

    button.setGraphic(imageView);
  }

  @Override
  void pause() {
    logger.debug("pause: -");
  }

  @Override
  void resume() {
    logger.debug("resume: -");
  }

  // To give the Chess2BoardController the possibility to set the Text of the playerTextField.
  public static void setPlayerTextField(String playerColor) {
    playerTextField.setText(playerColor);
  }
}
