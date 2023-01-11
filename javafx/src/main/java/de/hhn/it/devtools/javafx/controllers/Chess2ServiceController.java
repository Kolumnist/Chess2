package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.IllegalStateException;
import de.hhn.it.devtools.components.chess2.ChessGame;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public class Chess2ServiceController extends Controller implements Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Chess2ServiceController.class);
  private Board board;
  private ChessGame chessGame;
  private Chess2Service chess2Service;

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
    chess2Service.giveUp();
  }

  @FXML
  void onRulesButtonClick(ActionEvent event) {

  }

  @FXML
  void onStartEndGameButtonClick(ActionEvent event) throws IllegalStateException {

    if (startEndGameButton.getText().equals("Start Game")){
      startEndGameButton.setText("End Game");
      giveUpButton.setVisible(true);

      //TODO: Mit Collin klären wie dann alles weitere funktioniert
      board = chess2Service.startNewGame();

    }else {
      startEndGameButton.setText("Start Game");
      giveUpButton.setVisible(false);

      chess2Service.endGame();
    }
  }
}
