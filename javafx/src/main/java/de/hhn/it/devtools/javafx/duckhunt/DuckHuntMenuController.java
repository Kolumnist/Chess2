package de.hhn.it.devtools.javafx.duckhunt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for the main Menu screen.
 */
public class DuckHuntMenuController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuckHuntMenuController.class);

  public static final String SCREEN = "menu.screen";
  private DuckHuntScreenController screenController;
  private DuckHuntSoundManager soundManager;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    DuckHuntAttributeStore duckHuntAttributeStore = DuckHuntAttributeStore.getReference();
    screenController =
        (DuckHuntScreenController) duckHuntAttributeStore
            .getAttribute(DuckHuntScreenController.SCREEN_CONTROLLER);
    soundManager = (DuckHuntSoundManager) duckHuntAttributeStore.getAttribute("soundManager");
  }

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Button howToPlayButton;

  @FXML
  private Button settingsButton;

  @FXML
  private Button startGameButton;

  @FXML
  void showInstructions(ActionEvent event) {
    soundManager.playSound(DuckHuntSounds.BUTTONCLICK);
    screenController.switchTo(DuckHuntMenuController.SCREEN, DuckHuntDescriptionController.SCREEN);
  }

  @FXML
  void showSettings(ActionEvent event) {
    soundManager.playSound(DuckHuntSounds.BUTTONCLICK);
    screenController.switchTo(DuckHuntMenuController.SCREEN, DuckHuntSettingsController.SCREEN);
  }

  @FXML
  void startGame(ActionEvent event) {
    soundManager.playSound(DuckHuntSounds.BUTTONCLICK);
    screenController.switchTo(DuckHuntMenuController.SCREEN, DuckHuntGameController.SCREEN);
  }
}
