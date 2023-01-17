package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.javafx.controllers.template.ScreenController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class RgcScreenController {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcScreenController.class);


  private final AnchorPane anchorPane;


  public RgcScreenController(AnchorPane anchorPane) {
    this.anchorPane = anchorPane;
  }


  public void switchTo(String filename) throws IOException {
    logger.info("Switch to: " + filename);

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reactiongame/"
        + filename + ".fxml"));
    Node n = loader.load();

    anchorPane.getChildren().clear();
    anchorPane.getChildren().add(n);
  }

}
