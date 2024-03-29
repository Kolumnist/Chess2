package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * Popup for the winning screen of memory game.
 */
public class WinningPopupController implements Initializable {
  public static final String OPEN_POPUP = "winning.popup.open";
  public static final String CLOSE_POPUP = "winning.popup.close";


  MemoryScreenController screenController;

  @FXML
  private Label difficultyLabel;

  @FXML
  private Button finishButton;

  @FXML
  private Label timeLabel;


  public WinningPopupController() {
    this.screenController =
        (MemoryScreenController) MemoryAttributeStore.getReference()
            .getAttribute(MemoryServiceController.SCREEN_CONTROLLER);
  }

  @FXML
  void onFinishButtonPressed(ActionEvent event) {
    screenController.switchTo(WinningPopupController.CLOSE_POPUP);
    screenController.closeGameScreen();
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

  /**
   * Called to initialize a controller after its root element has been
   * completely processed.
   *
   * @param url  The location used to resolve relative paths for the root object, or
   *             <code>null</code> if the location is not known.
   * @param resourceBundle The resources used to localize the root object, or <code>null</code> if
   *                       unknown.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    difficultyLabel.setText(difficultyLabel.getText() + " " + getTextFromDifficulty((Difficulty)
            MemoryAttributeStore.getReference().getAttribute(MemoryServiceController.DIFFICULTY)));
    timeLabel.setText(timeLabel.getText() + " " + screenController.getCurrentTime() + " Sekunden");
    screenController.disableGameScreen();
  }

  /**
   * Return the difficulty as german text.
   *
   * @param difficulty the difficulty the text should be made for
   * @return String difficulty as german text
   */
  public String getTextFromDifficulty(Difficulty difficulty) {
    return switch (difficulty) {
      case EASY -> "Einfach";
      case MEDIUM -> "Mittel";
      case HARD -> "Schwer";
      case VERYHARD -> "Extrem";
    };
  }
}
