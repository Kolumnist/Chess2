package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.javafx.controllers.connectfour.SceneChanger;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.io.FileIO;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

/**
 * Main controller for the component.
 */
public class ConnectFourController extends Controller {

  private final FileIO file = new FileIO();

  @FXML
  BorderPane root;

  @FXML
  void onSingleplayer() {
    SceneChanger.changeScene(root, "/fxml/connectfour/SingleplayerScreen.fxml");
  }

  @FXML
  void onMultiplayer() {
    SceneChanger.changeScene(root, "/fxml/connectfour/MultiplayerScreen.fxml");
  }

  @FXML
  void onHighscores() {
    SceneChanger.changeScene(root, "/fxml/connectfour/HighscoresScreen.fxml");
  }

  @FXML
  void onProfiles() {
    SceneChanger.changeScene(root, "/fxml/connectfour/ProfilesScreen.fxml");
  }

  @FXML
  public void initialize() {
    file.loadProfileData();
  }

}
