package de.hhn.it.devtools.javafx.controllers.memory;


import de.hhn.it.devtools.javafx.controllers.template.PurProgrammingScreen;
import de.hhn.it.devtools.javafx.controllers.template.UnknownTransitionException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MemoryScreenController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MemoryScreenController.class);

  AnchorPane anchorPane;

  private StartScreen startScreen;
  private GameScreen gameScreen;
  private Node startScreenContent;
  private Node gameScreenContent;

  public MemoryScreenController(final AnchorPane templateAnchorPane) {
    this.anchorPane = templateAnchorPane;
  }


  private Node getStartScreen() {
    if (startScreenContent == null) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MemoryService.fxml"));
      try {
        startScreenContent = loader.load();
      } catch (IOException e) {
        e.printStackTrace();
      }
      startScreen = loader.getController();

    }
    return startScreenContent;
  }

  private Node getGameScreen() {
    if (gameScreenContent == null) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/memory/MemoryGameField.fxml"));
      try {
        gameScreenContent = loader.load();
      } catch (IOException e) {
        e.printStackTrace();
      }
      gameScreen = loader.getController();

    }
    return gameScreenContent;
  }


  public void switchTo(String fromScreen, String toScreen) throws UnknownTransitionException {
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
      default: throw new UnknownTransitionException("unknown screen: " + toScreen);
    }
  }
}
