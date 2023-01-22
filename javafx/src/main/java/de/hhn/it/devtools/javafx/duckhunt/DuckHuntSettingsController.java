package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.GameSettingsDescriptor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DuckHuntSettingsController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuckHuntSettingsController.class);

  public static final String SCREEN = "settings.screen";
  private DuckHuntScreenController screenController;
  private GameSettingsDescriptor gameSettings;
  private DuckHuntSoundManager soundManager;


  @FXML
  private Label ammoCountLabel;

  @FXML
  private Slider ammoCountSlider;

  @FXML
  private Button applyButton;

  @FXML
  private Button backButton;

  @FXML
  private Label maxDuckCountLabel;

  @FXML
  private Slider maxDuckCountSlider;
  @FXML
  private Label missedCountLabel;
  @FXML
  private Slider missedCountSlider;

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Label volumeLabel;

  @FXML
  private Slider volumeSlider;

  @FXML
  void applyGameSettings(MouseEvent event) {
    gameSettings.setAmmoAmount((int) ammoCountSlider.getValue());
    gameSettings.setDuckAmount((int) maxDuckCountSlider.getValue());
    gameSettings.setMaxMissedCount((int) missedCountSlider.getValue());
    soundManager.changeVolume(volumeSlider.getValue());
    soundManager.playSound(DuckHuntSounds.BUTTONCLICK);
  }

  @FXML
  void backToMainMenu(MouseEvent event) {
    screenController.switchTo(SCREEN, DuckHuntMenuController.SCREEN);
    soundManager.playSound(DuckHuntSounds.BUTTONCLICK);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    DuckHuntAttributeStore duckHuntAttributeStore = DuckHuntAttributeStore.getReference();
    screenController =
        (DuckHuntScreenController) duckHuntAttributeStore
            .getAttribute(DuckHuntScreenController.SCREEN_CONTROLLER);

    gameSettings = (GameSettingsDescriptor) duckHuntAttributeStore
        .getAttribute("gameSettings");
    soundManager = (DuckHuntSoundManager) duckHuntAttributeStore
            .getAttribute("soundManager");

    logger.info("Start Screen initialized.");


    maxDuckCountSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      maxDuckCountLabel.setText(Integer.toString((int) maxDuckCountSlider.getValue()));
      if ((int) maxDuckCountSlider.getValue() >= (int) ammoCountSlider.getValue()) {
        ammoCountSlider.setValue((int) maxDuckCountSlider.getValue());
      }
    });
    missedCountSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      missedCountLabel.setText(Integer.toString((int) missedCountSlider.getValue()));
    });
    ammoCountSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      ammoCountLabel.setText(Integer.toString((int) ammoCountSlider.getValue()));
      if ((int) ammoCountSlider.getValue() <= (int) maxDuckCountSlider.getValue()) {
        maxDuckCountSlider.setValue((int) ammoCountSlider.getValue());
      }
    });
    volumeSlider.valueProperty().addListener((observable, oldValue, newValue) ->
        volumeLabel.setText(Integer.toString((int) volumeSlider.getValue())));

    maxDuckCountSlider.setValue(gameSettings.getduckAmount());
    ammoCountSlider.setValue(gameSettings.getAmmoAmount());
  }
}