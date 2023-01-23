package de.hhn.it.devtools.javafx.battleship;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Reminder {

  Stage stage = new Stage();
  StackPane stackpane = new StackPane();
  Scene scene = new Scene(stackpane);
  Label lbl = new Label("Help -> shipsLeft -> select ship");

  Reminder() {
    stage.setScene(scene);
    stackpane.getChildren().add(lbl);
    stage.setHeight(400);
    stage.setWidth(400);
    stage.setAlwaysOnTop(true);
    stackpane.setStyle("-fx-background-color: indianred");
    stage.show();
  }
}
