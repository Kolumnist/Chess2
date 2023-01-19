package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.javafx.controllers.connectfour.SceneChanger;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.FileIO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 * Main controller for the component.
 */
public class ConnectFourController extends Controller implements Initializable {

  private final FileIO file = new FileIO();

  @FXML
  BorderPane root;

  /**
   * Opens singleplayer screen.
   */
  @FXML
  void onSingleplayer() {
    SceneChanger.changeScene(root, "/fxml/connectfour/SingleplayerScreen.fxml");
  }

  /**
   * Opens multiplayer screen.
   */
  @FXML
  void onMultiplayer() {
    SceneChanger.changeScene(root, "/fxml/connectfour/MultiplayerScreen.fxml");
  }

  /**
   * Opens highscores screen.
   */
  @FXML
  void onHighscores() {
    SceneChanger.changeScene(root, "/fxml/connectfour/HighscoresScreen.fxml");
  }

  /**
   * Opens profiles screen.
   */
  @FXML
  void onProfiles() {
    SceneChanger.changeScene(root, "/fxml/connectfour/ProfilesScreen.fxml");
  }

  /**
   * Loads existing profiles.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    file.loadProfileData();
  }
}
