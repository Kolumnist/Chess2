package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class StartScreen implements Initializable {
  public static final String SCREEN = "start.screen";
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(StartScreen.class);
  private MemoryScreenController screenController;
  private SfsMemoryService memoryService;

  @FXML
  private Button finishButton;

  @FXML
  private Button optionsButton;

  @FXML
  private Button startButton;

  @FXML
  private Label difficultyLabel;

  @FXML
  void handle(MouseEvent event) {
    Object source = event.getSource();
    if (source == finishButton) {
      if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
        finishButton.setStyle("-fx-background-color: LIGHTGRAY; -fx-border-color: BLACK;"
                + " -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
        finishButton.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK;"
                + " -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      }
    } else if (source == optionsButton) {
      if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
        optionsButton.setStyle("-fx-background-color: LIGHTGRAY; -fx-border-color: BLACK; "
                + "-fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
        optionsButton.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK; "
                + "-fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      }
    } else if (source == startButton) {
      if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
        startButton.setStyle("-fx-background-color: LIGHTGRAY; -fx-border-color: BLACK; "
                + "-fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
        startButton.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK; "
                + "-fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      }
    }
  }

  @FXML
  void onFinishButtonClicked(ActionEvent event) {
    Stage stage = (Stage) finishButton.getScene().getWindow();
    stage.close();
  }

  @FXML
  void onOptionButtonClicked(ActionEvent event) {
    screenController.switchTo(DifficultyPopup.OPEN_POPUP);
  }

  @FXML
  void onStartButtonClicked(ActionEvent event) {
    try {
      memoryService.newGame((Difficulty) MemoryAttributeStore.getReference()
              .getAttribute(MemoryServiceController.DIFFICULTY));
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
    screenController.switchTo(GameScreen.SCREEN);
  }


  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    MemoryAttributeStore memoryAttributeStore = MemoryAttributeStore.getReference();
    screenController =
            (MemoryScreenController) memoryAttributeStore
                    .getAttribute(MemoryServiceController.SCREEN_CONTROLLER);
    memoryService = (SfsMemoryService) memoryAttributeStore
            .getAttribute(MemoryServiceController.MEMORY_SERVICE);

    logger.info("Start Screen initialized.");
  }

  public void changeDifficulty(String difficulty) {
    String difficultyText = switch (difficulty) {
      case "easy" -> "Einfach";
      case "medium" -> "Mittel";
      case "hard" -> "Schwer";
      case "veryhard" -> "Extrem";
      default -> "";
    };
    difficultyLabel.setText(difficultyText);
  }

}
