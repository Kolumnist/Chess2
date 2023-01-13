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

  private void openPopup(Stage stage) {
    stage.show();
  }

  public void closePopup(Stage stage) {
    stage.close();
  }

  private Stage getDifficultyStage() {
    if (difficultyPopup == null) {
      difficultyPopup = new DifficultyPopup(this);
    }
    if (difficultyScene == null) {
      difficultyScene = new Scene(difficultyPopup, 600, 400);
    }
    if (difficultyStage == null) {
      difficultyStage = new Stage();
      difficultyStage.setAlwaysOnTop(true);
      difficultyStage.setScene(difficultyScene);
    }
    return difficultyStage;
  }

  private Stage getWinningStage() {
    WinningPopup winningPopup = new WinningPopup(this);
    Scene winningScene = new Scene(winningPopup, 600, 400);
    winningStage = new Stage();
    winningStage.setAlwaysOnTop(true);
    winningStage.setScene(winningScene);
    return winningStage;
  }

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

  public void changeDifficulty(String difficulty) {
    MemoryAttributeStore.getReference().setAttribute(MemoryServiceController
            .DIFFICULTY, getDifficultyFromString(difficulty));
    startScreen.changeDifficulty(difficulty);
  }

  public void closeGameScreen() {
    if (gameScreen != null) {
      gameScreen.closeGame();
    }
  }

  public void disableStartScreen() {
    startScreenContent.setDisable(true);
  }

  public void enableStartScreen() {
    startScreenContent.setDisable(false);
  }

  public void disableGameScreen() {
    if (gameScreen == null) {
      return;
    }
    gameScreen.disableGameScreen();
  }

  public void enableGameScreen() {
    if (gameScreen == null) {
      return;
    }
    gameScreen.enableGameScreen();
  }

  public void setGameScreenMessage(String message) {
    gameScreen.setSystemMessage(message);
  }

  public void gameWon() {
    gameScreen.gameWon();
    switchTo(WinningPopup.OPEN_POPUP);
  }

  public String getCurrentTime() {
    return gameScreen.getCurrentTime();
  }

  public void disableGameGrid() {
    gameScreen.disableGrid();
  }

  public void enableGameGrid() {
    gameScreen.activateGrid();
  }
}
