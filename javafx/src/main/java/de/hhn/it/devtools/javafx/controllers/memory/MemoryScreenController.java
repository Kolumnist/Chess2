package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import de.hhn.it.devtools.javafx.controllers.template.UnknownTransitionException;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MemoryScreenController {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MemoryScreenController.class);

  AnchorPane anchorPane;

  private StartScreen startScreen;
  private Node startScreenContent;
  private GameScreen gameScreen;
  private DifficultyPopup difficultyPopup;
  private Stage difficultyStage;
  private Stage winningStage;
  private Scene difficultyScene;

  public MemoryScreenController(final AnchorPane anchorPane) {
    this.anchorPane = anchorPane;
  }

  /**
   * Returns the start screen content as node and sets up the controller
   * @return Node the start screen
   */
  private Node getStartScreen() {
    if (startScreen == null) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/memory/StartScreen.fxml"));
      try {
        startScreenContent = loader.load();
        startScreen = loader.getController();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return startScreenContent;
  }

  /**
   * Returns the game screen content as node and set ups the controller
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
    gameScreen = loader.getController();
    return gameScreenContent;
  }

  /**
   * Builds up difficulty popup stage and returns it
   * @return stage the difficulty popup stage
   */
  private Stage getDifficultyStage() {
    if (difficultyPopup == null) {
      difficultyPopup = new DifficultyPopup(this);
    }
    if (difficultyScene == null) {
      difficultyScene = new Scene(difficultyPopup, 600, 400);
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
   * Builds up winning popup stage and returns it
   * @return stage the winning popup stage
   */
  private Stage getWinningStage() {
    WinningPopup winningPopup = new WinningPopup(this);
    Scene winningScene = new Scene(winningPopup, 600, 400);
    winningStage = new Stage();
    winningStage.initStyle(StageStyle.UNDECORATED);
    winningStage.setAlwaysOnTop(true);
    winningStage.setScene(winningScene);
    winningStage.setOnCloseRequest((event) -> switchTo(WinningPopup.CLOSE_POPUP));
    winningStage.setResizable(false);
    return winningStage;
  }

  /**
   * Switches between screens and popups
   * @param toScreen the screen or popup that should be switched to
   * @throws UnknownTransitionException if the screen or popup is unknown
   */
  public void switchTo(String toScreen) throws UnknownTransitionException {
    logger.info("Switch to " + toScreen);

    switch (toScreen) {
      case StartScreen.SCREEN -> {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(getStartScreen());
      }
      case GameScreen.SCREEN -> {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(getGameScreen());
      }
      case DifficultyPopup.OPEN_POPUP -> {
        disableGameScreen();
        disableStartScreen();
        openPopup(getDifficultyStage());
      }
      case WinningPopup.OPEN_POPUP -> openPopup(getWinningStage());
      case DifficultyPopup.CLOSE_POPUP -> closePopup(getDifficultyStage());
      case WinningPopup.CLOSE_POPUP -> closePopup(winningStage);
      default -> throw new UnknownTransitionException("unknown screen: " + toScreen);
    }
  }

  /**
   * Saves changed difficulty in MemoryAttributeStore and calls the change on the start screen
   * @param difficulty the chosen difficulty
   */
  public void changeDifficulty(String difficulty) {
    MemoryAttributeStore.getReference().setAttribute(MemoryServiceController
            .DIFFICULTY, getDifficultyFromString(difficulty));
    startScreen.changeDifficulty(difficulty);
  }

  /**
   * Opens a popup
   * @param stage the popup stage
   */
  private void openPopup(Stage stage) {
    stage.show();
  }

  /**
   * Closes a popup
   * @param stage the popup stage
   */
  private void closePopup(Stage stage) {
    stage.close();
  }

  /**
   * Closes the game screen
   */
  public void closeGameScreen() {
    if (gameScreen != null) {
      gameScreen.closeGame();
    }
  }

  /**
   * Disables the start screen
   */
  public void disableStartScreen() {
    startScreenContent.setDisable(true);
  }

  /**
   * Enables the start screen
   */
  public void enableStartScreen() {
    startScreenContent.setDisable(false);
  }

  /**
   * Disables the game screen
   */
  public void disableGameScreen() {
    if (gameScreen == null) {
      return;
    }
    gameScreen.disableGameScreen();
  }

  /**
   * Enables the game screen
   */
  public void enableGameScreen() {
    if (gameScreen == null) {
      return;
    }
    gameScreen.enableGameScreen();
  }

  /**
   * Sets a game screen message for the player to see what happens during the matching of cards
   * @param message the game screen message
   */
  public void setGameScreenMessage(String message) {
    gameScreen.setSystemMessage(message);
  }

  /**
   * Changes to winning popup if game is won
   */
  public void gameWon() {
    gameScreen.gameWon();
    switchTo(WinningPopup.OPEN_POPUP);
  }

  /**
   * Disables grid of the cards
   */
  public void disableGameGrid() {
    gameScreen.disableGrid();
  }

  /**
   * Enables the grid of the cards
   */
  public void enableGameGrid() {
    gameScreen.activateGrid();
  }

  /**
   * Returns the difficulty from a String
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
   * Return the current time of the game as String
   * @return String current time
   */
  public String getCurrentTime() {
    return gameScreen.getCurrentTime();
  }

}
