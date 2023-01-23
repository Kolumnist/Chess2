package de.hhn.it.devtools.javafx.battleship;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Rules {

  Stage stage = new Stage();
  GridPane gridPane = new GridPane();
  Scene sceneRules = new Scene(gridPane);

  Rules() {

    // Label displays the rules of Battleship
    Label txt = new Label("""
        You have to guess and hit the enemies ships.\s

        If you sink all of the computers ships, you win.\s

        If the computer sink all of your ships, you lose.\s

        To see what kind of ships you can still set, press help -> ships left""");

    txt.setWrapText(true);
    txt.setStyle(
        "-fx-text-fill: white; -fx-font-size: 20; -fx-font-family: Arial, sans-serif; -fx-font-weight: bold");

    gridPane.setStyle("-fx-background-color: #5BC0BE");
    gridPane.setPadding(new Insets(20, 10, 10, 10));

    gridPane.getChildren().add(txt);
    stage.setWidth(500);
    stage.setHeight(800);
    stage.setResizable(false);
    stage.setScene(sceneRules);
    stage.setTitle("Rules");
    stage.show();
  }
}
