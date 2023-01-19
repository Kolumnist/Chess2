package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.apis.memory.State;
import de.hhn.it.devtools.apis.memory.TimerListener;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import de.hhn.it.devtools.javafx.memory.CardView;
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

/**
 * Game Screen of memory.
 */
public class GameScreenController implements Initializable, TimerListener {
  public static final String SCREEN = "game.screen";
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(GameScreenController.class);
  private MemoryScreenController screenController;
  private SfsMemoryService memoryService;
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


  /**
   * Called to initialize a controller after its root element has been
   * completely processed.
   *
   * @param location  The location used to resolve relative paths for the root object, or
   *                  <code>null</code> if the location is not known.
   * @param resources The resources used to localize the root object, or <code>null</code> if
   *                  unknown
   */
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
    for (int i = 0; i < mainGrid.getColumnCount(); i++) {
      for (int j = 0; j < mainGrid.getRowCount(); j++) {
        mainGrid.add(new CardView(currentDeck[cnt++]), i, j);
      }
    }

    try {
      memoryService.addCallback(this);
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
    screenController.switchTo(DifficultyPopupController.OPEN_POPUP);
  }

  /**
   * Disables the game screen.
   */
  public void disableGameScreen() {
    optionsButton.setDisable(true);
    finishButton.setDisable(true);
    disableGrid();
    memoryService.stopTimer();
  }

  /**
   * Enables the game screen.
   */
  public void enableGameScreen() {
    optionsButton.setDisable(false);
    finishButton.setDisable(false);
    activateGrid();
    memoryService.startTimer();
  }

  /**
   * Handles timer and grid of cards if game is won.
   */
  public void gameWon() {
    memoryService.stopTimer();
    mainGrid.setDisable(true);
  }

  /**
   * Disables the grid of cards.
   */
  public void disableGrid() {
    mainGrid.setDisable(true);
  }

  /**
   * Activates the grid of cards.
   */
  public void activateGrid() {
    mainGrid.setDisable(false);
  }

  /**
   * Sets the message on the game screen for the player to see.
   *
   * @param message that should be displayed to the player
   */
  public void setSystemMessage(String message) {
    systemLabel.setText(message.trim());
  }

  /**
   * Closes the game.
   */
  public void closeGame() {
    memoryService.stopTimer();
    memoryService.resetTimer();
    for (PictureCardDescriptor c : memoryService.getPictureCardDescriptors()) {
      c.setState(State.HIDDEN);
    }
    memoryService.closeGame();
    try {
      memoryService.removeCallback(this);
    } catch (IllegalParameterException e) {
    }
    screenController.switchTo(StartScreenController.SCREEN);
  }

  /**
   * Shuffles the card descriptors.
   *
   * @param array the card descriptors
   * @return shuffled card descriptors
   */
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

  /**
   * Returns the current time shown in the label on the game screen.
   *
   * @return String the current time
   */
  public String getCurrentTime() {
    return timeDisplayLabel.getText();
  }

  @Override
  public void currentTime(int time) {
    Platform.runLater(() ->
        timeDisplayLabel.setText(Integer.toString(time)));
  }
}
