package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class DifficultyPopup extends VBox {
  public static final String OPEN_POPUP = "difficulty.popup.open";
  public static final String CLOSE_POPUP = "difficulty.popup.close";

  MemoryScreenController screenController;
  RadioButton hover;

  @FXML
  private Button closeDifficulty;

  @FXML
  private Button saveDifficulty;

  @FXML
  private RadioButton easy;

  @FXML
  private RadioButton medium;

  @FXML
  private RadioButton hard;

  @FXML
  private RadioButton veryhard;

  public DifficultyPopup(final MemoryScreenController screenController) {
    this.screenController = screenController;
    FXMLLoader loader = new FXMLLoader(getClass()
            .getResource("/fxml/memory/DifficultyScreen.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    hover = easy;
  }

  @FXML
  void onCloseButtonPressed(ActionEvent event) {
    switch ((Difficulty) MemoryAttributeStore.getReference()
            .getAttribute(MemoryServiceController.DIFFICULTY)) {
      case EASY -> {
        hover = easy;
        easy.setSelected(true);
      }
      case MEDIUM -> {
        hover = medium;
        medium.setSelected(true);
      }
      case HARD -> {
        hover = hard;
        hard.setSelected(true);
      }
      case VERYHARD -> {
        hover = veryhard;
        veryhard.setSelected(true);
      }
      default -> {
      }
    }
    screenController.switchTo(CLOSE_POPUP);
    screenController.enableStartScreen();
    screenController.enableGameScreen();
  }

  @FXML
  void onSaveButtonPressed(ActionEvent event) {
    screenController.changeDifficulty(hover.getId().trim().toLowerCase());
    screenController.switchTo(CLOSE_POPUP);
    screenController.closeGameScreen();
    screenController.enableStartScreen();
    screenController.switchTo(StartScreen.SCREEN);
  }

  @FXML
  void onSelect(ActionEvent event) {
    hover = (RadioButton) event.getSource();
  }

  @FXML
  void handle(MouseEvent event) {
    Object source = event.getSource();
    if (source == closeDifficulty) {
      if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
        closeDifficulty.setStyle("-fx-background-color: LIGHTGRAY;"
                + " -fx-border-color: BLACK; -fx-border-radius: 10;"
                + " -fx-background-radius: 10; -fx-border-width: 3;");
      } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
        closeDifficulty.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK; "
                + "-fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      }
    } else if (source == saveDifficulty) {
      if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
        saveDifficulty.setStyle("-fx-background-color: LIGHTGRAY; -fx-border-color: BLACK; "
                + "-fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
        saveDifficulty.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK; "
                + "-fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      }
    }
  }
}
