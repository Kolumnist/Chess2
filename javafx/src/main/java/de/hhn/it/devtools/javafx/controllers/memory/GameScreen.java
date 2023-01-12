package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.apis.memory.TimerListener;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import de.hhn.it.devtools.javafx.controllers.TemplateController;
import de.hhn.it.devtools.javafx.controllers.template.ScreenController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameScreen implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameScreen.class);
  public static final String SCREEN = "game.screen";

  private MemoryScreenController screenController;
  private SfsMemoryService memoryService;
  private TimerListener timerListener;
  @FXML
  private Button finishButton;

  @FXML
  private AnchorPane gameScreenAnchorPane;

  @FXML
  private Button optionsButton;

  @FXML
  private Label timeDisplayLabel;

  @FXML
  private Label timeTextLabel;

  @FXML
  private GridPane mainGrid;



  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    MemoryAttributeStore memoryAttributeStore = MemoryAttributeStore.getReference();
    screenController =
            (MemoryScreenController) memoryAttributeStore.getAttribute(MemoryServiceController.SCREEN_CONTROLLER);
    memoryService = (SfsMemoryService) memoryAttributeStore.getAttribute(MemoryServiceController.MEMORY_SERVICE);
    int cnt = 0;
    for (int i = 0; i < mainGrid.getColumnCount(); i++) {
      for (int j = 0; j < mainGrid.getRowCount(); j++) {
        mainGrid.add(new CardController(memoryService.getCurrentCardSet().getDescriptor().getPictureCardDescriptors()[cnt++]), i, j);
      }
    }

    timerListener = new TimerListener() {
      @Override
      public void currentTime(int time) {
        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            timeDisplayLabel.setText(Integer.toString(time));
          }
        });
      }
    };

    try {
      memoryService.addCallback(timerListener);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }

    memoryService.startTimer();


    logger.info("Game Screen initialized.");
  }


  @FXML
  void handle(MouseEvent event) {
    Object source = event.getSource();
    if (source == finishButton) {
      if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
        finishButton.setStyle("-fx-background-color: LIGHTGRAY; -fx-border-color: BLACK; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
        finishButton.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      }
    } else if (source == optionsButton) {
      if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
        optionsButton.setStyle("-fx-background-color: LIGHTGRAY; -fx-border-color: BLACK; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
        optionsButton.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      }
    }
  }

  @FXML
  void onFinishButtonClicked(ActionEvent event) throws IllegalParameterException {
    memoryService.closeGame();
    memoryService.stopTimer();
    memoryService.resetTimer();
    memoryService.removeCallback(timerListener);

    screenController.switchTo(StartScreen.SCREEN);
  }

  @FXML
  void onOptionsButtonClicked(ActionEvent event) {
    screenController.switchTo(DifficultyPopup.OPEN_POPUP);
  }

  public void changeDifficulty(Difficulty difficulty) {
    try {
      memoryService.changeDifficulty(difficulty);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
  }
}
