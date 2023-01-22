package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import de.hhn.it.devtools.javafx.controllers.template.UnknownTransitionException;
import de.hhn.it.devtools.javafx.memory.DifficultyPopupView;
import de.hhn.it.devtools.javafx.memory.WinningPopupView;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Screencontroller for memory screens and popups.
 */
public class MemoryScreenController {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MemoryScreenController.class);

  AnchorPane anchorPane;
  private int highScore = 0;

  private StartScreenController startScreenController;
  private Node startScreenContent;
  private GameScreenController gameScreenController;
  private DifficultyPopupView difficultyPopupView;
  private Stage difficultyStage;
  private Stage winningStage;
  private Scene difficultyScene;

  public MemoryScreenController(final AnchorPane anchorPane) {
    this.anchorPane = anchorPane;
  }

  /**
   * Returns the start screen content as node and sets up the controller.
   *
   * @return Node the start screen
   */
  private Node getStartScreen() {
    if (startScreenController == null) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/memory/StartScreen.fxml"));
      try {
        startScreenContent = loader.load();
        startScreenController = loader.getController();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return startScreenContent;
  }

  /**
   * Returns the game screen content as node and set ups the controller.
   *
   * @return Node the game screen
   */
  private Node getGameScreen() {
    Node gameScreenContent = null;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/memory/GameScreen.fxml"));
    try {
      gameScreenContent = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    gameScreenController = loader.getController();
    return gameScreenContent;
  }

  /**
   * Builds up difficulty popup stage and returns it.
   *
   * @return stage the difficulty popup stage
   */
  private Stage getDifficultyStage() {
    if (difficultyPopupView == null) {
      difficultyPopupView = new DifficultyPopupView();
    }
    if (difficultyScene == null) {
      difficultyScene = new Scene(difficultyPopupView, 600, 400);
    }
    if (difficultyStage == null) {
      difficultyStage = new Stage();
      difficultyStage.initStyle(StageStyle.UNDECORATED);
      difficultyStage.setAlwaysOnTop(true);
      difficultyStage.setScene(difficultyScene);
      difficultyStage.setResizable(false);
    }
    return difficultyStage;
  }

  /**
   * Builds up winning popup stage and returns it.
   *
   * @return stage the winning popup stage
   */
  private Stage getWinningStage() {
    WinningPopupView winningPopupView = new WinningPopupView();
    Scene winningScene = new Scene(winningPopupView, 600, 400);
    winningStage = new Stage();
    winningStage.initStyle(StageStyle.UNDECORATED);
    winningStage.setAlwaysOnTop(true);
    winningStage.setScene(winningScene);
    winningStage.setOnCloseRequest((event) -> switchTo(WinningPopupController.CLOSE_POPUP));
    winningStage.setResizable(false);
    return winningStage;
  }

  /**
   * Switches between screens and popups.
   *
   * @param toScreen the screen or popup that should be switched to
   * @throws UnknownTransitionException if the screen or popup is unknown
   */
  public void switchTo(String toScreen) throws UnknownTransitionException {
    logger.info("Switch to " + toScreen);

    switch (toScreen) {
      case StartScreenController.SCREEN -> {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(getStartScreen());
      }
      case GameScreenController.SCREEN -> {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(getGameScreen());
      }
      case DifficultyPopupController.OPEN_POPUP -> {
        disableGameScreen();
        disableStartScreen();
        openPopup(getDifficultyStage());
      }
      case WinningPopupController.OPEN_POPUP -> openPopup(getWinningStage());
      case DifficultyPopupController.CLOSE_POPUP -> closePopup(getDifficultyStage());
      case WinningPopupController.CLOSE_POPUP -> closePopup(winningStage);
      default -> throw new UnknownTransitionException("unknown screen: " + toScreen);
    }
  }

  /**
   * Saves changed difficulty in MemoryAttributeStore and calls the change on the start screen.
   *
   * @param difficulty the chosen difficulty
   */
  public void changeDifficulty(String difficulty) {
    MemoryAttributeStore.getReference().setAttribute(MemoryServiceController
            .DIFFICULTY, getDifficultyFromString(difficulty));
    startScreenController.changeDifficulty(difficulty);
  }

  /**
   * Opens a popup.
   *
   * @param stage the popup stage
   */
  private void openPopup(Stage stage) {
    stage.show();
  }

  /**
   * Closes a popup.
   *
   * @param stage the popup stage
   */
  private void closePopup(Stage stage) {
    stage.close();
  }

  /**
   * Closes the game screen.
   */
  public void closeGameScreen() {
    if (gameScreenController != null) {
      gameScreenController.closeGame();
    }
  }

  /**
   * Disables the start screen.
   */
  public void disableStartScreen() {
    startScreenContent.setDisable(true);
  }

  /**
   * Enables the start screen.
   */
  public void enableStartScreen() {
    startScreenContent.setDisable(false);
  }

  /**
   * Disables the game screen.
   */
  public void disableGameScreen() {
    if (gameScreenController == null) {
      return;
    }
    gameScreenController.disableGameScreen();
  }

  /**
   * Enables the game screen.
   */
  public void enableGameScreen() {
    if (gameScreenController == null) {
      return;
    }
    gameScreenController.enableGameScreen();
  }

  /**
   * Sets a game screen message for the player to see what happens during the matching of cards.
   *
   * @param message the game screen message
   */
  public void setGameScreenMessage(String message) {
    gameScreenController.setSystemMessage(message);
  }

  /**
   * Changes to winning popup if game is won.
   */
  public void gameWon() {
    gameScreenController.gameWon();
    checkHighScore();
    switchTo(WinningPopupController.OPEN_POPUP);
  }

  /**
   * Changes the highScore on the start screen.
   */
  public void checkHighScore() {
    if (Integer.parseInt(getCurrentTime()) <= highScore) {
      return;
    }
    startScreenController.setHighScore(getCurrentTime() + " sec");
  }

  /**
   * Disables grid of the cards.
   */
  public void disableGameGrid() {
    gameScreenController.disableGrid();
  }

  /**
   * Enables the grid of the cards.
   */
  public void enableGameGrid() {
    gameScreenController.activateGrid();
  }

  /**
   * Returns the difficulty from a String.
   *
   * @param difficulty difficulty as String
   * @return Difficulty that matches the String difficulty
   */
  public Difficulty getDifficultyFromString(String difficulty) {
    return switch (difficulty.trim().toLowerCase()) {
      case "easy" -> Difficulty.EASY;
      case "medium" -> Difficulty.MEDIUM;
      case "hard" -> Difficulty.HARD;
      case "veryhard" -> Difficulty.VERYHARD;
      default -> (Difficulty) MemoryAttributeStore.getReference()
              .getAttribute(MemoryServiceController.DIFFICULTY);
    };
  }

  /**
   * Return the current time of the game as String.
   *
   * @return String current time
   */
  public String getCurrentTime() {
    return gameScreenController.getCurrentTime();
  }

}
