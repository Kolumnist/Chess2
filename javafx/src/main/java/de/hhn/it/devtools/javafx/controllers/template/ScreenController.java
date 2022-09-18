package de.hhn.it.devtools.javafx.controllers.template;

import de.hhn.it.devtools.javafx.controllers.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class ScreenController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ScreenController.class);

  AnchorPane anchorPane;
  private PurProgrammingScreen purProgrammingScreen;
  private FxmlPlusProgrammingScreen fxmlPlusProgrammingScreen;
  private FxmlCreatesControllerScreen fxmlCreatesControllerScreen;
  private Node thirdScreenContent;

  public ScreenController(final AnchorPane templateAnchorPane) {
    this.anchorPane = templateAnchorPane;
  }

  private PurProgrammingScreen getFirstScreen() {
    if (purProgrammingScreen == null) {
      purProgrammingScreen = new PurProgrammingScreen(this);
    }
    return purProgrammingScreen;
  }

  private FxmlPlusProgrammingScreen getSecondScreen() {
    if (fxmlPlusProgrammingScreen == null) {
      fxmlPlusProgrammingScreen = new FxmlPlusProgrammingScreen(this);
    }
    return fxmlPlusProgrammingScreen;
  }

  private Node getThirdScreen() {
    if (thirdScreenContent == null) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/template/ThirdScreen.fxml"));
      try {
        thirdScreenContent = loader.load();
      } catch (IOException e) {
        e.printStackTrace();
      }
      fxmlCreatesControllerScreen = loader.getController();

    }
    return thirdScreenContent;
  }

  public void switchTo(String fromScreen, String toScreen) throws UnknownTransitionException {
    logger.info("Switching from " + fromScreen + " to " + toScreen);
    switch (toScreen) {
      case PurProgrammingScreen.SCREEN:
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(getFirstScreen());
        break;
      case FxmlPlusProgrammingScreen.SCREEN:
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(getSecondScreen());
        break;
      case FxmlCreatesControllerScreen.SCREEN:
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(getThirdScreen());
        break;

      default: throw new UnknownTransitionException("unknown screen: " + toScreen);
    }
  }
}
