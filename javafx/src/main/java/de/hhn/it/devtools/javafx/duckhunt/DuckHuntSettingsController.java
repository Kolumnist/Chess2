package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.GameSettingsDescriptor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DuckHuntSettingsController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuckHuntSettingsController.class);

  public static final String SCREEN = "settings.screen";
  private DuckHuntScreenController screenController;
  private GameSettingsDescriptor gameSettings;
  private Media media;
  private MediaPlayer mediaPlayer;
  private File directory;
  private File[] files;
  ArrayList<File> songs;
  private int songNumber = 0;
  private DuckHuntSoundManager duckHuntSoundManager;


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
  private AnchorPane anchorPane;

  @FXML
  private Label volumeLabel;

  @FXML
  private Slider volumeSlider;

  @FXML
  void applyGameSettings(MouseEvent event) {
    gameSettings.setAmmoAmount((int) ammoCountSlider.getValue());
    gameSettings.setDuckAmount((int) maxDuckCountSlider.getValue());
    duckHuntSoundManager.changeVolume(volumeSlider.getValue());
    duckHuntSoundManager.playSound(DuckHuntSounds.BUTTONCLICK);
  }

  @FXML
  void backToMainMenu(MouseEvent event) {
    screenController.switchTo(SCREEN, DuckHuntMenuController.SCREEN);
    duckHuntSoundManager.playSound(DuckHuntSounds.BUTTONCLICK);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    DuckHuntAttributeStore duckHuntAttributeStore = DuckHuntAttributeStore.getReference();
    screenController =
        (DuckHuntScreenController) duckHuntAttributeStore
            .getAttribute(DuckHuntScreenController.SCREEN_CONTROLLER);

    gameSettings = (GameSettingsDescriptor) duckHuntAttributeStore
        .getAttribute("gameSettings");

    logger.info("Start Screen initialized.");


    maxDuckCountSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      maxDuckCountLabel.setText(Integer.toString((int) maxDuckCountSlider.getValue()));
      if ((int) maxDuckCountSlider.getValue() >= (int) ammoCountSlider.getValue()) {
        ammoCountSlider.setValue((int) maxDuckCountSlider.getValue());
      }
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

    duckHuntSoundManager = new DuckHuntSoundManager();
  }
}

