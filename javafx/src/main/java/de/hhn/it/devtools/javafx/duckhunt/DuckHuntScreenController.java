package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.javafx.controllers.template.FxmlCreatesControllerScreen;
import de.hhn.it.devtools.javafx.controllers.template.FxmlPlusProgrammingScreen;
import de.hhn.it.devtools.javafx.controllers.template.PurProgrammingScreen;
import de.hhn.it.devtools.javafx.controllers.template.UnknownTransitionException;
import javafx.scene.layout.AnchorPane;

public class DuckHuntScreenController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuckHuntScreenController.class);

  AnchorPane anchorPane;
  private DuckHuntSettingsScreen duckHuntSettingsScreen;

  public DuckHuntScreenController(final AnchorPane templateAnchorPane) {
    this.anchorPane = templateAnchorPane;
  }

  /*public void switchTo(String fromScreen, String toScreen) throws UnknownTransitionException {
    logger.info("Switching from " + fromScreen + " to " + toScreen);
    switch (toScreen) {
      case DuckHuntSettingsScreen.SCREEN:
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add();
        break;
      case DuckHuntServiceController.SCREEN:
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add();
        break;

      default: throw new UnknownTransitionException("unknown screen: " + toScreen);
    }
  }*/
}
