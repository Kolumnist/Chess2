package de.hhn.it.devtools.javafx.battleship;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ShipsLeft {

    Stage stage = new Stage();
    BorderPane borderPane=new BorderPane();

    ShipsLeft(){

        stage.setWidth(800);
        stage.setHeight(600);
        stage.setResizable(false);



        Label title = new Label("Ship left to place");

        title.setStyle("-fx-font-size: 30; -fx-background-color: white; -fx-text-fill: #5BC0BE; -fx-background-radius: 10");
        title.setPadding(new Insets(30,30,30,30));


        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setMargin(title,new Insets(30,0,30,0));
        borderPane.setTop(title);
        borderPane.setStyle("-fx-background-color: #5BC0BE");


        stage.setScene(new Scene(borderPane));
        stage.setTitle("Ships left to place");
        stage.show();

    }
}
