package de.hhn.it.devtools.javafx.controllers.connectfour;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class SceneChanger {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SceneChanger.class);

  public static void changeScene(Pane wrapperPane, String screenPath) {
    logger.info("Try changing to " + screenPath + "...");
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(SceneChanger.class.getResource(screenPath));
      wrapperPane.getChildren().clear();
      wrapperPane.getChildren().add(fxmlLoader.load());
    } catch (IOException e) {
      logger.error("Couldn't change to " + screenPath);
      throw new RuntimeException(e);
    }
  }
}
