package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.apis.memory.MemoryService;
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


/**
 * Start screen of memory.
 */
public class StartScreenController implements Initializable {
  public static final String SCREEN = "start.screen";
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(StartScreenController.class);
  private MemoryScreenController screenController;
  private MemoryService memoryService;

  @FXML
  private Button finishButton;

  @FXML
  private Button optionsButton;

  @FXML
  private Button startButton;

  @FXML
  private Label difficultyLabel;

  @FXML
  private Label highScore;

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
    screenController.switchTo(DifficultyPopupController.OPEN_POPUP);
  }

  @FXML
  void onStartButtonClicked(ActionEvent event) {
    try {
      screenController.closeGameScreen();
      memoryService.newGame((Difficulty) MemoryAttributeStore.getReference()
              .getAttribute(MemoryServiceController.DIFFICULTY));
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
    screenController.switchTo(GameScreenController.SCREEN);
  }


  /**
   * Called to initialize a controller after its root element has been
   * completely processed.
   *
   * @param location  The location used to resolve relative paths for the root object, or
   *                  <code>null</code> if the location is not known.
   * @param resources The resources used to localize the root object, or <code>null</code> if
   *                  unknown.
   */
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

  /**
   * Changes the label to show the current difficulty on the start screen in german.
   *
   * @param difficulty the current difficulty
   */
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

  /**
   * Sets the highScore on the start screen.
   *
   * @param highScore the current highScore
   */
  public void setHighScore(String highScore) {
    this.highScore.setText(highScore.trim());
  }
}
