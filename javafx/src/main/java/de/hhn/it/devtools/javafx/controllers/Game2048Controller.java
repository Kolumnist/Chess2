package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.Game2048Listener;
import de.hhn.it.devtools.apis.game2048.State;
import de.hhn.it.devtools.components.game2048.provider.ImplementationGame2048Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;



public class Game2048Controller extends Controller implements Initializable, Game2048Listener {
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

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    currentState = null;
    ImplementationGame2048Service gameService = new ImplementationGame2048Service();
    try {
      gameService.addGameServiceById(0);
      gameService = gameService.getGameServiceById(0);
      gameService.addCallback(this);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
    highscoreValueStart.setText(String.valueOf(currentState.getNewHighscore()));
  }

  @Override
  public void newState(State state) {
    currentState = state;
  }
}
