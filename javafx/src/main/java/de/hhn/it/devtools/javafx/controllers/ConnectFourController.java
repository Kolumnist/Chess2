package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.javafx.controllers.connectfour.SceneChanger;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.io.FileIo;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

/**
 * Main controller for the component.
 */
public class ConnectFourController extends Controller {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ConnectFourController.class);

  private final FileIo file = new FileIo();

  @FXML
  BorderPane root;

  @FXML
  void onSingleplayer() {
    logger.info("onSingleplayer: no params");
    SceneChanger.changeScene(root, "/fxml/connectfour/SingleplayerScreen.fxml");
  }

  @FXML
  void onMultiplayer() {
    logger.info("onMultiplayer: no params");
    SceneChanger.changeScene(root, "/fxml/connectfour/MultiplayerScreen.fxml");
  }

  @FXML
  void onHighscores() {
    logger.info("onHighscores: no params");
    SceneChanger.changeScene(root, "/fxml/connectfour/HighscoresScreen.fxml");
  }

  @FXML
  void onProfiles() {
    logger.info("onProfiles: no params");
    SceneChanger.changeScene(root, "/fxml/connectfour/ProfilesScreen.fxml");
  }

  @FXML
  public void initialize() {
    logger.info("initialize: no params");
    file.loadProfileData();
  }

}
