package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.javafx.controllers.template.UnknownTransitionException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DuckHuntScreenController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuckHuntScreenController.class);

  public static final String SCREEN_CONTROLLER = "screen.controller";

  AnchorPane anchorPane;
  private DuckHuntSettingsController duckHuntSettingsController;
  private DuckHuntMenuController duckHuntMenuController;
  private DuckHuntGameController duckHuntGameController;

  public DuckHuntScreenController(final AnchorPane anchorPane) {
    this.anchorPane = anchorPane;
  }

  private Node getMenuScreen() {
    Node menuScreenContent = null;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/duckhunt/DuckHuntMenuScreen.fxml"));
    try {
      menuScreenContent = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    duckHuntMenuController = loader.getController();
    return menuScreenContent;
  }

  private Node getSettingsScreen() {
    Node settingsScreenContent = null;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/duckhunt/DuckHuntSettingsScreen.fxml"));
    try {
      settingsScreenContent = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    duckHuntSettingsController = loader.getController();
    return settingsScreenContent;
  }

  public void switchTo(String fromScreen, String toScreen) throws UnknownTransitionException {
    logger.info("Switching from " + fromScreen + " to " + toScreen);
    switch (toScreen) {
      case DuckHuntSettingsController.SCREEN:
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(getSettingsScreen());
        break;
      case DuckHuntMenuController.SCREEN:
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(getMenuScreen());
        break;
      case DuckHuntGameController.SCREEN:
        openGameView();
        break;

      default: throw new UnknownTransitionException("unknown screen: " + toScreen);
    }
  }

  private void openGameView() {
  }
}
