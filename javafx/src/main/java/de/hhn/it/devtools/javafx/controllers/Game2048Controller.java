package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.game2048.State;
import de.hhn.it.devtools.javafx.controllers.game2048.Game2048ScreenController;
import de.hhn.it.devtools.javafx.game2048.Game2048FileIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class Game2048Controller extends Controller implements Initializable {
  @FXML
  public AnchorPane game2048AnchorPane;

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="HighscoreValueStart"
  private Label highscoreValueStart; // Value injected by FXMLLoader


  @FXML // fx:id="startGameButton"
  private Button startGameButton; // Value injected by FXMLLoader

  private State currentState;

  @FXML
  void startGameButtonClicked(ActionEvent event) {
    Game2048ScreenController screenController = new Game2048ScreenController(game2048AnchorPane);
    try {
      screenController.switchTo("GameView2048");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    highscoreValueStart.setText(String.valueOf(Game2048FileIO.loadHighscore()));
  }
}