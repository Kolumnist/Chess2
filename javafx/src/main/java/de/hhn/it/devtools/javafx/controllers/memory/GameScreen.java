package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.apis.memory.TimerListener;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class GameScreen implements Initializable {
  public static final String SCREEN = "game.screen";
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(GameScreen.class);
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

  @FXML
  private Label systemLabel;


  public void disableGameScreen() {
    optionsButton.setDisable(true);
    finishButton.setDisable(true);
    disableGrid();
    memoryService.stopTimer();
  }

  public void enableGameScreen() {
    optionsButton.setDisable(false);
    finishButton.setDisable(false);
    activateGrid();
    memoryService.startTimer();
  }


  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    MemoryAttributeStore memoryAttributeStore = MemoryAttributeStore.getReference();
    screenController =
            (MemoryScreenController) memoryAttributeStore
                    .getAttribute(MemoryServiceController.SCREEN_CONTROLLER);
    memoryService = (SfsMemoryService) memoryAttributeStore
            .getAttribute(MemoryServiceController.MEMORY_SERVICE);

    int cnt = 0;
    PictureCardDescriptor[] currentDeck = shuffle(memoryService
            .getCurrentCardSet().getDescriptor().getPictureCardDescriptors());
    // Arrays.stream(currentDeck).toList().forEach(
    // PictureCardDescriptor -> logger.info("Id: "+PictureCardDescriptor
    // .getId()+" Name: " + PictureCardDescriptor.getName()+
    // " Ref: "+ PictureCardDescriptor.getPictureRef()));
    for (int i = 0; i < mainGrid.getColumnCount(); i++) {
      for (int j = 0; j < mainGrid.getRowCount(); j++) {
        mainGrid.add(new CardController(currentDeck[cnt++]), i, j);
      }
    }


    timerListener = time -> Platform.runLater(() ->
            timeDisplayLabel.setText(Integer.toString(time)));

    try {
      memoryService.addCallback(timerListener);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }

    memoryService.startTimer();


    logger.info("Game Screen initialized.");
  }

  public void gameWon() {
    memoryService.stopTimer();
    mainGrid.setDisable(true);
  }

  public void disableGrid() {
    mainGrid.setDisable(true);
  }

  public void activateGrid() {
    mainGrid.setDisable(false);
  }

  public PictureCardDescriptor[] shuffle(PictureCardDescriptor[] array) {
    Random random = new Random();
    for (int i = array.length - 1; i > 0; i--) {
      int index = random.nextInt(i + 1);
      PictureCardDescriptor temp = array[index];
      array[index] = array[i];
      array[i] = temp;
    }
    return array;
  }

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
        optionsButton.setStyle("-fx-background-color: LIGHTGRAY; -fx-border-color: BLACK;"
                + " -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
        optionsButton.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK;"
                + " -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
      }
    }
  }

  @FXML
  void onFinishButtonClicked(ActionEvent event) {
    closeGame();
  }

  @FXML
  void onOptionsButtonClicked(ActionEvent event) {
    screenController.disableGameGrid();
    memoryService.stopTimer();
    screenController.switchTo(DifficultyPopup.OPEN_POPUP);
  }

  public String getCurrentTime() {
    return timeDisplayLabel.getText();
  }

  public void setSystemMessage(String message) {
    systemLabel.setText(message.trim());
  }

  public void closeGame() {
    memoryService.closeGame();
    memoryService.stopTimer();
    memoryService.resetTimer();
    memoryService.closeGame();
    if (timerListener != null) {
      try {
        memoryService.removeCallback(timerListener);
        timerListener = null;
      } catch (IllegalParameterException e) {
        e.printStackTrace();
      }
    }
    screenController.switchTo(StartScreen.SCREEN);
  }
}
