package de.hhn.it.devtools.javafx.battleship;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Concede {

  VBox vbox = new VBox();
  Scene sceneGameMenu = new Scene(vbox);

  //
  Stage stage = new Stage();

  public Concede() {
    Label a = new Label("You lost");
    stage.setScene(sceneGameMenu);
    stage.setHeight(400);
    stage.setWidth(250);

    vbox.getChildren().add(a);
    stage.show();

  }
}
