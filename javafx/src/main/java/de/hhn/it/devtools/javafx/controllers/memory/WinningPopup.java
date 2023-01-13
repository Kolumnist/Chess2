package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class WinningPopup extends VBox implements Initializable {
  public static final String OPEN_POPUP = "winning.popup.open";
  public static final String CLOSE_POPUP = "winning.popup.close";


  MemoryScreenController screenController;

  @FXML
  private Label difficultyLabel;

  @FXML
  private Button finishButton;

  @FXML
  private Label timeLabel;


  public WinningPopup(final MemoryScreenController screenController) {
    this.screenController = screenController;

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/memory/WinningScreen.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void onFinishButtonPressed(ActionEvent event) {
    screenController.switchTo(WinningPopup.CLOSE_POPUP);
  }

  @FXML
  void handle(MouseEvent event) {
    Object source = event.getSource();
    if (source == finishButton) {
      if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
        finishButton.setStyle("-fx-background-color: LIGHTGRAY; -fx-border-color: BLACK;"
                + " -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
        finishButton.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK; "
                + "-fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      }
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    difficultyLabel.setText(difficultyLabel.getText() + " " + getTextFromDifficulty((Difficulty)
            MemoryAttributeStore.getReference().getAttribute(MemoryServiceController.DIFFICULTY)));
    timeLabel.setText(timeLabel.getText() + " " + screenController.getCurrentTime() + " Sekunden");
    screenController.disableGameScreen();
  }

  public String getTextFromDifficulty(Difficulty difficulty) {
    return switch (difficulty) {
      case EASY -> "Einfach";
      case MEDIUM -> "Mittel";
      case HARD -> "Schwer";
      case VERYHARD -> "Extrem";
    };
  }
}
