package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.IllegalStateException;
import de.hhn.it.devtools.apis.chess2.WinningPlayerState;
import de.hhn.it.devtools.components.chess2.ChessGame;
import de.hhn.it.devtools.javafx.chess2.Chess2BoardController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Chess2ServiceController extends Controller implements Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Chess2ServiceController.class);
  private Board board;
  private ChessGame chessGame;
  private Chess2Service chess2Service;

  private Coordinate[] coordinates;

  @FXML
  private AnchorPane boardPane;

  @FXML
  private Button giveUpButton;

  @FXML
  private TextField playerTextField;

  @FXML
  private Button rulesButton;

  @FXML
  private Button startEndGameButton;

  public Chess2ServiceController(){
    chessGame = new ChessGame();
    chess2Service = chessGame;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    startEndGameButton.setText("Start Game");
    giveUpButton.setVisible(false);
  }

  @FXML
  void onGiveUpButtonClick(ActionEvent event) {
    if (chessGame.getWinningPlayer() == WinningPlayerState.STILL_RUNNING){
      chess2Service.giveUp();
      //Hier bitte das GiveUp PopUp Fenster aufrufen.
    }
  }

  @FXML
  void onRulesButtonClick(ActionEvent event) {
    //Hier bitte das Rules PopUp Fenster aufrufen.
  }

  @FXML
  void onStartEndGameButtonClick(ActionEvent event) throws IllegalStateException {

    if (startEndGameButton.getText().equals("Start Game")){
      startEndGameButton.setText("End Game");
      giveUpButton.setVisible(true);

      board = chess2Service.startNewGame();
      coordinates = chessGame.getCurrentFields();

      Node node;
      try {
        node = (Node) FXMLLoader.load(getClass().getResource("/fxml/chess2/Chess2Board.fxml"));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      boardPane.getChildren().setAll(node);

    }else {
      startEndGameButton.setText("Start Game");
      giveUpButton.setVisible(false);

      chess2Service.endGame();
    }
  }
}
