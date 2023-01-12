package de.hhn.it.devtools.javafx.controllers.memory;


import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import de.hhn.it.devtools.javafx.controllers.template.UnknownTransitionException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MemoryScreenController {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MemoryScreenController.class);

  AnchorPane anchorPane;

  private StartScreen startScreen;
  private Node startScreenContent;
  private GameScreen gameScreen;
  private Node gameScreenContent;
  private DifficultyPopup difficultyPopup;
  private WinningPopup winningPopup;
  private Stage difficultyStage;
  private Stage winningStage;
  private Scene difficultyScene;
  private Scene winningScene;

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
    gameScreenContent = null;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/memory/MemoryGameField.fxml"));
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
      difficultyStage.setScene(difficultyScene);
    }
    return difficultyStage;
  }

  private Stage getWinningStage() {
    if (winningPopup == null) {
      winningPopup = new WinningPopup(this);
    }
    if (winningScene == null) {
      winningScene = new Scene(winningPopup, 600, 400);
    }
    if (winningStage == null) {
      winningStage = new Stage();
      winningStage.setScene(winningScene);
    }
    return winningStage;
  }

  public void switchTo(String toScreen) throws UnknownTransitionException {
    logger.info("Switch to " + toScreen);

    switch (toScreen) {
      case StartScreen.SCREEN:
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(getStartScreen());
        break;
      case GameScreen.SCREEN:
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(getGameScreen());
        break;
      case DifficultyPopup.OPEN_POPUP:
        openPopup(getDifficultyStage());
        break;
      case WinningPopup.OPEN_POPUP:
        openPopup(getWinningStage());
        break;
      case DifficultyPopup.CLOSE_POPUP:
        closePopup(getDifficultyStage());
        break;
      case WinningPopup.CLOSE_POPUP:
        closePopup(getWinningStage());
        break;
      default:
        throw new UnknownTransitionException("unknown screen: " + toScreen);
    }
  }


  public Difficulty getDifficultyFromString(String difficulty) {
    switch (difficulty.trim().toLowerCase()) {
      case "easy":
        return Difficulty.EASY;
      case "medium":
        return Difficulty.MEDIUM;
      case "hard":
        return Difficulty.HARD;
      case "veryhard":
        return Difficulty.VERYHARD;
      default:
        return (Difficulty) MemoryAttributeStore.getReference().getAttribute(MemoryServiceController.DIFFICULTY);
    }
  }

  public void changeDifficulty(String difficulty) {
    MemoryAttributeStore.getReference().setAttribute(MemoryServiceController.DIFFICULTY, getDifficultyFromString(difficulty));
    startScreen.changeDifficulty(difficulty);
    if (gameScreen != null) {
      gameScreen.changeDifficulty(getDifficultyFromString(difficulty));
    }
  }
}
