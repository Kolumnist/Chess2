package de.hhn.it.devtools.javafx.controllers.game2048;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Game2048ScreenController {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(Game2048ScreenController.class);
    private final AnchorPane anchorPane;

    public Game2048ScreenController(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public void switchTo(String fileName) throws IOException {
        logger.info("SwitchTo: fileName = {}" + fileName);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/game2048/" + fileName + ".fxml"));
        Node n = loader.load();
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(n);
    }
}