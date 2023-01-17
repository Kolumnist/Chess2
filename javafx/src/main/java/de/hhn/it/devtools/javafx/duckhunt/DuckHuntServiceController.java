package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.javafx.controllers.Controller;
import de.hhn.it.devtools.javafx.controllers.template.PurProgrammingScreen;
import de.hhn.it.devtools.javafx.controllers.template.ScreenController;
import de.hhn.it.devtools.javafx.controllers.template.UnknownTransitionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DuckHuntServiceController extends Controller implements Initializable {

  @FXML
  private Button howToPlayButton;

  @FXML
  private Button settingsButton;

  @FXML
  private Button startGameButton;

  @FXML
  private AnchorPane anchorPane;

  DuckHuntScreenController screenController;

  @FXML
  void showInstructions(ActionEvent event) {

  }

  @FXML
  void showSettings(ActionEvent event) {

  }

  @FXML
  void startGame(ActionEvent event) {

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    screenController = new DuckHuntScreenController(anchorPane);
    /*try {
      screenController.switchTo(null, PurProgrammingScreen.SCREEN);
    } catch (UnknownTransitionException e) {
      e.printStackTrace();
    }*/
  }
}