package de.hhn.it.devtools.javafx.duckhunt;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

  }

  @FXML
  void backToMainMenu(MouseEvent event) {
    screenController.switchTo(SCREEN, DuckHuntMenuController.SCREEN);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    DuckHuntAttributeStore duckHuntAttributeStore = DuckHuntAttributeStore.getReference();
    screenController =
        (DuckHuntScreenController) duckHuntAttributeStore
            .getAttribute(DuckHuntScreenController.SCREEN_CONTROLLER);

    logger.info("Start Screen initialized.");

    maxDuckCountSlider.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        maxDuckCountLabel.setText(Integer.toString((int) maxDuckCountSlider.getValue()));
        if ((int) maxDuckCountSlider.getValue() >= (int) ammoCountSlider.getValue()) {
          ammoCountSlider.setValue((int) maxDuckCountSlider.getValue());
        }
      }
    });
    ammoCountSlider.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        ammoCountLabel.setText(Integer.toString((int) ammoCountSlider.getValue()));
        if ((int) ammoCountSlider.getValue() <= (int) maxDuckCountSlider.getValue()) {
          maxDuckCountSlider.setValue((int) ammoCountSlider.getValue());
        }
      }
    });
    volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        volumeLabel.setText(Integer.toString((int) volumeSlider.getValue()));
      }
    });
  }
}

